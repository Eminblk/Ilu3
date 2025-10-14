package cartes;

public class Botte extends Probleme{
	
	public Botte(Type type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return getType().getBotte();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Botte botte) {
	        return type.equals(botte.getType());
	    }
	    return false;
	}


}
