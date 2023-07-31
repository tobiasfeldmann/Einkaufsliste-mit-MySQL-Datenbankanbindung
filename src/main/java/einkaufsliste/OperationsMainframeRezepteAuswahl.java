package einkaufsliste;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperationsMainframeRezepteAuswahl {
    private static ArrayList<String> ausgewaehlteRezepteListe = new ArrayList<String>();
    private static ArrayList<Float> portionenListe = new ArrayList<Float>();
    private static String[] rezepteAlsArray;
    private static ArrayList<String> zutatenAlsArray = new ArrayList<>();
    private static String ausgabe = "";

    /**
     * aktualisiert die Anzeige der Rezepte in der Oberfläche
     * @param rezepte, JTextArea der Rezepte
     */ 
    public static void gebeRezepteAus(JTextArea rezepte) {
        rezepteAlsArray = DatabaseConnection.holeRezepteAusDB();
        Arrays.sort(rezepteAlsArray);
        String ausgabe = "";
        for(String stringRezept : rezepteAlsArray) {
            ausgabe = ausgabe + stringRezept + "\n";
        }
        rezepte.setText(ausgabe);
    }

    /**
     * fügt ausgewählte Rezepte der rezeptListe hinzu und aktualisiert die Ausgabe auf der Oberfläche
     * @param rezept ausgewähltes Rezept
     * @param rezepte JTextArea der Oberfläche
     */
    public static void fuegeRezeptHinzu(String rezept, JTextArea rezepte, float portionen) {
        ausgewaehlteRezepteListe.add(rezept);
        portionenListe.add(portionen);
        gebeAusgewaehlteRezepteAus(rezepte);
    }

    /**
     * entfernt das zuletzt hinzugefügte Rezept wieder und aktualisiert die Oberfläche durch aufrufen der Methode
     * @param rezepte  JTextArea der Oberfläche
     */
    public static void entferneRezept(JTextArea rezepte) {
        ausgewaehlteRezepteListe.remove(ausgewaehlteRezepteListe.size() - 1);
        gebeAusgewaehlteRezepteAus(rezepte);
    }

    /**
     * Holt die Zutaten der jeweiligen Rezepte aus der Datenbank
     * @param zutaten, JTextArea für die Ausgabe der Zutaten
     */
    public static void gebeZutatenlisteAus(JTextArea zutaten) {
        ausgabe = "";
        for(String zutat : DatabaseConnection.holeZutatenAusDB(ausgewaehlteRezepteListe, portionenListe)) {
            ausgabe = ausgabe + zutat + "\n";
            zutatenAlsArray.add(zutat);
        }
        zutaten.setText(ausgabe);
    }

    /**
     * Methode die aufgerufen wird um die Oberfläche zu aktualisieren
     * @param ausgewaehlteRezepte JTextArea der Oberfläche
     */
    public static void gebeAusgewaehlteRezepteAus(JTextArea ausgewaehlteRezepte) {
        String ausgabe = "";

        for(String rezept : ausgewaehlteRezepteListe) {
            ausgabe = ausgabe + rezept + "\n";
        }
        ausgewaehlteRezepte.setText(ausgabe);
    }   

    /**
     * Übersetzt den String aus der Portionsangabe in einen Float zur Berechnung
     * @param portionenFeld, JTextField in das die gewünschte Portionsmenge geschrieben wird
     * @return  float Wert
     */
    public static float stringZuFloat(JTextField portionenFeld) {
        String portionenString = portionenFeld.getText();
        portionenString = portionenString.replace(',', '.');
        float portionenFloat = Float.parseFloat(portionenString);
        return portionenFloat;
    }

    /**
     * Soll die erzeugte ArrayList aus zutaten als txt Datei speichern, um diese später drucken zu können
     */
    public static void speichereDatei() {
        String path = "C:/temp/tempDatei.txt";
        File file = new File(path);
        if(zutatenAlsArray.size() > 0) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(path));
                if(file.exists()) {
                    int counter = 1;
                    String temp = "";
                    for(String s : zutatenAlsArray) {
                        if(counter == 1) {
                            temp = temp + s;
                            counter++;
                        }
                        else {
                            temp = berechneLeerzeichen(temp) + s;
                            writer.println(temp);
                            writer.println("\n");
                            counter = 1;
                            temp = "";
                        }
                    }
                    writer.flush();
                    writer.close();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        druckeDatei(file);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loescheDatei(file);
    }

    /**
     * Methode um die Datei zu drucken
     * @param file, zu druckende Datei
     */
    public static void druckeDatei(File file) {
        if(file.exists()) {
            try {
                Desktop.getDesktop().print(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loescht die zuvor erstellte Datei wieder, da diese nur temporär zum drucken diente
     */
    public static void loescheDatei(File file) {
        file.delete();
    }

    /**
     * Methode um die erste Zeile Zeichen besser von der zweiten durch eine feste Anzahl aus Zeichen zu trennen
     * @param s
     * @return
     */
    public static String berechneLeerzeichen(String s) {
        StringBuilder builder = new StringBuilder(s);
        int abstand = 45;
        int laenge = s.length();
        int differenz = abstand - laenge;
        for(int i1 = differenz; i1 > 0; i1--) {
            builder.append(" ");
        }
        return builder.toString();
    }

}
