import java.awt.*;
import hsa.Console;

public class CPT_AlMalouf_Prentice 
{
    static Console c;
    public static void main (String[] args)
    {
	c= new Console(150,150);
	char startInput = '.';
	makeTitleScreen (startInput);
    }

    public static char makeTitleScreen ( char input)
    {
	Font newFont = new Font ("comic sans ms", Font.BOLD + Font.ITALIC, 100);
	c.setFont (newFont);
	c.setColor(Color.pink);
	c.drawString ("Cannon Launch", 270, 400);
	Font newFont2 = new Font ("comic sans ms", Font.BOLD + Font.ITALIC, 30);
	c.setFont (newFont2);
	c.drawString ("Press enter to begin", 450, 450);
	c.print (" ");
	input = c.readChar ();
	return input;

    }
    public 
}
