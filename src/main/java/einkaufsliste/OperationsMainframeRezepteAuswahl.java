package einkaufsliste;

import java.util.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperationsMainframeRezepteAuswahl {
    static ArrayList<String> ausgewaehlteRezepteListe = new ArrayList<String>();
    static ArrayList<Float> portionenListe = new ArrayList<Float>();
    static String[] rezepteAlsArray;

    /**
     * aktualisiert die Anzeige der Rezepte in der Oberfläche
     * @param rezepte, JTextArea der Rezepte
     */ 
    public static void gebeRezepteAus(JTextArea rezepte) {
        rezepteAlsArray = DatabaseConnection.holeRezepteAusDB();
        String ausgabe = "";
        for(String stringRezept : rezepteAlsArray) {
            ausgabe = ausgabe + stringRezept + "\n";
        }
        rezepte.setText(ausgabe);
    }

    /**
     * fügt ausgewwählte Rezepte der rezeptListe hinzu und aktualisiert die Ausgabe auf der Oberfläche
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

    public static void gebeZutatenlisteAus(JTextArea zutaten) {
        String ausgabe = "";
        for(String zutat : DatabaseConnection.holeZutatenAusDB(ausgewaehlteRezepteListe, portionenListe)) {
            ausgabe = ausgabe + zutat + "\n";
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


    public static float stringZuFloat(JTextField portionenFeld) {
        String portionenString = portionenFeld.getText();
        System.out.println(portionenString);
        portionenString = portionenString.replace(',', '.');
        System.out.println(portionenString);
        float portionenFloat = Float.parseFloat(portionenString);
        return portionenFloat;
    }
}
