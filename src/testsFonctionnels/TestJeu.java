package testsFonctionnels;
import jeu.*;
import cartes.*;
import utils.*;

public class TestJeu {
	
	public static void main(String[] args) {
		
		Jeu monJeu = new Jeu();
		Joueur joueur1 = new Joueur("Jack");
		Joueur joueur2 = new Joueur("Bill");
		Joueur joueur3 = new Joueur("Luffy");
		monJeu.inscrire(joueur1, joueur2, joueur3);
		monJeu.distribuerCartes();
		joueur1.afficherEtatJoueur();
		joueur2.afficherEtatJoueur();
		joueur3.afficherEtatJoueur();
		
		System.out.println(monJeu.jouerTour(joueur1));
		System.out.println(monJeu.jouerTour(joueur2));
		System.out.println(monJeu.jouerTour(joueur3));
		
	}

}
