import sas.*;
import java.awt.Color;

/**
 * Fußballfeld + Helikopter mit animierten (sichtbar drehenden) Rotorblättern.
 * Steuerung: WASD (SAS kennt i.d.R. nur einzelne Zeichen).
 */
public class SpielfeldMitHeli {

    // View und Spielfeld
    private final View fenster;
    private final Rectangle feld;
    private final Rectangle tor;
    private final Circle ball;

    // Helikopter-Hauptteile
    private final Rectangle fl;     // Flügel/Holm
    private final Rectangle js;     // Heckausleger
    private final Ellipse sk;       // Rumpf
    private final Ellipse sv;       // Cockpit

    // Rotorblätter (sichtbar drehend)
    private final Rectangle rotorBlade1;
    private final Rectangle rotorBlade2; // 90° versetzt für "bewegenden Propeller"
    // Heckrotor
    private final Rectangle tailRotorBlade1;
    private final Rectangle tailRotorBlade2;

    // Drehzentren
    private double cx;
    private double cy;
    private double tailCx;
    private double tailCy;

    // Farben aus Code 1
    private final Color yellowGreen = new Color(112, 116, 6);
    private final Color grey        = new Color(168, 168, 168);
    private final Color arsch       = new Color(56, 72, 50);

    public SpielfeldMitHeli() {
        fenster = new View(1000, 800);

        // --- Spielfeld ---
        feld = new Rectangle(75, 75, 600, 450);
        feld.setColor(yellowGreen);

        tor = new Rectangle(600, 200, 75, 200);
        tor.setColor(grey);

        ball = new Circle(90, 280, 30);
        ball.setColor(arsch);

        // --- Helikopter (Start nahe Mitte) ---
        sk = new Ellipse(300, 200, 100, 75);      // Rumpf
        fl = new Rectangle(250, 232.5, 60, 10);   // Ausleger
        sv = new Ellipse(360, 210, 40, 55);       // Cockpit
        js = new Rectangle(220, 229.8, 30, 15);   // Heckausleger

        sk.setColor(Color.gray);
        fl.setColor(Color.gray);
        sv.setColor(Color.blue);
        js.setColor(Color.gray);

        // Hauptrotor-Blätter (dünne Rechtecke)
        rotorBlade1 = new Rectangle(345, 175, 10, 120);
        rotorBlade2 = new Rectangle(295, 225, 110, 10); // quer, 90° versetzt
        rotorBlade1.setColor(Color.darkGray);
        rotorBlade2.setColor(Color.darkGray);

        // Heckrotor (klein, zwei Blätter)
        tailRotorBlade1 = new Rectangle(235, 235, 6, 30);
        tailRotorBlade2 = new Rectangle(226, 245, 24, 6);
        tailRotorBlade1.setColor(Color.darkGray);
        tailRotorBlade2.setColor(Color.darkGray);

        // Drehzentren
        cx = 350;
        cy = 235;
        tailCx = 235;
        tailCy = 250;

        // Intro: Ball rollt wie im Original
        rollBall();
    }

    private void rollBall() {
        int i = 0;
        while (i < 515) {
            ball.move(1, 0);
            i++;
            fenster.wait(5);
        }
    }

    public void run() {
        while (true) {
            // Rotoren drehen
            rotateMainRotor(25);
            rotateTailRotor(40);

            // Steuerung (WASD)
            int dx = 0, dy = 0;
            if (fenster.keyPressed('d')) dx += 2;
            if (fenster.keyPressed('a')) dx -= 2;
            if (fenster.keyPressed('s')) dy += 2;
            if (fenster.keyPressed('w')) dy -= 2;

            if (dx != 0 || dy != 0) {
                moveHeli(dx, dy);
            }

            fenster.wait(8);
        }
    }

    private void rotateMainRotor(double angle) {
        rotorBlade1.turn(cx, cy, angle);
        rotorBlade2.turn(cx, cy, angle);
    }

    private void rotateTailRotor(double angle) {
        tailRotorBlade1.turn(tailCx, tailCy, angle);
        tailRotorBlade2.turn(tailCx, tailCy, angle);
    }

    private void moveHeli(int dx, int dy) {
        sk.move(dx, dy);
        fl.move(dx, dy);
        sv.move(dx, dy);
        js.move(dx, dy);

        rotorBlade1.move(dx, dy);
        rotorBlade2.move(dx, dy);
        tailRotorBlade1.move(dx, dy);
        tailRotorBlade2.move(dx, dy);

        cx += dx;
        cy += dy;
        tailCx += dx;
        tailCy += dy;
    }

    public static void main(String[] args) {
        new SpielfeldMitHeli().run();
    }
}