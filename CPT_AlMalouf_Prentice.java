import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

public class CPT_AlMalouf_Prentice extends Applet implements KeyListener, MouseListener
{

    int keyCode = 0, menuSelect = 0, frameCounter = 0, x = 0, y = 0, cannonSelector = 0, starCounter = 0, resetCounter = 0, ifHit = 0, pastStars = 0;
    Button menuStart;
    boolean ifShoot = false, ifTravelled = false;
    BufferedImage[] img = new BufferedImage [8];
    Font starFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 20);

    public void init ()
    {
	getImage ();
	this.setLayout (null);
	menuStart = new Button ("Press to begin");
	menuStart.setBounds (470, 450, 250, 50);
	add (menuStart);
	menuStart.addMouseListener (this);
	addKeyListener (this);
	addMouseListener (this);
	setFocusable (true);
    }


    public void paint (Graphics g)
    {
	if (menuSelect == 0)
	{
	    makeTitleScreen (g);
	}
	if (menuSelect == 1)
	{
	    if (resetCounter == 0)
	    {
		valueReset ();
		cannonSelector = 0;
		ifHit = 0;
		resetCounter++;
	    }
	    remove (menuStart);
	    levelOne (g);

	}
	if (menuSelect == 2)
	{
	    endLevel (g);

	}
	if (menuSelect == 3)
	{
	    remove (menuStart);
	    if (resetCounter == 1)
	    {
		valueReset ();
		cannonSelector = 0;
		ifHit = 0;
		resetCounter++;
	    }
	    levelTwo (g);
	}
	if (menuSelect == 4)
	{
	    endLevel (g);

	}
	if (menuSelect == 5)
	{
	    if (resetCounter == 2)
	    {
		valueReset ();
		cannonSelector = 0;
		ifHit = 0;
		resetCounter++;
	    }
	    levelThree (g);
	}
	if (menuSelect == 6)
	{
	    endLevel (g);

	}
	if (menuSelect == 7)
	{
	if (resetCounter == 3)
	    {
		valueReset ();
		cannonSelector = 0;
		ifHit = 0;
		resetCounter++;
	    }
	    levelFour (g);

	}
	if (menuSelect == 8)
	{
	    endGame (g);
	}

    }


    public void makeTitleScreen (Graphics g)
    {
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	int xTitle = 300, yTitle = 150, dx = 1, dy = -1;
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
	Font newFont = new Font ("verdana", Font.BOLD, 100);
	g.setFont (newFont);
	g.setColor (Color.red);
	g.drawString ("Cannon Launch", 240, 400);
	Font newFont2 = new Font ("verdana", Font.PLAIN, 30);
	g.setFont (newFont2);
	g.setColor (Color.black);
	g.drawString ("Layya Al Malouf & Ian Prentice", 50, 750);
	g.drawString ("ICS 3U1 CPT", 50, 800);

	while (true)
	{
	    g.setColor (Color.white);
	    g.fillOval (xTitle, yTitle, 50, 50);
	    if (yTitle < 25)
	    {
		g.setColor (new Color (171, 149, 132));
		g.fillOval (xTitle, yTitle, 50, 50);
		break;
	    }
	    delay (30);
	    g.setColor (new Color (171, 149, 132));
	    g.fillOval (xTitle, yTitle, 50, 50);
	    xTitle = xTitle + 10;
	    yTitle = yTitle - 12;
	}
    }


    public void starsCollected (Graphics g)
    {
	Font starFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 20);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Stars Collected: " + starCounter, 0, 50);
    }


    public void endLevel (Graphics g)
    {
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};

	g.setColor (new Color (255, 204, 229));
	g.fillRect (0, 0, 1200, 800);
	Font levelFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 80);
	g.setFont (levelFont);
	g.setColor (Color.white);
	g.drawString ("Level Completed!", 280, 350);
	Font levelFont2 = new Font ("verdana", Font.BOLD + Font.ITALIC, 50);
	g.setFont (levelFont2);
	g.setColor (new Color (96, 96, 96));
	g.drawString ("Stars Collected: " + starCounter, 370, 420);
	Font levelFont3 = new Font ("verdana", Font.PLAIN, 25);
	g.setFont (levelFont3);
	g.drawString ("Press the left arrow key to retry the level", 345, 700);
	g.drawString ("Press the right arrow key to go to the next level", 300, 750);
	g.setColor (Color.black);
	g.fillPolygon (pointx, pointy, 4);
	g.fillOval (115, 315, 100, 100);
	g.setColor (Color.yellow);
	g.fillRect (130, 420, 100, 30);
	g.fillRect (170, 360, 15, 80);

	if (keyCode == 37)
	{
	    resetCounter--;
	    starCounter = pastStars;
	    menuSelect--;
	}
	if (keyCode == 39)
	{
	    pastStars = starCounter;
	    menuSelect++;
	}

    }


    public void endGame (Graphics g)
    {

	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	int xTitle = 300, yTitle = 150, dx = 1, dy = -1;
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
	Font newFont = new Font ("verdana", Font.BOLD, 100);
	g.setFont (newFont);
	g.setColor (Color.red);
	g.drawString ("Game Over!", 240, 400);
	Font newFont2 = new Font ("verdana", Font.PLAIN, 30);
	g.setFont (newFont2);
	g.setColor (Color.black);
	g.drawString ("Congratulations! You have completed all the levels!", 50, 750);
	g.drawString ("Stay tuned for more levels to come!", 50, 800);

	while (true)
	{
	    g.setColor (Color.white);
	    g.fillOval (xTitle, yTitle, 50, 50);
	    if (yTitle < 25)
	    {
		g.setColor (new Color (171, 149, 132));
		g.fillOval (xTitle, yTitle, 50, 50);
		break;
	    }
	    delay (30);
	    g.setColor (new Color (171, 149, 132));
	    g.fillOval (xTitle, yTitle, 50, 50);
	    xTitle = xTitle + 10;
	    yTitle = yTitle - 12;
	}
    }


    public void levelOne (Graphics g)
    {
	int[] xOfStar = {330, 610, 890};
	int[] yOfStar = {325, 325, 325};
	int[] xOfCannon = {50}, yOfCannon = {250};
	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level One", 0, 20);
	starsCollected (g);
	drawTarget (g);
	boolean failedLevel = false;

	if (ifHit == 0)
	{
	    drawStar (g, xOfStar [0], yOfStar [0]);
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}
	else if (ifHit == 1)
	{
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}
	else if (ifHit == 2)
	{
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}

	if (cannonSelector == 0)
	{

	    drawCannon (50, 250, 250, g);
	}
	if (ifShoot)
	{
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (50, 250, 200, 200);
	    g.drawImage (img [frameCounter], 50, 250, null);
	    cannonSelector++;
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, g);

	}
	if (failedLevel)
	{
	    failScreen (g);
	}
	repaint ();
    }


    public void levelTwo (Graphics g)
    {
	boolean failedLevel = false;
	int[] xOfCannon = {80, 330, 580};
	int[] yOfCannon = {250, 0, 250};
	int[] xOfStar = {275, 525, 835};
	int[] yOfStar = {200, 200, 325};
	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Two", 0, 20);
	starsCollected (g);

	g.setColor (Color.black);
	g.fillRect (400, 270, 60, 200);
	//g.fillRect(400,100,100,40);
	drawTarget (g);
	if (ifHit == 0)
	{
	    drawStar (g, xOfStar [0], yOfStar [0]);
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}
	else if (ifHit == 1)
	{
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}
	else if (ifHit == 2)
	{
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}

	if (cannonSelector == 0)
	{
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    drawCannon (xOfCannon [0], yOfCannon [0], 220, g);
	}
	else if (cannonSelector == 1)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    drawCannon (xOfCannon [1], yOfCannon [1], 220, g);
	}
	else if (cannonSelector == 2)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    drawCannon (xOfCannon [2], yOfCannon [2], 220, g);
	}
	if (ifShoot)
	{
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (xOfCannon [cannonSelector], yOfCannon [cannonSelector], 200, 200);
	    g.drawImage (img [frameCounter], xOfCannon [cannonSelector], yOfCannon [cannonSelector], null);
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, g);
	    //ifShoot = false;
	}
	if (failedLevel)
	{
	    failScreen (g);
	}

	//drawObstacle (g, 850, 200);
	repaint ();
    }


    public void levelThree (Graphics g)
    {
	boolean failedLevel = false;
	int[] xOfCannon = {0, 200, 230, 450, 780};
	int[] yOfCannon = {250, 0, 500, 250, 250};
	int[] xOfStar = {430, 430, 695};
	int[] yOfStar = {200, 445, 325};

	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Three", 0, 20);
	starsCollected (g);

	g.setColor (Color.black);
	g.fillRect (690, 100, 60, 200);
	g.fillRect (690, 410, 60, 200);
	g.fillRect (300, 250, 60, 170);
	drawTarget (g);

	if (ifHit == 0)
	{
	    drawStar (g, xOfStar [0], yOfStar [0]);
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);

	}
	else if (ifHit == 1)
	{
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);

	}
	else if (ifHit == 2)
	{
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}


	if (cannonSelector == 0)
	{
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    drawCannon (xOfCannon [0], yOfCannon [0], 200, g);
	}
	else if (cannonSelector == 1)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    drawCannon (xOfCannon [1], yOfCannon [1], 200, g);
	}
	else if (cannonSelector == 2)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    drawCannon (xOfCannon [2], yOfCannon [2], 200, g);
	}
	else if (cannonSelector == 3)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    drawCannon (xOfCannon [3], yOfCannon [3], 200, g);
	}
	else if (cannonSelector == 4)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    drawCannon (xOfCannon [4], yOfCannon [4], 200, g);
	}

	if (ifShoot)
	{
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (xOfCannon [cannonSelector], yOfCannon [cannonSelector], 200, 200);
	    g.drawImage (img [frameCounter], xOfCannon [cannonSelector], yOfCannon [cannonSelector], null);
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, g);
	    //ifShoot = false;
	}

	if (failedLevel)
	{
	    failScreen (g);
	}
	//drawObstacle (g, 980, 200);
	repaint ();
    }

public void levelFour (Graphics g)
    {
	boolean failedLevel = false;
	int[] xOfCannon = {0, 0, 0, 440, 440, 840, };
	int[] yOfCannon = {80, 320, 560, 80, 560, 250};
	int[] xOfStar = {475, 0, 0};
	int[] yOfStar = {395, 0, 100};

	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Four", 0, 20);
	starsCollected (g);

	g.setColor (Color.black);
	g.fillRect (200, 80, 60, 100);
	g.fillRect (200, 560, 60, 100);
	drawTarget (g);

	if (ifHit == 0)
	{
	    drawStar (g, xOfStar [0], yOfStar [0]);
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}
	else if (ifHit == 1)
	{
	    drawStar (g, xOfStar [1], yOfStar [1]);
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}
	else if (ifHit == 2)
	{
	    drawStar (g, xOfStar [2], yOfStar [2]);
	}

	if (cannonSelector == 0)
	{
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    drawCannon (xOfCannon [0], yOfCannon [0], 200, g);
	}
	else if (cannonSelector == 1)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    drawCannon (xOfCannon [1], yOfCannon [1], 200, g);
	}
	else if (cannonSelector == 2)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    drawCannon (xOfCannon [2], yOfCannon [2], 200, g);
	}
	else if (cannonSelector == 3)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    drawCannon (xOfCannon [3], yOfCannon [3], 200, g);
	}
	else if (cannonSelector == 4)
	{
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    drawCannon (xOfCannon [4], yOfCannon [4], 200, g);
	}
	else if (cannonSelector == 5)
	{
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    drawCannon (xOfCannon [5], yOfCannon [5], 200, g);
	}
	if (ifShoot)
	{
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (xOfCannon [cannonSelector], yOfCannon [cannonSelector], 200, 200);
	    g.drawImage (img [frameCounter], xOfCannon [cannonSelector], yOfCannon [cannonSelector], null);
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, g);
	    ifShoot = false;
	}

	if (failedLevel)
	{
	    failScreen (g);
	}
	//drawObstacle (g, 980, 200);
	repaint ();
    }

    public void levelBackground (Graphics g)
    {
	g.setColor (new Color (152, 218, 255));
	g.fillRect (0, 0, 1200, 800);
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


    public void drawObstacle (Graphics g, int xObstacle, int yObstacle)
    {

	int dy = 2;

	while (true)
	{
	    g.setColor (Color.black);
	    g.fillRect (xObstacle, yObstacle, 40, 100);
	    delay (5);
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (xObstacle, yObstacle, 40, 100);
	    yObstacle += dy;
	    if (yObstacle > 540 || yObstacle < 100)
	    {
		dy = -dy;
	    }
	}
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
	Color filler = new Color (152, 218, 255);


	if (!ifShoot)
	{
	    g.drawImage (img [frameCounter], xCannon, yCannon, null);
	    delay (delayTime - 0);
	    if (keyCode != 32)
	    {
		g.setColor (filler);
		g.fillRect (xCannon, yCannon, 200, 200);
		frameCounter++;
		if (frameCounter == 8)
		{
		    frameCounter = 0;
		}
	    }
	    else
	    {
		if (frameCounter == 0)
		{
		    frameCounter = 8;
		}
		frameCounter--;
		ifShoot = true;

	    }
	    x = xCannon + 75;
	    y = yCannon + 75;

	}
    }


    public boolean projectile (int[] xStars, int[] yStars, int[] xCannons, int[] yCannons, Graphics g)
    {
	int[] dxValues = {0, 1, 1, 1, 0, -1, -1, -1};
	int[] dyValues = { - 1, -1, 0, 1, 1, 1, 0, -1};
	int delayTime = 0;
	boolean ifFailed = false, ifItemHit = false;

	while (ifTravelled == false)
	{
	    x += dxValues [frameCounter];
	    y += dyValues [frameCounter];
	    g.setColor (Color.black);
	    g.fillOval (x, y, 50, 50);
	    delay (3);
	    g.setColor (new Color (152, 218, 255));
	    g.fillOval (x, y, 50, 50);
	    for (int i = 0 ; i < xCannons.length ; i++)
	    {
		if (x == xCannons [i]+75) //&& x < xCannons[i] + 200)
		{
		    if (y == yCannons[i] + 75)
		    {
			valueReset ();
			cannonSelector = i;
			ifItemHit = true;
			break;

		    }
		}
		
	    }

	    for (int j = 0 ; j < xStars.length ; j++)
	    {
		if (x == xStars [j]) //x > xStars [j] - 25 && x < xStars[j] + 25)
		{
		    if (y < yStars [j] + 25 && y > yStars [j] - 25)
		    {
			starCounter++;
			ifHit = j + 1;
			ifItemHit = true;
			break;

		    }
		}
	    }
	    if (ifItemHit)
	    {
		break;
	    }
	    
	    if (x > 1040)
	    {
		if (y >= 310 && y <= 390)
		{
		    menuSelect++;
		    ifTravelled = true;
		    break;
		}
		else
		{
		    ifFailed = true;
		    ifTravelled = true;
		    break;
		}
	    }
	    else if (x < 0)
	    {
		ifFailed = true;
		ifTravelled = true;
		break;
	    }
	    else if (y > 750 || y < 0)
	    {
		ifFailed = true;
		ifTravelled = true;
		break;
	    } //yuh


	}


	return ifFailed;
    }


    public void failScreen (Graphics g)
    {
	g.setColor (Color.white);
	g.fillRect (400, 350, 400, 100);
	g.setColor (Color.black);
	g.setFont (new Font ("verdana", Font.BOLD, 30));
	g.drawString ("YOU HAVE FAILED", 450, 410);
	delay (1000);
	cannonSelector = 0;
	frameCounter = 0;
	x = 0;
	y = 0;
	ifHit = 0;
	ifShoot = false;
	ifTravelled = false;
	starCounter = pastStars;
	keyCode = 0;

    }


    public void valueReset ()
    {
	frameCounter = 0;
	x = 0;
	y = 0;
	//cannonSelector = 0;
	keyCode = 0;
	//ifHit = 0;
	ifShoot = false;
	ifTravelled = false;

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
	repaint ();

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


	repaint ();
    }
}




