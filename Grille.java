import java.util.ArrayList;

public class Grille {
	
	// Attributs --------------------------------------------------------
	private Pion[] pions; // tableau des pions de la grille
	private String[] colonneUn;   // "|1| "
	private String[] colonneDeux; // "|      "
	private String[] cases;       // "|     |"  Pour l'affichage
	private Pion[] listeCase; // tableau des cases épurées pour calculer siGagne()
	private boolean [] pionRestant;  // tab des pions restant (true = dispo; false = non dispo)
	private boolean [] caseRestante; // tab des cases restantes (true = dispo; false = non dispo) 
	private static final int taille = 16; // taille des différents tableaux
	private static final int[] listeComb = new int[40]; // Liste des combinaisons a tester pour le gain
	
	// ------------------------------------------------------------------

	public Grille () {
		
		//----------------------------------------------------------------- 
		pions = new Pion [taille];		
		colonneUn = new String [taille];
		colonneDeux = new String [taille];
		cases = new String [taille];
		pionRestant = new boolean[taille];
		caseRestante = new boolean[taille];
		listeCase = new Pion[taille];
		//-----------------------------------------------------------------
		
		for (int i=0; i<pionRestant.length; i++){  // initialisation pionRestant[]
			pionRestant[i] = true;
		}
		
		for (int i=0; i<caseRestante.length; i++){ // initialisation caseRestant[]
			caseRestante[i] = true;
		}
		
		StringBuilder str = new StringBuilder();
		for(int i=0; i<taille; i++){ 			   // initialisation colonneUn
			if (i<9) {
				str.append("|");
				str.append(i+1);
				str.append(" | ");
				colonneUn[i] = str.toString();
			}
			else {
				str.append("|");
				str.append(i+1);
				str.append("| ");
				colonneUn[i] = str.toString();
			}
		}
		str.delete(0, str.length());

		for(int i=0; i<taille; i++){ 			   // initialisation colonneDeux
			if(i==0) colonneDeux[i] = " |      A ";
			else if(i==1) colonneDeux[i] = " |      B ";
			else if(i==2) colonneDeux[i] = " |      C ";
			else if(i==3) colonneDeux[i] = " |      D ";
			else colonneDeux[i] = " |        ";
		}
		
		for(int i=0; i<taille; i++){ 			   // initialisation cases[]
		this.cases[i] = "|     |";
		}
		// --------------- PIONS ---------------
		pions[0] = new Pion("M", "B", "P", "C");
		pions[1] = new Pion("M", "B", "P", "R");
		pions[2] = new Pion("M", "B", "V", "C");
		pions[3] = new Pion("M", "B", "V", "R");

		pions[4] = new Pion("M", "N", "P", "C");
		pions[5] = new Pion("M", "N", "P", "R");
		pions[6] = new Pion("M", "N", "V", "C");
		pions[7] = new Pion("M", "N", "V", "R");

		pions[8] = new Pion("G", "B", "P", "C");
		pions[9] = new Pion("G", "B", "P", "R");
		pions[10] = new Pion("G", "B", "V", "C");
		pions[11] = new Pion("G", "B", "V", "R");

		pions[12] = new Pion("G", "N", "P", "C");
		pions[13] = new Pion("G", "N", "P", "R");
		pions[14] = new Pion("G", "N", "V", "C");
		pions[15] = new Pion("G", "N", "V", "R");
		//---------------------------------------
		
		// INITIALISATION LISTE GAIN ------------
		// Lignes
		for(int i=0; i<16; i++){
			Grille.listeComb[i] = i;
		}
		
		// Colonnes
		Grille.listeComb[16] = 0;
		Grille.listeComb[17] = 4;
		Grille.listeComb[18] = 8;
		Grille.listeComb[19] = 12;
		
		Grille.listeComb[20] = 1;
		Grille.listeComb[21] = 5;
		Grille.listeComb[22] = 9;
		Grille.listeComb[23] = 13;
		
		Grille.listeComb[24] = 2;
		Grille.listeComb[25] = 6;
		Grille.listeComb[26] = 10;
		Grille.listeComb[27] = 14;
		
		Grille.listeComb[28] = 3;
		Grille.listeComb[29] = 7;
		Grille.listeComb[30] = 11;
		Grille.listeComb[31] = 15;
		
		// Diagonales
		Grille.listeComb[32] = 0;
		Grille.listeComb[33] = 5;
		Grille.listeComb[34] = 10;
		Grille.listeComb[35] = 15;
		
		Grille.listeComb[36] = 3;
		Grille.listeComb[37] = 6;
		Grille.listeComb[38] = 9;
		Grille.listeComb[39] = 12;
	}
	
	// ----------------------------------------------------------------------------------------------------------
	// ---------------------------------------------METHODES UTILES----------------------------------------------
	// ----------------------------------------------------------------------------------------------------------

	public Pion getPion (int i){ // retourne un String du pion à l'indice i 
		return this.pions[i];
	}
	
	public void setListeCase(Pion p, int i){		
		this.listeCase[i] = new Pion(p.getTaille(), p.getCouleur(), p.getInterieur(), p.getForme());
	}
	
	public Pion[] getListeCase(){
		return this.listeCase;
	}
	
	public static int getListeComb(int i){
		return Grille.listeComb[i];
	}
	
	public void setCase (String pion, int indice){ // concatène le pion dans la case du tableau de cases
		this.cases[indice] = "|"+pion+"|";
	}
	
	public void setNulli(int i){ // efface le pion à l'indice i
		this.pions[i].setNull();
	}
	
	public boolean getPionRestant(int i){ // retourne true si le pion à l'indice i est disponible
		return pionRestant[i-1];
	}

	public void setPionRestantI(int i, boolean b){ // modifie le tableau des pions restants. En général b = false
		this.pionRestant[i-1] = b;
	}

	public void setCaseRestanteI(int i, boolean b){ // modifie le tableau des cases restantes. En général b = false
		this.caseRestante[i] = b;
	}
	
	// ----------------------------------------------------------------------------------------------------------
	// ---------------------------------------------METHODES UTILES----------------------------------------------
	// ----------------------------------------------------------------------------------------------------------
	
	public String construction (){ // Concatène la grille en String
		
		StringBuilder res = new StringBuilder();
		res.append("                      1      2      3      4\n");
		
		for (int i=0; i<taille; i++){
			
			res.append(this.colonneUn[i]);
			res.append(this.pions[i].toStringPion());
			res.append(this.colonneDeux[i]);
			
			if(i==0) {
				res.append(this.cases[0]);
				res.append(this.cases[1]);
				res.append(this.cases[2]);
				res.append(this.cases[3]);
			}
			else if(i==1) {
				res.append(this.cases[4]);
				res.append(this.cases[5]);
				res.append(this.cases[6]);
				res.append(this.cases[7]);
			}
			else if(i==2) {
				res.append(this.cases[8]);
				res.append(this.cases[9]);
				res.append(this.cases[10]);
				res.append(this.cases[11]);
			}
			else if(i==3) {
				res.append(this.cases[12]);
				res.append(this.cases[13]);
				res.append(this.cases[14]);
				res.append(this.cases[15]);
			}
			
			res.append("\n");
		}
		return res.toString();
	}
	
	public int recupCase (String ligne, String colonne){ // retourne l'indice dans le tableau des cases, de la case choisit
		
		int i, c;
		i = -1;
		
		c = Integer.parseInt(colonne);
		if (ligne.equals("A")) i = c -1;
		else if (ligne.equals("B")) i = c +3;
		else if (ligne.equals("C")) i = c +7;
		else if (ligne.equals("D")) i = c + 11;
		
		return i;
	}
	
	public String[] transfo (String s){ // transforme une String en tab 
		String[] position;
		
		position = s.split(""); 
		return position;
	}
	
	public boolean caseOk (String s){ // Début de la procédure de vérification de la case. Renvoi true si la case est dispo
		
		String[] ligneColonne = transfo(s);
		int indiceC = recupCase(ligneColonne[0], ligneColonne[1]);
		
		if (indiceC != -1){                    
			if (this.caseRestante[indiceC]){
					return true;
			}
			else {System.out.println("Erreur : La case n'est pas disponible"); return false;}
		}
		else {System.out.println("Erreur : La case n'existe pas"); return false;}
	}
	
	public void modifCase (Pion pion, String s){ // Effectue les modifications dans les attributs pour que la case ne soit plus dispo

		String pionString = pion.toStringPion();
		String[] position = transfo(s);
		int indiceC = recupCase(position[0], position[1]);
		
		this.setCase(pionString, indiceC); // Pour affichage
		this.setListeCase(pion, indiceC); // Pour gestion de gain
		this.setCaseRestanteI(indiceC, false);
	}
	
	/*
	 * renvois une ArrayList<String> selon : [0]: le critère qui fait gagner
	 * 							  			 [1]: listeComb[j]
	 */
	public ArrayList<String> gagne (){
		
		ArrayList<String> res = new ArrayList<String>();
		
		for(int i=0; i< Grille.listeComb.length; i+=4){
			if(this.listeCase[Grille.listeComb[i]] != null && this.listeCase[Grille.listeComb[i+1]] != null && this.listeCase[Grille.listeComb[i+2]] != null && this.listeCase[Grille.listeComb[i+3]] != null){
				if( this.listeCase[Grille.listeComb[i]].memeCouleur(this.listeCase[Grille.listeComb[i+1]], this.listeCase[Grille.listeComb[i+2]], this.listeCase[Grille.listeComb[i+3]])){
					// Gain par les couleurs, case j à j+4
					res.add("couleur");
					res.add(Integer.toString(i));							
				}
			
				if( this.listeCase[Grille.listeComb[i]].memeTaille(this.listeCase[Grille.listeComb[i+1]], this.listeCase[Grille.listeComb[i+2]], this.listeCase[Grille.listeComb[i+3]])){
					// Gain par les Tailles, case j à j+4
					res.add("taille");
					res.add(Integer.toString(i));	
				}
			
				if( this.listeCase[Grille.listeComb[i]].memeInterieur(this.listeCase[Grille.listeComb[i+1]], this.listeCase[Grille.listeComb[i+2]], this.listeCase[Grille.listeComb[i+3]])){
					// Gain par les Interieur, case j à j+4
					res.add("interieur");
					res.add(Integer.toString(i));	
				}
			
				if( this.listeCase[Grille.listeComb[i]].memeForme(this.listeCase[Grille.listeComb[i+1]], this.listeCase[Grille.listeComb[i+2]], this.listeCase[Grille.listeComb[i+3]])){
					// Gain par les Forme, case j à j+4
					res.add("forme");
					res.add(Integer.toString(i));	
				}
			}
		}
		return res;
	}
}
