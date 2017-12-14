import java.awt.*;
import hsa.Console;

public class CPT_AlMalouf_Prentice
{
    static Console c, d;
    public static void main (String[] args)
    {
	c = new Console (190, 150);
	char startInput = '.';
	makeBallMove ();
	makeTitleScreen (startInput);
	if (startInput == ' ')
	{
	    levelOne ();
	}


    }


    public static void makeTitleScreen (char input)
    {
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	int x = 260, y = 200, dx = 1, dy = -1;

	//this section contains the graphics that creates the cannon
	c.setColor (new Color (171, 149, 132));
	c.fillRect (0, 0, c.maxx (), c.maxy ());
	c.setColor (Color.black);
	c.fillPolygon (pointx, pointy, 4);
	c.fillOval (115, 315, 100, 100);
	c.setColor (Color.yellow);
	c.fillRect (130, 420, 100, 30);
	c.fillRect (170, 360, 15, 80);

	//this section contains all the draw string methods that print the game title and others
	Font newFont = new Font ("optima regular", Font.BOLD + Font.ITALIC, 100);
	c.setFont (newFont);
	c.setColor (Color.red);
	c.drawString ("Cannon Launch", 240, 400);
	Font newFont2 = new Font ("optima regular", Font.BOLD + Font.ITALIC, 30);
	c.setFont (newFont2);
	c.setColor (Color.black);
	c.drawString ("Layya Al Malouf & Ian Prentice", 50, 800);
	//c.drawString ("Press enter to begin", 450, 450);
	//c.print (" ");
	//input = c.readChar ();

	while (true)
	{
	    for (int i = 0 ; i < 100000 ; i++)

		{
		    c.setColor (Color.white);
		    c.drawOval (x, y, 50, 50);
		    x += dx;
		    y += dy;
		    c.setColor (new Color (171, 149, 132));
		    c.drawOval (x, y, 50, 50);

		    if (y == 500)
		    {
			y += -dy;
		    }
		}
	}
	//return input;

    }


    public static void makeBallMove ()
    {
	int xpoint = 150, ypoint = 100;

    }


    public static void levelOne ()
    {
	d.println ("level one");
    }
}
