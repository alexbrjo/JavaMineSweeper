package co.alexjo.javaminesweeper.field;

/**
 * Square represents a single mine field tile. It stores the field position and
 * whether or not in contains a mine.
 * @author Alex Johnson
 */
public class Square {
    
    /** The x position of the square in the mine field, relative to the top */
    private int x;
    
    /** The y position of the square in the mine field, relative to the left */
    private int y;
    
    /** If the square contains a mine */
    private boolean mine;
    
    /** the number of mines that are adjacent to the square */
    private int adjacentMines;
    
    /** Whether the square has been cleared */
    private boolean cleared = false;
    
    /** Whether the square has been flagged */
    private boolean flagged = false;
    
    /**
     * Creates a new Square for a x and y position and whether or not it 
     * contains an incredibly dangerous mine.
     * @param x The x position of the mine, must be a positive number
     * @param y The y position of the mine, must be a positive number
     * @param mine Whether or not the square contains a mine
     */
    public Square (int x, int y, boolean mine) {
        setX(x);
        setY(y);
        setMine(mine);
    }
    
    /**
     * Clicks the square. Returns whether the square exploded -1, nothing 0, 
     * or cleared 1.
     * @return whether the square exploded -1, nothing 0, cleared or 1
     */
    public int click () {
        return click(false);
    }
    
    /**
     * Clicks the square. Returns whether the square exploded -1, nothing 0, 
     * or cleared 1.
     * @param right Whether the mouse click was the right mouse button
     * @return whether the square exploded -1, nothing 0, cleared or 1
     */
    public int click (boolean right) {
        if (cleared) {
            return 0;
        } 
        
        if (right) {
            flagged = !flagged;
            return 0;
        } else if (!flagged) {
            cleared = true;
            return mine ? -1 : 1;
        }
        
        return 0;
    }
    
    /**
     * Gets whether the square is cleared.
     * @return whether the square is cleared
     */
    public boolean isCleared () {
        return cleared;
    }

    /**
     * Gets the x coordinate of the square. Relative to the left of the field.
     * @return the x coordinate of the square on the field
     */
    public int getX() {
        return x;
    }

    /**
     * The x position of the square on the minefield. Since the square does not
     * know the size of the minefield it cannot check if its x position is
     * greater than the x bounds.
     * @param x the x to set
     * @throws IllegalArgumentException if the x is less than 0
     */
    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException();
        }
        this.x = x;
    }

    /**
     * Gets the y coordinate of the square. Relative to the top of the field.
     * @return the y coordinate of the square on the field.
     */
    public int getY() {
        return y;
    }

    /**
     * The y position of the square on the minefield. Since the square does not
     * know the size of the minefield it cannot check its y position is
     * greater than the x bounds.
     * @param y the y to set
     * @throws IllegalArgumentException if the y is less than 0
     */
    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException();
        }
        this.y = y;
    }

    /**
     * Whether the square is a mine. Either has a mine or not.
     * @return whether the square has a mine
     */
    public boolean isMine() {
        return mine;
    }

    /**
     * Sets if the square contains the mine. Either has a mine or not.
     * @param isMine the isMine to set
     */
    public void setMine(boolean isMine) {
        this.mine = isMine;
    }

    /**
     * Returns the number of adjacent mines. Returns -1 if the square 
     * contains a mine.
     * @return the AdjacentMines
     */
    public int getAdjacentMines() {
        return adjacentMines;
    }
    
    /**
     * Sets the number of adjacent mines. Must be a number 0 to 8.
     * @param adjacentMines the adjacentMines to set
     * @throws IllegalArgumentException if adjacentMines is greater than 8 or
     *      less than 0 
     */
    public void setAdjacentMines(int adjacentMines) {
        if (adjacentMines < 0 || adjacentMines > 8) {
            throw new IllegalArgumentException();
        }
        this.adjacentMines = adjacentMines;
    }

    /**
     * If the square is flagged.
     * @return if the square is flagged
     */
    public boolean isFlagged() {
        return flagged;
    }
}
