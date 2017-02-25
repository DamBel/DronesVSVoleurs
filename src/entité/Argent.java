package entit�;

import terrain.Terrain;

/**
 * "Entit�" fixe. Disparait du terrain 
 * une fois vol�e par un voleur
 * @author Damien
 *
 */
public class Argent extends Entit�{
	private boolean estVol�; //�tat actuel de la somme d'argent
	
	public Argent(int x, int y, char valeur){
		super(x, y, valeur, null);
		this.estVol� = false;
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
	
	public boolean estVol�(){
		return estVol�;
	}
	
	public void setValeur(int n){
		this.setId(getCharValeur(n));
	}
	
	public void setStatus(boolean b){
		estVol� = b;
	}
	
	/**
	 * Permet de r�afficher la case une fois
	 * qu'elle n'est plus occup�e par une entit�
	 * @param t le terrain
	 */
	public void miseAJour(Terrain t){
		if (!this.estVol� && t.caseVide(getX(), getY()))
			t.setCase(getX(), getY(), getCharValeur());
	}
}
