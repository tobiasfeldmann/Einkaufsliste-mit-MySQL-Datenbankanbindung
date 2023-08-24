package einkaufsliste;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperationsMainframeRezepteAuswahl {
    private static ArrayList<String> ausgewaehlteRezepteListe = new ArrayList<String>();
    private static ArrayList<Float> portionenListe = new ArrayList<Float>();
    private static String[] rezepteAlsArray;
    private static ArrayList<String> zutatenAlsArray = new ArrayList<>();
    private static String ausgabe = "";
    private static Map<String, Integer> sonstiges = new HashMap<String, Integer>();
    private static ArrayList<String> zutatenZurSortierung = new ArrayList<String>();
    private static ArrayList<String> ausgabeZutaten = new ArrayList<String>();
    private static ArrayList<String> zutatenDB = new ArrayList<String>();

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
        ausgabe = "";
        for(String zutat : DatabaseConnection.holeZutatenAusDB(ausgewaehlteRezepteListe, portionenListe, zutatenZurSortierung, zutatenDB)) {
            ausgabe = ausgabe + zutat + "\n";
            zutatenAlsArray.add(zutat);
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
        portionenString = portionenString.replace(',', '.');
        float portionenFloat = Float.parseFloat(portionenString);
        return portionenFloat;
    }

    /**
     * Soll die erzeugte ArrayList aus zutaten zu einem String zusammenführen und dann als txt Datei speichern, um diese später drucken zu können´
     * Außerdem wird eine zweite Version dieses Strings erzeugt, mit Trennzeichen / zwischen den einzelnen Produkten um diese später auf der WEbsite auseinander
     * halten zu können
     */
    public static void speichereDatei() {
        //Die jeweiligen Paths + Files
        String path = "C:/temp/tempDatei.txt";
        String pathJS = "C:/temp/javaScriptTempDatei.txt";
        File file = new File(path);
        File fileJS = new File(pathJS);
        OperationsMainframeRezepteAuswahl.sortiereMitKategorien();
        if(ausgabeZutaten.size() > 0) {
            try {
                /**
                 * PrintWriter zur Erzeugung einer txt Datei zum ausdrucken
                 */
                PrintWriter writer = new PrintWriter(new FileWriter(path));
                if(file.exists()) {
                    int counter = 1;
                    String temp = "";
                    for(String s : ausgabeZutaten) {
                        if(counter == 1) {
                            if(s.equals("Vorrat: ") || s.equals("Gemüse / Obst: ") || s.equals("Gekühlt: ") || s.equals("Tiefkühl: ") || s.equals("Sonstiges: ")) {
                                writer.println("  ");
                                writer.println(s);
                                continue;
                            }
                            temp = temp + s;
                            counter++;
                        }
                        else {
                            if(s.equals("Vorrat: ") || s.equals("Gemüse / Obst: ") || s.equals("Gekühlt: ") || s.equals("Tiefkühl: ") || s.equals("Sonstiges: ")) {
                                writer.println(temp);
                                temp = "";
                                counter = 1;
                                writer.println(" ");
                                writer.println(s);
                                continue;
                            }
                            temp = berechneLeerzeichen(temp) + s;
                            writer.println(temp);
                            counter = 1;
                            temp = "";
                        }
                    }
                    writer.flush();
                    writer.close();
                }
                /**
                 * PrintWriter um eine TextDatei extra für die Auswertung auf der Website zu erzeugen
                 */
                PrintWriter writerJS = new PrintWriter(new FileWriter(pathJS));
                if(fileJS.exists()) {
                    int counter = 1;
                    String temp = "";
                    for(String s : ausgabeZutaten) {
                        s = s.replaceAll("ä", "ae");
                        s = s.replaceAll("ü", "ue");
                        s = s.replaceAll("ö", "oe");
                        if(counter == 1) {
                            if(s.equals("Vorrat: ") || s.equals("Gemüse / Obst: ") || s.equals("Gekühlt: ") || s.equals("Tiefkühl: ") || s.equals("Sonstiges: ")) {
                                writerJS.println(" ");
                                writerJS.println(s + "*");
                                continue;
                            }
                            temp = temp + s + "*";
                            counter++;
                        }
                        else {
                            if(s.equals("Vorrat: ") || s.equals("Gemüse / Obst: ") || s.equals("Gekühlt: ") || s.equals("Tiefkühl: ") || s.equals("Sonstiges: ")) {
                                writerJS.println(temp);
                                temp = "";
                                counter = 1;
                                writerJS.println(" ");
                                writerJS.println(s);
                                continue;
                            }
                            temp = berechneLeerzeichen(temp) + s + "*";
                            writerJS.println(temp);
                            counter = 1;
                            temp = "";
                        }
                    }
                    writerJS.flush();
                    writerJS.close();
                }

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //druckeDatei(file);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //loescheDatei(file);
    }

    /**
     * Methode um die Datei zu drucken
     * @param file, zu druckende Datei
     */
    public static void druckeDatei(File file) {
        if(file.exists()) {
            try {
                Desktop.getDesktop().print(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loescht die zuvor erstellte Datei wieder, da diese nur temporär zum drucken diente
     */
    public static void loescheDatei(File file) {
        file.delete();
    }

    /**
     * Methode um die erste Zeile Zeichen besser von der zweiten durch eine feste Anzahl aus Zeichen zu trennen
     * @param s
     * @return
     */
    public static String berechneLeerzeichen(String s) {
        StringBuilder builder = new StringBuilder(s);
        int abstand = 45;
        int laenge = s.length();
        int differenz = abstand - laenge;
        for(int i1 = differenz; i1 > 0; i1--) {
            builder.append(" ");
        }
        return builder.toString();
    }

    /**
     * Speichert die Zutaten je nach Kategorie in einer entsprechenden Map mit passendem Index von zutatenAlsArray ab
     * @param zutatenListeAusMethode, ArrayListe NUR mit den Bezeichnungen der Zutaten
     */
    public static void speichereZutatenZurSortierung(ArrayList<String> zutatenListeAusMethode) {
        zutatenZurSortierung = zutatenListeAusMethode;
    }

    /**
     * sortiert die eintrage aus der ArrayList zutatenZurSortierung in entsprechende Maps für die einzelnen Kategorien
     */
    public static void sortiereNachKategorien() {
        int counter = 0;
        Map<String,String> map = Kategorien.getZuordnung();
        for(String zutat : zutatenZurSortierung) {
            if(map.containsKey(zutat)) {
                String kategorie = map.get(zutat);
                switch(kategorie) {
                    case("Vorrat"):
                        Vorrat.addVorratListe(zutat, counter);
                        break;
                    case("Gekühlt"):
                        Gekuehlt.addGekuehltListe(zutat, counter);
                        break;
                    case("Tiefkühl"):
                        Tiefkuehl.addTiefkuehlListe(zutat, counter);
                        break;
                    case("Gemüse / Obst"):
                        GemueseObst.addGemueseObstListe(zutat, counter);
                        break;
                }
            }
            else {
                sonstiges.put(zutat, counter);
            }
            counter++;
        }
    }


    /**
     * Sortiert die Zutaten in einer ArrayList mit vorhergehender Bezeichnung der Kategorie
     * Hierfür werden die entsprechenden Maps abgerufen und durchlaufen
     */
    public static void sortiereMitKategorien() {
        Map<String, Integer> mapVorrat = Vorrat.getMap();
        Map<String, Integer> mapGemueseObst = GemueseObst.getMap();
        Map<String, Integer> mapGekuehlt = Gekuehlt.getMap();
        Map<String, Integer> mapTiefkuehl = Tiefkuehl.getMap();

        ausgabeZutaten.add("Vorrat: ");
        for(String key : mapVorrat.keySet()) {
            ausgabeZutaten.add(zutatenAlsArray.get(mapVorrat.get(key)));
        }

        ausgabeZutaten.add("Gemüse / Obst: ");
        for(String key: mapGemueseObst.keySet()) {
            ausgabeZutaten.add(zutatenAlsArray.get(mapGemueseObst.get(key)));
        }

        ausgabeZutaten.add("Gekühlt: ");
        for(String key : mapGekuehlt.keySet()) {
            ausgabeZutaten.add(zutatenAlsArray.get(mapGekuehlt.get(key)));
        }

        ausgabeZutaten.add("Tiefkühl: ");
        for(String key : mapTiefkuehl.keySet()) {
            ausgabeZutaten.add(zutatenAlsArray.get(mapTiefkuehl.get(key)));
        }

        ausgabeZutaten.add("Sonstiges: ");
        for(String key : sonstiges.keySet()) {
            ausgabeZutaten.add(zutatenAlsArray.get(sonstiges.get(key)));
        }
    }

    /**
     * setzt alle ArrayListen, die für die Ausgabe und verarbeitung der Rezepte und Zutaten verwendet werden zurück bzw. leert sie
     * @param ausgewaehlteRezepte
     * @param aktuelleZutaten
     * @param eingabeRezept
     * @param portionsAuswahl
     * @param anzeigePortionen
     * @param anzeigeRezepteingabe
     */
    public static void felderZurueckSetzen(JTextArea ausgewaehlteRezepte, JTextArea aktuelleZutaten, JTextField eingabeRezept,JTextField portionsAuswahl, JLabel anzeigePortionen, JLabel anzeigeRezepteingabe) {
        ausgewaehlteRezepte.setText("");
        aktuelleZutaten.setText("");
        eingabeRezept.setText("Hier Rezept eingeben");
        portionsAuswahl.setText("Hier Portionen eingeben");
        anzeigePortionen.setText("Anzeige Portionen");
        anzeigeRezepteingabe.setText("Anzeige Rezept");
        ausgewaehlteRezepteListe.clear();
        ausgabeZutaten.clear();
        portionenListe.clear();
        zutatenDB.clear();
        zutatenAlsArray.clear();
        zutatenZurSortierung.clear();
        sonstiges.clear();
        DatabaseConnection.leereZutatenEinheitMap();
        DatabaseConnection.leerezutatenMap();
        Vorrat.leereListeLeereMap();
        Tiefkuehl.leereListeLeereMap();
        GemueseObst.leereListeLeereMap();
        Gekuehlt.leereListeLeereMap();

    }
}
