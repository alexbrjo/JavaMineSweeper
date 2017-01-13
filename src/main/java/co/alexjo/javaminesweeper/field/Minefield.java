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
        
        if (board[x][y].click() == -1) {
            exploded = true;
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
        int chanceOfMine = numberOfMines / area;
        int minesPlaced = 0;
        board = new Square[width][height];
        mines = new Square[numberOfMines];
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Square s = null;
                if ((chanceOfMine < Math.random() || 
                        area - ((i + 1) * width + j) < numberOfMines - minesPlaced) &&
                        numberOfMines > minesPlaced) {
                    s = new Square(i, j, true);
                    mines[minesPlaced] = s;
                    minesPlaced++;
                } else {
                    s = new Square(i, j, false);
                }
                board[i][j] = s;
            } // for j
        } // for i
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
