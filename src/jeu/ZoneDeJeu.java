package jeu;

import java.util.*;
import cartes.*;

public class ZoneDeJeu implements Cartes {

    private List<Limite> pileLimites;
    private List<Bataille> pileBataille;
    private List<Borne> collectionBornes;
    private Set<Botte> bottes;

    public ZoneDeJeu() {
        pileLimites = new ArrayList<>();
        pileBataille = new ArrayList<>();
        collectionBornes = new ArrayList<>();
        bottes = new HashSet<>();
    }


    public boolean estPrioritaire() {
        return bottes.contains(PRIORITAIRE);
    }

    @Override
    public String toString() {
        return "ZoneDeJeu {" +
                "\n  Limites : " + pileLimites +
                "\n  Batailles : " + pileBataille +
                "\n  Bornes : " + collectionBornes +
                "\n  Bottes : " + bottes +
                "\n}";
    }


    public int donnerLimitationVitesse() {
        if (estPrioritaire()) return 200;
        if (pileLimites.isEmpty()) return 200;
        Carte sommet = pileLimites.get(pileLimites.size() - 1);
        return (sommet instanceof FinLimite) ? 200 : 50;
    }
    
    public List<Bataille> getPileBataille() {
        return pileBataille;
    }


    public int donnerKmParcourus() {
        int total = 0;
        for (Borne borne : collectionBornes) total += borne.getKm();
        return total;
    }

    public void deposer(Carte c) {
        if (c instanceof Borne) collectionBornes.add((Borne) c);
        else if (c instanceof Limite) pileLimites.add((Limite) c);
        else if (c instanceof Bataille) pileBataille.add((Bataille) c);
        else if (c instanceof Botte) bottes.add((Botte) c);
    }


    public boolean peutAvancer() {
        if (pileBataille.isEmpty()) return estPrioritaire();

        Bataille sommet = pileBataille.get(pileBataille.size()-1);
        System.out.println(sommet); 
        System.out.println("-test-test------------------");

        if(sommet.equals(FEU_VERT)) return true;
        if(sommet instanceof Parade) return estPrioritaire();
        if(sommet instanceof Attaque att) {
        	if(!estPrioritaire() ) return false;
        	if(att.getType() == Type.FEU ) return true;
        	return bottes.contains(new Botte(att.getType()));
        }
        
        return false;
        
        
        
    }


    public boolean estDepotFeuVertAutorise() {
        if (estPrioritaire()) return false;
        if (pileBataille.isEmpty()) return true;
        Carte sommet = pileBataille.get(pileBataille.size() - 1);
        return sommet.equals(FEU_ROUGE)
                || (sommet instanceof Parade && !sommet.equals(FEU_VERT))
                || (sommet instanceof Attaque
                    && bottes.contains(new Botte(((Attaque) sommet).getType())));
    }

    public boolean estDepotLimiteAutorise(Limite limite) {
        if (estPrioritaire()) return false;
        if (limite instanceof DebutLimite) {
            return pileLimites.isEmpty() ||
                    pileLimites.get(pileLimites.size() - 1) instanceof FinLimite;
        } else if (limite instanceof FinLimite) {
            return pileLimites.get(pileLimites.size() - 1) instanceof DebutLimite;
        }
        return false;
    }

    public boolean estDepotBorneAutorise(Borne borne) {
        return peutAvancer()
                && borne.getKm() <= donnerLimitationVitesse()
                && donnerKmParcourus() + borne.getKm() <= 1000;
    }

    public boolean estDepotBatailleAutorise(Bataille bataille) {
        if (bottes.contains(new Botte(bataille.getType()))) return false;

        if (bataille instanceof Attaque) return peutAvancer();
        if (bataille instanceof Parade) {
            if (bataille.equals(FEU_VERT)) return estDepotFeuVertAutorise();
            if (!pileBataille.isEmpty()) {
                Carte sommet = pileBataille.get(pileBataille.size() - 1);
                return sommet instanceof Attaque
                        && ((Attaque) sommet).getType() == ((Parade) bataille).getType();
            }
        }
        return false;
    }

    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Botte) return true;
        if (carte instanceof Borne) return estDepotBorneAutorise((Borne) carte);
        if (carte instanceof Limite) return estDepotLimiteAutorise((Limite) carte);
        if (carte instanceof Bataille) return estDepotBatailleAutorise((Bataille) carte);
        return false;
    }
}
