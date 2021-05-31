package klad6;

public class KlientBSTre {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KjedetBSTre<Integer> bstre = new KjedetBSTre<Integer>();

		bstre.leggTil2(100);
		bstre.leggTil2(50);
		bstre.leggTil2(150);
		bstre.leggTil2(25);
		bstre.leggTil2(75);
		bstre.leggTil2(125);
		bstre.leggTil2(175);
		bstre.leggTil2(12);
		bstre.leggTil2(30);
		bstre.leggTil2(60);
		bstre.leggTil2(80);
		bstre.leggTil2(120);
		bstre.leggTil2(130);
		bstre.leggTil2(160);
		bstre.leggTil2(180);
		System.out.println("\n\n");
		System.out.println("");
		System.out.println("Tre hogden er " + bstre.hoyde());
		System.out.println("Tre hogden er " + bstre.hoyde2());

		bstre.visInorden();
		bstre.fjern(25);
		bstre.fjern(75);
		bstre.fjern(125);
		bstre.fjern(150);
		System.out.println("\n\nsletting 75,120,180,30 \ninOrderTraverseTree");
		bstre.visInorden();
		System.out.println("\n\n");
		Integer min = bstre.finnMin();
		Integer max = bstre.finnMaks();
		System.out.println("min = " + min + ", max = " + max);
		System.out.println("\n\nsletter min og max");
		bstre.fjernMin();
		bstre.fjernMaks();
		bstre.visInorden();
		System.out.println("\n\n");

		// Tester på sortert utskrift
		System.out.println("Skriver ut elementene sortert i bs-treet");
		bstre.visInorden();

//		for (int a = 0; a < bstre.antall(); a++) {
//			System.out.print(bstre.sortertTab()[a] + "      ");
//
//		}

		// Tester på om et bestemt element fins
		int element = 8;
		System.out.println("\nTester paa om elementet " + element + " fins");

		if (bstre.finn(element) != null) {
			System.out.println("Elementet " + element + " fins i bs-treet");
		} else {
			System.out.println("Elementet " + element + " fins ikke i bs-treet");
		}

		element = 1;
		System.out.println("\nTester paa om elementet " + element + " fins");

		if (bstre.finn(element) != null) {
			System.out.println("Elementet " + element + " fins i bs-treet");
		} else {
			System.out.println("Elementet " + element + " fins ikke i bs-treet");
		}
	}

}
