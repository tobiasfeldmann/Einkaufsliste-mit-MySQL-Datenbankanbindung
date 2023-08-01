package einkaufsliste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {
    static String url="jdbc:mysql://localhost:3306/einkaufslistedatabase";
    static String username = "tobi";
    static String password = "Bazinga0398x";
    static Map<String, Float> zutatenMap = new HashMap<>();
    static Map<String, String> zutatenEinheitMap = new HashMap<>();


    /**
     * Gibt die aktuell höchste vergebene RezeptID inkrementiert um 1 zurück, für die Erstellung eines neuen Rezepts.
     * @return neue RezeptID für neues Rezept
     */
    public static int getLatestID() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT MAX(idrezepte) FROM rezepte");

            if(result.next()) {
                int neuesteID = result.getInt(1);
                neuesteID++;
                return neuesteID;
            }

            connection.close();
            result.close();
            statement.close();

        } 
        catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            System.out.println(e);
        }

        return -1;
    }

    /**
     * gibt die aktuell höchste ID, um 1 inkrementiert, für die Speicherung einer neuen Zutat zurück
     * @return  höchste ID um 1 inkrementiert
     */
    public static int getLatestZutatId() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT MAX(id) FROM zutaten");

            if(result.next()) {
                int neuesteID = result.getInt(1);
                neuesteID++;
                return neuesteID;
            }

            connection.close();
            result.close();
            statement.close();

        } 
        catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            System.out.println(e);
        }

        return -1;
    }

    /**
     * Methode um ein erstelltes Rezept mitsamt aller Zutaten in der Datenbank zu speichern
     * @param rezept, Objekt der Klasse Rezept mit rezeptname und id
     * @param listeZutaten, ArrayList aus Zutaten, die jeweils einen Namen, Menge und Mengeneinheit haben
     */
    public static void speichereRezept(Rezept rezept, ArrayList<Zutat> listeZutaten) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String rezeptname = rezept.getRezeptname();
            int rezeptid = rezept.getRezeptID();
            String sqlRezept = "INSERT INTO rezepte (idrezepte, rezeptName) VALUES (" + rezeptid + ", '" + rezeptname + "')";
            statement.executeUpdate(sqlRezept);

            for(Zutat zutat : listeZutaten) {
                String zutatname = zutat.getName();
                int menge = zutat.getMenge();
                String einheit = zutat.getEinheit();
                int rezeptID = zutat.getRezeptID();
                int zutatid = getLatestZutatId();
                String sqlZutat = "INSERT INTO zutaten (idrezepte, zutatenname, menge, mengeneinheit, id) VALUES (" + rezeptID + ", '" + zutatname + "', " + menge + ", '" + einheit + "', " + zutatid + ")";
                statement.executeUpdate(sqlZutat);
            }

            connection.close();
            statement.close();
        } 
        catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            System.out.println(e);
        }

    }

    /**
     * gibt die in der Datenbank vorhandenen Rezepte als Strings in einem String Array aus
     * @return String[] Array aus rezepten
     */
    public static String[] holeRezepteAusDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT rezeptname FROM rezepte");

            int anzahlRezepte = getLatestID() - 1;
            String[] rueckgabe = new String[anzahlRezepte];

            int counter = 0;
            while(result.next()) {
                rueckgabe[counter] = result.getString(1);
                counter++;
            }
            connection.close();
            statement.close();
            result.close();
            return rueckgabe;
        } 
        catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            System.out.println(e);
        }

        return null;
    }

    
    /**
     * Entnimmt einer ArrayList die ausgewählten Rezepte und sucht für jedes einzelne die Zutaten raus und hinterlegt diese in einer Map
     * @param rezepte ArrayList der ausgewählten Rezepte
     */
    public static ArrayList<String> holeZutatenAusDB(ArrayList<String> rezepte, ArrayList<Float> portionen, ArrayList<String> zutatenListeSortierung) {
        ArrayList<String> zutaten = new ArrayList<String>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            int counter = 0;
            for(String rezept : rezepte) {
                String rezeptname = rezept;
                String sqlAnfrage = "SELECT zutatenname, menge, mengeneinheit FROM zutaten WHERE zutaten.idrezepte = (SELECT idrezepte FROM rezepte WHERE rezeptName = '" + rezeptname + "')";
                ResultSet result = statement.executeQuery(sqlAnfrage);
                while(result.next()) {
                    float mengeTemp = result.getInt(2) * portionen.get(counter);
                    zutatenMap.merge(result.getString(1),mengeTemp, Float::sum);
                    zutatenEinheitMap.put(result.getString(1), result.getString(3));
                }
                counter++;
            }
            connection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            e.printStackTrace();
        }
        for(String zutat : zutatenMap.keySet()) {
            String zutatKomplett = Float.toString(zutatenMap.get(zutat)) + " " + zutatenEinheitMap.get(zutat) + " " + zutat; 
            zutatenListeSortierung.add(zutat);
            zutaten.add(zutatKomplett);
        }
        
        OperationsMainframeRezepteAuswahl.speichereZutatenZurSortierung(zutatenListeSortierung);
        return zutaten;
    }
}
