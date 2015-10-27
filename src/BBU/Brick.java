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
public class Brick {
    private int x, y;
    private int width = 45;
    private int height = 20;
    
    public Brick(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int[] getCoords()
    {
        int z[] = new int[2];
        z[0] = this.x;
        z[1] = this.y;
        return z;
    }
}
