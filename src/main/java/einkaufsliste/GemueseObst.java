package einkaufsliste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GemueseObst {
    private static ArrayList<String> gemueseObstListe = new ArrayList<String>();
    private static Map<String,Integer> gemueseobst = new HashMap<String, Integer>();

    public static void addGemueseObstListe(String artikel, int index) {
        gemueseobst.put(artikel, index);
    }

    public static Map<String, Integer> getMap() {
        return gemueseobst;
    }

    public static ArrayList<String> getGemueseObstListe() {
        return gemueseObstListe;
    }
}
