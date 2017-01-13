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
        try {
            mf = new Minefield(1, 1, 0);
            assertEquals(1, mf.getWidth());
            assertEquals(1, mf.getHeight());
            assertEquals(0, mf.getNumberOfMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Test max mines for size of 4x4, below boundary
        mf = null;
        try {
            mf = new Minefield(4, 4, 15);
            assertEquals(4, mf.getWidth());
            assertEquals(4, mf.getHeight());
            assertEquals(15, mf.getNumberOfMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Test max mines for size of 3x5, on boundary
        mf = null;
        try {
            mf = new Minefield(3, 5, 15);
            assertEquals(3, mf.getWidth());
            assertEquals(5, mf.getHeight());
            assertEquals(15, mf.getNumberOfMines());
        } catch (IllegalArgumentException e) {
            fail();
        }
        
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
        Minefield mf = null;
        
        // Tests that it returns not null
        mf = new Minefield(1, 1, 0);
        try {
            mf.getMines();
            assertNotNull(mf);
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests when Minefield has 0 mines
        mf = new Minefield(1, 1, 0);
        try {
            Square[] mines = mf.getMines();
            assertEquals(0, mines.length);
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests when Minefield has 3 mines
        mf = new Minefield(3, 3, 3);
        try {
            Square[] mines = mf.getMines();
            assertEquals(3, mines.length);
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests that each cell has a Square
        mf = new Minefield(3, 3, 3);
        try {
            Square[] mines = mf.getMines();
            for (Square mine : mines) {
                assertTrue(mine instanceof Square);
            }
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
    
    /**
     * Tests the getFieldState method, of class Minefield
     */
    @Test
    public void testGetFieldState () {
        Minefield mf = null;
        
        // Tests when single mine and not clicked, should be not clear
        mf = new Minefield(1, 1, 1);
        try {
            assertEquals(0, mf.getFieldState());
        } catch (Exception e) {
            fail();
        }
        
        // Tests when single empty and clicked, should clear
        mf = new Minefield(1, 1, 0);
        try {
            mf.click(0, 0);
            assertEquals(1, mf.getFieldState());    
        } catch (Exception e) {
            fail();
        }
        
        // Tests when single empty and clicked, should explode
        mf = new Minefield(1, 1, 1);
        try {
            mf.click(0, 0);
            assertEquals(-1, mf.getFieldState());    
        } catch (Exception e) {
            fail();
        }
        
        // Tests 3x3 board with 8 mines and 8 clicks, should explode. 2 clicks 
        // in a size 9 board with 8 mines will guarantee a hit
        mf = new Minefield(3, 3, 8);
        try {
            mf.click(0, 0);
            mf.click(0, 1);
            assertEquals(-1, mf.getFieldState());    
        } catch (Exception e) {
            fail();
        }
        
        // Tests 2x2 board with 0 mines and 3 clicks, should not clear. 4 
        // clicks in a size 4 board with 4 mines will guarantee a clear
        mf = new Minefield(2, 2, 0);
        try {
            mf.click(0, 0);
            mf.click(0, 1);
            mf.click(1, 0);
            assertEquals(0, mf.getFieldState());    
        } catch (Exception e) {
            fail();
        }
        
        // Tests 2x2 board with 0 mines and 4 clicks, should clear. 4 clicks 
        // in a size 4 board with 4 mines will guarantee a clear
        mf = new Minefield(2, 2, 0);
        try {
            mf.click(0, 0);
            mf.click(0, 1);
            mf.click(1, 0);
            mf.click(1, 1);
            assertEquals(1, mf.getFieldState());    
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests the getNumberOfMines method, of class Minefield 
     */
    @Test
    public void testGetNumberOfMines () {
        Minefield mf = null;
        
        // Test when numberOfMines is the min
        mf = new Minefield(4, 4, 0);
        try {
            assertEquals(0, mf.getNumberOfMines());
        } catch (Exception e) {
            fail();
        }
        
        // Test when numberOfMines is in the middle
        mf = new Minefield(5, 5, 12);
        try {
            assertEquals(12, mf.getNumberOfMines());
        } catch (Exception e) {
            fail();
        }
        
        // Test when numberOfMines is the max
        mf = new Minefield(6, 6, 36);
        try {
            assertEquals(36, mf.getNumberOfMines());
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests the getWidth method, of class Minefield
     */
    @Test
    public void testGetWidth () {
        Minefield mf = null;
        
        // Tests when the width is set to a minimum
        mf = new Minefield(1, 1, 0);
        try {
            assertEquals(1, mf.getWidth());
        } catch (Exception e) {
            fail();
        }
        
        // Tests when the height is set to a middle value
        mf = new Minefield(10, 10, 0);
        try {
            assertEquals(10, mf.getWidth());
        } catch (Exception e) {
            fail();
        }
    }
    
    
    /**
     * Tests the getHeight method, of class Minefield
     */
    @Test
    public void testGetHeight () {
        Minefield mf = null;
        
        // Tests when the height is set to a minimum
        mf = new Minefield(1, 1, 0);
        try {
            assertEquals(1, mf.getHeight());
        } catch (Exception e) {
            fail();
        }
        
        // Tests when the height is set to a middle value
        mf = new Minefield(10, 10, 0);
        try {
            assertEquals(10, mf.getHeight());
        } catch (Exception e) {
            fail();
        }
    }
    
}
