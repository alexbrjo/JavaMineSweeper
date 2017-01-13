package co.alexjo.javaminesweeper.field.graphics;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Generates MineSweeper sprites from a single spritesheet.
 * @author Alex Johnson
 */
public class SpriteCropper {
    
    private File spriteSheet;
    
    private BufferedImage[] clearBlock;
    
    private BufferedImage[] number;
    
    private BufferedImage[] face;
    
    private BufferedImage mine;
    
    private BufferedImage wrongMine;
    
    private BufferedImage detonatedMine;
    
    private BufferedImage block;
    
    public SpriteCropper (File spriteSheet) {}
    
    public BufferedImage getClearBlock () {return null;}
    
    public BufferedImage getClearBlock (int adjacentMines) {return null;}
    
    public BufferedImage getNumber (int n) {return null;}
    
    public BufferedImage getMine () {return null;}
    
    public BufferedImage getMine (int n) {return null;}
    
}
