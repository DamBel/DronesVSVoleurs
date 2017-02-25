package entit�;
import terrain.Direction;
import terrain.Terrain;

/**
 * Le drone h�rite de la classe "Entit�". Il poss�de la capacit�
 * d'arr�ter un voleur
 * @author Damien
 *
 */
public class Drone extends Entit�{

	public Drone(int x, int y, char c, Direction d){
		super(x,y,c,d);
	}
	
	@Override
	public void dessiner(Terrain t){
		t.setCase(getX(), getY(), getId());
	}
	
	/**
	 * Examine les cases autour du drone et arr�te
	 * tous les voleurs qui s'y trouve (seulement s'ils
	 * sont en possession d'argent)
	 * @param t le terrain
	 */
	public void arr�terVoleur(Terrain t){
			for (int i = this.getX() - 1; i <= this.getX() + 1; ++i){
				for (int j = this.getY() - 1; j <= this.getY() + 1; ++j){
					for (Voleur v : t.getListeVoleurs()){
						if (v.getX() == i && v.getY() == j && !(v.getStatus()) && t.estVoleur(i, j)){
							if (v.getSommeArgent() > 0){
								v.setStatus(true);
								String s = "voleur '" + v.getId() + "' arr�t� en case (" 
										+ v.getX() + "," + v.getY() + ") pour " + (v.getSommeArgent()) 
										+ " tours. Montant r�cup�r�: " + v.getSommeArgent() + "�.";
								this.ajouterR�cap(s);
							}
					}
				}
			}
		}
	}
}
