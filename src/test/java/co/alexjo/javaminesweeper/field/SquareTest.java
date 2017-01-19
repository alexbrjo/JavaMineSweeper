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
        Square s1 = new Square (0, 0, false);
        assertEquals(0, s1.getX());
        assertEquals(0, s1.getY());
        assertEquals(false, s1.isMine());
        
        // Testing a valid construction
        Square s2 = new Square (15, 7, true);
        assertEquals(15, s2.getX());
        assertEquals(7, s2.getY());
        assertEquals(true, s2.isMine());
        
        // Testing x below bounds
        Square s3 = null;
        try {
            s3 = new Square (-1, 0, false);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(s3);
        }
        
        // Testing y below bounds
        Square s4 = null;
        try {
            s4 = new Square (0, -1, false);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(s4);
        }
    }
    
    /**
     * Tests the click method, of class Square
     */
    @Test
    public void testClick () {
        
        // Tests when the Squared is clicked
        Square s1 = new Square(0, 0, false);
        assertFalse(s1.isCleared());
        s1.click();
        assertTrue(s1.isCleared());
        
        // Tests when the Squared is clicked twice
        Square s2 = new Square(0, 0, false);
        assertFalse(s2.isCleared());
        s2.click();
        assertTrue(s2.isCleared());
        s2.click();
        assertTrue(s2.isCleared());
        
    }
    
    /**
     * Test of getX method, of class Square.
     */
    @Test
    public void testGetX() {
        
        // Tests x as 0
        Square s1 = new Square(0, 5, false);
        assertEquals(0, s1.getX());
        
        // Tests x as 32
        Square s2 = new Square(32, 1, true);
        assertEquals(32, s2.getX());
        
    }

    /**
     * Test of setX method, of class Square.
     */
    @Test
    public void testSetX() {
        
        // Tests set x above lower boundary, 1
        Square s1 = new Square(0, 0, false);
        try {
            s1.setX(1);
            assertEquals(1, s1.getX());
            assertEquals(0, s1.getY());
            assertEquals(false, s1.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Test set x past lower boundary, -1
        Square s2 = new Square(0, 0, false);
        try {
            s2.setX(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s2.getX());
            assertEquals(0, s2.getY());
            assertEquals(false, s2.isMine());
        }
        
        // Test x extreme value, 12345
        Square s3 = new Square(0, 0, false);
        try {
            s3.setX(12345);
            assertEquals(12345, s3.getX());
            assertEquals(0, s3.getY());
            assertEquals(false, s3.isMine());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Test of getY method, of class Square.
     */
    @Test
    public void testGetY() {
        
        // Tests y at lower bound, 0
        Square s1 = new Square(0, 5, false);
        assertEquals(5, s1.getY());
        
        // Tests y as standard value, 26
        Square s2 = new Square(1, 26, true);
        assertEquals(26, s2.getY());
        
    }

    /**
     * Test of setY method, of class Square.
     */
    @Test
    public void testSetY() {
         
        // Tests set y above lower boundary, 1
        Square s1 = new Square(0, 0, false);
        try {
            s1.setY(1);
            assertEquals(0, s1.getX());
            assertEquals(1, s1.getY());
            assertEquals(false, s1.isMine());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
        
        // Test set y past lower boundary, -1
        Square s2 = new Square(0, 0, false);
        try {
            s2.setY(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s2.getX());
            assertEquals(0, s2.getY());
            assertEquals(false, s2.isMine());
        }
        
        // Test x extreme value, 54321
        Square s3 = new Square(0, 0, false);
        try {
            s3.setY(54321);
            assertEquals(0, s3.getX());
            assertEquals(54321, s3.getY());
            assertEquals(false, s3.isMine());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
        
    }

    /**
     * Test of isIsMine method, of class Square.
     */
    @Test
    public void testIsMine() {
        
        // Test when mine is true
        Square s1 = new Square(0, 0, true);
        assertTrue(s1.isMine());
        
        // Test when mine is false
        Square s2 = new Square(0, 0, false);
        assertFalse(s2.isMine());
        
    }

    /**
     * Test of setIsMine method, of class Square.
     */
    @Test
    public void testSetMine() {
        
        // Tests set mine to true
        Square s1 = new Square(0, 0, false);
        s1.setMine(true);
        assertEquals(0, s1.getX());
        assertEquals(0, s1.getY());
        assertTrue(s1.isMine());
        
        // Tests set mine to false
        Square s2 = new Square(1, 1, true);
        s2.setMine(false);
        assertEquals(1, s2.getX());
        assertEquals(1, s2.getY());
        assertFalse(s2.isMine());
            
    }

    /**
     * Test of getAdjacentMines method, of class Square.
     */
    @Test
    public void testGetAdjacentMines() {
        
        // Tests adjacentMines when not set
        Square s1 = new Square(0, 0, false);
        assertEquals(0, s1.getAdjacentMines());
        
        // Tests adjacentMines when set to lower bound, 0
        Square s2 = new Square(0, 0, false);
        s2.setAdjacentMines(0);
        assertEquals(0, s2.getAdjacentMines());
        
        // Tests adjacentMines when set to upper bound, 8
        Square s3 = new Square(0, 0, false);
        s3.setAdjacentMines(8);
        assertEquals(8, s3.getAdjacentMines());
        
    }

    /**
     * Test of setAdjacentMines method, of class Square.
     */
    @Test
    public void testSetAdjacentMines() {
        
        // Tests setting to reasonable number, 2
        Square s1 = new Square(0, 0, false);
        try {
            s1.setAdjacentMines(2);
            assertEquals(0, s1.getX());
            assertEquals(0, s1.getY());
            assertEquals(false, s1.isMine());
            assertEquals(2, s1.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to under lower bound, -1
        Square s2 = new Square(0, 0, false);
        try {
            s2.setAdjacentMines(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s2.getX());
            assertEquals(0, s2.getY());
            assertEquals(false, s2.isMine());
            assertEquals(0, s2.getAdjacentMines());
        }
        
        // Tests setting to lower bound, 0
        Square s3 = new Square(0, 0, false);
        try {
            s3.setAdjacentMines(0);
            assertEquals(0, s3.getX());
            assertEquals(0, s3.getY());
            assertEquals(false, s3.isMine());
            assertEquals(0, s3.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to above lower bound, 1
        Square s4 = new Square(0, 0, false);
        try {
            s4.setAdjacentMines(1);
            assertEquals(0, s4.getX());
            assertEquals(0, s4.getY());
            assertEquals(false, s4.isMine());
            assertEquals(1, s4.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to below upper bound, 7
        Square s5 = new Square(0, 0, false);
        try {
            s5.setAdjacentMines(7);
            assertEquals(0, s5.getX());
            assertEquals(0, s5.getY());
            assertEquals(false, s5.isMine());
            assertEquals(7, s5.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to upper bound, 8
        Square s6 = new Square(0, 0, false);
        try {
            s6.setAdjacentMines(8);
            assertEquals(0, s6.getX());
            assertEquals(0, s6.getY());
            assertEquals(false, s6.isMine());
            assertEquals(8, s6.getAdjacentMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests setting to above upper bound, 9
        Square s7 = new Square(0, 0, false);
        try {
            s7.setAdjacentMines(9);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, s7.getX());
            assertEquals(0, s7.getY());
            assertEquals(false, s7.isMine());
            assertEquals(0, s7.getAdjacentMines());
        }
        
    }
    
    /**
     * Tests isCleared method, of class Square
     */
    @Test
    public void testIsCleared () {
        
        // Tests when the Square is initalized
        Square s1 = new Square(0, 0, false);
        assertFalse(s1.isCleared());
        
        // tests after black Square is clicked
        Square s2 = new Square(0, 0, false);
        assertFalse(s2.isCleared());
        s2.click();
        assertTrue(s2.isCleared());
        
    }
    
    /**
     * Tests the isFlagged method, of class Square
     */
    @Test
    public void testIsFlagged () {
        
        // Tests that no flag when the Square is initalized
        Square s1 = new Square(0, 0, false);
        assertFalse(s1.isFlagged());
        
        // Test for flag after flag is added
        Square s2 = new Square(0, 0, false);
        assertFalse(s2.isFlagged());
        s2.addFlag();
        assertTrue(s2.isFlagged());
    }
        
    /**
     * Tests the addFlag method, of class Square
     */
    @Test
    public void testAddFlag () {
        
        // Tests that addFlag is default false
        Square s1 = new Square(0, 0, false);
        assertFalse(s1.isFlagged());
        s1.addFlag();
        assertTrue(s1.isFlagged());
        
    }
    
    /**
     * Tests the removeFlag method, of class Square
     */
    @Test
    public void testRemoveFlag () {
        
        // Test removing flag when there is a flag
        Square s1 = new Square(0, 0, false);
        s1.addFlag();
        assertTrue(s1.isFlagged()); // flagged
        s1.removeFlag();
        assertFalse(s1.isFlagged()); // removed flag
        
        // Test removing flag when there isn't a flag
        Square s2 = new Square(0, 0, false);
        assertFalse(s2.isFlagged()); // no flag
        s2.removeFlag();
        assertFalse(s2.isFlagged()); // still no flag
        
    }
    
    /**
     * Tests the toggleFlag method, of class Square
     */
    @Test
    public void testToggleFlag () {
        
        // Test toggling there is a flag
        Square s1 = new Square(0, 0, false);
        s1.addFlag();
        assertTrue(s1.isFlagged()); // make sure theres a flag
        s1.toggleFlag();
        assertFalse(s1.isFlagged()); // toggled to false
        
        // Test toggling when there isn't a flag
        Square s2 = new Square(0, 0, false);
        s2.removeFlag();
        assertFalse(s2.isFlagged()); // make sure no flag
        s2.toggleFlag();
        assertTrue(s2.isFlagged()); // toggled to true
    }
    
    /**
     * Tests the hasExploded method, of class Square
     */
    @Test
    public void testHasExploded () {
        
        // Test hasExploded on a mine after square is initialized 
        Square s1 = new Square(0, 0, true);
        assertFalse(s1.hasExploded());
        
        // Test hasExploded on a mine after square is exploded 
        Square s2 = new Square(0, 0, true);
        assertFalse(s2.hasExploded()); 
        s2.explode();
        assertTrue(s2.hasExploded());
        
        // Test hasExploded on a blank square, always false
        Square s3 = new Square(0, 0, true);
        assertFalse(s3.hasExploded());
    }
    
    /**
     * Tests the explode method, of class Square
     */
    @Test
    public void testExplode (){
        
        // Test explode on a mine when not exploded
        Square s1 = new Square(0, 0, true);
        assertFalse(s1.hasExploded()); 
        s1.explode();
        assertTrue(s1.hasExploded());  
        
        // Test explode on a mine when exploded
        Square s2 = new Square(0, 0, true);
        assertFalse(s2.hasExploded()); 
        s2.explode();
        assertTrue(s2.hasExploded()); 
        s2.explode();
        assertTrue(s2.hasExploded());
        
        // Test explode on a blank square, should throw IllegalArgument
        Square s3 = new Square(0, 0, false);
        try {
            assertFalse(s3.hasExploded()); 
            s3.explode();
            fail();
        } catch (IllegalArgumentException e) {
            assertFalse(s3.hasExploded());
        }
    }
    
}
