package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class GestionCartes {

	public static <E> E extraire(List<E> liste) {
		if (liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne doit pas être vide");
		}
		int index = liste.size() - 1; // on prend le dernier
		E element = liste.get(index);
		liste.remove(index);
		return element;
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
		List<E> melangee = new ArrayList<>();
		while (!liste.isEmpty()) {
			E e = extraire(liste);
			melangee.add(e);
		}
		return melangee;
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
		if (liste.isEmpty())
			return true;

		ListIterator<E> it1 = liste.listIterator();
		E precedent = it1.next();

		while (it1.hasNext()) {
			E courant = it1.next();
			if (!courant.equals(precedent)) {
				ListIterator<E> it2 = liste.listIterator(it1.nextIndex());
				while (it2.hasNext()) {
					if (it2.next().equals(precedent)) {
						return false;
					}
				}
			}
			precedent = courant;
		}
		return true;
	}
}
