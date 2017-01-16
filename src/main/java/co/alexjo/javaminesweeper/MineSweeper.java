package co.alexjo.javaminesweeper;

import co.alexjo.javaminesweeper.field.Minefield;
import co.alexjo.javaminesweeper.graphics.MineSweeperGraphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
public class MineSweeper implements MouseListener, MouseMotionListener, Runnable{
    
    /** The width of the frame */
    public int FRAME_WIDTH = 100;
    
    /** The height of the frame */
    public int FRAME_HEIGHT = 100;
    
    /** The settings of the Minesweeper game. Width, height and number of mines */
    public int[] SETTINGS;
    
    /** The height of the title bar */
    public final int TITLE_BAR_SIZE = 72;
    
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
        MineSweeper game = new MineSweeper(30, 30, 100);
    }
    
    /**
     * Constructs an instance of the MineSweeper game
     * @param width The width of the minefield
     * @param height The height of the minefield
     * @param mines The number of mines to generate
     */
    public MineSweeper (int width, int height, int mines) {
        FRAME_WIDTH = width * 16;
        FRAME_HEIGHT = height * 16;
        SETTINGS = new int[]{width, height, mines}; 
        
        frame = new JFrame("JavaMineSweeper");
        frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT + TITLE_BAR_SIZE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT + TITLE_BAR_SIZE);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
		
	startGame();
    }
    
    /**
     * Starts or restarts the game for a width, height and number of mines.
     */
    public void startGame () {
        minefield = new Minefield(SETTINGS[0], SETTINGS[1], SETTINGS[2]);
        graphics = new MineSweeperGraphics("minesprites.png");
        
        loop = new Thread(this);
        loop.start();
    }
    
    /**
     * Stops the game
     */
    public void stopGame () {
        loop.interrupt(); 
    }
    
    /**
     * Quits the application
     */
    public void quit () {
        stopGame();
        frame.dispose();
    }
    
    /**
     * Override Thread abstract method run. The main game loop.
     */
    @Override
    public void run() {
        boolean running = true;
        
        // paints the initial game field
        graphics.paintMinefield(minefield, frame);
        graphics.repaint(minefield, frame);
        
        while (running) {
            // If there is an error stop running
            try {
                graphics.repaint(minefield, frame);
            } catch (Exception e) {
                running = false;
            }
            
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        quit();
    }

    
    /**
     * 
     * @param x The x position of the mouse
     * @param y The y position of the mouse
     * @param key The key, if any, that was pressed/released
     * @param pressed If the mouse button was pressed
     * @param released If the mouse button was released
     */
    public void mouseUpdate (int x, int y, int key, boolean pressed, boolean released) {
        int coordX = (x - (x % 16)) / 16;
        int coordY = ((y - TITLE_BAR_SIZE) - ((y - TITLE_BAR_SIZE) % 16)) / 16;
        
        if (x > 0 && y > TITLE_BAR_SIZE && x < FRAME_WIDTH && y < FRAME_HEIGHT + TITLE_BAR_SIZE) {
            minefield.click(coordX, coordY, key, pressed, released);
            minefield.setButton(false);
        } else if (y > 0 && y < TITLE_BAR_SIZE) {
            if (pressed) {
                minefield.setButton(true);
            } else if (released && minefield.button()) {
                stopGame();
                startGame();
            }
        }
    } 
    
    @Override
    public void mousePressed(MouseEvent e) {
        mouseUpdate(e.getX(), e.getY(), e.getButton(), true, false);
        
        graphics.paintMinefield(minefield, frame);
        graphics.repaint(minefield, frame);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseUpdate(e.getX(), e.getY(), e.getButton(), false, true);
        
        graphics.paintMinefield(minefield, frame);
        graphics.repaint(minefield, frame);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseUpdate(e.getX(), e.getY(), e.getButton(), false, false);
        
        graphics.paintMinefield(minefield, frame);
        graphics.repaint(minefield, frame);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
}
