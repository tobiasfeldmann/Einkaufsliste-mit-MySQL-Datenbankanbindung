package einkaufsliste;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mainframe extends JFrame {

    final private Font mainFont = new Font("Arial" ,  Font.PLAIN, 18);
    
    JButton speichereRezept, rezeptNamenFestlegen, letzteZutatEntfernen, zutatHinzufuegen, rezepteAuswahlOeffnen;

    JLabel anzeigeRezeptname;

    JTextField eingabeRezeptname, eingabeZutat, eingabeMenge, eingabeMengeneinheit;

    JTextArea aktuelleZutaten, vorhandeneRezepte;

    JPanel mainPanel;

    Rezept rezept;

    public void initialize() {

        MainframeRezepteAuswahl mainframeRezepteAuswahl = new MainframeRezepteAuswahl();

        //NORTH Panel

        /**
         * enthält die Anzeige für den Namen des Rezepts, sowie ein Feld für dessen Eingabe und einen Button zum festlegen
         */

        anzeigeRezeptname = new JLabel("Rezept",0);
        anzeigeRezeptname.setFont(mainFont);

        eingabeRezeptname = new JTextField("Rezeptname eingeben");
        eingabeRezeptname.setFont(mainFont);
        eingabeRezeptname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                eingabeRezeptname.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

                //späteres Hinzufügen einer Funktion, die das Textfeld von eingabeRezeptname leert
                /*try {
                    wait(5000);
                    eingabeRezeptname.setText("");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }*/
            }
        });

        rezeptNamenFestlegen = new JButton("Rezeptnamen festlegen");
        rezeptNamenFestlegen.setFont(mainFont);
        rezeptNamenFestlegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!eingabeRezeptname.getText().equals("")) {
                    anzeigeRezeptname.setText(eingabeRezeptname.getText());
                }
                rezept = new Rezept();
                rezept.setAnzahl(1);
                rezept.setRezeptname(eingabeRezeptname.getText());
                int neueID = DatabaseConnection.getLatestID();
                rezept.setRezeptID(neueID);
            }
        });

    

        JPanel northPanel = new JPanel(new GridLayout(1, 3,5,5));
        northPanel.setFont(mainFont);
        northPanel.add(anzeigeRezeptname);
        northPanel.add(eingabeRezeptname);
        northPanel.add(rezeptNamenFestlegen);
        northPanel.setOpaque(false);




        //EAST PANEL
        vorhandeneRezepte = new JTextArea();
        vorhandeneRezepte.setFont(mainFont);
        OperationsMainframe.gebeRezepteAus(vorhandeneRezepte);
        
        JPanel eastPanel = new JPanel(new GridLayout(1,1,5,5));
        eastPanel.setFont(mainFont);
        eastPanel.add(vorhandeneRezepte);
        eastPanel.setOpaque(false);



        //SOUTH Panel

        /**
         * fügt die aktuelle Zutat zu dem Rezept hinzu
         * Nur möglich wenn Name UND Menge eingetragen wurde
         */
        zutatHinzufuegen = new JButton("Zutat hinzufügen");
        zutatHinzufuegen.setFont(mainFont);
        zutatHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsMainframe.zutatHinzufuegen(eingabeZutat, eingabeMenge, eingabeMengeneinheit, aktuelleZutaten);
            }
        });

        /**
         * entfernt die zuletzt hinzugefügte Zutat wieder aus dem Rezept
         */
        letzteZutatEntfernen = new JButton("letzte Zutat entfernen");
        letzteZutatEntfernen.setFont(mainFont);
        letzteZutatEntfernen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperationsMainframe.letzteZutatEntfernen(aktuelleZutaten);
            }
        });

        /**
         * speichert das Rezept in der DB und setzt alle aktuellen Felder (Anzeige Rezeptname etc) zurück
         */
        speichereRezept = new JButton("Speichere Rezept");
        speichereRezept.setFont(mainFont);
        speichereRezept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnection.speichereRezept(rezept, OperationsMainframe.getArrayListOfZutaten());
                eingabeZutat.setText("Zutat");
                eingabeMenge.setText("Menge");
                eingabeMengeneinheit.setText("Mengeneinheit");
                OperationsMainframe.gebeRezepteAus(vorhandeneRezepte);
            }
        });

        rezepteAuswahlOeffnen = new JButton("Öffne Rezepteauswahl");
        rezepteAuswahlOeffnen.setFont(mainFont);
        rezepteAuswahlOeffnen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframeRezepteAuswahl.rezepteAuswahlOeffnen();
            }
        });
        

        JPanel southPanel = new JPanel(new GridLayout(4, 1,5,5));
        southPanel.setFont(mainFont);
        southPanel.add(zutatHinzufuegen);
        southPanel.add(letzteZutatEntfernen);
        southPanel.add(speichereRezept);
        southPanel.add(rezepteAuswahlOeffnen);
        southPanel.setOpaque(false);



        //WEST Panel

        aktuelleZutaten = new JTextArea("Aktuelle Zutaten");
        aktuelleZutaten.setFont(mainFont);

        JPanel westPanel = new JPanel(new GridLayout(1,1,5,5));
        westPanel.setFont(mainFont);
        westPanel.add(aktuelleZutaten);
        westPanel.setOpaque(false);



        //CENTER Panel

        //JTextField eingabeRezeptname, eingabeZutat, eingabeMenge, eingabeMengeneinheit;

        eingabeZutat = new JTextField("Zutat");
        eingabeZutat.setFont(mainFont);
        eingabeZutat.addFocusListener(new FocusListener() {
            @Override 
            public void focusGained(FocusEvent evt) {
                if(eingabeZutat.getText().equals("Zutat")) {
                    eingabeZutat.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(eingabeZutat.getText().equals("")) {
                    eingabeZutat.setText("Zutat");
                }
            }
        });

        eingabeMenge = new JTextField("Menge");
        eingabeMenge.setFont(mainFont);
        eingabeMenge.addFocusListener(new FocusListener() {
            @Override 
            public void focusGained(FocusEvent evt) {
                if(eingabeMenge.getText().equals("Menge")) {
                    eingabeMenge.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(eingabeMenge.getText().equals("")) {
                    eingabeMenge.setText("Menge");
                }
            }
        });
        
        eingabeMengeneinheit = new JTextField("Mengeneinheit");
        eingabeMengeneinheit.setFont(mainFont);
        eingabeMengeneinheit.addFocusListener(new FocusListener() {
            @Override 
            public void focusGained(FocusEvent evt) {
                if(eingabeMengeneinheit.getText().equals("Mengeneinheit")) {
                    eingabeMengeneinheit.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(eingabeMengeneinheit.getText().equals("")) {
                    eingabeMengeneinheit.setText("Mengeneinheit");
                }
            }
        });

        JPanel centerPanel = new JPanel(new GridLayout(3,1,5,5));
        centerPanel.setFont(mainFont);
        centerPanel.add(eingabeZutat);
        centerPanel.add(eingabeMenge);
        centerPanel.add(eingabeMengeneinheit);
        centerPanel.setOpaque(false);



        //MainPanel erstellen
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0,128,128));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        //NorthPanel hinzufuegen
        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        northPanel.setOpaque(false);
        mainPanel.add(northPanel, BorderLayout.NORTH);


        //EastPanel hinzufuegen
        eastPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        eastPanel.setOpaque(false);
        mainPanel.add(eastPanel, BorderLayout.EAST);


        //SouthPanel hinzufügen
        southPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        southPanel.setOpaque(false);
        mainPanel.add(southPanel, BorderLayout.SOUTH);


        //WestPanel hinzufügen
        westPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        westPanel.setOpaque(false);
        mainPanel.add(westPanel, BorderLayout.WEST);


        //CenterPanel hinzufügen
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        centerPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    

        add(mainPanel);
        setTitle("Einkaufsliste");
        setSize(1400,790);
        setMinimumSize(new Dimension(1400,790));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
