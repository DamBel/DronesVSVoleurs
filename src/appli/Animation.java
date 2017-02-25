package appli;
import entit�.Argent;
import entit�.Drone;
import entit�.Voleur;
import terrain.Terrain;

/**
 * Classe qui s'occupe du bon d�roulement de l'animation
 * @author Damien + Gaetan
 *
 */
public class Animation {
	
	private int compteur; //compte le nombre de pas d'animation
	private int dur�ePause; //dur�e de la pause entre deux pas d'animation
	
	public Animation(int dur�ePause){
		this.compteur = 0;
		this.dur�ePause = dur�ePause;
	}
	
	/**
	 * S'occupe d'un pas d'animation en d�pla�ant les entit�s
	 * @param t le terrain
	 */
	public void animer(Terrain t){
		while (!(this.estTermin�(t))){
			for (Voleur v : t.getListeVoleurs()){
				v.d�placer(t);
				v.prendreArgent(t);
			}
			for (Drone d : t.getListeDrones()){
				d.d�placer(t);
				d.arr�terVoleur(t);
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
			Thread.sleep(dur�ePause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean estTermin�(Terrain t){
		return (this.AllArgentVol�(t) || this.AllVoleurArr�t�(t));
	}
	
	/**
	 * Parcourt toutes les cases "Argent" du terrain
	 * @param t le terrain
	 * @return true si elles ont toutes �t� vol�es, false sinon
	 */
	public boolean AllArgentVol�(Terrain t){
		for (Argent a : t.getListeArgents())
			if (!(a.estVol�()))
				return false;
		return true;
	}
	
	/**
	 * Observe l'�tat de tous les voleurs
	 * @param t le terrain
	 * @return true s'ils sont tous arr�t�s, false sinon
	 */
	public boolean AllVoleurArr�t�(Terrain t){
		for (Voleur v : t.getListeVoleurs())
			if (!(v.getStatus()))
				return false;
		return true;
	}
	
	public void Affichage(){
		System.out.println();
		System.out.println("La simulation s'est termin� en " + compteur + " tours.");
	}
}
