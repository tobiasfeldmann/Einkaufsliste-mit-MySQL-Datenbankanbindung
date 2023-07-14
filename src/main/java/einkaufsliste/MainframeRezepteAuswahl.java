package einkaufsliste;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainframeRezepteAuswahl extends JFrame {

    final private Font mainFont = new Font("Arial", Font.PLAIN, 18);
    
    JTextArea vorhandeneRezepte, ausgewaehlteRezepte, aktuelleZutaten;

    JButton rezeptHinzufuegen, letztesRezeptEntfernen, zueruckZurRezeptEingabe;

    JTextField eingabeRezept;

    public void rezepteAuswahlOeffnen() {

        //CENTER Panel
        vorhandeneRezepte = new JTextArea("Vorhandene Rezepte");
        vorhandeneRezepte.setFont(mainFont);
        OperationsMainframeRezepteAuswahl.gebeRezepteAus(vorhandeneRezepte);

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

        rezeptHinzufuegen = new JButton("Rezept zur Liste");
        rezeptHinzufuegen.setFont(mainFont);
        rezeptHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsMainframeRezepteAuswahl.fuegeRezeptHinzu(eingabeRezept.getText(), ausgewaehlteRezepte);
            }
        });

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

        JPanel centerPanel = new JPanel(new GridLayout(5,1,5,5));
        centerPanel.setFont(mainFont);
        centerPanel.add(vorhandeneRezepte);
        centerPanel.add(eingabeRezept);
        centerPanel.add(rezeptHinzufuegen);
        centerPanel.add(letztesRezeptEntfernen);
        centerPanel.add(zueruckZurRezeptEingabe);
        centerPanel.setOpaque(false);



        //EAST Panel
        aktuelleZutaten = new JTextArea("Zutatenliste");
        aktuelleZutaten.setFont(mainFont);
        OperationsMainframeRezepteAuswahl.gebeZutatenlisteAus(aktuelleZutaten);

        JPanel eastPanel = new JPanel(new GridLayout(1,1,5,5));
        eastPanel.setFont(mainFont);
        eastPanel.add(aktuelleZutaten);
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
