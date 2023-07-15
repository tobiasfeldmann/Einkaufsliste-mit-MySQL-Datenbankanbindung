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
    static Map<String, Integer> zutatenMap = new HashMap<>();


    /**
     * Testmethode für die Verbindung mit der Datenbank
     */
    public static void databaseTest() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM zutaten");

            while (result.next()) {
                System.out.println("Zutatname: " + result.getString(1) + "\n" + "Menge: " + result.getInt(2) + "\n" + "Mengeneinheit: " + result.getString(3) + "\n");
            }
            connection.close();
            result.close();
            statement.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

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
        /*System.out.println("Rezeptname: " + rezept.getRezeptname() + " RezeptID: " + rezept.getRezeptID());
        for(Zutat zutat : listeZutaten) {
            System.out.println(zutat.getName());
            System.out.println(zutat.getMenge());
            System.out.println(zutat.getEinheit());
            System.out.println("zugehörig zu:" + zutat.getRezeptID());
        }*/

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
    public static void holeZutatenAusDB(ArrayList<String> rezepte) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            for(String rezept : rezepte) {
                String rezeptname = rezept;
                String sqlAnfrage = "SELECT zutatenname, menge FROM zutaten WHERE zutaten.idrezepte = (SELECT idrezepte FROM rezepte WHERE rezeptName = '" + rezeptname + "')";
                ResultSet result = statement.executeQuery(sqlAnfrage);
                while(result.next()) {
                    zutatenMap.merge(result.getString(1), result.getInt(2), Integer::sum);
                }
                System.out.println(zutatenMap);
            }
        } catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            e.printStackTrace();
        }
    }
}
