package entit�;

import terrain.Direction;
import terrain.Terrain;

/**
 * Le policier est une entit� tr�s dangereuse pour les voleurs, capable de les �liminer � distance
 * tout en ignorant leur status actuel (d�guis� ou non, arr�t�, etc).
 * Il h�rite de la classe Entit�.
 * @version 0.1, en cours de dev
 * @see Entit�
 * @author Damien_Admin2
 */
public class Policier extends Entit�{

	//port�e de l'arme du policier
	private int port�e;
	
	//Au bout de X tours, un policier � une chance d'apparaitre � partir des entr�es d�finies au d�part lors de la g�n�ration du terrain
	private final int NB_TOURS_AV_APPAR = 50;
	
	//Apr�s X tours (NB_TOURS_AV_APPAR), un policier � une chance sur PROBA APPARITION d'apparaitre
	private final int PROBA_APPARITION = 20; // = 10% par tour
	
	public Policier(int x, int y, char c, Direction d, int port�e) {
		super(x, y, c, d);
		
		// TODO Auto-generated constructor stub
		
		this.port�e = port�e;
	}
	
	public void �liminerVoleur(Terrain t){
		
		
		
	}
	
	public boolean peutTirer(int xV, int yV){
		
		int xDistance = Math.abs(xV - this.getX());
		int yDistance = Math.abs(yV - this.getY());
		
		
		
	}
	
	public int getPort�e(){
		return this.port�e;
	}
	
	

}
