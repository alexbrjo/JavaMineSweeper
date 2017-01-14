package co.alexjo.javaminesweeper.field;

/**
 * The Minefield
 * @author Alex Johnson
 */
public class Minefield {
    
    /** The max width to set the Minefield to */
    public final int MAX_WIDTH = 20;
    
    /** The max height to set the Minefield to */
    public final int MAX_HEIGHT = 20;
    
    /** The number of mines on the field */
    private int numberOfMines;
    
    /** The width of the Minefield */
    private int width;
    
    /** The height of the Minefield */
    private int height;
    
    /** The array of squares in the field */
    private Square[][] board;
    
    /** The array of mines in the field */
    private Square[] mines;
    
    /** If a mine has been detonated */
    private boolean exploded;
    
    /**
     * Creates a minefield for a width, height and number of mines.
     * @param width The width of the Minefield
     * @param height The height of the Minefield
     * @param numberOfMines The number of mines to generate on the field
     */
    public Minefield (int width, int height, int numberOfMines) {
        setSize(width, height);
        generateField(numberOfMines);
        exploded = false;
    }
    
    /**
     * Gets the mines form the field.
     * @return all the mines from the field
     */
    public Square[] getMines () {
        return mines;
    }
    
    /**
     * Gets a specific square for a coordinate position.
     * @param x The x coordinate of the square
     * @param y The y coordinate of the square
     * @return the square for the coordinates given
     * @throws IllegalArgumentException if the x or y coordinate in out of 
     *      bounds of the field.
     */
    public Square getSquare (int x, int y) {
        if (x < 0 || x >= getWidth()) {
            throw new IllegalArgumentException();
        }
        
        if (y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException();
        }
        
        return board[x][y];
    }
    
    /**
     * Safely gets a specific square for a coordinate position.
     * @param x The x coordinate of the square
     * @param y The y coordinate of the square
     * @return the square for the coordinates given
     */
    private Square safeGetSquare (int x, int y) {
        try {
            return getSquare(x, y);
        } catch (IllegalArgumentException e) {
            return new Square(width, height, false);
        }
    }
    
    /**
     * Manually gets and updates the state of the field. -1 for exploded, 
     * 0 for in-play and 1 for cleared.
     * @return the state of the field.
     */
    public int getFieldState () {
        boolean clear = true;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].isCleared()) {
                    if (board[i][j].isMine()) {
                        exploded = true;
                        return -1;
                    }
                } else {
                    clear = false;
                }
            }
        }
        exploded = false;
        return clear ? 1 : 0;
    }
    
    /**
     * Clicks a specific square. 
     * @param x The x of the square to click
     * @param y The y of the square to click
     * @throws IllegalArgumentException if the x or y is outside the bounds
     */
    public void click (int x, int y) {
        
        if (x < 0 || x > width - 1) {
            throw new IllegalArgumentException("Out of bounds");
        }
        
        if (y < 0 || y > height - 1) {
            throw new IllegalArgumentException("Out of bounds");
        }
        
        int res = board[x][y].click();
        if (res == -1) {
            exploded = true;
        } else if (res == 1) {
            if (board[x][y].getAdjacentMines() == 0) {
               clearAdjancentSquares(x, y);
            }
        }
    }
    
    private void clearAdjancentSquares (int i, int j) {
        
        if (i >= width || j >= height) {
            return;
        }
        
        Square s = safeGetSquare(i, j);
        if (!s.isMine() && safeGetSquare(i, j).getAdjacentMines() > 0) {
            s.click();
            return;
        }
        
        if (!safeGetSquare(i, j - 1).isMine() && j > 0) {
            click(i, j - 1);
        }
        if (!safeGetSquare(i - 1, j).isMine() && i > 0) {
            click(i - 1, j);
        }
        if (!safeGetSquare(i + 1, j).isMine() && i < width - 1) {
            click(i + 1 ,j    );
        }
        if (!safeGetSquare(i, j + 1).isMine() && j < height - 1) {
            click(i, j + 1);
        }
    }
    
    /**
     * Gets the number of mines.
     * @return the number of mines on the field
     */
    public int getNumberOfMines () {
        return numberOfMines;
    }
    
    /**
     * Generates the squares for the minefield. Must be a positive integer of
     * number of mines.
     * @param numberOfMines The number of mines to generate
     * @throws IllegalArgumentException if number of mines is a negative or 
     *      zero integer. Or is greater than the total number of squares.
     */
    private void generateField (int numberOfMines) {
        // determines the area of the field
        int area = width * height;
        
        // can't have a negative number of mines
        if (numberOfMines < 0) {
            throw new IllegalArgumentException("Mines must be a non-negative integer");
        }
        
        // can't have more mines than spaces
        if (area < numberOfMines) {
            throw new IllegalArgumentException("More mines than spaces");
        }
        
        this.numberOfMines = numberOfMines;
       
        
        // given chance of mine in square
        double chanceOfMine = (double)numberOfMines / area;
        int minesPlaced = 0;
        board = new Square[width][height];
        mines = new Square[numberOfMines];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Square s = null;
                if (Math.random() < chanceOfMine && numberOfMines > minesPlaced) {
                    s = new Square(i, j, true);
                    mines[minesPlaced] = s;
                    minesPlaced++;
                } else {
                    s = new Square(i, j, false);
                }
                board[i][j] = s;
            } // for j
        } // for i
        
        //sets adjacent mines
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int adjacentMines = 0;
                if (safeGetSquare(i - 1, j - 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i    , j - 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i + 1, j - 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i - 1, j    ).isMine()) {adjacentMines++;}
                if (safeGetSquare(i    , j    ).isMine()) {adjacentMines++;}
                if (safeGetSquare(i + 1, j    ).isMine()) {adjacentMines++;}
                if (safeGetSquare(i - 1, j + 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i    , j + 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i + 1, j + 1).isMine()) {adjacentMines++;}
                getSquare(i, j).setAdjacentMines(adjacentMines);
            }
        }
    }
    
    /**
     * Gets the width of the Minefield.
     * @return width The width of the Minefield.
     */
    public int getWidth () {
        return width;
    }
    
    /**
     * Gets the height of the Minefield.
     * @return height The height of the Minefield.
     */
    public int getHeight () {
        return height;
    }
    
    /**
     * Sets the size of the field. Should only be called once by the 
     * constructor.
     * @param width The width to set the width to
     * @param height The height to set the height to
     * @throws IllegalArgumentException if width or height is a non-negative
     *      integer
     */
    private void setSize (int width, int height) {
        
        if (width < 1) {
            throw new IllegalArgumentException("Width must be a positive integer");
        }
        
        if (height < 1) {
            throw new IllegalArgumentException("Height must be a positive integer");
        }
        
        this.width = width;
        this.height = height;
    }
}
