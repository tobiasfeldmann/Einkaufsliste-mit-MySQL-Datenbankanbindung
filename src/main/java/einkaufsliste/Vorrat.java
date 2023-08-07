package einkaufsliste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vorrat {
    private static ArrayList<String> vorratListe = new ArrayList<String>();
    private static Map<String,Integer> vorrat = new HashMap<String, Integer>();

    public static void addVorratListe(String artikel, int index) {
        vorrat.put(artikel, index);
    }

    public static Map<String, Integer> getMap() {
        return vorrat;
    }

    public static ArrayList<String> getVorratListe() {
        return vorratListe;
    }

    public static void leereListeLeereMap() {
        vorratListe.clear();
        vorrat.clear();
    }
}
