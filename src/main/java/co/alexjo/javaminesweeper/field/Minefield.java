package co.alexjo.javaminesweeper.field;

/**
 * Skeleton of the class Minefield, a grid of Squares
 * @author Alex Johnson
 */
public class Minefield {
    
    public final int MAX_WIDTH = 20;
    
    public final int MAX_HEIGHT = 20;
    
    private int numberOfMines;
    
    private int width;
    
    private int height;
    
    private Square[][] board = null;
    
    public Minefield (int numberOfMines, int width, int height) {}
    
    public Square[] getMines () {return new Square[0];}
    
    public int getFieldState () {return 0;}
    
    public void click (int x, int y) {}
    
    public int getNumberOfMines () {return 0;}
    
    private void setNumberOfMines () {}
    
    public int getWidth () {return 0;}
    
    private void setWidth () {}
    
    public int getHeight () {return 0;}
    
    private void setHeight () {}
}
