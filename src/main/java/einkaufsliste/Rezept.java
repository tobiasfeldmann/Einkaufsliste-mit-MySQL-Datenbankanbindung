package einkaufsliste;

public class Rezept {
    int rezeptID;
    int anzahl;
    String rezeptname;


    public int getRezeptID() {
        return this.rezeptID;
    }

    public void setRezeptID(int rezeptID) {
        this.rezeptID = rezeptID;
    }

    public int getAnzahl() {
        return this.anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public String getRezeptname() {
        return this.rezeptname;
    }

    public void setRezeptname(String name) {
        this.rezeptname = name;
    }

    public static Rezept neuesRezept() {
        return new Rezept();
    }

}
