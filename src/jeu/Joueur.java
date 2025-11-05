package jeu;

import cartes.Carte;

public class Joueur {

	private final String nom; 
	private final ZoneDeJeu zonedejeu;
	private final MainJoueur mainDuJoueur;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.zonedejeu = new ZoneDeJeu();
		this.mainDuJoueur = new MainJoueur();
	}
	
	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zonedejeu = zoneDeJeu;
		this.mainDuJoueur = new MainJoueur();
	}
	
	public String getNom() {
		return nom;
	}
	
	public ZoneDeJeu getZoneDeJeu() {
		return zonedejeu;
	}
	
	public MainJoueur getMainDuJoueur() {
		return mainDuJoueur;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return joueur.nom.equals(nom);
		} 
		return false;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public void donner(Carte carte) {
		mainDuJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte c = sabot.piocher();
		donner(c);
		return c;
	}
	
	public int donnerKmParcourus() {
		return zonedejeu.donnerKmParcourus();
	}
	
	public int donnerLimitationVitesse() {
		return zonedejeu.donnerLimitationVitesse();
	}
	
	public void deposer(Carte carte) {
		zonedejeu.deposer(carte);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zonedejeu.estDepotAutorise(carte);
	}
	
	public boolean peutAvancer() {
		return zonedejeu.peutAvancer();
	}
}
