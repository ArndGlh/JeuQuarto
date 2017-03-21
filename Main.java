import java.util.Scanner;

public class Main {

	
	public static int choixPion (Grille g, boolean joueur){
		int pion;
		boolean boucle = false;
		Scanner sc = new Scanner(System.in);
		String question = "Choisissez un pion (1 à 16)";
		
		if (joueur) question += " (JOUEUR 1)";
		else question += " (JOUEUR 2)";
		
		do { // vérification du pion
			System.out.println(question);
			pion = sc.nextInt();
		
			if (pion<1) {System.out.println("Erreur : Pion négatif"); boucle = true;}
			else if (pion>16) {System.out.println("Erreur : Pion supérieur à 16"); boucle = true;}
			else if (!g.getPionRestant(pion)) {System.out.println("Erreur : Pion non disponible"); boucle = true;}
			else boucle = false;
			
		} while (boucle);
		
		return pion;
	}
	
	public static String choixCase (Grille g, boolean joueur){
		String cases;
		Scanner sc = new Scanner(System.in);
		String question = "Ou voulez-vous le placer ? (Par ex : 'A1')";
		
		if (joueur) question += " (JOUEUR 1)";
		else question += " (JOUEUR 2)";
		
		do {
			System.out.println(question);
			cases = sc.nextLine();
		
		} while (!g.caseOk(cases));
		
		return cases;
	}
	
	public static void main(String[] args){
		
		// VARIABLES
		int pion, indiceC;     // pion : numéro du pion choisit // indiceC : indice de la case du prochain pion a jouer dans la grille
		String cases, getPion; // cases :  // getPion : 
		String[] position;     // position :  
		boolean joueur;        // true : joueur 1 // false : joueur 2
		
		System.out.println("Règles :\nLa colonne de droite vous donne la liste des pions disponibles."
				+ "\nChaque pion est caractérisé par 4 caractéristiques :\n- M/G : Moyen ou Grand\n- B/N : Blanc ou Noir"
				+ "\n- V/P : Vide ou Plein\n- R/C : Rond ou Carré\n\nLe but est de former un ligne ou colonne ou diagonale"
				+ " de quatre pions respectants un de ces critères en commun.\nL'adversaire choisit le pion que vous allez placer.\n\n\n");
		
		joueur = true;
		Grille quarto = new Grille();
		System.out.print(quarto.construction());
		System.out.println();
		
		int a;
		Scanner sc = new Scanner(System.in);
		
		// ------------------------------------------------------------------------------------------------------
		// ----------------------------------------- DEBUT PHASE DE JEU -----------------------------------------
		// ------------------------------------------------------------------------------------------------------
		do {
			
			// ------ Choix du pion et de la case
			pion = choixPion(quarto, joueur);
			joueur = !joueur;
		
			cases = choixCase(quarto, joueur);
			// ----------------------------------
		
			quarto.setPionRestantI(pion, false); // effacement du pion dans this.pionRestant[]
		
			getPion = quarto.getPion(pion-1);    // récupération du String du pion (ex : "MB PC")
		                                              
			quarto.setNulli(pion-1);             // effacement du pion dans la grille
		
			quarto.modifCase(getPion, cases);    // inscription dans la Grille.case[indiceC]
		
			System.out.println("Continuer ? (0/1)");
			a = sc.nextInt();
			
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.print(quarto.construction());
			
		}while (a!=0);
		
		/*
		 * 
		 * 
		 * après g.recupCase, indiceC contient l'indice de la case de ce tableau (grille):
		 * 
		 * [0]  [1]  [2]  [3]
		 * [4]  [5]  [6]  [7]
		 * [8]  [9]  [10] [11]
		 * [12] [13] [14] [15]
		 * 
		 * PROCEDURE : 
		 * créer 4 tableau d'int puis pour chaque caractère des pions attribuer un numéro
		 * 
		 * tabMG = new int [16];   // moyen = 0; grand = 1;
		 * tabBN // blanc = 2; noir = 3
		 * tabVP // vide = 4; plein = 5;
		 * tabRC // rond = 6; carré = 7;
		 * 
		 * inscrire à l'indiceC
		 * 
		 * 
		 * vérifier que aux indices de :
		 * les lignes 0,1,2,3 // 4,5,6,7 // 8,9,10,11 // 12,13,14,15
		 * les colonnes 0,4,8,12 // 1,5,9,13 // 2,6,10,14 // 3,7,11,15
		 * les diagonales 12,9,6,3 // 0,5,10,15
		 * 
		 * pour chaque tab vérifier si ces combinaisons ci dessus marche
		 * par exemple vérifier: tabMG[0] == tabMG[1] == tabMG[2] == tabMG[3];
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 *
		 *
		 * 
		 * 
		 * A faire :
		 * 
		 * - Vérification siGagné
		 * 
		 * bugs :
		 * 
		 * - 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
	}

}
