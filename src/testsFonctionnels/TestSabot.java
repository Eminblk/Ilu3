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
			System.out.println("Je pioche " + cartePiochee);

			try {
				for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
					Carte carte = iterator.next();
					System.out.println("Je pioche " + carte);
					sabot.piocher();
				}
			} catch (ConcurrentModificationException e) {
				System.out.println("Exception attendue car piocher pendant l’itération : " + e);
			}

			try {
				for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
					Carte carte = iterator.next();
					System.out.println("Je pioche " + carte);
					sabot.ajouterCarte(new Botte(Type.ACCIDENT));
				}
			} catch (ConcurrentModificationException e) {
				System.out.println("Exception attendue car ajout pendant l’itération : " + e);
			}
			

		} catch (NoSuchElementException e) {
			System.out.println("La pioche est vide: " + e);
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
