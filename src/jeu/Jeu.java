package jeu;

import java.util.*;
import cartes.*;

public class Jeu {
    private Sabot sabot;
    private List<Joueur> joueurs;
    private Iterator<Joueur> iter;

    public Jeu() {
    	JeuDeCartes jeuDeCartes = new JeuDeCartes();
        List<Carte> listeCartes = new ArrayList<>(Arrays.asList(jeuDeCartes.donnerCartes()));
        Collections.shuffle(listeCartes); // mélange
        sabot = new Sabot(listeCartes.toArray(new Carte[0])); // ✅ passage en paramètre
        joueurs = new ArrayList<>();
    }

    public void inscrire(Joueur... joueursAAjouter) {
        joueurs.addAll(Arrays.asList(joueursAAjouter));
        iter = joueurs.iterator();
    }

    public void distribuerCartes() {
        for (int i = 0; i < 6; i++) {
            for (Joueur j : joueurs) {
                Carte carte = sabot.piocher();
                if (carte != null) j.donner(carte);
            }
        }
    }

    public Joueur donnerJoueurSuivant() {
        if (!iter.hasNext()) iter = joueurs.iterator();
        return iter.next();
    }

    public String jouerTour(Joueur joueur) {
        StringBuilder sb = new StringBuilder();
        Carte carte = joueur.prendreCarte(sabot);
        sb.append("Le joueur ").append(joueur)
                .append(" a pioché ").append(carte).append("\n")
                .append("Il a dans sa main : ").append(joueur.getMain()).append("\n");

        Coup coup = joueur.choisirCoup(new HashSet<>(joueurs));
        joueur.retirerDeLaMain(coup.getCarte());
        if (coup.getCible() == null) {
            sb.append(joueur).append(" défausse ").append(coup.getCarte()).append("\n");
        } else {
            coup.getCible().getZoneDeJeu().deposer(coup.getCarte());
            sb.append(coup).append("\n");
        }

        return sb.toString();
    }

    public String lancer() {
        StringBuilder sb = new StringBuilder();
        boolean fin = false;
        while (!fin && !sabot.estVide()) {
            for (Joueur j : joueurs) {
                sb.append(jouerTour(j)).append("\n");
                if (j.getZoneDeJeu().donnerKmParcourus() >= 1000) {
                    sb.append(j).append(" a gagné !\n");
                    fin = true;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
