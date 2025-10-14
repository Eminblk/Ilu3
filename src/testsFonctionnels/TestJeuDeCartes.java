package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {
    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        System.out.println("JEU:\n" + jeu.affichageJeuCartes());

        if (!jeu.checkCountTotal()) {
            System.out.println("erreur de nombre");
        } else {
        	System.out.println(" Nombre correct pour checkCountTotal");
        }
        
        if(!jeu.checkCount()) {
        	System.out.println("Erreur de nombre par configuration");
        } else {
        	System.out.println(" Nombre correct pour checkCount");
        }
        
    }
}
