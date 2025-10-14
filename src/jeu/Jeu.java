package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Jeu {

    private Sabot sabot;

    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();

        Carte[] cartes = jeuDeCartes.donnerCartes();
        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, cartes);

        Collections.shuffle(listeCartes);

        sabot = new Sabot(listeCartes.toArray(new Carte[0]));
    }

    public Sabot getSabot() {
        return sabot;
    }
}
