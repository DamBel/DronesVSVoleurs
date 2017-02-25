package entité;
import terrain.Direction;
import terrain.Terrain;

/**
 * Le drone hérite de la classe "Entité". Il possède la capacité
 * d'arrêter un voleur
 * @author Damien
 *
 */
public class Drone extends Entité{

	public Drone(int x, int y, char c, Direction d){
		super(x,y,c,d);
	}
	
	@Override
	public void dessiner(Terrain t){
		t.setCase(getX(), getY(), getId());
	}
	
	/**
	 * Examine les cases autour du drone et arrête
	 * tous les voleurs qui s'y trouve (seulement s'ils
	 * sont en possession d'argent)
	 * @param t le terrain
	 */
	public void arrêterVoleur(Terrain t){
			for (int i = this.getX() - 1; i <= this.getX() + 1; ++i){
				for (int j = this.getY() - 1; j <= this.getY() + 1; ++j){
					for (Voleur v : t.getListeVoleurs()){
						if (v.getX() == i && v.getY() == j && !(v.getStatus()) && t.estVoleur(i, j)){
							if (v.getSommeArgent() > 0){
								v.setStatus(true);
								String s = "voleur '" + v.getId() + "' arrêté en case (" 
										+ v.getX() + "," + v.getY() + ") pour " + (v.getSommeArgent()) 
										+ " tours. Montant récupéré: " + v.getSommeArgent() + "€.";
								this.ajouterRécap(s);
							}
					}
				}
			}
		}
	}
}
