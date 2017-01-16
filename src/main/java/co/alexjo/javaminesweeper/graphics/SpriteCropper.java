package co.alexjo.javaminesweeper.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Generates MineSweeper sprites from a single spritesheet.
 * @author Alex Johnson
 */
public class SpriteCropper {
    
    /** The height of the standard sprite */
    public final int SPRITE_WIDTH = 16;
    
    /** The height of the standard sprite */
    public final int SPRITE_HEIGHT = 16;
    
    /** The file of the sprite sheet */
    private BufferedImage spriteSheet;
    
    /** An array of the block image */
    private BufferedImage[] clearBlock;
    
    /** An array of images for the numbers */
    private BufferedImage[] number;
    
    /** An array of face sprite images */
    private BufferedImage[] face;
    
    /** Mine image */
    private BufferedImage mine;
    
    /** Wrong marked mine  */
    private BufferedImage wrongMine;
    
    /** Detonated Mine */
    private BufferedImage detonatedMine;
    
    /** The covered block sprite */
    private BufferedImage[] block;
    
    private final int t = BufferedImage.TYPE_INT_ARGB;
    
    /**
     * Constructs a new sprite sheet
     * @param sheetImagePath The image path of the spriteSheet
     */
    public SpriteCropper (String sheetImagePath) {
        
        File sheet = new File(getClass().getClassLoader().getResource(sheetImagePath).getFile());
        
        try {
            spriteSheet = ImageIO.read(sheet) ;
        } catch (IOException ex) {
            throw new IllegalArgumentException("File must exist");
        }
        
        clearBlock = new BufferedImage[9];
        for (int i = 0; i < clearBlock.length; i++) {
            clearBlock[i] = makeSprite(i * SPRITE_WIDTH, 0);
        }
        
        number = new BufferedImage[10];
        for (int i = 0; i < number.length; i++) {
            number[i] = makeSprite(i * 13, 32, 13, 23);
        }
        
        face = new BufferedImage[5];
        for (int i = 0; i < face.length; i++) {
            face[i] = makeSprite(i * 26, 55, 26, 26);
        }
        
        block = new BufferedImage[3];
        for (int i = 0; i < block.length; i++) {
            block[i] = makeSprite(i * SPRITE_WIDTH + 48, 16);
        }
        
        mine = makeSprite(0, 16);
        
        wrongMine = makeSprite(16, 16);
    
        detonatedMine = makeSprite(32, 16);
        
    }
    
    /**
     * 
     * @return BufferedImage of the drawn sprite
     */
    private BufferedImage makeSprite(int x, int y) {
        return makeSprite(x, y, SPRITE_WIDTH, SPRITE_HEIGHT);
    }
    
    /**
     * 
     * @return BufferedImage of the drawn sprite
     */
    private BufferedImage makeSprite(int x, int y, int w, int h) {
        BufferedImage sprite = new BufferedImage(w, h, t);
        sprite.getGraphics().drawImage(spriteSheet, 0, 0, w, h, x, y, 
                x + w, y + h, null);
        return sprite;
    }
    
    /**
     * Gets the sprite of a face.
     * @param index The index of the face sprite in the face array
     * @return the sprite of a face
     * @throws IllegalArgumentException if the number of adjacentMine is invalid
     */
    public BufferedImage getFace (int index) {
        if (index < 0 || index >= face.length) {
            throw new IllegalArgumentException();
        }
        return face[index];
    }
    
    /**
     * Gets the sprite of a clear block without adjacent mines.
     * @return the sprite of a clear block
     */
    public BufferedImage getClearBlock () {
        return getClearBlock(0);
    }
    
    /**
     * Gets the sprite of a clear block with a given number of adjacent mines.
     * @return the sprite of a clear block
     * @throws IllegalArgumentException if the number of adjacentMine is invalid
     */
    public BufferedImage getClearBlock (int adjacentMines) {
        if (adjacentMines < 0 || adjacentMines >= clearBlock.length) {
            throw new IllegalArgumentException();
        }
        return clearBlock[adjacentMines];
    }
    
    /**
     * Gets the sprite of the display number.
     * @param n The number of the image to be of
     * @return the sprite of the number image
     * @throws IllegalArgumentException if n is out of the scope of the 
     *      number array
     */
    public BufferedImage getNumber (int n) {
        if (n < 0 || n >= number.length) {
            throw new IllegalArgumentException();
        }
        return number[n];
    }
    
    /**
     * Gets the sprite of an uncovered mine.
     * @return the sprite of an uncovered mine.
     */
    public BufferedImage getMine () {
        return getMine(0);
    }
    
    /**
     * Gets the sprite of a mine.
     * @param n The index of the type if mine to get. 0 for an uncovered mine, 
     *      1 for a incorrectly guessed mine and 2 for a detonated mine.
     * @return the sprite of an uncovered, incorrectly guessed mine or a
     *      detonated mine.
     */
    public BufferedImage getMine (int n) {
        if (n == 0) {
            return mine;
        } else if (n == 1) {
            return wrongMine;
        } else if (n == 2) {
            return detonatedMine;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Gets the image of a covered block.
     * @return image of covered block
     */
    public BufferedImage getBlock () {
        return getBlock(0);
    }
    
    /**
     * Gets the image of a covered block that has been flagged or has a 
     * question mark;
     * @return image of covered block
     */
    public BufferedImage getBlock (int n) {
        if (n < 0 || n >= block.length) {
            throw new IllegalArgumentException();
        }
        return block[n];
    }
}
