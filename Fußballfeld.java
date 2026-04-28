import sas.*;
import java.awt.Color;


public class Fußballfeld
{
    View fenster;
    Rectangle feld;
    Rectangle tor;
    Circle ball;
    Color YellowGreen = new Color(112,116,6);
    Color Grey = new Color(168,168,168);
    Color Arsch = new Color(56,72,50);

    public Fußballfeld()
    {
      fenster = new View(800,600);
      //feld
      feld = new Rectangle(75,75, 600,450);
      feld.setColor(YellowGreen);
      //tor
      tor = new Rectangle(600,200, 75,200);
      tor.setColor(Grey);
      //ball
      ball = new Circle(90,280, 30);
      ball.setColor(Arsch);
      
      int i=0;
       while(i<515)
       {
           ball.move(1 , 0);
           i++;
           fenster.wait(10);
        }
    }

    
    
    {
        
    }
}
