package entité;
import terrain.Direction;
import terrain.Représentation;
import terrain.Terrain;

/**
 * Classe distribuant des entités
 * @author Damien + Gaetan
 *
 */
public class FabriqueEntité {
	
	public Drone getDrone(int x, int y, char c, Direction d){
		return new Drone(x, y, c, d);
	}
	
	public Drone getBigDrone(int x, int y, char c, Direction d, Terrain t){
		return new BigDrone(x, y, c, d, t);
	}
	
	public Voleur getVoleur(int x, int y, char c, Direction d){
		return new Voleur(x, y, c, d);
	}
	
	public Voleur getBigVoleur(int x, int y, char c, Direction d){
		return new BigVoleur(x, y, c, d);
	}
	
	public Argent getArgent(int x, int y, char valeur){
		return new Argent(x, y, valeur);
	}
	
	public Policier getPolicier(int x, int y, char c, Direction d){
		return new Policier(x, y, c, d, Représentation.getPortéePolicier());
	}
}
