package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {
	
	private final List<Carte> listeMainJoueur = new ArrayList<>();
	
	public void prendre(Carte carte) {
		listeMainJoueur.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert listeMainJoueur.contains(carte);
		listeMainJoueur.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Carte carte : listeMainJoueur) {
			sb.append(carte).append(" ");
		}
		return sb.toString().trim();
	}

	@Override
	public Iterator<Carte> iterator() {
		return listeMainJoueur.iterator();
	}
}
