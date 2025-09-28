package jeu;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {

    private final Carte[] cartes;
    private int nbCartes;
    private int nbOperations = 0; 

    public Sabot(Carte[] cartesInitiales) {
        this.cartes = cartesInitiales;
        this.nbCartes = cartesInitiales.length;
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte c) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacité du sabot dépassée");
        }
        cartes[nbCartes++] = c;
        nbOperations++;
    }

    public Carte piocher() {
        Iterator<Carte> it = iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Sabot vide");
        }
        Carte carte = it.next();
        it.remove();
        return carte;
    }

    @Override
    public Iterator<Carte> iterator() {
        return new Iterateur();
    }

    private class Iterateur implements Iterator<Carte> {
        private int indice = 0;
        private boolean nextEffectue = false;
        private int nbOperationsReference = nbOperations;

        private void verificationConcurrence() {
            if (nbOperations != nbOperationsReference) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
        	verificationConcurrence();
            return indice < nbCartes;
        }

        @Override
        public Carte next() {
        	verificationConcurrence();
            if (!hasNext()) throw new NoSuchElementException();
            nextEffectue = true;
            return cartes[indice++];
        }

        @Override
        public void remove() {
        	verificationConcurrence();
            if (!nextEffectue) throw new IllegalStateException();
            for (int i = indice - 1; i < nbCartes - 1; i++) {
                cartes[i] = cartes[i + 1];
            }
            nbCartes--;
            indice--;
            nextEffectue = false;
            nbOperations++;
            nbOperationsReference++;
        }
    }
}
