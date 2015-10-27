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
public class Ball {
    private int x;
    private int y;
    private int size;
    
    public Ball(int x, int y, int size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int getSize()
    {
        return this.size;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
}
