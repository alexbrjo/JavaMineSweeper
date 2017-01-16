package co.alexjo.javaminesweeper.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the SpriteCropper class
 * @author Alex Johnson
 */
public class SpriteCropperTest {
    
    /**
     * Compares a BufferedImage with a image file. Returns false quickly if
     * the sizes are not equal.
     * @param filePath The path to the image file in the recourses folder
     * @param sprite2 The BufferedImage of the second sprite
     * @return if the images are equal
     * @throws IllegalArguementException if the file doesn't exist or 
     * BufferedImage is null, or if the filePath is null or the empty String
     */
    private boolean compareImages (String filePath, BufferedImage sprite2) {
        
        if (filePath == null || filePath.equals("")) {
            throw new IllegalArgumentException("File path needs to be a String with at least 1 character");
        }
        
        BufferedImage sprite1 = null;
        File imageFile = null;
        
        try {
            imageFile = new File(getClass().getClassLoader().getResource(filePath).getFile());
            sprite1 = ImageIO.read(imageFile) ;
        } catch (IOException ex) {
            throw new IllegalArgumentException("File must exist");
        }
        
        // Only compare pixels if size is equal
        if (sprite1.getWidth() == sprite2.getWidth() && 
                sprite1.getHeight() == sprite2.getHeight()) {
            int w = sprite1.getWidth();
            int h = sprite1.getHeight();
            
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (sprite1.getRGB(i, j) != sprite2.getRGB(i, j)) {
                        return false;
                    }
                }
            }
        
            // if all iterations of pixel comparision don't reture false, 
            // images are equals and return true
            return true;
        }
        
        return false;
    }
    
    /**
     * Tests the getNumber method, of class SpriteCropper
     */
    @Test
    public void testGetNumber () {
        SpriteCropper sc = new SpriteCropper("minesprites.png");
        BufferedImage image = null;
        
        // test all valid int parameters of getNumber
        try {
            assertTrue(compareImages("number_0.png", sc.getNumber(0)));
            assertTrue(compareImages("number_1.png", sc.getNumber(1)));
            assertTrue(compareImages("number_2.png", sc.getNumber(2)));
            assertTrue(compareImages("number_3.png", sc.getNumber(3)));
            assertTrue(compareImages("number_4.png", sc.getNumber(4)));
            assertTrue(compareImages("number_5.png", sc.getNumber(5)));
            assertTrue(compareImages("number_6.png", sc.getNumber(6)));
            assertTrue(compareImages("number_7.png", sc.getNumber(7)));
            assertTrue(compareImages("number_8.png", sc.getNumber(8)));
            assertTrue(compareImages("number_9.png", sc.getNumber(9)));
        } catch (Exception e) {
            fail();
        }
        
        // Test one below border value
        image = null;
        try {
            image = sc.getNumber(-1);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
        
        // Test one past border value
        image = null;
        try {
            image = sc.getNumber(10);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
    }
    
    /**
     * Tests the getBlock method, of class SpriteCropper
     */
    @Test
    public void testGetBlock () {
        SpriteCropper sc = new SpriteCropper("minesprites.png");
        BufferedImage image = null;
        
        // test all valid int parameters of getBlock
        try {
            assertTrue(compareImages("block.png", sc.getBlock(0)));
            assertTrue(compareImages("block_question.png", sc.getBlock(1)));
            assertTrue(compareImages("block_flagged.png", sc.getBlock(2)));
        } catch (Exception e) {
            fail();
        }
        
        // Test one below border value
        image = null;
        try {
            image = sc.getBlock(-1);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
        
        // Test one past border value
        image = null;
        try {
            image = sc.getBlock(3);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
    }
    
    /**
     * Tests the getMine method, of class SpriteCropper
     */
    @Test
    public void testGetMine () {
        SpriteCropper sc = new SpriteCropper("minesprites.png");
        BufferedImage image = null;
        
        // test all valid int parameters of getMine
        try {
            assertTrue(compareImages("mine.png", sc.getMine(0)));
            assertTrue(compareImages("mine_incorrect.png", sc.getMine(1)));
            assertTrue(compareImages("mine_origin.png", sc.getMine(2)));
        } catch (Exception e) {
            fail();
        }
        
        
        // Test one below border value
        image = null;
        try {
            image = sc.getMine(-1);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
        
        // Test one past border value
        image = null;
        try {
            image = sc.getMine(3);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
    }
    
    /**
     * Tests the getFace method, of class SpriteCropper
     */
    @Test
    public void testGetFace () {
        SpriteCropper sc = new SpriteCropper("minesprites.png");
        BufferedImage image = null;
        
        // test all valid int parameters of getFace
        try {
            assertTrue(compareImages("face_pressed.png", sc.getFace(0)));
            assertTrue(compareImages("face_smile.png", sc.getFace(1)));
            assertTrue(compareImages("face_ohno.png", sc.getFace(2)));
            assertTrue(compareImages("face_dead.png", sc.getFace(3)));
            assertTrue(compareImages("face_win.png", sc.getFace(4)));
        } catch (Exception e) {
            fail();
        }
        
        // Test one below border value
        image = null;
        try {
            image = sc.getFace(-1);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
        
        // Test one past border value
        image = null;
        try {
            image = sc.getFace(5);
            fail();
        } catch (IllegalArgumentException e){
            assertNull(image);
        }
    }
    
}
