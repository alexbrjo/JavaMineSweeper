package co.alexjo.javaminesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class MineSweeper implements MouseListener{
    
    public final int FRAME_WIDTH = 600;
    
    public final int FRAME_HEIGHT = 600;
    
    private JFrame frame;
    
    public static void main(String[] args) {}
    
    public MineSweeper () {}
    
    public void quit () {}

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
