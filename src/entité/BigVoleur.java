package entité;
import java.util.Random;
import terrain.Direction;
import terrain.Terrain;

/**
 * Classe qui hérite de "Voleur". Le BigVoleur est un voleur plus
 * intelligent que le voleur de base. Il est en effet capable de 
 * se déguiser en employé pour échapper à la surveillance des drones
 * @author Damien + Gaetan
 *
 */
public class BigVoleur extends Voleur{
	
	private boolean estDéguisé; //état du déguisement (actif ou non)
	private int tpsDéguiRestant; //nombre de pas d'animation restant du déguisement
	private static final char DEGUISEMENT = 'E';
	private static final int DUREE_DEGUISEMENT = 30;
	
	/*
	 * A chaque tour, le voleur a une chance de se déguiser.
	 * Pour se déguiser, il doit cependant être libre. La chance pour le
	 * BigVoleur de se déguiser est égale à une chance sur "PROBA_DEGUISEMENT"
	 */
	private static final int PROBA_DEGUISEMENT = 25;
	
	public BigVoleur(int x, int y, char c, Direction d){
		super(x, y, c, d);
		this.estDéguisé = false;
		tpsDéguiRestant = 0;
	}
	
	/**
	 * Déguise le voleur
	 */
	public void déguiser(){
		estDéguisé = true;
		tpsDéguiRestant = DUREE_DEGUISEMENT;
	}
	
	/**
	 * Décide à chaque tour si le bigvoleur va se déguiser
	 */
	public void peutSeDéguiser(){
		Random r = new Random();
		if (r.nextInt(PROBA_DEGUISEMENT) == 0)
			this.déguiser();
	}
	
	@Override
	public void déplacer(Terrain t){
		if (getSommeArgent() <= 0 && this.getStatus())
			setStatus(false);
		if (!getStatus()){
			if (estDéguisé == false)
				this.peutSeDéguiser();
			else{
					--tpsDéguiRestant;
					if (tpsDéguiRestant == 0)
						estDéguisé = false;
			}
			super.déplacer(t);
		}
		else{
			this.setSommeArgent(this.getSommeArgent() - 1);
			if (this.getSommeArgent() <= 0 && this.getStatus()){
				String s = "Le voleur " + this.getId() + " en (" + getX() + ","
						+ getY() + ") est sorti de son état d'arrestation.";
				this.ajouterRécap(s);
			}
		}
	}
	
	@Override
	public void dessiner(Terrain t){
		if (estDéguisé)
			t.setCase(getX(), getY(), DEGUISEMENT);
		else
			t.setCase(getX(), getY(), getId());
	}
	
	public char getDégui(){
		return DEGUISEMENT;
	}
}
