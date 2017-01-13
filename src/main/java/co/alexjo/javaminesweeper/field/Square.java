package co.alexjo.javaminesweeper.field;

/**
 * Skeleton of the Square class, a single tile on a Minefield
 * @author Alex Johnson
 */
public class Square {
    
    private int x;
    
    private int y;
    
    private boolean mine;
    
    private int adjacentMines;
    
    private boolean cleared;
    
    public Square (int x, int y, boolean mine) {}
    
    public void click () {}
    
    public boolean isCleared () {return false;}
    
    public int getX() {return 0;}
    
    public void setX(int x) {}

    public int getY() {return 0;}
    
    public void setY(int y) {}

    public boolean isMine() {return false;}
    
    public void setMine(boolean isMine) {}
    
    public int getAdjacentMines() {return 0;}
    
    public void setAdjacentMines(int adjacentMines) {}
}
