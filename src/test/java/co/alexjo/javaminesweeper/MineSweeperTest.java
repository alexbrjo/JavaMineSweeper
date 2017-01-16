package co.alexjo.javaminesweeper;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the MineSweeperTest class
 * @author Alex Johnson
 */
public class MineSweeperTest {
    
    /** The width of the Minefield */
    private final int WIDTH = 30;
    
    /** The height of the Minefield */
    private final int HEIGHT = 30;
    
    /** The number of mines on the field */
    private final int MINES = 100;
    
    /**
     * Tests the default MineSweeper construction
     */
    @Test
    public void testMineSweeper () {
        MineSweeper ms = null;
        
        // Tests default construction
        ms = null;
        try { 
            ms = new MineSweeper(WIDTH, HEIGHT, MINES);
            assertNotNull(ms);
        } catch (Exception e) {
            fail();
        }       
    }
    
    /**
     * Tests the quit method,of class MineSweeper 
     */
    @Test
    public void testQuit () {
        MineSweeper ms = null;
        
        // Tests the quit method
        ms = null;
        try {
            ms = new MineSweeper(WIDTH, HEIGHT, MINES);
            ms.quit();
        } catch (Exception e) {
            fail();
        }
                
    }
}
