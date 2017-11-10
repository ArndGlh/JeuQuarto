
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
	
	public String toStringPion(){
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
	
	public boolean memeTaille(Pion a, Pion b, Pion c){
		if(a.getTaille().equals(b.getTaille()) && a.getTaille().equals(c.getTaille()) && a.getTaille().equals(this.getTaille())) return true;
		else return false;
	}
	
	public boolean memeCouleur(Pion a, Pion b, Pion c){
		if(a.getCouleur().equals(b.getCouleur()) && a.getCouleur().equals(c.getCouleur()) && a.getCouleur().equals(this.getCouleur())) return true;
		else return false;
	}
	
	public boolean memeInterieur(Pion a, Pion b, Pion c){
		if(a.getInterieur().equals(b.getInterieur()) && a.getInterieur().equals(c.getInterieur()) && a.getInterieur().equals(this.getInterieur())) return true;
		else return false;
	}
	
	public boolean memeForme(Pion a, Pion b, Pion c){
		if(a.getForme().equals(b.getForme()) && a.getForme().equals(c.getForme()) && a.getForme().equals(this.getForme())) return true;
		else return false;
	}
}
