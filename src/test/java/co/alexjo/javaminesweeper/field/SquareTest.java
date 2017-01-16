package co.alexjo.javaminesweeper.field;

import co.alexjo.javaminesweeper.field.Square;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the Square class
 * @author Alex Johnson
 */
public class SquareTest {
    
    /**
     * Tests the Square constructor
     */
    @Test
    public void testSquareIntIntBoolean () {
        // Testing a valid construction with x and y bounds
        Square s = null;
        try {
            s = new Square (0, 0, false);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Testing a valid construction
        s = null;
        try {
            s = new Square (15, 7, true);
            assertEquals(15, s.getX());
            assertEquals(7, s.getY());
            assertEquals(true, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Testing x below bounds
        s = null;
        try {
            s = new Square (-1, 0, false);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(s);
        }
        
        // Testing y below bounds
        s = null;
        try {
            s = new Square (0, -1, false);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(s);
        }
    }
    
    /**
     * Tests the click method, of class Square
     */
    @Test
    public void testClick () {
        Square s = null;
        
        // Tests when the Squared is clicked
        s = new Square(0, 0, false);
        try {
            assertEquals(false, s.isCleared());
            s.click();
            assertEquals(true, s.isCleared());
        } catch (Exception e) {
            fail();
        }
        
        // Tests when the Squared is clicked twice
        s = new Square(0, 0, false);
        try {
            assertEquals(false, s.isCleared());
            s.click();
            assertEquals(true, s.isCleared());
            s.click();
            assertEquals(true, s.isCleared());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Test of getX method, of class Square.
     */
    @Test
    public void testGetX() {
        Square s = null;
        
        // Tests x as 0
        s = new Square(0, 5, false);
        try {
            assertEquals(0, s.getX());
        } catch (Exception e) {
            fail();
        }
        
        // Tests x as 32
        s = new Square(32, 1, true);
        try {
            assertEquals(32, s.getX());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test of setX method, of class Square.
     */
    @Test
    public void testSetX() {
        Square s = null;
        
        // Tests set x above lower boundary, 1
        s = new Square(0, 0, false);
        try {
            s.setX(1);
            assertEquals(1, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Test set x past lower boundary, -1
        s = new Square(0, 0, false);
        try {
            s.setX(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
        }
        
        // Test x extreme value, 12345
        s = new Square(0, 0, false);
        try {
            s.setX(12345);
            assertEquals(12345, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Test of getY method, of class Square.
     */
    @Test
    public void testGetY() {
        Square s = null;
        
        // Tests y at lower bound, 0
        s = new Square(0, 5, false);
        try {
            assertEquals(5, s.getY());
        } catch (Exception e) {
            fail();
        }
        
        // Tests y as standard value, 27
        s = new Square(1, 26, true);
        try {
            assertEquals(26, s.getY());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test of setY method, of class Square.
     */
    @Test
    public void testSetY() {
         Square s = null;
        
        // Tests set y above lower boundary, 1
        s = new Square(0, 0, false);
        try {
            s.setY(1);
            assertEquals(0, s.getX());
            assertEquals(1, s.getY());
            assertEquals(false, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Test set y past lower boundary, -1
        s = new Square(0, 0, false);
        try {
            s.setY(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
        }
        
        // Test x extreme value, 54321
        s = new Square(0, 0, false);
        try {
            s.setY(54321);
            assertEquals(0, s.getX());
            assertEquals(54321, s.getY());
            assertEquals(false, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Test of isIsMine method, of class Square.
     */
    @Test
    public void testIsMine() {
        Square s = null;
        
        // Test when mine is true
        s = new Square(0, 0, true);
        try {
            assertEquals(true, s.isMine());
        } catch (Exception e) {
            fail();
        }
        
        // Test when mine is false
        s = new Square(0, 0, false);
        try {
            assertEquals(false, s.isMine());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test of setIsMine method, of class Square.
     */
    @Test
    public void testSetMine() {
        Square s = null;
        
        // Tests set mine to true
        s = new Square(0, 0, false);
        try {
            s.setMine(true);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(true, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests set mine to false
        s = new Square(1, 1, true);
        try {
            s.setMine(false);
            assertEquals(1, s.getX());
            assertEquals(1, s.getY());
            assertEquals(false, s.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Test of getAdjacentMines method, of class Square.
     */
    @Test
    public void testGetAdjacentMines() {
        Square s = null;
        
        // Tests adjacentMines when not set
        s = new Square(0, 0, false);
        try {
            assertEquals(0, s.getAdjacentMines());
        } catch (Exception e) {
            fail();
        }
        
        // Tests adjacentMines when set to lower bound, 0
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(0);
            assertEquals(0, s.getAdjacentMines());
        } catch (Exception e) {
            fail();
        }
        
        // Tests adjacentMines when set to upper bound, 8
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(8);
            assertEquals(8, s.getAdjacentMines());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test of setAdjacentMines method, of class Square.
     */
    @Test
    public void testSetAdjacentMines() {
        Square s = null;
        
        // Tests setting to reasonable number, 2
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(2);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(2, s.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to under lower bound, -1
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(0, s.getAdjacentMines());
        }
        
        // Tests setting to lower bound, 0
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(0);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(0, s.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to above lower bound, 1
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(1);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(1, s.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to below upper bound, 7
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(7);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(7, s.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to upper bound, 8
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(8);
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(8, s.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to above upper bound, 9
        s = new Square(0, 0, false);
        try {
            s.setAdjacentMines(9);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s.getX());
            assertEquals(0, s.getY());
            assertEquals(false, s.isMine());
            assertEquals(0, s.getAdjacentMines());
        }
        
    }
    
    /**
     * Tests isCleared method, of class Square
     */
    @Test
    public void testIsCleared () {
        Square s = null;
        
        // Tests when the Square is initalized
        s = new Square(0, 0, false);
        try {
            assertEquals(false, s.isCleared());
        } catch (Exception e) {
            fail();
        }
        
        // 
        s = new Square(0, 0, false);
        try {
            assertEquals(false, s.isCleared());
            s.click();
            assertEquals(true, s.isCleared());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests the isFlagged method, of class Square
     */
    @Test
    public void testIsFlagged () {}
    
    /**
     * Tests the addFlag method, of class Square
     */
    @Test
    public void testAddFlag () {}
    
    /**
     * Tests the removeFlag method, of class Square
     */
    @Test
    public void testRemoveFlag () {}
    
    /**
     * Tests the toggleFlag method, of class Square
     */
    @Test
    public void testToggleFlag () {}
    
    /**
     * Tests the hasExploded method, of class Square
     */
    @Test
    public void testHasExploded () {}
    
    /**
     * Tests the explode method, of class Square
     */
    @Test
    public void testExplode (){}
    
}
