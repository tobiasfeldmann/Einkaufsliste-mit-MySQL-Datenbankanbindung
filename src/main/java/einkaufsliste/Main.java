package einkaufsliste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/einkaufslistedatabase";
        String username = "tobi";
        String password = "Bazinga0398x";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM zutaten");

            while (result.next()) {
                System.out.println("Zutatname: " + result.getString(1) + "\n" + "Menge: " + result.getInt(2) + "\n" + "Mengeneinheit: " + result.getString(3) + "\n");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        Mainframe mainframe = new Mainframe();
        mainframe.initialize();
    }
    
}
