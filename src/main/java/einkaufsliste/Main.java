package einkaufsliste;

public class Main {
    public static void main(String[] args) {
        MainframeRezepteAuswahl mainframeRezepteAuswahl = new MainframeRezepteAuswahl();
        Mainframe mainframe = new Mainframe();
        MainframeSpeicher mainframeSpeicher = new MainframeSpeicher(mainframe, mainframeRezepteAuswahl);
        mainframe.initialize(mainframeSpeicher);
    }
    
}
