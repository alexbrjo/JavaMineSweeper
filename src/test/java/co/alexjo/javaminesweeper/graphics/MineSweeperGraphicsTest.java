package co.alexjo.javaminesweeper.graphics;

import co.alexjo.javaminesweeper.field.Minefield;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the MineSweeperGraphics class.
 * @author Alex Johnson
 */
public class MineSweeperGraphicsTest {
    
    /** The path to the sprite sheet */
    private final String SPRITE_SHEET = "minesprites.png";
    
    /**
     * Tests the construction of MineSweeperGraphics
     */
    @Test
    public void testMineSweeperGraphics () {
        MineSweeperGraphics g = null;
        
        // Tests the construction with null
        g = null;
        try {
            g = new MineSweeperGraphics(null, null);
            fail();
        } catch (Exception e) {
            assertNull(g);
        }
        
        // Tests the default construction with invalid file path
        g = null;
        try {
            g = new MineSweeperGraphics("notsprites.png", null);
            fail();
        } catch (Exception e) {
            assertNull(g);
        }
        
        // Tests the default construction with invalid file path
        g = null;
        try {
            g = new MineSweeperGraphics("minesprites.png", null);
            fail();
        } catch (Exception e) {
            assertNull(g);
        }
    
    }
    
    /**
     * Tests the repaint method, of class MineSweeperGraphics
     */
    @Test
    public void testRepaint () {
        MineSweeperGraphics g = null;
        Minefield mf = null;
        JFrame frame = null;
        
        // Tests the repaint method with null mf
        g = new MineSweeperGraphics(SPRITE_SHEET, null);
        frame = new JFrame("Psuedo frame");
        try {
            g.repaint(null, frame);
        } catch (IllegalArgumentException e) {
            fail();
        }
        
        // Tests the repaint method with valid mf
        g = new MineSweeperGraphics(SPRITE_SHEET, null);
        mf = new Minefield(1, 1, 1);
        frame = new JFrame("Psuedo frame");
        try {
            g.repaint(mf, frame);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
    
    /**
     * Tests the getMinefield method, of class MineSweeperGraphics
     */
    @Test
    public void testGetMinefield () {
        MineSweeperGraphics g = null;
        Minefield mf = null;
        
        // Tests getMinefield before painting
        try {
            
        } catch (Exception e) {
            
        }
                
    }
    
    /**
     * Tests the getDispaly method, of class MineSweeperGraphics
     */
    @Test
    public void testGetDisplay () {
        MineSweeperGraphics g = null;
        Minefield mf = null;
        
        // Tests getDisplay before painting
        g = new MineSweeperGraphics(SPRITE_SHEET, null);
        try {
            
        } catch (Exception e) {
            
        }
    }
}
