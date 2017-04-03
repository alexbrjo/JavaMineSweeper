package co.alexjo.javaminesweeper.field;

import co.alexjo.javaminesweeper.field.Minefield;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the Minefield class
 * @author Alex Johnson
 */
public class MinefieldTest {
    
    /**
     * Tests the Minefield constructor. This effectively tests
     * the private setters for the encapsulated fields
     */
    @Test
    public void testMinefieldIntIntInt () {
        Minefield mf = null;
        
        // Test invalid x below boundary, -1
        mf = null;
        try {
            mf = new Minefield(-1, 1, 1);
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
        
        // Test invalid x on boundary, 0
        mf = null;
        try {
            mf = new Minefield(0, 1, 1);
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
        
        // Test invalid y below boundary, -1
        mf = null;
        try {
            mf = new Minefield(1, -1, 1);
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
        
        // Test invalid y on boundary, 0
        mf = null;
        try {
            mf = new Minefield(1, 0, 1);
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
            
        // Test valid x and y both above boundary, 1 1
        mf = null;
        try {
            mf = new Minefield(1, 1, 1);
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
                
        // Test mines below lower boundary, -1
        mf = null;
        try {
            mf = new Minefield(1, 1, -1);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
        
        // Test mines on lower boundary, 0
        mf = null;
        mf = new Minefield(1, 1, 0);
        assertEquals(1, mf.getWidth());
        assertEquals(1, mf.getHeight());
        assertEquals(0, mf.getNumberOfMines());
        
        // Test max mines for size of 4x4, below boundary
        mf = null;
        mf = new Minefield(4, 4, 15);
        assertEquals(4, mf.getWidth());
        assertEquals(4, mf.getHeight());
        assertEquals(15, mf.getNumberOfMines());
        
        // Test max mines for size of 3x5, on boundary
        mf = null;
        mf = new Minefield(3, 5, 15);
        assertEquals(3, mf.getWidth());
        assertEquals(5, mf.getHeight());
        assertEquals(15, mf.getNumberOfMines());
        
        // Test max mines for size of 6x7, above boundary
        mf = null;
        try {
            mf = new Minefield(6, 7, 43);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(mf);
        }
    } 
    
    /**
     * Tests the getMines method
     */
    @Test
    public void testGetMines () {
        
        // Tests that it returns not null
        Minefield m1 = new Minefield(1, 1, 0);
        assertNotNull(m1.getMines());
        
        // Tests when Minefield has 0 mines
        Minefield m2 = new Minefield(1, 1, 0);
        Square[] mines2 = m2.getMines();
        assertEquals(0, mines2.length);
        
        // Tests when Minefield has 3 mines
        Minefield m3 = new Minefield(3, 3, 3);
        Square[] mines3 = m3.getMines();
        assertEquals(3, mines3.length);
        
        // Tests that each cell has a Square
        Minefield m4 = new Minefield(3, 3, 3);
        Square[] mines4 = m4.getMines();
        for (Square mine : mines4) {
            assertTrue(mine.isMine());
        }
        
    }
    
    /**
     * Tests the getFieldState method, of class Minefield
     */
    @Test
    public void testGetFieldState () {
        
        // Tests when single mine and not clicked, should be not clear
        Minefield m1 = new Minefield(1, 1, 1);
        assertEquals(0, m1.getFieldState());
        
        // Tests when single empty and clicked, should clear
        Minefield m2 = new Minefield(1, 1, 0);
        m2.click(0, 0, 1, false, true);
        assertEquals(1, m2.getFieldState());  
        
        // Tests when single empty and clicked, should explode
        Minefield m3 = new Minefield(1, 1, 1);
        m3.click(0, 0, 1, false, true);
        m3.click(0, 0, 0, false, false); // release mouse
        assertEquals(-1, m3.getFieldState());    
        
        // Tests 3x3 board with 8 mines and 2 clicks, should explode. 2 clicks 
        // in a size 9 board with 8 mines will guarantee a hit
        Minefield m4 = new Minefield(3, 3, 8);
        m4.click(0, 0, 1, false, true);
        m4.click(0, 1, 0, false, false); // release mouse
        assertEquals(-1, m4.getFieldState());  
        
        // Tests 2x2 board with 0 mines and 1 clicks, should clear entire board
        Minefield m5 = new Minefield(2, 2, 0);
        m5.click(0, 0, 1, false, true);
        assertEquals(1, m5.getFieldState());    
        
        // Tests 2x2 board with 0 mines and 4 clicks, should clear. 4 clicks 
        // in a size 4 board with 4 mines will guarantee a clear
        Minefield m6 = new Minefield(2, 2, 0);
        m6.click(0, 0, 1, false, true);
        m6.click(0, 1, 1, false, true);
        m6.click(1, 0, 1, false, true);
        m6.click(1, 1, 1, false, true);
        assertEquals(1, m6.getFieldState());  
            
    }
    
    /**
     * Tests the getSquare method, of class Minefield
     */
    @Test
    public void testGetSqaure () {
        
        // Tests if it doesn't throw an error
        Minefield m1 = new Minefield(1, 1, 0);
        m1.getSquare(0, 0);
        
        // @TODO write actual tests
        
    }
    
    /**
     * Tests the click method, of class Minefield
     */
    @Test
    public void testClick () {
        
        // Tests if click clears a square
        Minefield m1 = new Minefield(1, 1, 0);
        m1.click(0, 0, 1, false, true);
        assertTrue(m1.getSquare(0, 0).isCleared());
        
        // Tests if click explodes a mine
        Minefield m2 = new Minefield(1, 1, 1);
        m2.click(0, 0, 1, false, true);
        assertTrue(m2.getSquare(0, 0).hasExploded());
        
        // Tests if click flags/unflags a square
        Minefield m3 = new Minefield(1, 1, 0);
        m3.click(0, 0, 3, true, false);
        assertTrue(m3.getSquare(0, 0).isFlagged());
        m3.click(0, 0, 3, true, false);
        assertFalse(m3.getSquare(0, 0).isFlagged());
        
    }
    
    /**
     * Tests the isClear method, of class Minefield
     */
    @Test
    public void testIsClear () {
        
        // Tests if isClear by default
        Minefield m1 = new Minefield(1, 1, 0);
        assertFalse(m1.isClear());
        
        // Tests isClear after only square is cleared
        Minefield m2 = new Minefield(1, 1, 0);
        m2.click(0, 0, 1, false, true);
        assertTrue(m2.isClear());
        
    }
   
    /**
     * Tests the isExploded method, of class Minefield
     */
    @Test
    public void testIsExploded () {
        
        // Tests if no mines are exploded
        Minefield m1 = new Minefield(4, 4, 2);
        assertFalse(m1.isExploded());
        
        // Tests if all (1) mines are exploded
        Minefield m2 = new Minefield(1, 1, 1);
        m2.click(0, 0, 1, false, true);
        assertTrue(m2.isExploded());
        
        // Tests if all (1) squares are cleared
        Minefield m3 = new Minefield(1, 1, 0);
        m3.click(0, 0, 1, false, true);
        assertFalse(m3.isExploded());
        
    }
    
    /**
     * Tests the getNumberOfMines method, of class Minefield 
     */
    @Test
    public void testGetNumberOfMines () {
        
        // Tests when theres no mines 
        Minefield m1 = new Minefield(4, 4, 0);
        assertEquals(0, m1.getNumberOfMines());
        
        // Tests when a 1/4 of the area is mines
        Minefield m2 = new Minefield(4, 4, 4);
        assertEquals(4, m2.getNumberOfMines());
        
        // Test when 1/2 of the area is mines
        Minefield m3 = new Minefield(6, 6, 18);
        assertEquals(18, m3.getNumberOfMines());
        
        // Test when 4/7 of the area is mines
        Minefield m4 = new Minefield(7, 7, 28);
        assertEquals(28, m4.getNumberOfMines());
        
        // Test when area is all mines
        Minefield m5 = new Minefield(5, 5, 25);
        assertEquals(25, m5.getNumberOfMines());
        
    }
    
    /**
     * Tests the getFace method, of class Minefield
     */
    @Test
    public void testGetFace () {
        
        // test when field is normal 
        Minefield m1 = new Minefield(1, 1, 1);
        assertEquals(1, m1.getFace());
        
        // test when button is down and up
        Minefield m2 = new Minefield(1, 1, 1);
        m2.setButton(true);
        assertEquals(0, m2.getFace());
        m2.setButton(false);
        assertEquals(1, m2.getFace());
        
        // test when field is exploded 
        Minefield m3 = new Minefield(1, 1, 1);
        m3.click(0, 0, 1, false, true); // click mouse, exploding mine
        m3.click(0, 0, 0, false, false); // release mouse
        assertEquals(3, m3.getFace());
        
        // test when field is cleared 
        Minefield m4 = new Minefield(1, 1, 0);
        m4.click(0, 0, 1, true, false); // click mouse, clearing the board
        m4.click(0, 0, 0, false, false); // release mouse
        assertEquals(4, m4.getFace());
            
    }
    
    /**
     * Tests the getNumberOfMinesDigitized method, of class Minefield 
     */
    @Test
    public void testGetNumberOfMinesDigitized () {
        
        // Test when numberOfMines is the min
        Minefield m1 = new Minefield(4, 4, 0);
        assertEquals(0, m1.getNumberOfMines());
        
        // Test when numberOfMines is in the middle
        Minefield m2 = new Minefield(5, 5, 12);
        assertEquals(12, m2.getNumberOfMines());
        
        // Test when numberOfMines is the max
        Minefield m3 = new Minefield(6, 6, 36);
        assertEquals(36, m3.getNumberOfMines());
        
    }
    
    /**
     * Tests the getTime method, of class Minefield 
     */
    @Test
    public void testGetTime () {
        
        // Test that time is greater than zero
        Minefield m1 = new Minefield(1, 1, 0);
        assertTrue(m1.getTime() >= 0);
            
    }
    
    /**
     * Test the getTimeDigitized method, of class Minefield
     */
    @Test
    public void testGetTimeDigitized () {
        
        // Tests getTime immediatly
        Minefield m1 = new Minefield(1, 1, 0); 
        int[] a = m1.getTimeDigitized();
        assertEquals(3, a.length);
        assertTrue(a[0] < 10);
        assertTrue(a[1] < 10);
        assertTrue(a[2] < 10);
        // no combo of array numbers should be greater than 99
        assertTrue(a[1] * 10 + a[0] <= 99);
        assertTrue(a[2] * 10 + a[0] <= 99);
        assertTrue(a[2] * 10 + a[1] <= 99);
        // no combo of array numbers
        assertTrue(a[0] * 100 + a[1] * 10 + a[2] <= 999);
        
        // Tests getTime after a few more nano-seconds
        Minefield m2 = new Minefield(1, 1, 0);
        int[] b = m2.getTimeDigitized();
        assertTrue(a.length == 3);
        assertTrue(a[0] < 10);
        assertTrue(a[1] < 10);
        assertTrue(a[2] < 10);
        
    }
    
    /**
     * Tests the getWidth method, of class Minefield
     */
    @Test
    public void testGetWidth () {
        
        // Tests when the width is set to a minimum
        Minefield m1 = new Minefield(1, 1, 0);
        assertEquals(1, m1.getWidth());
        
        // Tests when the height is set to a middle value
        Minefield m2 = new Minefield(10, 10, 0);
        assertEquals(10, m2.getWidth());
        
    }
    
    /**
     * Tests the getHeight method, of class Minefield
     */
    @Test
    public void testGetHeight () {
        
        // Tests when the height is set to a minimum
        Minefield m1 = new Minefield(1, 1, 0);
        assertEquals(1, m1.getHeight());
        
        // Tests when the height is set to a middle value
        Minefield m2 = new Minefield(10, 10, 0);
        assertEquals(10, m2.getHeight());
        
    }
    
    /**
     * Tests the button method and the setButton method, of class Minefield
     */
    @Test
    public void testButtonAndSetButton () {
        
        // Tests when the button is not set
        Minefield m1 = new Minefield(1, 1, 0);
        assertFalse(m1.button());
        
        // Tests when the button is set to down
        Minefield m2 = new Minefield(1, 1, 0);
        m2.setButton(true);
        assertTrue(m2.button());
        
        // Tests when the button is set to flase
        Minefield m3 = new Minefield(1, 1, 0);
        m3.setButton(false);
        assertFalse(m3.button());
        
        // Tests the button set to true, false, true and false
        Minefield m4 = new Minefield(1, 1, 0);
        assertEquals(false, m4.button());
        m4.setButton(true);
        assertTrue(m4.button());
        m4.setButton(false);
        assertFalse(m4.button());
        m4.setButton(true);
        assertTrue(m4.button());
        m4.setButton(false);
        assertFalse(m4.button());
        
    }
}
