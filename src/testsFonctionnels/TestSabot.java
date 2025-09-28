package testsFonctionnels;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {

    private final JeuDeCartes jeu = new JeuDeCartes();
    private final Sabot sabot = new Sabot(jeu.donnerCartes());
    
    public void questionA() {
        while (!sabot.estVide()) {
            Carte carte = sabot.piocher();
            System.out.println("Je pioche " + carte);
        }
    }

    public void questionB() {
        for (Iterator<Carte> it = sabot.iterator(); it.hasNext();) {
            Carte carte = it.next();
            System.out.println("Je pioche " + carte);
            it.remove();
        }
    }

    public void questionC() {
        try {

            Carte cartePiochee = sabot.piocher();
            System.out.println("Je pioche (hors boucle) " + cartePiochee);
            for (Iterator<Carte> it = sabot.iterator(); it.hasNext();) {
                Carte carte = it.next();
                System.out.println("Je pioche " + carte);
                it.remove();

                try {
                    sabot.piocher();
                } catch (ConcurrentModificationException e) {
                    System.out.println("Exception attendue (piocher pendant l’itération) : " + e);
                }

                try {
                    sabot.ajouterCarte(new Botte(Type.ACCIDENT));
                } catch (ConcurrentModificationException e) {
                    System.out.println("Exception attendue (ajout pendant l’itération) : " + e);
                }
            }

            Iterator<Carte> fin = sabot.iterator();
            System.out.println("\nLa pioche contient encore des cartes ? " + fin.hasNext());

        } catch (NoSuchElementException e) {
            System.out.println("La pioche est vide : " + e);
        }
    }

    public static void main(String[] args) {
        TestSabot test = new TestSabot();
        System.out.println("--- Question A ---");
        test.questionA();
        System.out.println("\n--- Question B ---");
        TestSabot test2 = new TestSabot();
        test2.questionB();

        System.out.println("\n--- Question C ---");
        TestSabot test3 = new TestSabot();
        test3.questionC();
    }
}
