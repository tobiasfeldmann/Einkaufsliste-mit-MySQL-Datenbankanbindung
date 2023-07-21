package einkaufsliste;

import java.util.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperationsMainframe {
    static ArrayList<Zutat> list = new ArrayList<Zutat>();
    

    public void rezeptEntfernen(int rezeptID) {

    }

    public void mengenAusgeben() {
        
    }

    /**
     * fügt eine neue Zutat zu der ArrayList aus Zutaten hinzu
     * @param zutat Textfield für den Namen
     * @param menge JTextField für die Menge
     * @param mengeneinheit JTextField für die Mengeneinheit
     * @param zutaten   JTextArea für die Anzeige der aktuellen Zutaten
     */

    public static void zutatHinzufuegen(JTextField zutat, JTextField menge, JTextField mengeneinheit, JTextArea zutaten) {
        Zutat neueZutat = new Zutat(zutat.getText(), Integer.parseInt(menge.getText()), mengeneinheit.getText(), DatabaseConnection.getLatestID());
        list.add(neueZutat);
        gebeZutatenAus(zutaten);
    }

    public static void letzteZutatEntfernen(JTextArea zutaten) {
        list.remove(list.size() - 1);
        gebeZutatenAus(zutaten);
    }

    /**
     * gibt die ArrayList der Zutaten aus
     * @return ArrayList<Zutat> bestehend aus Zutaten
     */
    public static ArrayList<Zutat> getArrayListOfZutaten() {
        return list;
    }

    /**
     * aktualisiert das JTextArea für die Ausgabe der hinzugefügten Zutaten
     * @param zutaten JTextArea der hinzugefügten Zutaten
     */
    public static void gebeZutatenAus(JTextArea zutaten) {
        String ausgabe = "";
        for(Zutat zutatListe : list) {
            ausgabe = ausgabe + zutatListe.getName() + " " + zutatListe.getMenge() + " "+ zutatListe.getEinheit() + "\n";
        }
        zutaten.setText(ausgabe);
    }

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

}
