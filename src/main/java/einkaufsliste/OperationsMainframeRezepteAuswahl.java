package einkaufsliste;

import java.util.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperationsMainframeRezepteAuswahl {
    static ArrayList<String> list = new ArrayList<String>();

    /**
     * aktualisiert die Anzeige der Rezepte in der Oberfläche
     * @param rezepte, JTextArea der Rezepte
     */ 
    public static void gebeRezepteAus(JTextArea rezepte) {
        String[] rezepteAlsArray = DatabaseConnection.holeRezepteAusDB();
        String ausgabe = "";
        for(String stringRezept : rezepteAlsArray) {
            ausgabe = ausgabe + stringRezept + "\n";
        }
        rezepte.setText(ausgabe);
    }

    public static void fuegeRezeptHinzu(String rezept, JTextArea rezepte) {
        list.add(rezept);
        gebeAusgewaehlteRezepteAus(rezepte);
    }

    public static void entferneRezept(JTextArea rezepte) {
        list.remove(list.size() - 1);
        gebeAusgewaehlteRezepteAus(rezepte);
    }

    public static void gebeZutatenlisteAus(JTextArea zutaten) {

    }

    public static void gebeAusgewaehlteRezepteAus(JTextArea ausgewaehlteRezepte) {
        String ausgabe = "";

        for(String rezept : list) {
            ausgabe = ausgabe + rezept + "\n";
        }
        ausgewaehlteRezepte.setText(ausgabe);
    }   
}
