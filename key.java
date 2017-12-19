import java.awt.event.*;
import java.applet.*;
import java.awt.*;
public class key extends Applet implements KeyListener
{
    int keyCode;
    public void init()
    {
	addKeyListener(this);
	addMouseListener (this);
	setFocusable(true);
    }
    
    public void paint(Graphics g)
    {
	
	
    }
    
    public void keyTyped (KeyEvent k)
    {
	
    }
    
    public void keyPressed (KeyEvent k)
    {
	keyCode = k.getKeyCode();
	repaint();
    
    }
    public void keyReleased (KeyEvent k)
    {
    
    }
}
