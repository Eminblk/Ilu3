package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	
	private final List<Bataille> pileBataille = new ArrayList<>();
	private final List<Limite>   pileLimite   = new ArrayList<>();
	private final List<Borne>    pileBorne    = new ArrayList<>();
	
	
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty()) {
			return 200;
		}
		Limite sommet = pileLimite.get(pileLimite.size() - 1);
		if (sommet instanceof FinLimite) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int total = 0;
		for (Borne b : pileBorne) {
			total += b.getKm();
		}
		return total;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Borne borne) {
			pileBorne.add(borne);
		} else if (carte instanceof Limite limite) {
			pileLimite.add(limite);
		} else if (carte instanceof Bataille bataille) {
			pileBataille.add(bataille);
		}
	}

	
	public boolean peutAvancer() {
		if (pileBataille.isEmpty()) {
			return false;
		}
		Bataille sommet = pileBataille.get(pileBataille.size() - 1);
		return (sommet instanceof Parade parade)
				&& parade.getType() == Type.FEU;
	}
	
	private boolean estBloque() {
		if (pileBataille.isEmpty()) {
			return false;
		}
		return pileBataille.get(pileBataille.size() - 1) instanceof Attaque;
	}
	
	private boolean estDepotFeuVertAutorise() {
		if (pileBataille.isEmpty()) {
			return true;
		}
		Bataille sommet = pileBataille.get(pileBataille.size() - 1);
		
		if (sommet instanceof Attaque att && att.getType() == Type.FEU) {
			return true; // feu rouge
		}
		if (sommet instanceof Parade parade && parade.getType() != Type.FEU) {
			return true; // autre parade
		}
		return false;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer()
				&& borne.getKm() <= donnerLimitationVitesse()
				&& donnerKmParcourus() + borne.getKm() <= 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileLimite.isEmpty()
					|| pileLimite.get(pileLimite.size() - 1) instanceof FinLimite;
		} else if (limite instanceof FinLimite) {
			return !pileLimite.isEmpty()
					&& pileLimite.get(pileLimite.size() - 1) instanceof DebutLimite;
		}
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			// on peut attaquer si on n'est pas déjà bloqué
			return !estBloque();
		}
		
		if (bataille instanceof Parade parade) {
			if (parade.getType() == Type.FEU) {
				// feu vert
				return estDepotFeuVertAutorise();
			} else {
				// autre parade : il faut une attaque du même type au sommet
				if (pileBataille.isEmpty()) {
					return false;
				}
				Bataille sommet = pileBataille.get(pileBataille.size() - 1);
				return (sommet instanceof Attaque att)
						&& att.getType() == parade.getType();
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		return false;
	}
}
