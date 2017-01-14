package co.alexjo.javaminesweeper.graphics;

import co.alexjo.javaminesweeper.field.Minefield;
import co.alexjo.javaminesweeper.field.Square;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * The graphics of MineSweeper. Generated images of the display and the 
 * minefield to be drawn on the jFrame.
 * @author Alex Johnson
 */
public class MineSweeperGraphics {
    
    /** The cached image of the minefield */
    private BufferedImage minefieldImage;
    
    /** The cached image of the display image */
    private BufferedImage displayImage;
    
    /** The cut up sprite sheet */
    private SpriteCropper sprite;
    
    /**
     * Constructs a new MineSweeperGraphics and generates the initial images.
     * @param spriteSheetPath The file path to the sprite sheet
     * @throws IllegalArgumentException if sprite sheet path is null or an 
     *      empty string
     */
    public MineSweeperGraphics (String spriteSheetPath, JFrame f) {
        if (spriteSheetPath == null || spriteSheetPath.equals("")) {
            throw new IllegalArgumentException();
        }
        
        File spriteSheet = new File(getClass().getClassLoader().getResource(spriteSheetPath).getFile());
        sprite = new SpriteCropper(spriteSheet, f);
        
    }
    
    /**
     * Repaints the display and the Minefield.
     * @param mf The minefield to draw
     * @param frame The frame of the graphics
     */
    public void repaint (Minefield mf, JFrame frame) {
        Graphics g = frame.getGraphics();
        
        paintDisplay(mf, frame, g); // always print display
        paintMinefield(mf, frame, g);
        
        BufferedImage display = getDisplay();
        BufferedImage minefield = getMinefield();
        
        g.drawImage(sprite.getClearBlock(8), 4, 26, frame);
        g.drawImage(minefield, 0, 22, frame);
    }
    
    private void paintDisplay (Minefield mf, ImageObserver io, Graphics g) {}
    
    private void paintMinefield (Minefield mf, ImageObserver io, Graphics g) {
        if (mf == null || io == null) {
            throw new IllegalArgumentException();
        }
        
        int w = 16 * mf.getWidth();
        int h = 16 * mf.getHeight();
        BufferedImage current = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics image = current.getGraphics();
        for (int i = 0; i < mf.getWidth(); i++) {
            for (int j = 0; j < mf.getHeight(); j++) {
                Square s = mf.getSquare(i, j);
                if (s.isMine()) {
                    image.drawImage(sprite.getMine(), i * 16, j * 16, io);
                } else {
                    image.drawImage(sprite.getClearBlock(s.getAdjacentMines()), i * 16, j * 16, io);
                }
            }
        }
        minefieldImage = current;
    }
    
    public BufferedImage getMinefield () {
        return minefieldImage;
    }
    
    public BufferedImage getDisplay () {
        return displayImage;
    }
    
}
