package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {

    public static <E> boolean testOccurrences(List<E> liste1, List<E> liste2) {
        if (liste1.size() != liste2.size()) return false;
        for (E e : liste1) {
            if (Collections.frequency(liste1, e) != Collections.frequency(liste2, e)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        List<Carte> listeCarteNonMelangee = new LinkedList<>();
        for (Carte carte : jeu.donnerCartes()) {
            listeCarteNonMelangee.add(carte);
        }

        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
        System.out.println("=- Liste originale -=");
        System.out.println(listeCartes);

        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println("\n- Liste melange --");
        System.out.println(listeCartes);

        System.out.println("\nListe melange sans erreur ? "
                + GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));

        listeCartes = GestionCartes.rassembler(listeCartes);
        System.out.println("\n=- Liste rassemble -=");
        System.out.println(listeCartes);

        System.out.println("\nListe rassemble sans erreur ? "
                + GestionCartes.verifierRassemblement(listeCartes));

        System.out.println("\nTest des occurrences conserv√©es ? "
                + testOccurrences(listeCarteNonMelangee, listeCartes));

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = List.of(1, 1, 2, 1, 3);
        List<Integer> l3 = List.of(1, 4, 3, 2);
        List<Integer> l4 = List.of(1, 1, 2, 3, 1);

        System.out.println("\n==- Tests de rassemblement simples -==");
        System.out.println("[]" + GestionCartes.verifierRassemblement(l1));
        System.out.println("[1,1,2,1,3]" + GestionCartes.verifierRassemblement(new ArrayList<>(l2)));
        System.out.println("[1,4,3,2]" + GestionCartes.verifierRassemblement(new ArrayList<>(l3)));
        System.out.println("[1,1,2,3,1]í " + GestionCartes.verifierRassemblement(new ArrayList<>(l4)));
    }
}
