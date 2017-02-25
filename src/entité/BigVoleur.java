package entit�;
import java.util.Random;
import terrain.Direction;
import terrain.Terrain;

/**
 * Classe qui h�rite de "Voleur". Le BigVoleur est un voleur plus
 * intelligent que le voleur de base. Il est en effet capable de 
 * se d�guiser en employ� pour �chapper � la surveillance des drones
 * @author Damien + Gaetan
 *
 */
public class BigVoleur extends Voleur{
	
	private boolean estD�guis�; //�tat du d�guisement (actif ou non)
	private int tpsD�guiRestant; //nombre de pas d'animation restant du d�guisement
	private static final char DEGUISEMENT = 'E';
	private static final int DUREE_DEGUISEMENT = 30;
	
	/*
	 * A chaque tour, le voleur a une chance de se d�guiser.
	 * Pour se d�guiser, il doit cependant �tre libre. La chance pour le
	 * BigVoleur de se d�guiser est �gale � une chance sur "PROBA_DEGUISEMENT"
	 */
	private static final int PROBA_DEGUISEMENT = 25;
	
	public BigVoleur(int x, int y, char c, Direction d){
		super(x, y, c, d);
		this.estD�guis� = false;
		tpsD�guiRestant = 0;
	}
	
	/**
	 * D�guise le voleur
	 */
	public void d�guiser(){
		estD�guis� = true;
		tpsD�guiRestant = DUREE_DEGUISEMENT;
	}
	
	/**
	 * D�cide � chaque tour si le bigvoleur va se d�guiser
	 */
	public void peutSeD�guiser(){
		Random r = new Random();
		if (r.nextInt(PROBA_DEGUISEMENT) == 0)
			this.d�guiser();
	}
	
	@Override
	public void d�placer(Terrain t){
		if (getSommeArgent() <= 0 && this.getStatus())
			setStatus(false);
		if (!getStatus()){
			if (estD�guis� == false)
				this.peutSeD�guiser();
			else{
					--tpsD�guiRestant;
					if (tpsD�guiRestant == 0)
						estD�guis� = false;
			}
			super.d�placer(t);
		}
		else{
			this.setSommeArgent(this.getSommeArgent() - 1);
			if (this.getSommeArgent() <= 0 && this.getStatus()){
				String s = "Le voleur " + this.getId() + " en (" + getX() + ","
						+ getY() + ") est sorti de son �tat d'arrestation.";
				this.ajouterR�cap(s);
			}
		}
	}
	
	@Override
	public void dessiner(Terrain t){
		if (estD�guis�)
			t.setCase(getX(), getY(), DEGUISEMENT);
		else
			t.setCase(getX(), getY(), getId());
	}
	
	public char getD�gui(){
		return DEGUISEMENT;
	}
}
