package einkaufsliste;

import java.util.HashMap;
import java.util.Map;

public class Kategorien {
    private static Map<String, String> zuordnung = new HashMap<String,String>();

    public static Map<String, String> getZuordnung() {
        zuordnung.put("Tiefkühllaugengebäck", "Tiefkühl");
        zuordnung.put("Lachs", "Tiefkühl");
        zuordnung.put("Spinat", "Tiefkühl");
        zuordnung.put("Erbsen", "Tiefkühl");
        zuordnung.put("Eis", "Tiefkühl");
        zuordnung.put("Brokkoli", "Tiefkühl");
        zuordnung.put("Pommes", "Tiefkühl");
        zuordnung.put("Kroketten", "Tiefkühl");
        zuordnung.put("Erbsen", "Tiefkühl");
        zuordnung.put("Möhren", "Tiefkühl");
        zuordnung.put("", "Tiefkühl");

        zuordnung.put("passierte Tomaten", "Vorrat");
        zuordnung.put("Nudeln", "Vorrat");
        zuordnung.put("Wrap", "Vorrat");
        zuordnung.put("scharfe Sauce", "Vorrat");
        zuordnung.put("Mayo", "Vorrat");
        zuordnung.put("Lasagneplatten", "Vorrat");
        zuordnung.put("Wraps", "Vorrat");
        zuordnung.put("Ketchup", "Vorrat");
        zuordnung.put("Senf", "Vorrat");
        zuordnung.put("Mehl", "Vorrat");
        zuordnung.put("Olivenöl", "Vorrat");
        zuordnung.put("Mais", "Vorrat");
        zuordnung.put("Paniermehl", "Vorrat");
        zuordnung.put("Eier", "Vorrat");
        zuordnung.put("Corned Beef", "Vorrat");
        zuordnung.put("Gewürzgurken", "Vorrat");
        zuordnung.put("Penne", "Vorrat");
        zuordnung.put("Sesamöl", "Vorrat");
        zuordnung.put("Mie Nudeln", "Vorrat");
        zuordnung.put("Fetuccine", "Vorrat");
        zuordnung.put("Proteinpulver", "Vorrat");
        zuordnung.put("geröstete Pinienkerne", "Vorrat");
        zuordnung.put("Tagliatelle", "Vorrat");
        zuordnung.put("Khiritharaki","Vorrat");
        zuordnung.put("Milch","Vorrat");

        zuordnung.put("Hähnchen", "Gekühlt");
        zuordnung.put("Hack", "Gekühlt");
        zuordnung.put("Streukäse", "Gekühlt");
        zuordnung.put("Käse", "Gekühlt");
        zuordnung.put("Schmand", "Gekühlt");
        zuordnung.put("Schinkenwürfel", "Gekühlt");
        zuordnung.put("Schafskäse", "Gekühlt");
        zuordnung.put("Hefe", "Gekühlt");
        zuordnung.put("Salami", "Gekühlt");
        zuordnung.put("Kochschinken", "Gekühlt");
        zuordnung.put("Cremefine", "Gekühlt");
        zuordnung.put("Butter", "Gekühlt");
        zuordnung.put("Frischkäse light", "Gekühlt");
        zuordnung.put("Tortellini", "Gekühlt");
        zuordnung.put("Mozarella", "Gekühlt");
        zuordnung.put("Feta light", "Gekühlt");
        zuordnung.put("Feta", "Gekühlt");

        zuordnung.put("Salat", "Gemüse / Obst");
        zuordnung.put("Gurke", "Gemüse / Obst");
        zuordnung.put("Tomaten", "Gemüse / Obst");
        zuordnung.put("Zuchini", "Gemüse / Obst");
        zuordnung.put("Karotten", "Gemüse / Obst");
        zuordnung.put("Zwiebeln", "Gemüse / Obst");
        zuordnung.put("Zwiebel", "Gemüse / Obst");
        zuordnung.put("Knoblauch", "Gemüse / Obst");
        zuordnung.put("Kartoffeln", "Gemüse / Obst");
        zuordnung.put("Eisbergsalat", "Gemüse / Obst");
        zuordnung.put("Frühlingszwiebeln", "Gemüse / Obst");
        zuordnung.put("Zitrone", "Gemüse / Obst");
        zuordnung.put("Porree", "Gemüse / Obst");
        zuordnung.put("Blattspinat", "Gemüse / Obst");

        return zuordnung;
    }
}
