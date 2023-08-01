package einkaufsliste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tiefkuehl {
    private static ArrayList<String> tiefkuehlListe = new ArrayList<String>();
    private static Map<String,Integer> tiefkuehl = new HashMap<String, Integer>();

    public static void addTiefkuehlListe(String artikel, int index) {
        tiefkuehl.put(artikel, index);
    }

    public static Map<String, Integer> getMap() {
        return tiefkuehl;
    }

    public static ArrayList<String> getTiefkuehlListe() {
        return tiefkuehlListe;
    }
}
