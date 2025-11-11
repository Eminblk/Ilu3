package jeu;
import cartes.*;

public class Coup {
    private Joueur joueurCourant;
    private Carte carte;
    private Joueur cible; // peut être null

    public Coup(Joueur joueurCourant, Carte carte, Joueur cible) {
        this.joueurCourant = joueurCourant;
        this.carte = carte;
        this.cible = cible;
    }

    public Joueur getJoueurCourant() { return joueurCourant; }
    public Carte getCarte() { return carte; }
    public Joueur getCible() { return cible; }

    public boolean estValide() {
        if (cible == null) return true;
        if (carte instanceof Attaque || carte instanceof Limite)
            return !joueurCourant.equals(cible);
        return joueurCourant.equals(cible);
    }

    @Override
    public String toString() {
        if (cible == null)
            return joueurCourant + " défausse la carte " + carte;
        return joueurCourant + " dépose la carte " + carte +
                " dans la zone de jeu de " + cible;
    }
}
