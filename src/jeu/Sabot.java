package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.*;

public class Sabot implements Iterable<Carte> {

    private Carte[] sabot;
    private int nbCartes;
    private int nbOperationReference = 0;

    // ✅ Nouveau constructeur
    public Sabot(Carte[] cartes) {
        sabot = cartes;
        nbCartes = cartes.length;
    }

    @Override
    public Iterator<Carte> iterator() {
        return new Iterateur();
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte carte) {
        if (nbCartes < sabot.length) {
            sabot[nbCartes] = carte;
            nbCartes++;
            nbOperationReference++;
        } else {
            throw new IllegalStateException("Capacité du sabot dépassée");
        }
    }

    public Carte piocher() {
        Iterator<Carte> iter = iterator();
        Carte premiere = iter.next();
        iter.remove();
        System.out.println("Je pioche : " + premiere + "\n");
        return premiere;
    }

    // ----- Classe interne -----
    public class Iterateur implements Iterator<Carte> {

        int indexIter = 0;
        boolean nextEffectue = false;
        int nbOperation = nbOperationReference;

        private void verificationConcurrence() {
            if (nbOperation != nbOperationReference)
                throw new ConcurrentModificationException("Deux modifs concurrentes !");
        }

        @Override
        public boolean hasNext() {
            return indexIter < nbCartes;
        }

        @Override
        public Carte next() {
            verificationConcurrence();
            if (!hasNext()) {
                throw new IllegalStateException("Plus de cartes !");
            }
            Carte carte = sabot[indexIter];
            indexIter++;
            nextEffectue = true;
            return carte;
        }

        @Override
        public void remove() {
            verificationConcurrence();
            if (!nextEffectue || nbCartes < 1) {
                throw new NoSuchElementException("Erreur remove");
            }
            for (int i = indexIter - 1; i < nbCartes - 1; i++) {
                sabot[i] = sabot[i + 1];
            }
            indexIter--;
            nbCartes--;
            nextEffectue = false;
        }
    }
}
