import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

public class CPT_AlMalouf_Prentice extends Applet implements KeyListener, MouseListener
{

    int keyCode = 0, menuSelect = 0, frameCounter = 0, x = 0, y = 0, cannonSelector = 0, starCounter = 0, resetCounter = 0;
    int ifHit = 0;
    int pastStars = 0, totalStars = 0, numLives = 3, numFails = 0;
    Button menuStart;
    boolean ifShoot = false, ifTravelled = false;
    BufferedImage[] img = new BufferedImage [8];
    Font starFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 20);
    Font birdTalk = new Font ("verdana", Font.BOLD, 15);

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
		numLives = 3;
		starCounter = 0;
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
		numFails = 0;
		ifHit = 0;
		numLives = 3;
		starCounter = 0;
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
		numLives = 3;
		numFails = 0;
		starCounter = 0;
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
		numFails = 0;
		numLives = 3;
		starCounter = 0;
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

	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 50);
	g.setColor (Color.black);
	Font birdTalk = new Font ("verdana", Font.BOLD, 15);
	g.setFont (birdTalk);
	g.drawString ("Tweet, tweet!", 810, 670);
	g.drawString ("Click anywhere on the screen!", 755, 695);
	drawBird (g);

	//this section contains all the draw string methods that print the game title and others
	Font newFont = new Font ("verdana", Font.BOLD, 90);
	g.setFont (newFont);
	g.setColor (new Color (178, 34, 34));
	g.drawString ("CANNON LAUNCH", 240, 400);
	Font newFont3 = new Font ("verdana", Font.PLAIN, 30);
	g.setFont (newFont3);
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
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Stars Collected: " + starCounter, 990, 50);
	totalStars += starCounter;
    }


    public void livesRemaining (Graphics g)
    {
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Lives Remaining: " + numLives, 980, 80);
    }


    public void drawBird (Graphics g)
    {
	int[] beakX = {1090, 1105, 1120, 1105};
	int[] beakY = {730, 720, 730, 745};

	g.setColor (new Color (190, 0, 190));
	g.fillOval (1035, 705, 20, 50);
	g.fillOval (1155, 705, 20, 50);

	g.setColor (new Color (139, 0, 130));
	g.fillOval (1042, 645, 125, 130);

	g.setColor (Color.white);
	g.fillOval (1110, 680, 35, 40);
	g.fillOval (1060, 680, 35, 40);

	g.setColor (new Color (0, 191, 255));
	g.fillOval (1115, 690, 25, 25);
	g.fillOval (1065, 690, 25, 25);

	g.setColor (Color.black);
	g.fillOval (1119, 695, 16, 16);
	g.fillOval (1069, 695, 16, 16);

	g.setColor (Color.white);
	g.fillOval (1128, 693, 8, 8);
	g.fillOval (1078, 693, 8, 8);

	g.setColor (Color.orange);
	g.fillPolygon (beakX, beakY, 4);
	g.fillOval (1119, 770, 20, 10);
	g.fillOval (1069, 770, 20, 10);

    }


    public void endLevel (Graphics g)
    {
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};

	totalStars = pastStars + starCounter;

	g.setColor (new Color (255, 204, 229));
	g.fillRect (0, 0, 1200, 800);
	drawBird (g);
	drawLevelStars (g);

	Font levelFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 80);
	g.setFont (levelFont);
	g.setColor (Color.white);
	g.drawString ("Level Completed!", 280, 350);

	Font levelFont2 = new Font ("verdana", Font.BOLD + Font.ITALIC, 40);
	g.setFont (levelFont2);
	g.drawString ("Stars Collected In This Level: " + starCounter, 300, 420);

	Font levelFont3 = new Font ("verdana", Font.BOLD + Font.ITALIC, 30);
	g.setFont (levelFont3);
	g.setColor (new Color (96, 96, 96));
	g.drawString (" Total Stars Collected: " + totalStars, 370, 520);

	Font levelFont4 = new Font ("verdana", Font.PLAIN, 25);
	g.setFont (levelFont4);
	g.drawString ("Press the left arrow key to retry the level", 100, 700);
	g.drawString ("Press the right arrow key to go to the next level", 100, 750);

	g.setColor (Color.white);
	g.fillRect (740, 650, 285, 50);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Nice Job!", 830, 670);
	g.drawString ("Retry levels to collect more coins!", 745, 695);

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


    public void drawLevelStars (Graphics g)
    {

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
	Font birdTalk = new Font ("verdana", Font.PLAIN, 30);
	g.setFont (birdTalk);
	g.setColor (Color.black);
	g.drawString ("Congratulations! You have completed all the levels!", 250, 750);
	g.drawString ("Stay tuned for more levels to come!", 100, 800);
	drawBird (g);

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
	int[] fillerOne = { - 50};
	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level One", 1050, 20);

	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Welcome friend!", 810, 670);
	g.drawString ("Press the spacebar to shoot!", 755, 695);

	starsCollected (g);
	livesRemaining (g);
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
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, fillerOne, fillerOne, g);

	}
	if (failedLevel)
	{
	    numFails++;
	    numLives--;
	    failScreen (g);
	    if (numFails == 3)
	    {
		menuSelect = 1;
		g.setColor (Color.white);
		g.fillRect (740, 650, 270, 55);
		g.setColor (Color.black);
		g.setFont (birdTalk);
		g.drawString ("Oh no! I only have 3 lives!", 780, 675);
		g.drawString ("Back to level one :(", 800, 695);
		delay (2500);
		menuSelect = 1;
	    }
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
	int[] xOfObstacle = {350, 800};
	int[] yOfObstacle = {220, 150};

	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Two", 1050, 20);
	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Watch out for the obstacles!", 770, 675);
	g.drawString ("If you hit one you lose a life!", 765, 695);
	starsCollected (g);
	livesRemaining (g);

	g.setColor (Color.black);
	g.fillRect (400, 270, 60, 200);
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
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, xOfObstacle, yOfObstacle, g);
       }

	if (failedLevel)
	{
	    numFails++;
	    numLives--;
	    failScreen (g);
	    if (numFails == 3)
	    {
		menuSelect = 1;
		g.setColor (Color.white);
		g.fillRect (740, 650, 270, 55);
		g.setColor (Color.black);
		g.setFont (birdTalk);
	       g.drawString ("Oh no! I only have 3 lives!", 780, 675);
		g.drawString ("Back to level one :(", 800, 695);
		delay (2500);
		menuSelect = 1;
	    }
	}

	repaint ();
    }



    public void levelThree (Graphics g)
    {
	boolean failedLevel = false;
	int[] xOfCannon = {0, 250, 250, 500, 775};
	int[] yOfCannon = {250, 0, 500, 250, 250};
	int[] xOfStar = {450, 450, 700};
	int[] yOfStar = {200, 450, 325};
	int[] xOfObstacle = {275, 275, 650, 650, 650, 650};
	int[] yOfObstacle = {200, 300, 50, 150, 360, 460};

	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Three", 1050, 20);
	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Watch out for the obstacles!", 770, 675);
	g.drawString ("This level is tricky!", 765, 695);
	starsCollected (g);
	livesRemaining (g);

	g.setColor (Color.black);
	g.fillRect (700, 100, 50, 100);
	g.fillRect (700, 200, 50, 100);
	g.fillRect (700, 410, 50, 100);
	g.fillRect (700, 510, 50, 100);
	g.fillRect (325, 250, 50, 100);
	g.fillRect (325, 350, 50, 100);
	//drawObstacle (g, 980, 200);
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
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, xOfObstacle, yOfObstacle, g);
	}

	if (failedLevel)
	{
	    numFails++;
	    numLives--;
	    failScreen (g);
	    if (numFails == 3)
	    {
		menuSelect = 1;
		g.setColor (Color.white);
		g.fillRect (740, 650, 270, 55);
		g.setColor (Color.black);
		g.setFont (birdTalk);
	       g.drawString ("Oh no! I only have 3 lives!", 780, 675);
		g.drawString ("Back to level one :(", 800, 695);
		delay (2500);
		menuSelect = 1;
	    }
	}
	repaint ();
    }


    public void levelFour (Graphics g)
    {
	boolean failedLevel = false;
	int[] xOfCannon = {0, 0, 0, 480, 480, 720};
	int[] yOfCannon = {10, 250, 490, 10, 490, 250};
	int[] xOfStar = {555, 690, 1000};
	int[] yOfStar = {325, 445, 325};
	int[] xOfObstacle = {200, 200, 200, 200};
	int[] yOfObstacle = {0, 50, 450, 550};

	levelBackground (g);
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Four", 1050, 20);
	g.setColor (Color.white);
	g.fillRect (740, 650, 290, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Wow! You made it to the last level!", 750, 675);
	g.drawString ("You must be really good!", 785, 695);
	starsCollected (g);
	livesRemaining (g);

	g.setColor (Color.black);
	g.fillRect (250, 0, 50, 100);
	g.fillRect (250, 100, 50, 100);
	g.fillRect (250, 500, 50, 100);
	g.fillRect (250, 600, 50, 100);

	//drawObstacle (g, 365, 200);
	//drawObstacle (g, 900, 200);
	//drawObstacle (g, 580, 200);
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
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    drawCannon (xOfCannon [5], yOfCannon [5], 200, g);
	}

	if (ifShoot)
	{
	    g.setColor (new Color (152, 218, 255));
	    g.fillRect (xOfCannon [cannonSelector], yOfCannon [cannonSelector], 200, 200);
	    g.drawImage (img [frameCounter], xOfCannon [cannonSelector], yOfCannon [cannonSelector], null);
	    failedLevel = projectile (xOfStar, yOfStar, xOfCannon, yOfCannon, xOfObstacle, yOfObstacle, g);
	}

	if (failedLevel)
	{
	    numFails++;
	    numLives--;
	    failScreen (g);
	    if (numFails == 3)
	    {
		menuSelect = 1;
		g.setColor (Color.white);
		g.fillRect (740, 650, 270, 55);
		g.setColor (Color.black);
		g.setFont (birdTalk);
	       g.drawString ("Oh no! I only have 3 lives!", 780, 675);
		g.drawString ("Back to level one :(", 800, 695);
		delay (2500);
	    }
	}
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
	drawBird (g);

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


    public int drawObstacle (Graphics g, int xObstacle, int yObstacle)
    {

	int dyObstacle = 1;

	g.setColor (new Color (152, 218, 255));
	g.fillRect (xObstacle, yObstacle, 50, 100);
	g.setColor (Color.black);
	g.fillRect (xObstacle, yObstacle, 50, 100);


	yObstacle += dyObstacle;

	if (yObstacle > 540 || yObstacle < 100)
	{
	    dyObstacle = -dyObstacle;
	}
	return yObstacle;
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
	    x = xCannon;
	    y = yCannon;

	}
    }


    public boolean projectile (int[] xStars, int[] yStars, int[] xCannons, int[] yCannons, int[] xObstacles, int[] yObstacles, Graphics g)
    {
	int[] dxValues = {0, 1, 1, 1, 0, -1, -1, -1};
	int[] dyValues = { - 1, -1, 0, 1, 1, 1, 0, -1};
	int[] startxLocation = {75, 175, 175, 125, 75, 25, -25, -25};
	int[] startyLocation = {0, -25, 75, 125, 150, 125, 75, -25};
	boolean ifFailed = false, ifItemHit = false;

	for (int q = 0 ; q < xCannons.length ; q++)
	{
	    if (x == xCannons [q])
	    {
		if (y == yCannons [q])
		{
		    x += startxLocation [frameCounter];
		    y += startyLocation [frameCounter];
		}
	    }
	}

	while (ifTravelled == false)
	{
	    x += dxValues [frameCounter];
	    y += dyValues [frameCounter];
	    g.setColor (new Color (255, 255, 153));
	    g.fillOval (x, y, 50, 50);

	    delay (4);
	    g.setColor (new Color (152, 218, 255));
	    g.fillOval (x, y, 50, 50);
	    for (int i = 0 ; i < xCannons.length ; i++)
	    {
		if (x == xCannons [i] + 75)
		{
		    if (y == yCannons [i] + 75)
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
		if (x == xStars [j])
		{
		    if (y > yStars [j] - 25 && y < yStars [j] + 25)
		    {
			starCounter++;
			ifHit = j + 1;
			ifItemHit = true;
			break;

		    }
		}
	    }
	    for (int m = 0 ; m < yObstacles.length ; m++)
	    {
		if (x > xObstacles [m] && x < xObstacles [m] + 50)
		{

		    if (y > yObstacles [m] && y < yObstacles [m] + 100)
		    {
			ifFailed = true;
			ifTravelled = true;
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
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Oops! You failed!", 800, 675);
	g.drawString ("The level is going to restart!", 765, 695);
	delay (1500);
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
	keyCode = 0;
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







