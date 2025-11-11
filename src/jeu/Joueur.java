package jeu;
import java.util.*;
import cartes.*;

public class Joueur {
    private String nom;
    private ZoneDeJeu zone;
    private MainJoueur main;

    public Joueur(String nom) {
        this.nom = nom;
        this.zone = new ZoneDeJeu();
        this.main = new MainJoueur();
    }

    public String getNom() { return nom; }
    public ZoneDeJeu getZoneDeJeu() { return zone; }
    public MainJoueur getMain() { return main; }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Joueur j) && nom.equals(j.nom);
    }

    @Override
    public String toString() { return nom; }

    public void donner(Carte c) { main.prendre(c); }
    public void retirerDeLaMain(Carte c) { main.jouer(c); }

    public Carte prendreCarte(Sabot sabot) {
        if (sabot.estVide()) return null;
        Carte carte = sabot.piocher();
        donner(carte);
        return carte;
    }

    public Set<Coup> coupsPossibles(Set<Joueur> participants) {
        Set<Coup> coups = new HashSet<>();
        for (Joueur j : participants) {
            for (Carte c : main) {
                Coup coup = new Coup(this, c, j);
                if (coup.estValide()) coups.add(coup);
            }
        }
        return coups;
    }

    public Set<Coup> coupsDefausse() {
        Set<Coup> coups = new HashSet<>();
        for (Carte c : main) coups.add(new Coup(this, c, null));
        return coups;
    }

    public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> possibles = coupsPossibles(participants);
        List<Coup> coups = possibles.isEmpty()
                ? new ArrayList<>(coupsDefausse())
                : new ArrayList<>(possibles);
        return coups.get(new Random().nextInt(coups.size()));
    }

    public String afficherEtatJoueur() {
        return nom + " : bottes=" + zone.getPileBataille()
                + ", limite=" + zone.donnerLimitationVitesse()
                + ", bataille=" + zone.getPileBataille()
                + ", main=" + main;
    }
}
