package cartes;

public class JeuDeCartes {
	
	Configuration[] confiugration;
	
	
	
	public String affichageJeuDeCarte() {
		return 
	}
	
	private class Configuration extends Carte{
		
		private int nbExemplaires; 
		private Carte carte;
		
		public Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}

}
