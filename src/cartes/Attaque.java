package cartes;

public class Attaque extends Bataille {
	
	public Attaque(Type type) {
		super(type);
	}
	
	
	@Override
	public String toString() {
		return getType().getAttaque();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Attaque attaque) {
	        return getType().equals(attaque.getType());
	    }
	    return false;
	}


}
