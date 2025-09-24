package cartes;

public enum Type {

	FEU("Feu rouge", "Feu Vert", "Prioritaire"), 
	ESSENCE("Panne D'escence", "Essence", "Citerne"),
	CREVAISON("Crevaison", "Roue de secours", "Increvable"),
	ACCIDENT("Accident", "Réparation", "As du volent");
	
	String attaque;
	String parade;
	String botte;

	Type(String attaque, String parade, String botte) {
		this.attaque = attaque;
		this.parade = parade;
		this.botte = botte;
	}
	
	@Override
	public String toString() {
		return attaque;
	}

	public String getAttaque() {
		return attaque;
	}

	public String getParade() {
		return parade;
	}

	public String getBotte() {
		return botte;
	}
	
	
	
}
