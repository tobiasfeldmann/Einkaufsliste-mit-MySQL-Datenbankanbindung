package einkaufsliste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gekuehlt {
    private static ArrayList<String> gekuehltListe = new ArrayList<String>();
    private static Map<String,Integer> gekuehlt = new HashMap<String, Integer>();

    public static void addGekuehltListe(String artikel, int index) {
        gekuehlt.put(artikel, index);
    }

    public static Map<String, Integer> getMap() {
        return gekuehlt;
    }

    public static ArrayList<String> getGekuehltListe() {
        return gekuehltListe;
    }

    public static void leereListeLeereMap() {
        gekuehltListe.clear();
        gekuehlt.clear();
    }
}
