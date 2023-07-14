package einkaufsliste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
    static String url="jdbc:mysql://localhost:3306/einkaufslistedatabase";
    static String username = "tobi";
    static String password = "Bazinga0398x";


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

    public static int getLatestID() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT MAX(idrezepte) FROM rezepte");

            if(result.next()) {
                int neuesteID = result.getInt(1);
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

    public static void speichereRezept(Rezept rezept, ArrayList<Zutat> listeZutaten) {
        System.out.println("Rezeptname: " + rezept.getRezeptname() + " RezeptID: " + rezept.getRezeptID());
        for(Zutat zutat : listeZutaten) {
            System.out.println(zutat.getName());
            System.out.println(zutat.getMenge());
            System.out.println(zutat.getEinheit());
            System.out.println("zugehörig zu:" + zutat.getRezeptID());
        }

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
            
        } 
        catch (Exception e) {
            System.out.println("Fehlgeschlagen");
            System.out.println(e);
        }

    }
}
