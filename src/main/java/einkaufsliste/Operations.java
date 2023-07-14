package einkaufsliste;

import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Operations {
    static ArrayList<Zutat> list = new ArrayList<Zutat>();
    
    public void rezeptHinzufuegen(int rezeptID) {

    }

    public void rezeptEntfernen(int rezeptID) {

    }

    public void mengenAusgeben() {
        
    }

    public static void zutatHinzufuegen(JTextField zutat, JTextField menge, JTextField mengeneinheit, JTextArea zutaten) {
        Zutat neueZutat = new Zutat(zutat.getText(), Integer.parseInt(menge.getText()), mengeneinheit.getText(), DatabaseConnection.getLatestID() + 1);
        list.add(neueZutat);
        String ausgabe = "";
        for(Zutat zutatListe : list) {
            ausgabe = ausgabe + zutatListe.getName() + " " + zutatListe.getMenge() + " "+ zutatListe.getEinheit() + "\n";
        }
        zutaten.setText(ausgabe);
    }

    public void letzteZutatEntfernen() {
        
    }

    public static ArrayList<Zutat> getArrayListOfZutaten() {
        return list;
    }


}
