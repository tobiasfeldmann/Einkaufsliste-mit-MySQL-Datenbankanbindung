package einkaufsliste;

public class MainframeSpeicher {
    Mainframe mainframe;
    MainframeRezepteAuswahl mainframeRezepteAuswahl;

    public MainframeSpeicher(Mainframe mainframeX, MainframeRezepteAuswahl mainframeRezepteAuswahlX) {
        mainframe = mainframeX;
        mainframeRezepteAuswahl = mainframeRezepteAuswahlX;
    }

    public Mainframe getMainframe() {
        return this.mainframe;
    }

    public MainframeRezepteAuswahl getMainframeRezepteAuswahl() {
        return this.mainframeRezepteAuswahl;
    }

    
}
