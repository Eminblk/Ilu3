package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	private static final Random RANDOM = new Random();

	
	public static <E> E extraire(List<E> liste) {
	    if (liste.isEmpty()) {
	        throw new IllegalArgumentException("La liste ne doit pas être vide");
	    }

	    int index = RANDOM.nextInt(liste.size()); // 🔹 index aléatoire
	    return liste.remove(index); // retire et renvoie l’élément choisi
	}

	
	public static <E> E extraireAvecIterateur(List<E> liste) {
		if (liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne doit pas être vide");
		}

		ListIterator<E> it = liste.listIterator(liste.size());
		E element = null;

		if (it.hasPrevious()) {
			element = it.previous();
			it.remove();
		}
		return element;
	}
	
	public static <E> List<E> melanger(List<E> liste) {
	    List<E> listeRetour = new ArrayList<>();

	    while (!liste.isEmpty()) {
	        E element = extraire(liste);  // maintenant aléatoire
	        listeRetour.add(element);
	    }

	    return listeRetour;
	}


	public static <E> boolean verifierMelange(List<E> l1, List<E> l2) {
		if (l1.size() != l2.size())
			return false;

		for (E e : l1) {
			if (Collections.frequency(l1, e) != Collections.frequency(l2, e)) {
				return false;
			}
		}
		return true;
	}
	
	public static <E> List<E> rassembler(List<E> liste) {
		List<E> resultat = new ArrayList<>();

		for (E e : liste) {
			if (!resultat.contains(e)) {
				for (E f : liste) {
					if (f.equals(e)) {
						resultat.add(f);
					}
				}
			}
		}
		return resultat;
	}
	
	public static <E> boolean verifierRassemblement(List<E> liste) {
	    if (liste.isEmpty()) return true; // une liste vide est triviale

	    ListIterator<E> iter = liste.listIterator();
	    E precedent = iter.next(); // premier élément

	    // On parcourt la liste à partir du 2e élément
	    while (iter.hasNext()) {
	        E courant = iter.next();

	        // Si la valeur change
	        if (!courant.equals(precedent)) {
	            // On cherche si la valeur précédente réapparaît plus loin
	            ListIterator<E> iter2 = liste.listIterator(iter.nextIndex());
	            while (iter2.hasNext()) {
	                E suivant = iter2.next();
	                if (suivant.equals(precedent)) {
	                    return false; // violation : un ancien élément revient
	                }
	            }
	        }

	        precedent = courant; // on avance la comparaison
	    }

	    return true; // tout va bien
	}

	
}
