package BBU;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author portable mickael
 */
public class CustomBtn extends Component
{
    private Image img;
    
    public CustomBtn(Image background)
    {
        super();
        this.img = background;
        this.setPreferredSize(new Dimension(this.img.getWidth(this), this.img.getHeight(this)));
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(this.img, 0, 0, this);
    }
    
    public void setImage(Image background)
    {
        this.img = background;
    }
}
