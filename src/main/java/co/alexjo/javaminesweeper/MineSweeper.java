package co.alexjo.javaminesweeper;

import co.alexjo.javaminesweeper.field.Minefield;
import co.alexjo.javaminesweeper.graphics.MineSweeperGraphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Creates a new game of MineSweeper.
 * 
 * MineSweeper
 *      |
 *      |__Minefield
 *      |       |__Square
 *      |
 *      |__MineSweeperGraphics
 *      |       |__SpriteCropper
 *      |
 *      |__JFrame
 * 
 * @author Alex Johnson
 */
public class MineSweeper implements MouseListener, Runnable{
    
    /** The width of the frame */
    public int FRAME_WIDTH = 600;
    
    /** The height of the frame */
    public int FRAME_HEIGHT = 600;
    
    /** The height of the title bar */
    public final int TITLE_BAR_SIZE = 22;
    
    /** The MineSweeper JFrame */
    private JFrame frame;
    
    /** The minefield for the game */
    private Minefield minefield;
    
    /** The graphics of the game */
    private MineSweeperGraphics graphics;
    
    /** Loop Thread */
    private Thread loop;
    
    /**
     * Starts the JavaMineSweeper application
     * @param args The arguments from the command line
     */
    public static void main(String[] args) {
        MineSweeper game = new MineSweeper(20, 20, 30);
    }
    
    /**
     * Constructs an instance of the MineSweeper game
     */
    public MineSweeper (int width, int height, int mines) {
        frame = new JFrame("JavaMineSweeper");
        frame.setBounds(0, 0, width * 16, height * 16 + TITLE_BAR_SIZE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(width * 16, height * 16 + TITLE_BAR_SIZE);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
		
	minefield = new Minefield(width, height, mines);
        graphics = new MineSweeperGraphics("minesprites.png", frame);
        
        loop = new Thread(this);
        loop.start();
    }
    
    /**
     * Override Thread abstract method run. The main game loop.
     */
    @Override
    public void run() {
        boolean running = true; 
        while (running) {
            
            graphics.repaint(minefield, frame);
            
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        quit();
    }
    
    /**
     * Quits the application
     */
    public void quit () {
        frame.dispose();
        loop.interrupt(); // possibly could work
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
