package entité;

import terrain.Direction;
import terrain.Terrain;

/**
 * Le policier est une entité très dangereuse pour les voleurs, capable de les éliminer à distance
 * tout en ignorant leur status actuel (déguisé ou non, arrêté, etc).
 * Il hérite de la classe Entité.
 * @version 0.1, en cours de dev
 * @see Entité
 * @author Damien_Admin2
 */
public class Policier extends Entité{

	//portée de l'arme du policier
	private int portée;
	
	//Au bout de X tours, un policier à une chance d'apparaitre à partir des entrées définies au départ lors de la génération du terrain
	private final int NB_TOURS_AV_APPAR = 50;
	
	//Après X tours (NB_TOURS_AV_APPAR), un policier à une chance sur PROBA APPARITION d'apparaitre
	private final int PROBA_APPARITION = 20; // = 10% par tour
	
	public Policier(int x, int y, char c, Direction d, int portée) {
		super(x, y, c, d);
		
		// TODO Auto-generated constructor stub
		
		this.portée = portée;
	}
	
	public void éliminerVoleur(Terrain t){
		
		
		
	}
	
	public boolean peutTirer(int xV, int yV){
		
		int xDistance = Math.abs(xV - this.getX());
		int yDistance = Math.abs(yV - this.getY());
		
		
		
	}
	
	public int getPortée(){
		return this.portée;
	}
	
	

}
