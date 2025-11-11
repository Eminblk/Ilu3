package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;
import cartes.*;
import utils.*;

public class MainJoueur implements Iterable<Carte>{
	
	List<Carte> listeJoueur = new ArrayList<>();
	
	public void prendre(Carte carteAAjouter) {
		 listeJoueur.add(carteAAjouter);
	}
	
	public void jouer(Carte carteAJouer) {
		assert(listeJoueur.contains(carteAJouer));
		listeJoueur.remove(carteAJouer);
	}
	
	@Override
    public Iterator<Carte> iterator() {
        return listeJoueur.iterator();
    }
	
	@Override
    public String toString() {
        return "Main du joueur : " + listeJoueur.toString();
    }

	


}
