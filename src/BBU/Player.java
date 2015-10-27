/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BBU;

/**
 *
 * @author portable mickael
 */
public class Player {
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Player(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public int getWidth()
    {
        return this.width;
    }
    public int getHeight()
    {
        return this.height;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
}
