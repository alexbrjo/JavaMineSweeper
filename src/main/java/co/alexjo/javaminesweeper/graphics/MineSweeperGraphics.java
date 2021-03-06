package co.alexjo.javaminesweeper.graphics;

import co.alexjo.javaminesweeper.field.Minefield;
import co.alexjo.javaminesweeper.field.Square;
import java.awt.Color;
import java.awt.Font;
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
        
        sprite = new SpriteCropper(spriteSheetPath);
        
    }
    
    /**
     * Repaints the display and the Minefield.
     * @param mf The minefield to draw
     * @param frame The frame of the graphics
     */
    public void repaint (Minefield mf, JFrame frame) {
        
        if (mf == null || frame == null) {
            throw new IllegalArgumentException();
        }
        
        Graphics g = frame.getGraphics();
        
        if (minefieldImage == null) paintMinefield(mf);
        g.drawImage(minefieldImage, 0, 72, null);
        
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
     * Repaints the minefield grid squares. 
     * @param mf The minefield to paint
     */
    public void paintMinefield (Minefield mf) {
        if (mf == null) {
            throw new IllegalArgumentException();
        }
        int w = 16 * mf.getWidth();
        int h = 16 * mf.getHeight();
        minefieldImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics image = minefieldImage.getGraphics();
        for (int i = 0; i < mf.getWidth(); i++) {
            for (int j = 0; j < mf.getHeight(); j++) {
                Square s = mf.getSquare(i, j);
                BufferedImage squareSprite = sprite.getBlock();
                if (s.isCleared()) {
                    if (s.isMine()) {
                        squareSprite = sprite.getMine();
                        if (s.hasExploded()) {
                            squareSprite = sprite.getMine(2);
                        }
                    } else {
                        squareSprite = sprite.getClearBlock(s.getAdjacentMines());
                    }
                } else if (s.isFlagged()) {
                    squareSprite = sprite.getBlock(2);
                    if (mf.isExploded() && !s.isMine()) {
                        squareSprite = sprite.getMine(1);
                    }
                }
                image.drawImage(squareSprite, i * 16, j * 16, null);
            } // for j
        } // for i
    }
}
