package jeu;

public class Joueur {

	String nom; 
	ZoneDeJeu zonedejeu;
	MainJoueur mainDuJoueur;
	
	
	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zonedejeu = zoneDeJeu;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur) {
			return joueur.nom.equals(nom);
		} 
		return false;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public void donner() {
		mainDuJoueur.prendre(null);
	}
}
