/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BBU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author portable mickael
 */
public class Board extends JPanel implements Runnable {
    private Ball ball;
    private long timing;
    private int ballX, ballY;
    private Player player;
    private int playerWidth;
    private int playerX, playerY;
    public Thread tball;
    private boolean isAnimate = false;
    private boolean returnX, returnY;
    private boolean isDead = false;
    private Brick mat[][];
    private int [][] data;
    private boolean inLife = true;
    private long diff;
    private int nbBricks, width, height, score, life, level;
    private Image pinkBrick, darkGreyBrick, purpleBrick, brownBrick, greyBrick,
            blueBrick, redBrick, greenBrick, yellowBrick, orangeBrick, darkBlueBrick, infos, iball;
    private BufferedImage spritesheet;
    private Font f, f1;
    private int moveX;
    
    public Board(int width, int height){
        super();
        nbBricks = 0;
        score = 0;
        life = 3;
        level = 2;
        moveX = 5;
        
        f = new Font("Courier", Font.ITALIC + Font.BOLD, 25);
        f1 = new Font("Courier", Font.ITALIC + Font.BOLD, 16);
        
        tball = new Thread(this, "tball");
        tball.setPriority(Thread.MIN_PRIORITY);
        
        ball = new Ball(width/2, height - 75, 12);
        player = new Player((width / 2) - 30, height - 60, 120, 13);
        
        this.width = width;
        this.height = height;
        
        this.setLayout(new BorderLayout());
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(isAnimate == false)
                {
                    isAnimate = true;
                }
            }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                player.setX(e.getX() - (player.getWidth() / 2));
            }
        });
        
        mat = new Brick[18][18];
        data = new int[18][18];
        
        loadLevel(level);
        
        try{
            spritesheet = ImageIO.read(new File("gfx/spritesheet.png"));
            infos = ImageIO.read(new File("gfx/panneauInfo.png"));
            
            this.pinkBrick = spritesheet.getSubimage(45, 20, 45, 20);
            this.brownBrick = spritesheet.getSubimage(180, 0, 45, 20);
            this.darkGreyBrick = spritesheet.getSubimage(180, 20, 45, 20);
            this.purpleBrick = spritesheet.getSubimage(135, 0, 45, 20);
            this.greyBrick = spritesheet.getSubimage(135, 20, 45, 20);
            this.blueBrick = spritesheet.getSubimage(0, 0, 45, 20);
            this.redBrick = spritesheet.getSubimage(45, 0, 45, 20);
            this.greenBrick = spritesheet.getSubimage(90, 0, 45, 20);
            this.yellowBrick = spritesheet.getSubimage(225, 0, 45, 20);
            this.orangeBrick = spritesheet.getSubimage(0, 20, 45, 20);
            this.darkBlueBrick = spritesheet.getSubimage(90, 20, 45, 20);
            this.iball = spritesheet.getSubimage(2, 256, 12, 12);
        }
        catch(IOException e){e.printStackTrace();}
        
        JPanel p = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                int x = 20;
                int y = 20;
                int i = 1;
                
                g.drawImage(infos, 0, 0, this);
                while(i < 4)
                {
                    if(i <= life)
                    {
                        g.drawImage(iball, x, y, this);
                    }
                    x += 40;
                    i++;
                }
                g.setFont(f1);
                g.drawString("Briques Restantes :", 27, 78);
                g.drawString("Score :", 27, 154);
                g.drawString("Niveau :", 27, 230);
                g.setFont(f);
                g.drawString(""+nbBricks, 40, 106);
                g.drawString(""+score, 40, 183);
                g.drawString(""+level, 40, 259);
            }
        };
        p.setPreferredSize(new Dimension(198, this.getHeight()));
        this.add(p, BorderLayout.EAST);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if(!isDead)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, 824, 768);
            g.drawImage(this.iball, ball.getX(), ball.getY(), this);
            g.setColor(new Color(146, 170, 174));
            g.fill3DRect(player.getX(), player.getY(), player.getWidth(), player.getHeight(), true);
            for(int i=0;i<18;i++)
            {
                for(int j=0;j<18;j++)
                {
                        switch(data[i][j])
                        {
                            case 1:
                                g.drawImage(this.blueBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 2:
                                g.drawImage(this.redBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 3:
                                g.drawImage(this.greenBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 4:
                                g.drawImage(this.purpleBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 5:
                                g.drawImage(this.brownBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 6:
                                g.drawImage(this.yellowBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 7:
                                g.drawImage(this.orangeBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 8:
                                g.drawImage(this.pinkBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 9:
                                g.drawImage(this.darkBlueBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 10:
                                g.drawImage(this.greyBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                            case 11:
                                g.drawImage(this.darkGreyBrick, mat[i][j].getX(), mat[i][j].getY(), this);
                                break;
                        }
                }
            }
        }
        else
        {
            g.setFont(f);
            g.setColor(new Color(51,0,102));
	    g.drawString("Perdu !!!", width / 2, height / 2);
        }
    }
    
    @Override
    public void run() 
    {
        while(inLife)
        {
            timing = new Date().getTime();
             
            if(diff > 16)
            {
                diff  -= 16;
                display();
            }
            else
            {
                try 
                {
                    tball.sleep(3);
                } 
                catch(InterruptedException e){}
            }
            diff += (new Date().getTime()- timing);
        }
    }
    
    public void loadLevel(int lvl)
    {
        String line;
        String levelPath = "levels/level"+lvl+".lvl";
        File dataLevel = new File(levelPath);
        try
        {
            BufferedReader in;
            in = new BufferedReader(new FileReader(dataLevel));
            line = in.readLine();
            while(line != null)
            {
                int x = 0,y = 0;
                for(int i=0;i<18;i++)
                {
                    x = 0;
                    for(int j=0;j<18;j++)
                    {
                        int val = (new Integer(line.substring(j,j+1))).intValue();
                        if(val != 0)
                        {
                            mat[i][j] = new Brick(x,y);
                            data[i][j] = val;
                            nbBricks++;
                        }
                        x += 45;
                    }
                    y += 20;
                    line = in.readLine();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void display()
    {
        ballX = ball.getX(); 
        ballY = ball.getY();
        playerX = player.getX();
        playerY = player.getY();
        playerWidth = player.getWidth();
        
        if(this.isAnimate)
        {  
            if(ballY >= playerY + ball.getSize() && returnY == false) //alors on a perdu la balle
            {
                if(life > 0)
                {
                    Sound.loose.play();
                    life--;
                    ball.setX(width / 2);
                    ball.setY(height - 75);
                    player.setX((width / 2) - 40);
                    player.setY(height - 60);
                    isAnimate = false;
                }
                else
                {
                    isAnimate = false;
                    isDead = true;
                }
            }
            
            //si l'on arrive à la limite gauche de l'écran
            if (ballX <= ball.getSize() / 2)
            {
                //on retourne vers la droite
                returnX = false;
            }
            
            //si l'on arrive à la limite de la partie droite de l'écran
            if (ballX >= width - ball.getSize())
            {
                //on retourne vers la gauche
                returnX = true;
            }
            
            //si l'on arrive à la limite de la partie haute de l'écran
            if (ballY <= ball.getSize()/2)
            {
                //on retourne vers le bas
                returnY = false;
            }
            
            //si on est sur le joueur
            if ((ballY > playerY - ball.getSize()) && (ballX <= playerX + player.getWidth()) && (ballX >= playerX))
            {
                Sound.player.play();
                //si l'on est dans la première extremité du joueur
                if(((ballX >= playerX) && (ballX <= (playerX + (playerWidth/6)))) || (ballX >= playerX+(5*(playerWidth/6)) && ballX <= playerX + playerWidth))
                {
                    moveX = 7;
                }
                else if(((ballX >= playerX + playerWidth / 6) && (ballX <= (playerX + (playerWidth/3)))) || (ballX >= playerX + ( 2 * ( playerWidth / 3)) && ballX <= playerX+(5*( playerWidth / 6))))
                {
                    moveX = 6;
                }
                else
                {
                    moveX = 5;
                }
                returnY = true; //on retourne dans lautre sens en y
            }
            
            if (ballY >= height - ball.getSize())
            {
                returnY = true;
            }
            
            for(int i=0;i<18;i++)
            {
                for(int j=0;j<18;j++)
                {
                    if(mat[i][j] != null)
                    {
                        if((returnY == false) && (ballX >= mat[i][j].getX()) && (ballX <= (mat[i][j].getX()+45)) && (ballY >= mat[i][j].getY()-ball.getSize()/2) && (ballY <= mat[i][j].getY()+ball.getSize()/2))
                        {
                            Sound.brick.play();
                            returnY = true;
                            mat[i][j] = null;
                            data[i][j] = 0;
                            nbBricks --;
                            score += 10;
                        }
                        else if((returnY == true) && (ballX >= mat[i][j].getX()) && (ballX <= (mat[i][j].getX()+45)) && (ballY >= mat[i][j].getY()+20-(ball.getSize()/2)) && (ballY <= mat[i][j].getY()+20+ball.getSize()/2))
                        {
                            Sound.brick.play();
                            returnY = false;
                            mat[i][j] = null;
                            data[i][j] = 0;
                            nbBricks--;
                            score += 10;
                        }
                        else if((returnX == false) && (ballY >= mat[i][j].getY()) && (ballY <= (mat[i][j].getY()+20)) && (ballX >= mat[i][j].getX()-ball.getSize()/2) && (ballX <= mat[i][j].getX()+ball.getSize()/2))
                        {
                            Sound.brick.play();
                            returnX = true;
                            mat[i][j] = null;
                            data[i][j] = 0;
                            nbBricks--;
                            score += 10;
                        }
                        else if((returnX == true) && (ballY >= mat[i][j].getY()) && (ballY <= (mat[i][j].getY()+20)) && (ballX >= mat[i][j].getX()+45-(ball.getSize()/2)) && (ballX <= mat[i][j].getX()+45+ball.getSize()/2))
                        {
                            Sound.brick.play();
                            returnX = false;
                            mat[i][j] = null;
                            data[i][j] = 0;
                            nbBricks--;
                            score += 10;
                        }
                    }
                }
            }
        }
        if(isAnimate == true)
        {
            if (returnX)
            {
                ball.setX(ballX-moveX);
            }
            else
            {
                ball.setX(ballX+moveX);
            }
            if (returnY)
            {
                ball.setY(ballY-5);
            }
            else
            {
                ball.setY(ballY+5);
            }
        }
        else
        {
            ball.setX(player.getX()+player.getWidth()/2-ball.getSize()/2);
        }
        
        if(nbBricks == 0)
        {
            level++;
            loadLevel(level);
            isAnimate = false;
            ball.setX(width/2);
            ball.setY(height-75);
            player.setX((width/2)-40);
            player.setY(height-60);
        }
        repaint();
    }
}
