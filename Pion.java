
public class Pion {

	private String taille;    // Moyen / Grand
	private String couleur;   // Blanc / Noir
	private String interieur; // Vide / Plein
	private String forme;     // Rond / Carré
	
	public Pion (String t, String c, String i, String f){
		
		this.taille = t;
		this.couleur = c;
		this.interieur = i;
		this.forme = f;
	}
	
	public String toString (){
		
		return taille+couleur+" "+interieur+forme;
	}

	public String getTaille() {
		return taille;
	}

	public String getCouleur() {
		return couleur;
	}

	public String getInterieur() {
		return interieur;
	}

	public String getForme() {
		return forme;
	}
	
	public void setTaille(String taille) {
		this.taille = taille;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public void setInterieur(String interieur) {
		this.interieur = interieur;
	}

	public void setForme(String forme) {
		this.forme = forme;
	}
	
	public void setNull() {
		
		this.taille = " ";
		this.couleur = " ";
		this.interieur = " ";
		this.forme = " ";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
