package appli;
import entité.Argent;
import entité.Drone;
import entité.Voleur;
import terrain.Terrain;

/**
 * Classe qui s'occupe du bon déroulement de l'animation
 * @author Damien + Gaetan
 *
 */
public class Animation {
	
	private int compteur; //compte le nombre de pas d'animation
	private int duréePause; //durée de la pause entre deux pas d'animation
	
	public Animation(int duréePause){
		this.compteur = 0;
		this.duréePause = duréePause;
	}
	
	/**
	 * S'occupe d'un pas d'animation en déplaçant les entités
	 * @param t le terrain
	 */
	public void animer(Terrain t){
		while (!(this.estTerminé(t))){
			for (Voleur v : t.getListeVoleurs()){
				v.déplacer(t);
				v.prendreArgent(t);
			}
			for (Drone d : t.getListeDrones()){
				d.déplacer(t);
				d.arrêterVoleur(t);
			}
			for (Argent a : t.getListeArgents())
				a.miseAJour(t);
			
			t.Afficher();
			this.pause();
			++compteur;
		}
		this.Affichage();
	}
	
	private void pause(){
		try {
			Thread.sleep(duréePause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean estTerminé(Terrain t){
		return (this.AllArgentVolé(t) || this.AllVoleurArrêté(t));
	}
	
	/**
	 * Parcourt toutes les cases "Argent" du terrain
	 * @param t le terrain
	 * @return true si elles ont toutes été volées, false sinon
	 */
	public boolean AllArgentVolé(Terrain t){
		for (Argent a : t.getListeArgents())
			if (!(a.estVolé()))
				return false;
		return true;
	}
	
	/**
	 * Observe l'état de tous les voleurs
	 * @param t le terrain
	 * @return true s'ils sont tous arrêtés, false sinon
	 */
	public boolean AllVoleurArrêté(Terrain t){
		for (Voleur v : t.getListeVoleurs())
			if (!(v.getStatus()))
				return false;
		return true;
	}
	
	public void Affichage(){
		System.out.println();
		System.out.println("La simulation s'est terminé en " + compteur + " tours.");
	}
}
