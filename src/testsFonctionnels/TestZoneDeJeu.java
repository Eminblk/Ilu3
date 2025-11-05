package testsFonctionnels;

import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {

    public static void main(String[] args) {
        ZoneDeJeu zone = new ZoneDeJeu();

        System.out.println("Deposer carte 25 km");
        zone.deposer(new Borne(25));
        System.out.println("Deposer carte 50 km");
        zone.deposer(new Borne(50));
        System.out.println("Deposer carte 75 km");
        zone.deposer(new Borne(75));
        System.out.println("Total des bornes : " + zone.donnerKmParcourus());

        System.out.println("Limite : " + zone.donnerLimitationVitesse());
        zone.deposer(new DebutLimite());
        System.out.println("Limite : " + zone.donnerLimitationVitesse());
        zone.deposer(new FinLimite());
        System.out.println("Limite : " + zone.donnerLimitationVitesse());
    }
}
