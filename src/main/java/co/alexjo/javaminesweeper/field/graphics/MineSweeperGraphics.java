package co.alexjo.javaminesweeper.field.graphics;

import co.alexjo.javaminesweeper.field.Minefield;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The graphics of MineSweeper.
 * @author Alex Johnson
 */
public class MineSweeperGraphics {
    
    private BufferedImage minefieldImage;
    
    private BufferedImage displayImage;
    
    public MineSweeperGraphics (String spriteSheetPath) {}
    
    public void repaint (Minefield mf) {}
    
    private void paintDisplay () {}
    
    private void paintMinefield (Minefield mf) {}
    
    public BufferedImage getMinefield () {return null;}
    
    public BufferedImage getDisplay () {return null;}
    
}
