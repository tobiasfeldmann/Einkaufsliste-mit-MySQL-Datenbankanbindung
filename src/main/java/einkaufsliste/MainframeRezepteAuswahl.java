package einkaufsliste;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainframeRezepteAuswahl extends JFrame {

    final private Font mainFont = new Font("Arial", Font.PLAIN, 18);
    
    JScrollPane scrollbarZutaten, scrollbarRezepte;

    JTextArea vorhandeneRezepte, ausgewaehlteRezepte, aktuelleZutaten;

    JButton rezeptHinzufuegen, letztesRezeptEntfernen, zueruckZurRezeptEingabe, zutatenAusgeben;

    JTextField eingabeRezept, portionsAuswahl;

    String temp = "";

    String[] rezepte;

    public void rezepteAuswahlOeffnen() {

        rezepte = DatabaseConnection.holeRezepteAusDB();

        //NORTH Panel

        portionsAuswahl = new JTextField("Anzahl Portionen eingeben");
        portionsAuswahl.setFont(mainFont);
        portionsAuswahl.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if(portionsAuswahl.getText().equals("Anzahl Portionen eingeben")) {
                    portionsAuswahl.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(portionsAuswahl.getText().equals("")) {
                    portionsAuswahl.setText("Anzahl Portionen eingeben");
                }
            }
        });

        eingabeRezept = new JTextField("Hier Rezepte eingeben");
        eingabeRezept.setFont(mainFont);
        eingabeRezept.addFocusListener(new FocusListener() {
            @Override 
            public void focusGained(FocusEvent evt) {
                if(eingabeRezept.getText().equals("Hier Rezepte eingeben")) {
                    eingabeRezept.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(eingabeRezept.getText().equals("")) {
                    eingabeRezept.setText("Hier Rezepte eingeben");
                }
            }
        });
        eingabeRezept.addKeyListener(new KeyListener() {

            //Dropdown menü https://stackoverflow.com/questions/14186955/create-a-autocompleting-textbox-in-java-with-a-dropdown-list
            @Override
            public void keyPressed(KeyEvent e) {
                eingabeRezept.setText(temp);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                temp = eingabeRezept.getText();
                System.out.println(temp + "Test");
                OperationsMainframeRezepteAuswahl.gebeRezepteVorschlaegeAus(eingabeRezept, rezepte);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println(e.getKeyChar());
            }
        });

        JPanel northPanel = new JPanel(new GridLayout(1,2,5,5));
        northPanel.setFont(mainFont);
        northPanel.setOpaque(false);
        northPanel.add(portionsAuswahl);
        northPanel.add(eingabeRezept);

        //CENTER Panel

        vorhandeneRezepte = new JTextArea("Vorhandene Rezepte");
        vorhandeneRezepte.setFont(mainFont);
        OperationsMainframeRezepteAuswahl.gebeRezepteAus(vorhandeneRezepte);

        rezeptHinzufuegen = new JButton("Rezept zur Liste");
        rezeptHinzufuegen.setFont(mainFont);
        rezeptHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float temp = OperationsMainframeRezepteAuswahl.stringZuFloat(portionsAuswahl);
                OperationsMainframeRezepteAuswahl.fuegeRezeptHinzu(eingabeRezept.getText(), ausgewaehlteRezepte, temp);
            }
        });

        scrollbarRezepte = new JScrollPane(vorhandeneRezepte);

        letztesRezeptEntfernen = new JButton("Letztes Rezept wieder entfernen");
        letztesRezeptEntfernen.setFont(mainFont);
        letztesRezeptEntfernen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsMainframeRezepteAuswahl.entferneRezept(ausgewaehlteRezepte);
            }
        });

        zueruckZurRezeptEingabe = new JButton("Zurück zur Rezepteingabe");
        zueruckZurRezeptEingabe.setFont(mainFont);
        zueruckZurRezeptEingabe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mainframe mainframe = new Mainframe();
                mainframe.initialize();
            }
        });

        zutatenAusgeben = new JButton("Zutaten ausgeben");
        zutatenAusgeben.setFont(mainFont);
        zutatenAusgeben.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsMainframeRezepteAuswahl.gebeZutatenlisteAus(aktuelleZutaten);
            }
        });

        JPanel centerPanel = new JPanel(new GridLayout(5,1,5,5));
        centerPanel.setFont(mainFont);
        centerPanel.add(scrollbarRezepte);
        centerPanel.add(rezeptHinzufuegen);
        centerPanel.add(letztesRezeptEntfernen);
        centerPanel.add(zueruckZurRezeptEingabe);
        centerPanel.add(zutatenAusgeben);
        centerPanel.setOpaque(false);



        //EAST Panel
        aktuelleZutaten = new JTextArea("Zutatenliste");
        aktuelleZutaten.setFont(mainFont);

        scrollbarZutaten = new JScrollPane(aktuelleZutaten);

        JPanel eastPanel = new JPanel(new GridLayout(1,1,5,5));
        eastPanel.setFont(mainFont);
        eastPanel.add(scrollbarZutaten);
        eastPanel.setOpaque(false);


        //WEST Panel
        ausgewaehlteRezepte = new JTextArea("Ausgewählte Rezepte");
        ausgewaehlteRezepte.setFont(mainFont);

        JPanel westPanel = new JPanel(new GridLayout(1,1,5,5));
        westPanel.setFont(mainFont);
        westPanel.add(ausgewaehlteRezepte);
        westPanel.setOpaque(false);



        //Hinzufügen der Panels zum mainPanel

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0,128,128));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(northPanel, BorderLayout.NORTH);

        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        eastPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(eastPanel, BorderLayout.EAST);

        westPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(westPanel, BorderLayout.WEST);

        add(mainPanel);
        setTitle("Einkaufsliste");
        setSize(1400,790);
        setMinimumSize(new Dimension(1400,790));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
