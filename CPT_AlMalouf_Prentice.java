import java.awt.*;
import hsa.Console;

public class CPT_AlMalouf_Prentice
{
    static Console c, d;
    public static void main (String[] args)
    {
	c = new Console (190, 150);
	char startInput = '.';
	if (makeTitleScreen ())
	{
	    
	}
	
    }


    public static char makeTitleScreen ()
    {
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	int x = 300, y = 150, dx = 1, dy = -1;
	char input;

	while (true)
	{
	    //this section contains the graphics that creates the cannon
	    c.setColor (new Color (171, 149, 132));
	    c.fillRect (0, 0, c.maxx (), c.maxy ());
	    c.fillRect (0, 700, c.maxx (), c.maxy ());
	    c.setColor (Color.black);
	    c.fillPolygon (pointx, pointy, 4);
	    c.fillOval (115, 315, 100, 100);
	    c.setColor (Color.yellow);
	    c.fillRect (130, 420, 100, 30);
	    c.fillRect (170, 360, 15, 80);

	    //this section contains all the draw string methods that print the game title and others
	    Font newFont = new Font ("Arial", Font.BOLD + Font.ITALIC, 100);
	    c.setFont (newFont);
	    c.setColor (Color.red);
	    c.drawString ("Cannon Launch", 240, 400);
	    Font newFont2 = new Font ("optima regular", Font.BOLD + Font.ITALIC, 30);
	    c.setFont (newFont2);
	    c.setColor (Color.black);
	    c.drawString ("Layya Al Malouf & Ian Prentice", 50, 800);
	    c.drawString ("Press enter to begin", 450, 450);
	    c.print (" ");
	    input = c.readChar ();

	    for (int i = 0 ; i < 100000 ; i++)

		{
		    c.setColor (Color.white);
		    c.fillOval (x, y, 50, 50);
		    if (y < 25)
		    {
			c.setColor (new Color (171, 149, 132));
			c.fillOval (x, y, 50, 50);
			break;
		    }
		    delay (30);
		    c.setColor (new Color (171, 149, 132));
		    c.fillOval (x, y, 50, 50);
		    x = x + 10;
		    y = y - 5;

		}
	    return input;
	    
	}
    }


    public static void delay (int time)
    {
	try
	{
	    Thread.sleep (time);
	}

	catch (InterruptedException e)
	{
	}
    }
    
    public static void Cannon ()
    {
	
    }
}


