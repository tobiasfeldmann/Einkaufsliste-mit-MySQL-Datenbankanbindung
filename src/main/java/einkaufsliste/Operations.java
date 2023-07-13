package einkaufsliste;

import java.util.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Operations {
    static ArrayList<String[]> list = new ArrayList<String[]>();
    
    public void rezeptHinzufuegen(int rezeptID) {

    }

    public void rezeptEntfernen(int rezeptID) {

    }

    public void mengenAusgeben() {
        
    }

    public static void zutatHinzufuegen(JTextField zutat, JTextField menge, JTextField mengeneinheit, JTextArea zutaten) {
        String zutatName = zutat.getText();
        String mengeString = menge.getText();
        String mengeneinheitString = mengeneinheit.getText();
        String[] neuerEintrag = {zutatName, mengeString, mengeneinheitString};
        list.add(neuerEintrag);
        String eintrag = "";
        String ausgabe = "";
        for(String[] eintragString : list) {
            eintrag = "";
            eintrag = eintragString[0] + " " + eintragString[1] + " " + eintragString[2];
            ausgabe = ausgabe + eintrag + "\n";
        }
        zutaten.setText(ausgabe);
    }

    public void letzteZutatEntfernen() {
        
    }


}
