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

    /**
     * Übersetzt den String aus der Portionsangabe in einen Float zur Berechnung
     * @param portionenFeld, JTextField in das die gewünschte Portionsmenge geschrieben wird
     * @return  float Wert
     */
    public static float stringZuFloat(JTextField portionenFeld) {
        String portionenString = portionenFeld.getText();
        System.out.println(portionenString);
        portionenString = portionenString.replace(',', '.');
        System.out.println(portionenString);
        float portionenFloat = Float.parseFloat(portionenString);
        return portionenFloat;
    }

    public static void gebeRezepteVorschlaegeAus(JTextField rezeptAuswahl, String[] rezepte) {
        if(rezeptAuswahl.getText().length() == 0) {
            return;
        }
        String temp = rezeptAuswahl.getText();
        Arrays.sort(rezepte);
        for(String string : rezepte) {
            if(string.length() >= temp.length() && string.charAt(0) == temp.charAt(0)) {
                String zeichenTemp = string.substring(0, temp.length());
                System.out.println(zeichenTemp);
                System.out.println(temp);
                System.out.println("---");
                if(zeichenTemp.equals(temp)) {
                    System.out.println(string);
                    rezeptAuswahl.setText(string);
                    break;
                }
            }
        }
    }

}
