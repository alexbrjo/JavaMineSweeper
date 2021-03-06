package co.alexjo.javaminesweeper.field;

/**
 * The Minefield
 * @author Alex Johnson
 */
public class Minefield {
    /** The max width to set the Minefield to */
    public final int MAX_WIDTH = 40;
    /** The max height to set the Minefield to */
    public final int MAX_HEIGHT = 40;
    /** The width of the Minefield */
    private int width;
    /** The height of the Minefield */
    private int height;
    /** The array of squares in the field */
    private Square[][] board;
    /** The array of mines in the field */
    private Square[] mines;
    /** The creation of the minefield */
    private long startTime;
    /** The end time of win or lose  */
    private int finalTime;
    /** If the minefield has exploded */
    private boolean explode;
    /** If mouse is down on face button */
    private boolean faceButton;
    /** If the mouse is down on the mine field*/
    private boolean mouseDown;
    
    /**
     * Creates a minefield for a width, height and number of mines.
     * @param width The width of the Minefield
     * @param height The height of the Minefield
     * @param numberOfMines The number of mines to generate on the field
     */
    public Minefield (int width, int height, int numberOfMines) {
        setSize(width, height);
        generateField(numberOfMines);
        startTime = System.currentTimeMillis();
        finalTime = 0;
        explode = false;
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
        
        setAdjacentMines();
        
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Sets adjacent mines for every square in the minefield
     */
    private void setAdjacentMines () {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int adjacentMines = 0;
                if (safeGetSquare(i - 1, j - 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i    , j - 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i + 1, j - 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i - 1, j    ).isMine()) {adjacentMines++;}
                if (safeGetSquare(i + 1, j    ).isMine()) {adjacentMines++;}
                if (safeGetSquare(i - 1, j + 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i    , j + 1).isMine()) {adjacentMines++;}
                if (safeGetSquare(i + 1, j + 1).isMine()) {adjacentMines++;}
                getSquare(i, j).setAdjacentMines(adjacentMines);
            }
        }
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
                        return -1;
                    }
                } else {
                    clear = false;
                }
            }
        }
        return clear ? 1 : 0;
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
     * Clears a specific square. 
     * @param x The x of the square to click
     * @param y The y of the square to click
     * @throws IllegalArgumentException if the x or y is outside the bounds
     */
    private void clearSquare (int x, int y) {
        int res = board[x][y].click();
        if (res == -1) { // if exploded clear board and end game
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    board[i][j].click();
                }
            }
            finalTime = getTime();
            board[x][y].explode(); // sets the square as the origin of the explosion
            explode = true;
        } else if (res == 1) {  // if cleared clear adjacent
            if (board[x][y].getAdjacentMines() == 0) {
               clearAdjancentSquares(x, y);
            }

            // If the board is clear end the game and flag remaining mines
            if (isClear()) { 
                finalTime = getTime();
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        board[i][j].addFlag();
                    }
                }
            }
        }
    }
    
    /**
     * Clicks the minefield.
     * @param x The x of the square to click
     * @param y The y of the square to click
     * @param b The button pressed
     * @param pressed If the mouse was pressed
     * @param released If the mouse was released
     * @throws IllegalArgumentException if the x or y is outside the bounds
     */
    public void click (int x, int y, int b, boolean pressed, boolean released) {
        
        if (x < 0 || x > width - 1) {
            throw new IllegalArgumentException("Out of bounds");
        }
        
        if (y < 0 || y > height - 1) {
            throw new IllegalArgumentException("Out of bounds");
        }
        
        mouseDown = b != 0;
        
        if (finalTime == 0) { // if the game isn't over
            if (b == 1 && released) { // if the button is 1 and it is released
                clearSquare(x, y);
            } else if (b == 3 && pressed) { // if it's a right click toggle flag
                if (!board[x][y].isCleared()) {
                    board[x][y].toggleFlag();
                }
            } 
        } 
    }
    
    /**
     * Clears cardinally adjacent squares for an x and y coordinate. This 
     * function is recursive, if an adjacent square is also a blank square it 
     * will call clearsAdjacentSquares on it's position.
     * @param i X coordinate to clear from
     * @param j Y coordinate to clear from
     */
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
            clearSquare(i, j - 1);
        }
        if (!safeGetSquare(i - 1, j).isMine() && i > 0) {
            clearSquare(i - 1, j);
        }
        if (!safeGetSquare(i + 1, j).isMine() && i < width - 1) {
            clearSquare(i + 1 ,j    );
        }
        if (!safeGetSquare(i, j + 1).isMine() && j < height - 1) {
            clearSquare(i, j + 1);
        }
    }
    
    /**
     * If the Minefield is clear.
     * @return if the Minefield has been cleared or not
     */
    public boolean isClear () {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Square s = board[i][j];
                if (!s.isMine() && !s.isCleared()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * If the Minefield has exploded
     * @return If the Minefield has exploded or not
     */
    public boolean isExploded () {
        return this.explode;
    }
    
    /**
     * The time since creation of the battlefield. Stops when the game is won.
     * @return The time since creation of the battlefield
     */
    public int getTime () {
        if (finalTime > 0) { 
            return finalTime;
        }
        long milli_system_time = System.currentTimeMillis() - startTime;
        int milli_time = (int) ( milli_system_time % Integer.MAX_VALUE );
        return milli_time / 1000;
    }
    
    /**
     * The time since creation of the battlefield digitized into an array of
     * it's digits.
     * @return The time since creation of the battlefield
     */
    public int[] getTimeDigitized () {
        return smallIntToArray(getTime());
    }
    
    /**
     * Gets the number of mines.
     * @return the number of mines on the field
     */
    public int getNumberOfMines () {
        int flags = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j].isFlagged()) {flags++;}
            }
        }
        return mines.length - flags;
    }
    
    /**
     * Gets the number of mines formatted into an array.
     * @return an array of digits of the number of mines
     */
    public int[] getNumberOfMinesDigitized () {
        return smallIntToArray(getNumberOfMines());
    }
    
    /**
     * Gets the index of which face to draw, 0 for pressed, 1 for normal, 2
     * for square clicked, 3 for lose, 4 for win
     * @return the index of the current face
     */
    public int getFace () {
        if (faceButton) { // facebutton getting priority
            return 0;
        } else if (explode) {
            return 3; // if exploded return 3 for lose
        } else if (isClear()) {
           return 4;
        } else if (mouseDown) {
            return 2;
        } else { // If game state is normal print normal face
            return 1;
        }
    }
    
    /**
     * Separates a number less than 1000 into it's digits. If the number is 
     * greater than 1000 it returns {9, 9, 9} and less than 0 it returns 
     * {0, 0, 0}.
     * @return the array of digits
     */
    private int[] smallIntToArray (int i) {
        if (i < 0) {
            return new int[]{0,0,0};
        } else if (i > 999) {
            return new int[]{9,9,9};
        }
        
        int hun = i / 100;
        int ten = (i - hun * 100) / 10;
        int one = i - hun * 100 - ten * 10;
        
        return new int[]{hun, ten, one};
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
    
    /**
     * Sets if the face button is being pressed
     * @param down Whether the face button is down or not
     */
    public void setButton (boolean down) {
        faceButton = down;
    }
    
    /**
     * If the face button is down.
     * @return If the face button is down
     */
    public boolean button () {
        return faceButton;
    }
}
