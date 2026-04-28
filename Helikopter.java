import sas.*;
import java.awt.Color;
class Helikopter
{
    View fenster;
    Rectangle fl;
    Rectangle js;
    Rectangle rotor;
    Ellipse sk;
    Ellipse sv;
    double x;
    double y;
   Helikopter() {
       fenster =new View(1000,1000);
       sk = new Ellipse(200,200,100,75);
       fl = new Rectangle(150,232.5,60,10);
       sv = new Ellipse(260,210, 40,55);
       js = new Rectangle(120,229.8,30,15);
       rotor = new Rectangle(245,175,10,120);
       sv.setColor(Color.blue);
       sk.setColor(Color.gray);
       fl.setColor(Color.gray);
       js.setColor(Color.gray);
      x = 250;
      y = 235;
       
      Helikopter();
       
    }
    void Helikopter() {
        while (sk.getShapeY() < 2000)  {
          
       
    rotor.turn(x,y,20);    
                    
            if (fenster.keyPressed('d')) {
                sk.move(1,0);
                fl.move(1,0);
                sv.move(1,0);
                js.move(1,0);
                rotor.move(1,0);
                x = x+1;
          
    
            }
if (fenster.keyPressed('a')) {
                sk.move(-1,0);
                fl.move(-1,0);
                sv.move(-1,0);
                js.move(-1,0);
                rotor.move(-1,0);
                x = x-1;
          
    
            }
if (fenster.keyPressed('s')) {
                sk.move(0,1);
                fl.move(0,1);
                sv.move(0,1);
                js.move(0,1);
                rotor.move(0,1);
                y = y+1;
          
}
if (fenster.keyPressed('w')) {
                sk.move(0,-1);
                fl.move(0,-1);
                sv.move(0,-1);
                js.move(0,-1);
                rotor.move(0,-1);
                y = y-1;
         

}

fenster.wait(5);
}
}
}

