package BBU;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author portable mickael
 */
public class MainMenu extends JFrame
{
    private static CustomBtn btn1, btn2, btn3, btn4;
    private static Image img1, img1Selected, img2, img2Selected, img3, img3Selected, img4, img4Selected, background;
    private static JPanel container;
    
    public MainMenu(String title, int width, int height)
    {
        super();
        this.setTitle(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("gfx/logo.png");
        this.setIconImage(icon);
        
        loadAssets();
        
        container = new JPanel(new GridBagLayout())
        {
            @Override
            public void paintComponent(Graphics g)
            {
                g.drawImage(background, 0, 0, null);
            }
        };
        
        btn1 = new CustomBtn(img1);
        btn2 = new CustomBtn(img2);
        btn3 = new CustomBtn(img3);
        btn4 = new CustomBtn(img4);
        
        createMenu();
        
        addMouseMotionListener();
        addMouseListener();
    }
    
    private void loadAssets()
    {
        try{
            background = ImageIO.read(new File("gfx/background.png"));
            
            img1 = ImageIO.read(new File("gfx/nvPartie.png"));
            img1Selected = ImageIO.read(new File("gfx/nvPartieSelect.png"));
            
            //Images bouton option
            img2 = ImageIO.read(new File("gfx/option.png"));
            img2Selected = ImageIO.read(new File("gfx/optionSelect.png"));
            
            //images bouton quitter
            img3 = ImageIO.read(new File("gfx/quitter.png"));
            img3Selected = ImageIO.read(new File("gfx/quitterSelect.png"));
            
            img4 = ImageIO.read(new File("gfx/credits.png"));
            img4Selected = ImageIO.read(new File("gfx/creditsSelect.png"));
        }
        catch(IOException e){}
    }
    
    private void createMenu()
    {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(200,10,10,10);
        c.gridwidth = 2;
        
        c.gridx = 1;
        c.gridy = 1;
        container.add(btn1, c);
        
        c.insets = new Insets(10,10,10,10);
        c.gridy = 2;
        container.add(btn2, c);
        
        c.gridwidth = 1;
        c.gridy = 3;
        container.add(btn4, c);
        
        c.gridx = 2;
        container.add(btn3, c);
        
        this.add(container);
        this.setVisible(true);
    }
    
    private void addMouseMotionListener()
    {
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();
                if(x >= btn1.getX() && x <= btn1.getX() + btn1.getWidth() && y >= btn1.getY() && y <= btn1.getY() + btn1.getHeight())
                {
                    btn1.setImage(img1Selected);
                }
                else if(x >= btn2.getX() && x <= btn2.getX() + btn2.getWidth() && y >= btn2.getY() && y <= btn2.getY() + btn2.getHeight())
                {
                    btn2.setImage(img2Selected);
                }
                else if(x >= btn3.getX() && x <= btn3.getX() + btn3.getWidth() && y >= btn3.getY() && y <= btn3.getY() + btn3.getHeight())
                {
                    btn3.setImage(img3Selected);
                }
                else if(x >= btn4.getX() && x <= btn4.getX() + btn4.getWidth() && y >= btn4.getY() && y <= btn4.getY() + btn4.getHeight())
                {
                    btn4.setImage(img4Selected);
                }
                else
                {
                    btn1.setImage(img1);
                    btn2.setImage(img2);
                    btn3.setImage(img3);
                    btn4.setImage(img4);
                }
                container.repaint();
            }
        });
    }
    
    private void addMouseListener()
    {
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int x = e.getX();
                int y = e.getY();
                
                if(x >= btn1.getX() && x <= btn1.getX()+ btn1.getWidth() && y >= btn1.getY() && y <= btn1.getY()+ btn1.getHeight())
                {
                    Game g = new Game(getTitle(), getWidth(), getHeight());
                    dispose();
                }
                else if(x >= btn2.getX() && x <= btn2.getX()+btn2.getWidth() && y >= btn2.getY() && y <= btn2.getY()+btn2.getHeight())
                {
                    
                }
                else if(x >= btn3.getX() && x <= btn3.getX()+btn3.getWidth() && y >= btn3.getY() && y <= btn3.getY()+btn3.getHeight())
                {
                    dispose();
                }
            }
        });
    }
}
