import sas.*; 
import java.awt.Color; 

public class Lauflichterprogramm
{
    // Deklarationen
    View fenster;
    Circle[] lichter;

    // Konstruktor
    public Lauflichterprogramm()
    {
        fenster = new View(550, 100, "SaS-Programm");
        lichter = new Circle[5];

        // Lichter initialisieren (alle auf Grau/Ausgeschaltet)
        for(int i = 0; i < lichter.length; i++) {
            lichter[i] = new Circle(i*80 + 20, 20, 30);
            lichter[i].setColor(Color.gray); 
        }

        // Startet sofort die unendliche Animation
        starteSimulation();
    }

    // Die Haupt-Schleife: Führt nur noch das Hin- und Her-Lauflicht aus
    void starteSimulation() {
        while(!fenster.keyEnterPressed()) {
            starteLauflichter2();
        }
    }

    // --- Die Animations-Methoden ---

    // Wird nicht mehr aufgerufen, bleibt leer (für die Aufgabe)
    void starteLauflicht() {
    }

    // Das flüssige Hin- und Her-Lauflicht
    void starteLauflichter2() {
        // Von links nach rechts
        for(int i = 0; i < lichter.length; i++) {
            lichter[i].setColor(Color.red);
            fenster.wait(80); // Flüssige 80 Millisekunden
            lichter[i].setColor(Color.gray);
        }
        
        // Von rechts nach links 
        // (Startet bei length-2 und geht bis > 0, damit die Ränder nicht doppelt aufleuchten)
        for(int i = lichter.length - 2; i > 0; i--) {
            lichter[i].setColor(Color.red);
            fenster.wait(80);
            lichter[i].setColor(Color.gray);
        }
    }

    // Wird nicht mehr aufgerufen, bleibt leer (für die Aufgabe)
    void starteBlinken() {
    }
}