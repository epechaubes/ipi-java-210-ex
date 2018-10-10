package com.ipiecoles.java.java210;

import java.util.Scanner;

public class Sudoku {

	public static final String FIN_SAISIE = "FIN";
	public static boolean resolu = false;
	public static short[][] sudokuAResoudre;


	public short[][] getSudokuAResoudre() {
		return sudokuAResoudre;
	}

	public void setSudokuAResoudre(short[][] tab) {
		sudokuAResoudre = tab;
	}

	/**
	 * Constructeur par défaut
	 */
	public Sudoku() {
		setSudokuAResoudre(new short[9][9]);

	}

	public static boolean ligneSaisieEstCoherente(String ligneSaisie) {
		if (ligneSaisie == null || ligneSaisie.trim().equals("")) {
			System.out.print("Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces");
			return false;
		}


		if (ligneSaisie.length() != 3) {
			System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères");
			return false;
		}

		if (!ligneSaisie.matches("[0-8][0-8][1-9]")) {
			System.out.print("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9\n");
			return false;
		} else
			return true;
	}


	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
	 * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie.
	 * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
	 * comportant les coordonnées des chiffres saisis.
	 * <p>
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
	 * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
	 * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() {
		int i = 0;
		String saisie;
		String[] tableauCoordonnees = new String[81];
		Scanner scanner = new Scanner(System.in);

		System.out.print("Saisissez trois chiffres (abscysse, ordonnee et valeur) : ");
		saisie = scanner.nextLine();

		while (!saisie.equals("FIN")) {
			if (ligneSaisieEstCoherente(saisie)) {
				tableauCoordonnees[i] = saisie;
				i++;
			} else {
				System.out.print("Mauvaise saisie");
			}
			System.out.print("Saisissez trois chiffres (abscysse, ordonnee et valeur) ou 'FIN' pour terminer : ");
			saisie = scanner.nextLine();
		}
		scanner.close();
		return tableauCoordonnees;
	}

	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant
	 * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
	 * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est
	 * rencontrée dans le tableau, on arrête le traitement
	 * <p>
	 * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
	 *
	 * @param tableauCoordonnees
	 */

	public void remplitSudokuATrous(String[] tableauCoordonnees) {
		int coordx;
		int coordy;
		int valeur;

		for (int i = 0; i < tableauCoordonnees.length; i++) {
			if (tableauCoordonnees[i] == null) {
				break;
			} else {
				coordx = Integer.parseInt(tableauCoordonnees[i].substring(0, 1));
				coordy = Integer.parseInt(tableauCoordonnees[i].substring(1, 2));
				valeur = Integer.parseInt(tableauCoordonnees[i].substring(2, 3));
				sudokuAResoudre[coordx][coordy] = (short) valeur;
			}
		}
	}

	private int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console.
	 * Cela doit ressembler exactement à :
	 * -----------------------
	 * |   8   | 4   2 |   6   |
	 * |   3 4 |       | 9 1   |
	 * | 9 6   |       |   8 4 |
	 * -----------------------
	 * |       | 2 1 6 |       |
	 * |       |       |       |
	 * |       | 3 5 7 |       |
	 * -----------------------
	 * | 8 4   |       |   7 5 |
	 * |   2 6 |       | 1 3   |
	 * |   9   | 7   1 |   4   |
	 * -----------------------
	 *
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non).
	 *               Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur
	 *               non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
	 */

	public void ecrireSudoku(short[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			if (i == 0 || i == 3 || i == 6) {
				System.out.print(" -----------------------\n");
			}
			for (int j = 0; j < sudoku[1].length; j++) {
				if (j == 0 && sudoku[i][j] != 0) {
					System.out.print("| " + sudoku[i][j]);
				} else if ((j == 3 || j == 6) && sudoku[i][j] != 0) {
					System.out.print(" | " + sudoku[i][j]);
				} else if (j == 8 && sudoku[i][j] != 0) {
					System.out.print(" " + sudoku[i][j] + " |");
				} else if (sudoku[i][j] != 0) {
					System.out.print(" " + sudoku[i][j]);
				} else if (j == 0 && sudoku[i][j] == 0) {
					System.out.print("|  ");
				} else if ((j == 3 || j == 6) && sudoku[i][j] == 0) {
					System.out.print(" |  ");
				} else if (j == 8 && sudoku[i][j] == 0) {
					System.out.print("   |");
				} else {
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
		System.out.print(" -----------------------\n");
	}

	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'ligne et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes :
	 * <p>
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
	 * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
	 * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
	 *
	 * @param ligne
	 * @param colonne
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	public static boolean estAutorise(int ligne, int colonne, short chiffre, short[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[ligne][i] == chiffre) {
				return false;
			}
			if (sudoku[i][colonne] == chiffre) {
				return false;
			}
		}

		int abscysseCarree = ligne - ligne % 3;
		int ordonneeCarree = colonne - colonne % 3;
		System.out.print(abscysseCarree + ", " + ordonneeCarree);

		for (int i = abscysseCarree; i < abscysseCarree + 3; i++) {
			for (int j = ordonneeCarree; j < ordonneeCarree + 3; j++) {
				if (chiffre == sudoku[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean resoudre(int ligne, int colonne, short[][] sudoku) {
		/*int nbSolutions=0;
		short solution;
		short i;
		for(i=1; i<10; i++) {
			if (estAutorise(ligne, colonne, i, sudoku)){
				nbSolutions++;
				solution == i;
			}
		}
		if (nbSolutions>1){
			sudoku[ligne][colonne] == solution;
		}*/
		return true;
	}
}