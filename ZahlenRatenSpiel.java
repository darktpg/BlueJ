import java.util.Scanner;
import java.util.Random;

public class ZahlenRatenSpiel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=====================================");
        System.out.println("   WILLKOMMEN BEIM ZAHLEN-RATEN!    ");
        System.out.println("=====================================");
        System.out.println("Ich denke mir eine Zahl zwischen 1 und 100.");
        System.out.println("Kannst du sie erraten?\n");

        int geheimzahl = random.nextInt(100) + 1;  // Zahl von 1 bis 100
        int versuche = 0;
        int tipp = 0;
        boolean gueltigerTipp = false;

        while (tipp != geheimzahl) {
            System.out.print("Dein Tipp: ");
            
            // Bessere Eingabe-Überprüfung: Schleife bis gültige Zahl
            while (!gueltigerTipp) {
                if (scanner.hasNextInt()) {
                    tipp = scanner.nextInt();
                    gueltigerTipp = true;
                } else {
                    System.out.println("Bitte eine gültige Zahl eingeben (z.B. 42)! Versuch's nochmal.");
                    scanner.next();  // Falsche Eingabe wegwerfen
                    System.out.print("Dein Tipp: ");
                }
            }
            gueltigerTipp = false;  // Reset für nächsten Durchlauf
            versuche++;

            if (tipp < geheimzahl) {
                System.out.println("Zu klein! Versuch's höher.\n");
            } else if (tipp > geheimzahl) {
                System.out.println("Zu groß! Versuch's niedriger.\n");
            } else {
                System.out.println("\n🎉 RICHTIG! 🎉");
                System.out.println("Du hast die Zahl " + geheimzahl + " in " + versuche + " Versuchen erraten!");
                
                // Highscore-Kommentar
                if (versuche <= 5) {
                    System.out.println("Wow, das war echt gut!");
                } else if (versuche <= 8) {
                    System.out.println("Gute Leistung!");
                } else {
                    System.out.println("Naja... beim nächsten Mal klappt's schneller 😉");
                }
                System.out.println("\nDanke fürs Spielen! 😊");
            }
        }

        scanner.close();
    }
}