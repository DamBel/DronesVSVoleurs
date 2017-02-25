package entité;

import terrain.Terrain;

/**
 * "Entité" fixe. Disparait du terrain 
 * une fois volée par un voleur
 * @author Damien
 *
 */
public class Argent extends Entité{
	private boolean estVolé; //état actuel de la somme d'argent
	
	public Argent(int x, int y, char valeur){
		super(x, y, valeur, null);
		this.estVolé = false;
	}
	
	public int getValeur(){
		return Character.getNumericValue(this.getId());
	}
	
	public char getCharValeur(){
		String s = "" + this.getId();
		return s.charAt(0);
	}
	
	public char getCharValeur(int n){
		String s = "" + n;
		return s.charAt(0);
	}
	
	public boolean estVolé(){
		return estVolé;
	}
	
	public void setValeur(int n){
		this.setId(getCharValeur(n));
	}
	
	public void setStatus(boolean b){
		estVolé = b;
	}
	
	/**
	 * Permet de réafficher la case une fois
	 * qu'elle n'est plus occupée par une entité
	 * @param t le terrain
	 */
	public void miseAJour(Terrain t){
		if (!this.estVolé && t.caseVide(getX(), getY()))
			t.setCase(getX(), getY(), getCharValeur());
	}
}
