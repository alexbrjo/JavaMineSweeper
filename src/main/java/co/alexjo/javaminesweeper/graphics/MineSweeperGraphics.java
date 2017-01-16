package co.alexjo.javaminesweeper.graphics;

import co.alexjo.javaminesweeper.field.Minefield;
import co.alexjo.javaminesweeper.field.Square;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.swing.JFrame;

/**
 * The graphics of MineSweeper. Generated images of the display and the 
 * minefield to be drawn on the jFrame.
 * @author Alex Johnson
 */
public class MineSweeperGraphics {
    
    /** The cached image of the minefield */
    private BufferedImage minefieldImage;
    
    /** The cut up sprite sheet */
    private SpriteCropper sprite;
    
    /**
     * Constructs a new MineSweeperGraphics and generates the initial images.
     * @param spriteSheetPath The file path to the sprite sheet
     * @throws IllegalArgumentException if sprite sheet path is null or an 
     *      empty string
     */
    public MineSweeperGraphics (String spriteSheetPath) {
        if (spriteSheetPath == null || spriteSheetPath.equals("")) {
            throw new IllegalArgumentException();
        }
        
        File spriteSheet = new File(getClass().getClassLoader().getResource(spriteSheetPath).getFile());
        sprite = new SpriteCropper(spriteSheet);
        
    }
    
    /**
     * Repaints the display and the Minefield.
     * @param mf The minefield to draw
     * @param frame The frame of the graphics
     */
    public void repaint (Minefield mf, JFrame frame) {
        
        Graphics g = frame.getGraphics();
        
        g.drawImage(minefieldImage, 0, 72, frame);
        
        // Draws the display face
        int face = mf.getFace();
        g.drawImage(sprite.getFace(face), mf.getWidth() * 16 / 2 - sprite.getFace(1).getWidth() / 2, 
                34, frame);
        
        /*
          Prints the number of mines display in the upper left corner
        */
        int[] num = mf.getNumberOfMinesDigitized();
        for (int i = 1; i < 4; i++) {
            g.drawImage(sprite.getNumber(num[i - 1]), 13 * i, 34, frame);
        }
        
        /*
           Prints the second timer in the upper right corner
         */
        int[] time = mf.getTimeDigitized();
        for (int i = 4; i > 1; i--) {
            g.drawImage(sprite.getNumber(time[4 - i]), mf.getWidth() * 16 - 13 * i, 34, frame);
        }
    }
    
    /**
     * 
     * @param mf 
     * @param io 
     */
    public void paintMinefield (Minefield mf, ImageObserver io) {
        if (mf == null || io == null) {
            throw new IllegalArgumentException();
        }
        int w = 16 * mf.getWidth();
        int h = 16 * mf.getHeight();
        minefieldImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics image = minefieldImage.getGraphics();
        for (int i = 0; i < mf.getWidth(); i++) {
            for (int j = 0; j < mf.getHeight(); j++) {
                Square s = mf.getSquare(i, j);
                if (s.isCleared()) {
                    if (s.isMine()) {
                        image.drawImage(sprite.getMine(), i * 16, j * 16, null);
                    } else {
                        image.drawImage(sprite.getClearBlock(s.getAdjacentMines()), i * 16, j * 16, null);
                    }
                } else if (s.isFlagged()) {
                    image.drawImage(sprite.getBlock(2), i * 16, j * 16, null);
                } else {
                    image.drawImage(sprite.getBlock(), i * 16, j * 16, null);
                }
            }
        }
    }
}
