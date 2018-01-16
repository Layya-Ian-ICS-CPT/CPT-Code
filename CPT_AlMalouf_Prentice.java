/* "Egg-scape" CPT
    Programmed by Layya Al Malouf and Ian Prentice from December to January 16 2017
    This program involves a series of cannons containing a bird egg, which allows 
    the user to shoot the egg from cannon to cannon with the goal of getting it 
    to the target without hitting any obstacles*/
import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
public class CPT_AlMalouf_Prentice extends Applet implements KeyListener, MouseListener
{
    //global variable declarations
    int keyCode = 0, menuSelector = 0, frameCounter = 0, x = 0, y = 0, cannonSelector = 0, starCounter = 0, resetCounter = 0;
    int ifHit = 0;
    int pastStars = 0, totalStars = 0, numLives, numOfdy, ifReversed = 1;
    int[] xOfStar = new int [3];
    int[] yOfStar = new int [3];
    Button menuStart;
    boolean ifShoot = false, ifTravelled = false, ifDoneRotating = false;
    BufferedImage[] img = new BufferedImage [8];
    Font starFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 20);
    Font birdTalk = new Font ("verdana", Font.BOLD, 15);
    public void init ()
    {
	//initializing buttons, key listener, and mouse listener
	getImage ();
	this.setLayout (null);
	menuStart = new Button ("Press to begin");
	menuStart.setBounds (470, 450, 250, 50);
	add (menuStart);
	menuStart.addMouseListener (this);
	addKeyListener (this);
	addMouseListener (this);
	setFocusable (true);
    } //end of init method


    public void paint (Graphics g)
    {
	//each menuSelector value is connected to a certain screen in the game which will print when the paint method runs
	if (menuSelector == 0) //opening title screen
	{
	    makeTitleScreen (g);
	    numLives = 3;
	} //end of opening screen if statement
	if (menuSelector == 1) //level one
	{
	    if (resetCounter == 0)
	    {
		//resets the values of variables required for the level
		valueReset (true);
		//coordinates of the 3 stars
		xOfStar [0] = 330;
		xOfStar [1] = 610;
		xOfStar [2] = 890;
		yOfStar [0] = 325;
		yOfStar [1] = 325;
		yOfStar [2] = 325;
		resetCounter++;
	    } //end of inner if statement
	    remove (menuStart); //remove start button
	    levelOne (g);
	} //end of level one if statement
	if (menuSelector == 2) //end of level screen
	{
	    endLevel (g);
	} //end of end level screen if statement
	if (menuSelector == 3) //level two
	{
	    remove (menuStart);
	    if (resetCounter == 1)
	    {
		//resets the values of variables required for the level
		valueReset (true);
		//coordinates of the 3 stars
		xOfStar [0] = 280;
		xOfStar [1] = 530;
		xOfStar [2] = 835;
		yOfStar [0] = 200;
		yOfStar [1] = 200;
		yOfStar [2] = 325;
		resetCounter++;
	    }
	    levelTwo (g);
	} //end of level two if statement
	if (menuSelector == 4) //end of level screen
	{
	    endLevel (g);
	} //end of end level screen if statement
	if (menuSelector == 5) //level three
	{
	    if (resetCounter == 2)
	    {
		//resets the values of variables required for the level
		valueReset (true);
		//coordinates of the 3 stars
		xOfStar [0] = 450;
		xOfStar [1] = 450;
		xOfStar [2] = 700;
		yOfStar [0] = 200;
		yOfStar [1] = 450;
		yOfStar [2] = 325;
		resetCounter++;
	    }
	    levelThree (g);
	} //end of level three if statement
	if (menuSelector == 6) //end of level
	{
	    endLevel (g);
	} //end of end level screen if statement
	if (menuSelector == 7) //level four
	{
	    if (resetCounter == 3)
	    {
		//resets the values of variables required for the level
		valueReset (true);
		//coordinates of the 3 stars
		xOfStar [0] = 555;
		xOfStar [1] = 700;
		xOfStar [2] = 1000;
		yOfStar [0] = 325;
		yOfStar [1] = 430;
		yOfStar [2] = 325;
		resetCounter++;
	    } //end  of level four if statement
	    levelFour (g);
	}
	if (menuSelector == 8) //end of game screen
	{
	    endGame (g);
	} //end of end game screen if statement
    } //end of paint method


    public void makeTitleScreen (Graphics g)
    {
	//initializing arrays and variables
	int[] pointxCannon = {250, 300, 200, 130};
	int[] pointyCannon = {200, 230, 400, 330};
	int xTitle = 300, yTitle = 150, dx = 1, dy = -1;
	char input;
	//background colour of the screen
	g.setColor (new Color (249, 205, 213));
	g.fillRect (0, 0, 1200, 800);
	//draws cannon
	g.setColor (Color.black);
	g.fillPolygon (pointxCannon, pointyCannon, 4);
	g.fillOval (115, 315, 100, 100);
	g.setColor (Color.yellow);
	g.fillRect (130, 420, 100, 30);
	g.fillRect (170, 360, 15, 80);
	//draws clouds
	g.setColor (Color.white);
	//cloud 1
	g.fillOval (120, 120, 150, 60);
	g.fillOval (140, 100, 60, 100);
	g.fillOval (190, 100, 60, 100);
	//cloud 2
	g.fillOval (520, 210, 150, 60);
	g.fillOval (540, 190, 60, 100);
	g.fillOval (590, 190, 60, 100);
	//cloud 3
	g.fillOval (870, 90, 150, 60);
	g.fillOval (890, 70, 60, 100);
	g.fillOval (940, 70, 60, 100);
	//this section contains all the draw string methods that print the game title and other text
	Font newFont = new Font ("VT323", Font.BOLD, 120);
	g.setFont (newFont);
	g.setColor (new Color (178, 34, 34));
	g.drawString ("EGG-SCAPE", 270, 400);
	Font newFont3 = new Font ("verdana", Font.PLAIN, 30);
	g.setFont (newFont3);
	g.setColor (Color.black);
	g.drawString ("Layya Al Malouf & Ian Prentice", 50, 750);
	g.drawString ("ICS 3U1 CPT", 50, 800);
	//draws speech box and speech of the bird
	g.setColor (Color.white);
	g.fillRect (620, 650, 420, 100);
	g.setFont (birdTalk);
	g.setColor (Color.black);
	g.drawString ("Tweet, tweet!", 810, 670);
	g.drawString ("This is my baby egg! I'm trusting you with it!", 635, 700);
	g.drawString ("Make sure you get my baby safely to the target!", 630, 720);
	g.drawString ("Try clicking anywhere on the screen!", 680, 745);
	drawBirdBody (g);
	birdWithEgg (g);
	//draws the moving cannon everytime the mouse is pressed
	while (true)
	{
	    //draws the ball
	    g.setColor (new Color (255, 255, 153));
	    g.fillOval (xTitle, yTitle, 50, 50);
	    if (yTitle < 25) //when the ball reaches a certain point on the screen it stops printing
	    {
		//redraws ball
		g.setColor (new Color (249, 205, 213));
		g.fillOval (xTitle, yTitle, 50, 50);
		break;
	    } //end of inner if statement
	    delay (30);
	    g.setColor (new Color (249, 205, 213));
	    g.fillOval (xTitle, yTitle, 50, 50);
	    xTitle = xTitle + 10;
	    yTitle = yTitle - 12;
	} //end of while statement
    } //end of makeTitleScreen method


    public void levelOne (Graphics g)
    {
	//initializing variables and arrays
	int[] xOfCannon = {50}, yOfCannon = {250};
	int[] fillerOne = { - 50}, fillerTwo = {0};
	boolean failedLevel = false;
	levelBackground (g);
	starsCollected (g);
	livesRemaining (g);
	//draws all the text that remains on the screen for the entire level
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level One", 1050, 20);
	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Welcome friend!", 810, 670);
	g.drawString ("Press the spacebar to shoot!", 755, 695);
	//draws the 3 stars
	drawStar (g, xOfStar [0], yOfStar [0]);
	drawStar (g, xOfStar [1], yOfStar [1]);
	drawStar (g, xOfStar [2], yOfStar [2]);
	

	//each cannonSelector value represents a certain cannon
	if (cannonSelector == 0) //first cannon
	{
	    //begins cannon rotation
	    drawCannon (50, 250, 250, g);
	} //end of if statement

	if (ifShoot) //if the spacebar is pressed
	{
	    //g.drawImage (img [frameCounter], xOfCannon [cannonSelector], yOfCannon [cannonSelector], null);
	    //returns a boolean variable "true" - level passed or "false" - failed
	    failedLevel = projectile (fillerTwo, xOfCannon, yOfCannon, fillerOne, fillerOne, g);
	    //no obstacle values so the filler array act as place holders
	} //end of ifShoot if statement
	if (failedLevel)
	{
	    //if the level is failed the number of lives is reduced by one
	    numLives--;
	    failScreen (g);
	    // if the number of lives reaches zero
	    if (numLives == 0)
	    {
		//speech and speech bubble of the bird
		failGame(g);
	    } //end of numLives if statement
	} //end of failedLevel if statement


	repaint ();
    } //end of levelOne method


    public void levelTwo (Graphics g)
    {
	//initializing variables and arrays
	boolean failedLevel = false;
	int[] xOfCannon = {80, 330, 580};
	int[] yOfCannon = {250, 0, 250};
	int[] xOfObstacle = {400, 900};
	int[] yOfObstacle = {300, 150};
	int[] movingObstacle = {1};
	//draws level background
	levelBackground (g);
	//draws all the text that remains on the screen for the entire level
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Two", 1050, 20);
	starsCollected (g);
	livesRemaining (g);
	//bird speech and speech bubble
	g.setColor (Color.white);
	g.fillRect (740, 650, 300, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Watch out for the obstacles!", 770, 675);
	g.drawString ("If you hit one my baby loses a life!", 750, 695);
	//draws moving obstacle
	yOfObstacle [1] = drawObstacle (g, 900, 150);
	//draws stationary obstacle
	g.setColor (Color.black);
	g.fillRect (400, 300, 50, 150);
	//draws the 3 methods
	drawStar (g, xOfStar [0], yOfStar [0]);
	drawStar (g, xOfStar [1], yOfStar [1]);
	drawStar (g, xOfStar [2], yOfStar [2]);
	

	if (cannonSelector == 0) //runs the first cannon
	{
	    //draws the stationary cannons
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    //begins cannon rotation of the first cannon
	    drawCannon (xOfCannon [0], yOfCannon [0], 220, g);
	} //end of cannonSelector0 if statement
	else if (cannonSelector == 1) //runs the second cannon
	{
	    //draws the stationary cannons
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    //begins cannon rotation of the second cannon
	    drawCannon (xOfCannon [1], yOfCannon [1], 220, g);
	} //end of cannonSelector1 if statement
	else if (cannonSelector == 2) //runs the third cannon
	{
	    //draws the stationary cannons
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    //begins cannon rotation
	    drawCannon (xOfCannon [2], yOfCannon [2], 220, g);
	} //end of cannonSelector2 if statement

	if (ifShoot) //if the spacebar is pressed
	{
	    //returns a boolean variable "true" - level passed or "false" - failed
	    failedLevel = projectile (movingObstacle, xOfCannon, yOfCannon, xOfObstacle, yOfObstacle, g);
	} //end of ifShoot if statement
	
	if (failedLevel)
	{
	    //if the level is failed the number of lives is reduced by one
	    numLives--;
	    failScreen (g);
	    // if the number of lives reaches zero
	    if (numLives == 0)
	    {
		failGame(g);
	    } //end of numLives if statement
	} //end of failedLevel if statement
	repaint ();
    } //end of levelTwo method


    public void levelThree (Graphics g)
    {
	//initializing variables and arrays
	boolean failedLevel = false;
	int[] xOfCannon = {0, 250, 250, 500, 775};
	int[] yOfCannon = {250, 0, 500, 250, 250};
	int[] xOfObstacle = {325, 700, 700, 1030};
	int[] yOfObstacle = {200, 50, 360, 200};
	int[] movingObstacle = {3};
	//draws level background
	levelBackground (g);
	//draws all the text that remains on the screen for the entire level
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Three", 1050, 20);
	starsCollected (g);
	livesRemaining (g);
	//birds speech and speech bubble
	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Watch out for the obstacles!", 770, 675);
	g.drawString ("This level is tricky!", 765, 695);
	//draws stationary obstacles
	g.setColor (Color.black);
	g.fillRect (700, 100, 50, 150);
	//g.fillRect (700, 200, 50, 100);
	g.fillRect (700, 410, 50, 150);
	//g.fillRect (700, 510, 50, 100);
	g.fillRect (325, 250, 50, 150);
	//g.fillRect (325, 350, 50, 100);
	//draws moving obstacles
	yOfObstacle[3] = drawObstacle (g, 1030, 200);
	//draws the 3 stars
	drawStar (g, xOfStar [0], yOfStar [0]);
	drawStar (g, xOfStar [1], yOfStar [1]);
	drawStar (g, xOfStar [2], yOfStar [2]);
	//each cannonSelector value represents a certain cannon
	if (cannonSelector == 0) //runs the first cannon
	{
	    //draws the image of 4 stationary cannons (2,3,4,5)
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    //begins rotation of the first cannon
	    drawCannon (xOfCannon [0], yOfCannon [0], 200, g);
	} //end of cannonSelector0 if statement
	else if (cannonSelector == 1) //runs the second cannon
	{
	    //draw the image of the stationary cannons (1,3,4,5)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    //begins cannon rotation of the second cannon
	    drawCannon (xOfCannon [1], yOfCannon [1], 200, g);
	    repaint ();
	} //end of cannonSelector1 if statement
	else if (cannonSelector == 2) //runs the third cannon
	{
	    //draw the image of the stationary cannons (1,2,4,5)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    //begins cannon rotation of the third cannon
	    drawCannon (xOfCannon [2], yOfCannon [2], 200, g);
	    repaint ();
	} //end of cannonSelector2 if statement
	else if (cannonSelector == 3)
	{
	    //draw the image of the stationary cannons (1,2,3,5)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    drawCannon (xOfCannon [3], yOfCannon [3], 200, g);
	    repaint ();
	} //end of cannonSelector3 if statement
	else if (cannonSelector == 4)
	{
	    //draw the image of the stationary cannons (1,2,3,4)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    //begins cannon rotation of the third cannon
	    drawCannon (xOfCannon [4], yOfCannon [4], 200, g);
	    repaint ();
	} //end of cannonSelector4 if statement
	if (ifShoot) //if the spacebar is pressed
	{
	    //returns a boolean variable "true" -> level failed or "false" -> level not failed
	    failedLevel = projectile (movingObstacle, xOfCannon, yOfCannon, xOfObstacle, yOfObstacle, g);
	} //end of ifShoot if statement
	if (failedLevel)
	{
	    //if the level is failed the number of lives is reduced by one
	    numLives--;
	    failScreen (g);
	    // if the number of lives reaches zero
	    if (numLives == 0)
	    {
		failGame(g);
	    } //end of numLives if statement
	} //end of failedLevel if statement
	repaint ();
    } //end of levelThree method


    public void levelFour (Graphics g)
    {
	//intializing variables and arrays
	boolean failedLevel = false;
	int[] xOfCannon = {0, 0, 0, 480, 480, 720};
	int[] yOfCannon = {10, 250, 490, 10, 490, 250};
	int[] xOfObstacle = {200, 200, 365, 900, 580};
	int[] yOfObstacle = {0, 450, 200, 200, 200};
	int[] movingObstacle = {2, 3, 4};
	//draws level background
	levelBackground (g);
	//draws all the text that remains on the screen for the entire level
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Level Four", 1050, 20);
	starsCollected (g);
	livesRemaining (g);
	//draws bird speech bubble and text
	g.setColor (Color.white);
	g.fillRect (740, 650, 290, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Wow! You made it to the last level!", 750, 675);
	g.drawString ("You must be really good!", 785, 695);
	//draws stationary obstacles
	g.setColor (Color.black);
	g.fillRect (250, 0, 50, 150);
	//g.fillRect (250, 100, 50, 100);
	g.fillRect (250, 500, 50, 150);
	//g.fillRect (250, 600, 50, 100);
	//draws moving obstacles
	yOfObstacle [2] = drawObstacle (g, 365, 200);
	yOfObstacle [3] = drawObstacle (g, 900, 200);
	yOfObstacle [4] = drawObstacle (g, 580, 200);
	//draws the 3 stars
	drawStar (g, xOfStar [0], yOfStar [0]);
	drawStar (g, xOfStar [1], yOfStar [1]);
	drawStar (g, xOfStar [2], yOfStar [2]);
	if (cannonSelector == 0)
	{
	    //draw the image of the stationary cannons (2,3,4,5,6)
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    //begins the rotations of the first cannon
	    drawCannon (xOfCannon [0], yOfCannon [0], 200, g);
	} //end of cannonSelector0 if statement
	else if (cannonSelector == 1)
	{
	    //draw the image of the stationary cannons (2,3,4,5,6)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    //begins the rotations of the second cannon
	    drawCannon (xOfCannon [1], yOfCannon [1], 200, g);
	} //end of cannonSelector1 if statement
	else if (cannonSelector == 2)
	{
	    //draw the image of the stationary cannons (2,3,4,5,6)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    //begins the rotations of the third cannon
	    drawCannon (xOfCannon [2], yOfCannon [2], 200, g);
	} //end of cannonSelector2 if statement
	else if (cannonSelector == 3)
	{
	    //draw the image of the stationary cannons (2,3,4,5,6)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    //begins the rotations of the fourth cannon
	    drawCannon (xOfCannon [3], yOfCannon [3], 200, g);
	} //end of cannonSelector3 if statement
	else if (cannonSelector == 4)
	{
	    //draw the image of the stationary cannons (2,3,4,5,6)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [5], yOfCannon [5], null);
	    //begins the rotations of the fifth cannon
	    drawCannon (xOfCannon [4], yOfCannon [4], 200, g);
	} //end of cannonSelector4 if statement
	else if (cannonSelector == 5)
	{
	    //draw the image of the stationary cannons (1,2,3,4,5)
	    g.drawImage (img [0], xOfCannon [0], yOfCannon [0], null);
	    g.drawImage (img [0], xOfCannon [1], yOfCannon [1], null);
	    g.drawImage (img [0], xOfCannon [2], yOfCannon [2], null);
	    g.drawImage (img [0], xOfCannon [3], yOfCannon [3], null);
	    g.drawImage (img [0], xOfCannon [4], yOfCannon [4], null);
	    //begins the rotations of the sixth cannon
	    drawCannon (xOfCannon [5], yOfCannon [5], 200, g);
	} //end of cannonSelector5 if statement
	if (ifShoot) //if the spacebar is pressed
	{
	    //if the space bar is pressed it stops the iterations of the cannon images
	    g.drawImage (img [frameCounter], xOfCannon [cannonSelector], yOfCannon [cannonSelector], null);
	    //returns a boolean variable "true" - level passed or "false" - failed
	    failedLevel = projectile (movingObstacle, xOfCannon, yOfCannon, xOfObstacle, yOfObstacle, g);
	} //end of ifShoot if statement
	if (failedLevel)
	{
	    //if the level is failed the number of lives is reduced by one
	    numLives--;
	    failScreen (g);
	    // if the number of lives reaches zero
	    if (numLives == 0)
	    {
		failGame(g);
	    } //end of numLives if statement
	} //end of failedLevel if statement
	repaint ();
    } //end of levelFour method


    //screen printed at the end of each lebel
    public void endLevel (Graphics g)
    {
	//initializes coordinates of the polygon that draws the cannon
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	//calculates the total stars collected from current and previous levels
	totalStars = pastStars + starCounter;
	//background color
	g.setColor (new Color (255, 204, 229));
	g.fillRect (0, 0, 1200, 800);
	//draws bird and level stars
	birdHappy (g);
	drawBirdBody (g);
	drawLevelStars (g);
	//draws all the text for the end of level screen
	Font levelFont = new Font ("verdana", Font.BOLD + Font.ITALIC, 80);
	g.setFont (levelFont);
	g.setColor (Color.white);
	g.drawString ("Level Completed!", 280, 350);
	Font levelFont3 = new Font ("verdana", Font.BOLD + Font.ITALIC, 30);
	g.setFont (levelFont3);
	g.setColor (new Color (96, 96, 96));
	//prints the total number of stars collected
	g.drawString (" Total Stars Collected: " + totalStars, 370, 520);
	Font levelFont4 = new Font ("verdana", Font.PLAIN, 25);
	g.setFont (levelFont4);
	g.drawString ("Press the left arrow key to retry the level", 100, 700);
	g.drawString ("Press the right arrow key to go to the next level", 100, 750);
	//draws bird speech bubble and text
	g.setColor (Color.white);
	g.fillRect (740, 650, 285, 50);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Nice Job!", 830, 670);
	g.drawString ("Retry levels to collect more coins!", 745, 695);
	//draws cannon
	g.setColor (Color.black);
	g.fillPolygon (pointx, pointy, 4);
	g.fillOval (115, 315, 100, 100);
	g.setColor (Color.yellow);
	g.fillRect (130, 420, 100, 30);
	g.fillRect (170, 360, 15, 80);
	//if the left arrow key is clicked it takes to back to the previous menuSelector value
	if (keyCode == 37)
	{
	    resetCounter--;
	    menuSelector--;
	} //end of 37 if statement
	//if the right arrow key is click it increases the menuSelector value to take you to the next level
	if (keyCode == 39)
	{
	    pastStars += starCounter;
	    menuSelector++;
	} //end of 39 if statement
    } //end of endLevel methods


    //draws the screen the appears at the end of the game
    public void endGame (Graphics g)
    {
	//initializes the coordinates of the cannon and other variables
	int[] pointx = {250, 300, 200, 130};
	int[] pointy = {200, 230, 400, 330};
	int xTitle = 300, yTitle = 150;
	char input;
	//background colour
	g.setColor (new Color (249, 205, 213));
	g.fillRect (0, 0, 1200, 800);
	//draws cannon
	g.setColor (Color.black);
	g.fillPolygon (pointx, pointy, 4);
	g.fillOval (115, 315, 100, 100);
	g.setColor (Color.yellow);
	g.fillRect (130, 420, 100, 30);
	g.fillRect (170, 360, 15, 80);
	//draws clouds
	g.setColor (Color.white);
	g.fillOval (120, 120, 150, 60);
	g.fillOval (140, 100, 60, 100);
	g.fillOval (190, 100, 60, 100);
	g.fillOval (520, 210, 150, 60);
	g.fillOval (540, 190, 60, 100);
	g.fillOval (590, 190, 60, 100);
	g.fillOval (870, 90, 150, 60);
	g.fillOval (890, 70, 60, 100);
	g.fillOval (940, 70, 60, 100);
	//contains all the draw string methods that print the game title and other text
	Font newFont = new Font ("VT323", Font.BOLD, 120);
	g.setFont (newFont);
	g.setColor (new Color (178, 34, 34));
	g.drawString ("GAME OVER!", 270, 400);
	//draws bird speech and text
	g.setColor (Color.white);
	g.fillRect (700, 650, 350, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Thank you for returning my baby safely to me!", 705, 675);
	Font levelFont3 = new Font ("verdana", Font.BOLD + Font.ITALIC, 30);
	g.setFont (levelFont3);
	g.drawString ("Congratulations! You have completed all the levels!", 250, 450);
	g.drawString ("Stay tuned for more levels to come!", 400, 550);
	//draws bird with its egg in hands
	drawBirdBody (g);
	birdWithEgg (g);
	while (true) //infinite loop
	{
	    //draws the projectile coming out of the ball
	    g.setColor (Color.white);
	    g.fillOval (xTitle, yTitle, 50, 50);
	    //if the ball reaches a certain y value it just redraws the ball at the start of the cannon
	    if (yTitle < 25)
	    {
		g.setColor (new Color (249, 205, 213));
		g.fillOval (xTitle, yTitle, 50, 50);
		break;
	    } //end of inner if statement
	    delay (30);
	    //redraws the ball as the same colour as the background
	    g.setColor (new Color (249, 205, 213));
	    g.fillOval (xTitle, yTitle, 50, 50);
	    //increases coordinates of the ball
	    xTitle = xTitle + 10;
	    yTitle = yTitle - 12;
	} //end of while loop
    } //end of endGame method


    //this method draws the background which remains the same for all the levels
    public void levelBackground (Graphics g)
    {
	//background color
	g.setColor (new Color (152, 218, 255));
	g.fillRect (0, 0, 1200, 800);
	//bushes on the bottom (dark green)
	g.setColor (new Color (0, 153, 0));
	g.fillArc (0, 600, 500, 250, 0, 180);
	g.fillArc (350, 650, 500, 150, 0, 180);
	g.fillArc (700, 620, 700, 450, 0, 180);
	//light green ground
	g.setColor (new Color (0, 204, 0));
	g.fillRect (0, 700, 1200, 100);
	//draws target
	g.setColor (Color.red);
	g.fillOval (1090, 310, 80, 80);
	g.setColor (Color.white);
	g.fillOval (1103, 323, 55, 55);
	g.setColor (Color.red);
	g.fillOval (1115, 335, 30, 30);
	//draws the bird in the corner
	birdArmsDown (g);
	drawBirdBody (g);
    } //end of levelBackground method


    public void starsCollected (Graphics g)
    {
	g.setFont (starFont);
	g.setColor (Color.black);
	//prints the value of stars collected as it is increasing
	g.drawString ("Stars Collected: " + starCounter, 990, 50);
    } //end of starsCollected methos


    public void livesRemaining (Graphics g)
    {
	g.setFont (starFont);
	g.setColor (Color.black);
	g.drawString ("Lives Remaining: " + numLives, 980, 80);
	//prints the number of lives remaining which decreases each time you fail a level
    } //end of livesRemaining method


    //speech printed if you fail/lose a life
    public void failScreen (Graphics g)
    {
	//speech bubble and text
	g.setColor (Color.white);
	g.fillRect (740, 650, 270, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Oops! You failed!", 800, 675);
	g.drawString ("The level is going to restart!", 765, 695);
	delay (1500);
	resetCounter--;
	//you lose all stars so the counter is returned back to zero
	starCounter = 0;
	keyCode = 0;
    } //end of failScreen method


    //this method resets the game back to the first level
    public void failGame (Graphics g)
    {
	//draws bird speech bubble and texr
	g.setColor (Color.white);
	g.fillRect (740, 650, 290, 55);
	g.setColor (Color.black);
	g.setFont (birdTalk);
	g.drawString ("Oh no! I only have 3 lives!", 780, 675);
	g.drawString ("Back to level one :(", 800, 695);
	delay (2500);
	//resets menuSelector to 1 to take you back to level one
	menuSelector = 1;
	//initializes variables needed to replay level one
	numLives = 3;
	totalStars = 0;
	pastStars = 0;
	resetCounter = 0;
    }


    //this method draws the number of stars collected at the end of each level (1-3)
    public void drawLevelStars (Graphics g)
    {
	//coordinates of the stars
	int[] xStar = {420, 500, 580};
	int[] yStar = {390, 390, 390};
	//depending on the number of star collected value stored in the starCounter variable, a certain number of stars is drawn
	for (int i = 0 ; i < starCounter ; i++)
	{
	    drawStar (g, xStar [i], yStar [i]);
	    // adds a space between each star
	    for (int j = 0 ; j < xStar.length ; j++)
	    {
		xStar [j] += 50;
	    } //end of inner for looo
	} //end of outer for loop
    } //end of drawLevelStars method


    public void drawStar (Graphics g, int xStar, int yStar)
    {
	//takes the passed x-coordinate and y-coordinate of the star and adds a certain value to draw the polygon proportions
	int[] starXPoint = {xStar + 25, xStar + 40, xStar + 0, xStar + 50, xStar + 10};
	int[] starYPoint = {yStar + 0, yStar + 50, yStar + 20, yStar + 20, yStar + 50};
	//draws the star with the new points
	g.setColor (new Color (255, 240, 18));
	g.fillPolygon (starXPoint, starYPoint, 5);
	//inner circle to fill the star completely
	g.fillOval (xStar + 15, yStar + 18, 20, 21);
    } //end of drawStar method


    public int drawObstacle (Graphics g, int xObstacle, int yObstacle)
    {
	//int dyObstacle = 1;

	yObstacle += numOfdy;
	
	g.setColor (Color.black);
	g.fillRect (xObstacle, yObstacle, 50, 150);
	
	//yObstacle += numOfdy;
	if (yObstacle > 425 || yObstacle < 100)
	{
	    ifReversed = -ifReversed;
	}
	if (ifReversed == 1)
	{
	    numOfdy += 30;
	}
	else
	{
	    numOfdy -= 30;
	}
	return yObstacle;
    }

    //this method draws the birds generic body without it arms which indicate emotion
    public void drawBirdBody (Graphics g)
    {
	//beak coordinates
	int[] beakX = {1090, 1105, 1120, 1105};
	int[] beakY = {730, 720, 730, 745};
	//body
	g.setColor (new Color (139, 0, 130));
	g.fillOval (1042, 645, 125, 130);
	//outer eyes
	g.setColor (Color.white);
	g.fillOval (1110, 680, 35, 40);
	g.fillOval (1060, 680, 35, 40);
	//blue pupils
	g.setColor (new Color (0, 191, 255));
	g.fillOval (1115, 690, 25, 25);
	g.fillOval (1065, 690, 25, 25);
	//inner black eyes
	g.setColor (Color.black);
	g.fillOval (1119, 695, 16, 16);
	g.fillOval (1069, 695, 16, 16);
	//white circle/glare
	g.setColor (Color.white);
	g.fillOval (1128, 693, 8, 8);
	g.fillOval (1078, 693, 8, 8);
	//draws beak
	g.setColor (Color.orange);
	g.fillPolygon (beakX, beakY, 4);
	g.fillOval (1119, 770, 20, 10);
	g.fillOval (1069, 770, 20, 10);
    } //end of drawBirdBody method


    //the following 3 methods contain each of the birds different emotions/stances
    public void birdArmsDown (Graphics g)
    {
	//arms straight down
	g.setColor (new Color (190, 0, 190));
	g.fillOval (1035, 705, 20, 50);
	g.fillOval (1155, 705, 20, 50);
    } //end of birdArmsDown


    public void birdHappy (Graphics g)
    {
	//draws arms up
	g.setColor (new Color (190, 0, 190));
	g.fillOval (1030, 665, 20, 50);
	g.fillOval (1160, 665, 20, 50);
    } //end of birdHappy method


    public void birdWithEgg (Graphics g)
    {
	//draws arms facing inward to the body
	g.setColor (new Color (190, 0, 190));
	g.fillOval (1060, 750, 30, 10);
	g.fillOval (1120, 750, 30, 10);
	//draws egg
	g.setColor (new Color (255, 255, 153));
	g.fillOval (1088, 748, 35, 30);
    } //end of birdWithEgg method


    public void drawCannon (int xCannon, int yCannon, int delayTime, Graphics g)
    {
	g.drawImage (img [frameCounter], xCannon, yCannon, null);
	if (!ifDoneRotating)
	{

	    delay (delayTime - 0);
	    if (keyCode != 32) //if the space bar IS NOT pressed
	    {
		//after each frame is drawn the frame counter increases to draw the next image in the array
		frameCounter++;
		if (frameCounter == 8)
		    //once the cannon reaches its last frame, it resets to 0 and starts again
		    {
			frameCounter = 0;
		    } //end of inner if statement
	    } //end of outer if statement
	    else //if the spacebar IS pressed
	    {
		if (frameCounter == 0) //framecounter is reset to 8
		{
		    frameCounter = 8;
		} //end of inner if statement
		frameCounter--;
		//boolean value is set to true and is returned to the projectile method
		ifDoneRotating = true;
	    } //end of else statement

	    //of the ball is set to the coordinates of the cannon it is located at
	    x = xCannon;
	    y = yCannon;
	} //end of outer if statement
	else
	{
	    ifShoot = true;

	}
    } //end of drawCannon method


    public boolean projectile (int[] movingObstacles, int[] xCannons, int[] yCannons, int[] xObstacles, int[] yObstacles, Graphics g)
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
	    delay(4);
	    g.setColor (new Color (152, 218, 255));
	    g.fillOval (x, y, 50, 50);
	    for (int i = 0 ; i < xCannons.length ; i++)
	    {
		if (x == xCannons [i] + 75)
		{
		    if (y == yCannons [i] + 75)
		    {
			valueReset (false);
			cannonSelector = i;
			ifItemHit = true;
			break;
		    }
		}
	    }
	    for (int j = 0 ; j < xOfStar.length ; j++)
	    {
		if (x == xOfStar [j])
		{
		    if (y == yOfStar [j])
		    {
			starCounter++;
			xOfStar [j] = -50;
			ifItemHit = true;
			break;
		    }
		}
	    }
	    
	    for (int m = 0 ; m < yObstacles.length ; m++)
	    {
		if (x + 50 > xObstacles [m] && x + 25 < xObstacles [m] + 50)
		{
		    if (y > yObstacles [m] - 25 && y + 25 < yObstacles [m] + 150)
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
		    menuSelector++;
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


    public void valueReset (boolean fullReset)
    {
	//frame of the cannon
	frameCounter = 0;
	//value of the projectile set accoriding to the drawCannon method
	x = 0;
	y = 0;
	//user key input
	keyCode = 0;
	//returns boolean variables to false to stop the movement of the projectile
	ifShoot = false;
	ifTravelled = false;
	ifDoneRotating = false;
	if (fullReset)
	{
	    cannonSelector = 0;
	    ifHit = 0;
	    numOfdy = 0;
	    starCounter = 0;
	}
    } //end of valueReset method


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


    public void delay (int time)
    {
	try
	{
	    Thread.sleep (time);
	}
	catch (InterruptedException e)
	{
	}
    } //end of delay method


    public void keyTyped (KeyEvent k)
    {
    } //end of keyTyped method


    public void keyPressed (KeyEvent k)
    {
	keyCode = k.getKeyCode ();
	repaint ();
    } //end of keyPressed method


    public void keyReleased (KeyEvent k)
    {
    } //end of keyReleased method


    public void mousePressed (MouseEvent e)
    {
    } //end of mousePressed method


    public void mouseReleased (MouseEvent e)
    {
    } //end of mouseReleased method


    public void mouseEntered (MouseEvent e)
    {
    } //end of mouseEntered method


    public void mouseExited (MouseEvent e)
    {
    } //end of mouseExited


    public void mouseClicked (MouseEvent e)
    {
	//if the menu start button "press to begin" is clicked the menuSelector value is increased
	if (e.getSource () == menuStart)
	{
	    menuSelector++;
	} //end of if statement
	//repaints to level one
	repaint ();
    } //end of mouseClicked method
} //end of CPT_AlMalouf_Prentice class
