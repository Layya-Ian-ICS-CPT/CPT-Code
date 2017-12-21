import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
//yuh
public class CPT_AlMalouf_Prentice extends Applet implements KeyListener, MouseListener
{

    int keyCode, menuSelect = 0;

    Button menuStart, nextLevel, restart;
    boolean ifShoot = true;
    BufferedImage[] img = new BufferedImage [8];
    public void init ()
    {
	getImage ();
	this.setLayout (null);
	menuStart = new Button ("Press to begin");
	menuStart.setBounds (470, 450, 250, 50);
	nextLevel = new Button ("Next Level");
	nextLevel.setBounds (500, 500, 100, 40);
	restart = new Button ("Restart");
	restart.setBounds (590, 500, 100, 40);
	add (menuStart);
	add (nextLevel);
	add (restart);
	menuStart.addMouseListener (this);
	nextLevel.addMouseListener (this);
	restart.addMouseListener (this);
	addKeyListener (this);
	addMouseListener (this);
	setFocusable (true);
    }


    public void paint (Graphics g)
    {
	if (menuSelect == 0)
	{
	    remove (nextLevel);
	    remove (restart);
	    makeTitleScreen (g);
	}
	if (menuSelect == 1)
	{
	    remove (menuStart);
	    levelOne (g);
	}
	if (menuSelect == 2)
	{
	    //levelTwo ();
	}

    }


    public void makeTitleScreen (Graphics g)
    {
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	int x = 300, y = 150, dx = 1, dy = -1;
	char input;

	//this section contains the graphics that creates the cannon
	g.setColor (new Color (171, 149, 132));
	g.fillRect (0, 0, 1200, 800);
	g.fillRect (0, 700, 1200, 800);
	g.setColor (Color.black);
	g.fillPolygon (pointx, pointy, 4);
	g.fillOval (115, 315, 100, 100);
	g.setColor (Color.yellow);
	g.fillRect (130, 420, 100, 30);
	g.fillRect (170, 360, 15, 80);

	//this section contains all the draw string methods that print the game title and others
	Font newFont = new Font ("verdana", Font.BOLD + Font.PLAIN, 100);
	g.setFont (newFont);
	g.setColor (Color.red);
	g.drawString ("Cannon Launch", 240, 400);
	Font newFont2 = new Font ("verdana", Font.BOLD + Font.ITALIC, 30);
	g.setFont (newFont2);
	g.setColor (Color.black);
	g.drawString ("Layya Al Malouf & Ian Prentice", 50, 750);
	g.drawString ("ICS 3U1", 50, 800);

	while (true)
	{
	    g.setColor (Color.white);
	    g.fillOval (x, y, 50, 50);
	    if (y < 25)
	    {
		g.setColor (new Color (171, 149, 132));
		g.fillOval (x, y, 50, 50);
		break;
	    }
	    delay (30);
	    g.setColor (new Color (171, 149, 132));
	    g.fillOval (x, y, 50, 50);
	    x = x + 10;
	    y = y - 5;
	}
    }


    public void levelOne (Graphics g)
    {
	levelBackground (g);
	drawTarget (g);
	drawStar (g, 330, 325);
	drawStar (g, 610, 325);
	drawStar (g, 890, 325);
	drawCannon (50, 250, 250, g);


    }


    public void levelBackground (Graphics g)
    {
	g.setColor (new Color (152, 218, 255));
	g.fillRect (0, 0, 1200, 800);

	g.setColor (Color.white);
	g.fillOval (180, 150, 150, 60);
	g.fillOval (200, 130, 60, 100);
	g.fillOval (250, 130, 60, 100);
	g.setColor (Color.white);
	g.fillOval (520, 230, 150, 60);
	g.fillOval (540, 210, 60, 100);
	g.fillOval (590, 210, 60, 100);
	g.setColor (Color.white);
	g.fillOval (850, 90, 150, 60);
	g.fillOval (870, 70, 60, 100);
	g.fillOval (920, 70, 60, 100);

	g.setColor (new Color (0, 153, 0));
	g.fillArc (0, 600, 500, 250, 0, 180);
	g.fillArc (350, 650, 500, 150, 0, 180);
	g.fillArc (700, 620, 700, 450, 0, 180);
	g.setColor (new Color (0, 204, 0));
	g.fillRect (0, 700, 1200, 100);

    }


    public void drawTarget (Graphics g)
    {
	g.setColor (Color.red);
	g.fillOval (1090, 310, 80, 80);
	g.setColor (Color.white);
	g.fillOval (1103, 323, 55, 55);
	g.setColor (Color.red);
	g.fillOval (1115, 335, 30, 30);
    }


    public void drawStar (Graphics g, int xStar, int yStar)
    {
	int[] starXPoint = {xStar + 25, xStar + 40, xStar + 0, xStar + 50, xStar + 10};
	int[] starYPoint = {yStar + 0, yStar + 50, yStar + 20, yStar + 20, yStar + 50};

	g.setColor (new Color (255, 240, 18));
	g.fillPolygon (starXPoint, starYPoint, 5);
	g.fillOval (xStar + 15, yStar + 18, 20, 21);
    }


    public void getImage ()
    {
	String[] links = {"http://i.imgur.com/h4KxyQU.png",
	    "http://i.imgur.com/EwZOz7N.png",
	    "http://i.imgur.com/lbpcUy2.png",
	    "http://i.imgur.com/z3ZkqgE.png",
	    "http://i.imgur.com/qH6cBgJ.png",
	    "http://i.imgur.com/4Wt9OIu.png",
	    "http://i.imgur.com/hMOsH1Q.png",
	    "http://i.imgur.com/zWIWL05.png"};

	for (int j = 0 ; j < img.length ; j++)
	{
	    try
	    {
		URL url = new URL (links [j]);
		img [j] = ImageIO.read (url);
	    }
	    catch (IOException e)
	    {

	    }

	}

    }


    public void drawCannon (int xCannon, int yCannon, int delayTime, Graphics g)
    {
	for (int m = 0 ; m < img.length ; m++)
	{
	    g.drawImage (img [m], xCannon, yCannon, null);
	    delay (delayTime);
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (xCannon, yCannon, 200, 200);
	    if (!ifShoot)
	    {
		break;
	    }
	    if (m == 7)
	    {
		m = -1;
	    }
	}
    }


    public void delay (int time)
    {
	try
	{
	    Thread.sleep (time);
	}

	catch (InterruptedException e)
	{
	}
    }


    public void keyTyped (KeyEvent k)
    {

    }


    public void keyPressed (KeyEvent k)
    {
	keyCode = k.getKeyCode ();
	if (keyCode == 32)
	{
	    ifShoot = false;
	    repaint ();
	}            

    }


    public void keyReleased (KeyEvent k)
    {

    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseClicked (MouseEvent e)
    {
	if (e.getSource () == menuStart)
	{
	    menuSelect++;
	}
	if (e.getSource () == nextLevel)
	{
	    menuSelect += menuSelect;
	}
	if (e.getSource () == restart)
	{
	    menuSelect = menuSelect;
	}
	repaint ();
    }
}
