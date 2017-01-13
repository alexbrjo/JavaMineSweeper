package co.alexjo.javaminesweeper;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the MineSweeperTest class
 * @author Alex Johnson
 */
public class MineSweeperTest {
    
    /**
     * Tests the default MineSweeper construction
     */
    @Test
    public void testMineSweeper () {
        MineSweeper ms = null;
        
        // Tests default construction
        ms = null;
        try { 
            ms = new MineSweeper();
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
            ms = new MineSweeper();
            ms.quit();
        } catch (Exception e) {
            fail();
        }
                
    }
}
