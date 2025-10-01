package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Type;
import cartes.Parade;

public class TestMethodeEquals {
	
	public static void main(String[] args) {
		
		Borne borne25 = new Borne(25);
		Borne borne25_2 = new Borne(25);
		
		boolean verifBorne25 = borne25.equals(borne25_2);
		System.out.println(" Deux cartes de 25km sont identiques ? " + verifBorne25); // FIN TEST pour borne
		
		
		Attaque feu1 = new Attaque(Type.FEU);
		Attaque feu2 = new Attaque(Type.FEU);
		
		boolean verifFeu = feu1.equals(feu2);
		System.out.println(" Deux cartes de feux rouge sont identiques ? " + verifFeu);
		
		Attaque feuRouge = new Attaque(Type.FEU);
		Parade feuVert = new Parade(Type.FEU);
		
		boolean verifFeu2 = feuRouge.equals(feuVert); 
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + verifFeu2);
	}

}
