import javax.swing.JFrame;

public class Grille extends JFrame{
	
	// Attributs --------------------------------------------------------
	private Pion[] pions; // tableau des pions de la grille
	private String[] colonneUn;   // "|1| "
	private String[] colonneDeux; // "|      "
	private String[] colonneTrois;
	private String[] cases;       // "|     |"
	private boolean [] pionRestant;  // tab des pions restant (true = dispo; false = non dispo)
	private boolean [] caseRestante; // tab des cases restantes (true = dispo; false = non dispo) 
	private static final int taille = 16; // taille des différents tableaux
	// ------------------------------------------------------------------

	public Grille () {
		
		//----------------------------------------------------------------- 
		pions = new Pion [taille];		
		colonneUn = new String [taille];
		colonneDeux = new String [taille];
		cases = new String [taille];
		pionRestant = new boolean[16];
		caseRestante = new boolean[16];
		//-----------------------------------------------------------------
		
		
		for (int i=0; i<pionRestant.length; i++){  // initialisation pionRestant[]
			pionRestant[i] = true;
		}
		
		for (int i=0; i<caseRestante.length; i++){ // initialisation caseRestant[]
			caseRestante[i] = true;
		}
		
		for(int i=0; i<taille; i++){ 			   // initialisation colonneUn
			if (i<9) colonneUn[i] = "|"+(i+1)+" | ";
			else colonneUn[i] = "|"+(i+1)+"| ";
		}

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
		
	}
	
	// ----------------------------------------------------------------------------------------------------------
	// ---------------------------------------------METHODES UTILES----------------------------------------------
	// ----------------------------------------------------------------------------------------------------------

	public String getPion (int i){ // retourne un String du pion à l'indice i 
		
		return this.pions[i].toString();
	}
	
	public void setCase (String pion, int indice){ // concatène le pion dans la case du tableau de cases
		
		this.cases[indice] = "|"+pion+"|";
	}
	
	public void setNulli(int i){ // efface le pion à l'indice i
		this.pions[i].setNull();
	}
	
	public boolean getPionRestant(int i) { // retourne true si le pion à l'indice i est disponible
		return pionRestant[i-1];
	}

	public void setPionRestantI(int i, boolean b) { // modifie le tableau des pions restants. En général b = false
		this.pionRestant[i-1] = b;
	}

	public void setCaseRestanteI(int i, boolean b) { // modifie le tableau des cases restantes. En général b = false
		this.caseRestante[i] = b;
	}
	
	// ----------------------------------------------------------------------------------------------------------
	// ---------------------------------------------METHODES UTILES----------------------------------------------
	// ----------------------------------------------------------------------------------------------------------
	
	public String construction (){ // Concatène la grille en String
		
		String res = "";
		res += "                      1      2      3      4\n";
		
		for (int i=0; i<taille; i++){
			
			res += this.colonneUn[i];
			res += this.pions[i].toString();
			res += this.colonneDeux[i];
			
			if(i==0) res += this.cases[0]+this.cases[1]+this.cases[2]+this.cases[3];
			else if(i==1) res += this.cases[4]+this.cases[5]+this.cases[6]+this.cases[7];
			else if(i==2) res += this.cases[8]+this.cases[9]+this.cases[10]+this.cases[11];
			else if(i==3) res += this.cases[12]+this.cases[13]+this.cases[14]+this.cases[15];
			
			res += "\n";
		}
		return res;
	}
	
	public int recupCase (String ligne, String colonne){ // retourne l'indice dans le tableau des cases, de la case choisit
		
		int i, c;
		i = -1;
		
		c = Integer.parseInt(colonne);
		if (ligne.equals("A")) i = c -1;
		else if (ligne.equals("B")) i = c +3 ;
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
	
	public void modifCase (String pion, String s){ // Effectue les modifications dans les attributs pour que la case ne soit plus dispo
		
		String[] position = transfo(s);
		int indiceC = recupCase(position[0], position[1]);
		
		this.setCase(pion, indiceC);
		this.setCaseRestanteI(indiceC, false);
	}
	
}
