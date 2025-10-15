package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	
	List<Carte> listeMainJoueur = new ArrayList<>();
	
	public void prendre(Carte carte) {
		listeMainJoueur.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert(!listeMainJoueur.isEmpty());
		listeMainJoueur.remove(carte);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Carte carte : listeMainJoueur) {
			sb.append(carte);
		}
		return sb.toString();
		
	}

	//Pour valider l'implémentation itérable
	@Override
	public Iterator<Carte> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
