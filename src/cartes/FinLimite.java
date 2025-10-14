package cartes;

public class FinLimite extends Limite {
	@Override
	public String toString() {
		return "Fin Limite";
	}
	
	@Override
	public boolean equals(Object obj) {
	    return obj != null && getClass() == obj.getClass();
	}
}