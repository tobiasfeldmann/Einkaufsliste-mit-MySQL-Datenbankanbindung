package einkaufsliste;

public class Zutat {
    int rezeptID;
    
    String name;
    int menge;
    String einheit;

    public Zutat(String name, int menge, String einheit) {
        this.name = name;
        this.menge = menge;
        this.einheit = einheit;
    }


    public int getRezeptID() {
        return this.rezeptID;
    }

    public void setRezeptID(int rezeptID) {
        this.rezeptID = rezeptID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMenge() {
        return this.menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public String getEinheit() {
        return this.einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

}
