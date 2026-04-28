import sas.*;
import java.awt.Color;

public class Sonnensystem2 {
    View fenster;
    
    Circle sonne;
    Circle erde;
    Circle mond;
    Circle jupiter;
    Circle saturn;
    Circle uranus;
    Circle neptun;
    Circle pluto;
    Circle black;
    
    Rectangle fl;
    Rectangle js;
    Rectangle rotor;
    Ellipse sk;
    Ellipse sv;
    double heliX; 
    double heliY; 

    public Sonnensystem2 () {
        fenster = new View (1200,1200);
        fenster.setBackgroundColor(Color.black);
        
        black= new Circle (550,350,50,Color.black);
        sonne= new Circle (520,350,50,Color.yellow);
        erde= new Circle (700,340,15,Color.green);
        mond = new Circle(650,340, 10,Color.gray);
        jupiter = new Circle(820,340, 21,Color.orange);
        saturn = new Circle(870,340, 20,Color.red);
        uranus = new Circle(920,340, 23,Color.blue);
        neptun = new Circle(990,340, 24,Color.blue);
        pluto = new Circle(1050,340, 10,Color.white);

        sk = new Ellipse(200,200,100,75);
        fl = new Rectangle(150,232.5,60,10);
        sv = new Ellipse(260,210, 40,55);
        js = new Rectangle(120,229.8,30,15);
        rotor = new Rectangle(245,175,10,120);
        
        sv.setColor(Color.blue);
        sk.setColor(Color.gray);
        fl.setColor(Color.gray);
        js.setColor(Color.gray);
        rotor.setColor(Color.white);
        
        heliX = 250;
        heliY = 235;

        starteSimulation();
    }

    void starteSimulation (){
        while(!fenster.keyEnterPressed()){
            
            if (fenster.keyLeftPressed()){ bewegeSystem(-3, 0); }
            if (fenster.keyRightPressed()){ bewegeSystem(3, 0); }
            if (fenster.keyDownPressed()){ bewegeSystem(0, 3); }
            if (fenster.keyUpPressed()){ bewegeSystem(0, -3); }

            if (fenster.keyPressed('p')) { skaliereSystem(1.02); }
            if (fenster.keyPressed('m')) { skaliereSystem(0.98); }

            if (fenster.keyPressed('d')) { bewegeHeli(3, 0); }
            if (fenster.keyPressed('a')) { bewegeHeli(-3, 0); }
            if (fenster.keyPressed('s')) { bewegeHeli(0, 3); }
            if (fenster.keyPressed('w')) { bewegeHeli(0, -3); }

            erde.turn(sonne.getCenterX(), sonne.getCenterY(), 1);
            mond.turn(sonne.getCenterX(), sonne.getCenterY(), 1);
            mond.turn(erde.getCenterX(), erde.getCenterY(), 9);
            jupiter.turn(sonne.getCenterX(), sonne.getCenterY(), 4);
            saturn.turn(sonne.getCenterX(), sonne.getCenterY(), 5);
            uranus.turn(sonne.getCenterX(), sonne.getCenterY(), 6);
            neptun.turn(sonne.getCenterX(), sonne.getCenterY(), 7);
            pluto.turn(sonne.getCenterX(), sonne.getCenterY(), 8);
            
            rotor.turn(heliX, heliY, 30);
            
            fenster.wait(20);
        }
    }

    void bewegeHeli(double dx, double dy) {
        sk.move(dx, dy);
        fl.move(dx, dy);
        sv.move(dx, dy);
        js.move(dx, dy);
        rotor.move(dx, dy);
        
        heliX = heliX + dx;
        heliY = heliY + dy;
    }

    void bewegeSystem(double dx, double dy) {
        sonne.move(dx, dy);
        erde.move(dx, dy);
        mond.move(dx, dy);
        jupiter.move(dx, dy);
        saturn.move(dx, dy);
        uranus.move(dx, dy);
        neptun.move(dx, dy);
        pluto.move(dx, dy);
        black.move(dx, dy);
    }

    void skaliereSystem(double factor) {
        passeEntfernungAn(erde, factor);
        passeEntfernungAn(mond, factor);
        passeEntfernungAn(jupiter, factor);
        passeEntfernungAn(saturn, factor);
        passeEntfernungAn(uranus, factor);
        passeEntfernungAn(neptun, factor);
        passeEntfernungAn(pluto, factor);
        passeEntfernungAn(black, factor);
    }

    void passeEntfernungAn(Circle planet, double factor) {
        double dx = planet.getCenterX() - sonne.getCenterX();
        double dy = planet.getCenterY() - sonne.getCenterY();
        planet.move(dx * (factor - 1), dy * (factor - 1));
    }
}