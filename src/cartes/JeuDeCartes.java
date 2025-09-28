package cartes;

public class JeuDeCartes {

	private Configuration[] configurations;

	public JeuDeCartes() {
	    configurations = new Configuration[] {

	        new Configuration(new Borne(25), 10),
	        new Configuration(new Borne(50), 10),
	        new Configuration(new Borne(75), 10),
	        new Configuration(new Borne(100), 12),
	        new Configuration(new Borne(200), 4),

	
	        new Configuration(new Parade(Type.FEU), 14),        
	        new Configuration(new FinLimite(), 6),              
	        new Configuration(new Parade(Type.ESSENCE), 6),  
	        new Configuration(new Parade(Type.CREVAISON), 6),  
	        new Configuration(new Parade(Type.ACCIDENT), 6),   

	        new Configuration(new Attaque(Type.FEU), 5),      
	        new Configuration(new DebutLimite(), 4),         
	        new Configuration(new Attaque(Type.ESSENCE), 3),   
	        new Configuration(new Attaque(Type.CREVAISON), 3), 
	        new Configuration(new Attaque(Type.ACCIDENT), 3),   

	        new Configuration(new Botte(Type.FEU), 1),         
	        new Configuration(new Botte(Type.ESSENCE), 1),     
	        new Configuration(new Botte(Type.CREVAISON), 1),   
	        new Configuration(new Botte(Type.ACCIDENT), 1)      
	    };
	}


	public String affichageJeuCartes() {
		StringBuilder sb = new StringBuilder();
		for (Configuration conf : configurations) {
			sb.append(conf.getNbExemplaires()).append(" ").append(conf.getCarte()).append("\n");
		}
		return sb.toString();
	}

	public boolean checkCount() {
		int total = 0;
		for (Configuration conf : configurations) {
			total += conf.getNbExemplaires();
		}
		return total == 106;
	}

	public Carte[] donnerCartes() {
		int total = 0;
		for (Configuration conf : configurations) {
			total += conf.getNbExemplaires();
		}
		Carte[] toutes = new Carte[total];
		int index = 0;
		for (Configuration conf : configurations) {
			for (int i = 0; i < conf.getNbExemplaires(); i++) {
				toutes[index++] = conf.getCarte();
			}
		}
		return toutes;
	}	

	private class Configuration {
		private final int nbExemplaires;
		private final Carte carte;

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
