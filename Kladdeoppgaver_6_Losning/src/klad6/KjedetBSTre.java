package klad6;

import java.util.Iterator;

//********************************************************************
// KjedetBinærSøkeTre.java        
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>, Iterable<T> {

	private int antall;

	/**
	 * @return the rot
	 */
	public BinaerTreNode<T> getRot() {
		return rot;
	}

	/**
	 * @param rot the rot to set
	 */
	public void setRot(BinaerTreNode<T> rot) {
		this.rot = rot;
	}

	private BinaerTreNode<T> rot;

	/******************************************************************
	 * Oppretter et tomt binært søketre.
	 ******************************************************************/
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/******************************************************************
	 * Oppretter et binært søketre med en node..
	 ******************************************************************/
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære trett er tomt og usann ellers.
	 *****************************************************************/
	@Override
	public int antall() {
		return antall;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære treet er tom og usann ellers.
	 *****************************************************************/
	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	/**********************************************************************
	 * Legger det spesifiserte elementet på passende plass i BS-treet. Like
	 * elementer blir lagt til høyre. Bruk av rekursiv hjelpemetode.
	 ********************************************************************/
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {
		if (p == null) {
			return new BinaerTreNode<T>(element);
		} else {
			if (element.compareTo(p.getElement()) < 0) {
				p.setVenstre(leggTilRek(p.getVenstre(), element));
			} else {
				p.setHoyre(leggTilRek(p.getHoyre(), element));
			}
			return p;
		}

	}

	/******************************************************************
	 * Legger det spesifiserte elementet på passende plass i dette binære søketreet.
	 * Like elementer blir lagt til høyre.
	 ******************************************************************/

	public void leggTil2(T element) {
		BinaerTreNode<T> newNode = new BinaerTreNode<T>(element);
		if (rot == null) {
			rot = newNode;
			antall++;
		} else {
			BinaerTreNode<T> focusNode = rot;
			BinaerTreNode<T> parent;

			while (true) {
				parent = focusNode;
				if (element.compareTo(focusNode.getElement()) < 0) {
					focusNode = focusNode.getVenstre();
					if (focusNode == null) {
						parent.setVenstre(newNode);
						antall++;
						return;

					}

				} else {
					focusNode = focusNode.getHoyre();
					if (focusNode == null) {
						parent.setHoyre(newNode);
						antall++;
						return;

					}

				}

			}
		}

	}// Ikke rekrusive

	public void inOrderTraverseTree(BinaerTreNode<T> focusNode) {
		if (focusNode != null) {
			inOrderTraverseTree(focusNode.getVenstre());
			System.out.println((focusNode));
			inOrderTraverseTree(focusNode.getHoyre());
			System.out.println((focusNode));

		}
	}

	/******************************************************************
	 * Fjerner noden med minste verdi fra dette binære søketreet.
	 *********************************************************************/
	@Override
	public T fjernMin() {
		T svar = null;
		BinaerTreNode<T> focusNode = rot;
		BinaerTreNode<T> parent = rot;

		while (focusNode.getVenstre() != null) {
			parent = focusNode;
			focusNode = focusNode.getVenstre();
		}
		if (focusNode == rot) {
			if (antall() > 0) {
				svar = rot.getElement();
			}
			rot = focusNode.getHoyre();
		} else {
			svar = focusNode.getElement();
			parent.setVenstre(focusNode.getHoyre());

		}

		return svar;
	}//

	/******************************************************************
	 * Fjerner noden med største verdi fra dette binære søketreet.
	 ******************************************************************/
	@Override
	public T fjernMaks() {
		T svar = null;
		BinaerTreNode<T> focusNode = rot;
		BinaerTreNode<T> parent = rot;

		while (focusNode.getHoyre() != null) {
			parent = focusNode;
			focusNode = focusNode.getHoyre();
		}
		if (focusNode == rot) {
			if (antall() > 0) {
				svar = rot.getElement();
			}
			rot = focusNode.getVenstre();
		} else {
			svar = focusNode.getElement();
			parent.setHoyre(focusNode.getVenstre());

		}

		return svar;
	}//

	/******************************************************************
	 * Returnerer det minste elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMin() {
		BinaerTreNode<T> e = rot;
		while (e.getVenstre() != null) {
			e = e.getVenstre();
		}
		return e.getElement();
	}//

	/******************************************************************
	 * Returnerer det største elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMaks() {
		BinaerTreNode<T> e = rot;
		while (e.getHoyre() != null) {
			e = e.getHoyre();
		}
		return e.getElement();
	}//

	/*******************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det finst i dette
	 * BS-treet, null ellers. Bruk av rekursjon /
	 ******************************************************************************/
	@Override
	public T finn(T element) {
		// Søk med rekursiv hjelpemetode

		return finnRek(element, rot);

	}

	private T finnRek(T element, BinaerTreNode<T> p) {
		T svar = null;

		if (p != null) {
			int sml = element.compareTo(p.getElement());

			if (sml == 0) {
				svar = p.getElement();
			} else if (sml < 0) {
				svar = finnRek(element, p.getVenstre());
			} else {
				svar = finnRek(element, p.getHoyre());
			}
		}
		return svar;
	}// Den rekursive hjelpemetoden for søking

	public BinaerTreNode<T> findNode(T element) {
		BinaerTreNode<T> focusNode = rot;
		while (focusNode.getElement().compareTo(element) != 0) {
			if (element.compareTo(element) <= 0) {
				focusNode = focusNode.getVenstre();

			} else {
				focusNode = focusNode.getHoyre();

			}
			if (focusNode == null) {
				return null;
			}
		}

		return focusNode;
	}

	// TODO

	/************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * BS-treet, null ellers. Uten bruk av rekursjon. /
	 ************************************************************************/
	public T finn2(T element) {
		// TODO
		return null;
	}

	public int hoyde() {
		return hoydeRek(rot) - 1;
	}

	private int hoydeRek(BinaerTreNode<T> e) {
		int i = 0;
		int j = 0;
		if (e.getHoyre() != null) {
			i = hoydeRek(e.getHoyre());
		}
		if (e.getVenstre() != null) {
			j = hoydeRek(e.getVenstre());
		}
		if (i > j) {
			return i + 1;
		} else {
			return j + 1;
		}
	}

	public int hoyde2() {
		return hoydeRek2(rot);
	}

	private int hoydeRek2(BinaerTreNode<T> e) {
		if (e == null) {
			return -1;
		} else {

			int vHogde = hoydeRek2(e.getVenstre());
			int hHogde = hoydeRek2(e.getHoyre());
			return Math.max(vHogde, hHogde) + 1;
		}
	}

	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	private void visInorden(BinaerTreNode<T> p) {
		if (p != null) {
			visInorden(p.getVenstre());
			System.out.print(" " + p.getElement());
			visInorden(p.getHoyre());
		}
	}

	public T[] sortertTab() {
		T[] tab = (T[]) new Comparable[antall];

		sortertTab(rot, tab, 0);
		return tab;
	}

	public int sortertTab(BinaerTreNode<T> p, T[] tab, int neste) {

		if (p == null) {
			return neste;
		} else {
			int nyNeste = sortertTab(p.getVenstre(), tab, neste);
			tab[nyNeste] = p.getElement();
			nyNeste = sortertTab(p.getHoyre(), tab, nyNeste + 1);
			return nyNeste;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new InordenIterator<T>(rot);

	}

	public boolean fjern(T element) {
		BinaerTreNode<T> focusNode = rot;
		BinaerTreNode<T> parent = rot;
		boolean isItALeftChild = true;
		while (focusNode.getElement().compareTo(element) != 0) {
			parent = focusNode;
			if (element.compareTo(focusNode.getElement()) <= 0) {
				isItALeftChild = true;
				focusNode = focusNode.getVenstre();

			} else {
				isItALeftChild = false;
				focusNode = focusNode.getHoyre();

			}

			if (focusNode == null) {
				return false;
			}

		}
		if (focusNode.getVenstre() == null && focusNode.getVenstre() == null) {
			if (focusNode == rot) {
				rot = null;
			} else if (isItALeftChild) {
				parent.setVenstre(null);

			} else {
				parent.setHoyre(null);
			}
		}

		else if (focusNode.getHoyre() == null) {
			if (focusNode == rot) {
				rot = focusNode.getVenstre();
			} else if (isItALeftChild) {
				parent.setVenstre(focusNode.getVenstre());

			} else {
				parent.setHoyre(focusNode.getVenstre());
			}

		}

		else if (focusNode.getVenstre() == null) {
			if (focusNode == rot) {
				rot = focusNode.getHoyre();
			} else if (isItALeftChild) {
				parent.setVenstre(focusNode.getHoyre());

			} else {
				parent.setHoyre(focusNode.getHoyre());
			}

		}

		else {
			BinaerTreNode<T> replacement = getReplacementNodeVHAHogre(focusNode);

			if (focusNode == rot) {
				rot = replacement;

			} else if (isItALeftChild) {
				parent.setVenstre(replacement);

			} else {
				parent.setHoyre(replacement);
			}

			replacement.setVenstre(focusNode.getVenstre());

		}

		return true;
	}

	public boolean fjern2(T element) {
		BinaerTreNode<T> focusNode = rot;
		BinaerTreNode<T> parent = rot;
		boolean isItALeftChild = true;
		while (focusNode.getElement().compareTo(element) != 0) {
			parent = focusNode;
			if (element.compareTo(focusNode.getElement()) <= 0) {
				isItALeftChild = true;
				focusNode = focusNode.getVenstre();

			} else {
				isItALeftChild = false;
				focusNode = focusNode.getHoyre();

			}

			if (focusNode == null) {
				return false;
			}

		}
		if (focusNode.getVenstre() == null && focusNode.getVenstre() == null) {
			if (focusNode == rot) {
				rot = null;
			} else if (isItALeftChild) {
				parent.setVenstre(null);

			} else {
				parent.setHoyre(null);
			}
		}

		else if (focusNode.getHoyre() == null) {
			if (focusNode == rot) {
				rot = focusNode.getVenstre();
			} else if (isItALeftChild) {
				parent.setVenstre(focusNode.getVenstre());

			} else {
				parent.setHoyre(focusNode.getVenstre());
			}

		}

		else if (focusNode.getVenstre() == null) {
			if (focusNode == rot) {
				rot = focusNode.getHoyre();
			} else if (isItALeftChild) {
				parent.setVenstre(focusNode.getHoyre());

			} else {
				parent.setHoyre(focusNode.getHoyre());
			}

		}

		else {
			BinaerTreNode<T> replacement = getReplacementNodeVHAHogre(focusNode);

			if (focusNode == rot) {
				rot = replacement;

			} else if (isItALeftChild) {
				parent.setVenstre(replacement);

			} else {
				parent.setHoyre(replacement);
			}

			replacement.setHoyre(focusNode.getHoyre());

		}

		return true;
	}// ikke rekrusiv

	private BinaerTreNode<T> getReplacementNodeVHAHogre(BinaerTreNode<T> replacedNode) {

		BinaerTreNode<T> replacementParent = replacedNode;
		BinaerTreNode<T> replacement = replacedNode;

		BinaerTreNode<T> focusNode = replacedNode.getHoyre();

		while (focusNode != null) {
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.getVenstre();

		}

		if (replacement != replacedNode.getHoyre()) {
			replacementParent.setVenstre(replacement.getHoyre());
			replacement.setHoyre(replacedNode.getHoyre());

		}

		return replacement;
	}

	private BinaerTreNode<T> getReplacementNodeVHAVenstre(BinaerTreNode<T> replacedNode) {

		BinaerTreNode<T> replacementParent = replacedNode;
		BinaerTreNode<T> replacement = replacedNode;

		BinaerTreNode<T> focusNode = replacedNode.getVenstre();

		while (focusNode != null) {
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.getHoyre();

		}

		if (replacement != replacedNode.getVenstre()) {
			replacementParent.setHoyre(replacement.getVenstre());
			replacement.setVenstre(replacedNode.getVenstre());

		}

		return replacement;
	}
}// class
