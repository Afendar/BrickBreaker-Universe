package BBU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author portable mickael
 */
public class Game extends JFrame {
    
    private Board board;
    private JPanel info;
    private Image bgInfo;
    
    public Game(String title, int width, int height)
    {
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        
        board = new Board(810, this.getHeight());
        board.tball.start();
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
}
