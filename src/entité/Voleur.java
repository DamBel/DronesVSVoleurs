package entit�;
import terrain.Direction;
import terrain.Terrain;

/**
 * Le voleur h�rite de la classe "entit�". En plus de pouvoir se
 * d�placer, il peux �galement voler le contenu d'une case "Argent"
 * @author Damien + Gaetan
 *
 */
public class Voleur extends Entit�{
	
	private int sommeArgent; //Argent actuellement poss�d�
	private int totalArgent; //Argent vol� au total
	private boolean estArr�t�; //Statut actuel du voleur: libre ou arr�t�
	
	public Voleur(int x, int y, char c, Direction d){
		super(x,y,c, d.randomDirection());
		sommeArgent = 0;
		estArr�t� = false;
	}
	
	@Override
	public void d�placer(Terrain t){		
		if (!estArr�t�){		
			super.d�placer(t);
		}		
		else{
			--sommeArgent;
			if (sommeArgent <= 0){
				estArr�t� = false;
				String s = "Le voleur " + this.getId() + " en (" + getX() + ","
						+ getY() + ") est sorti de son �tat d'arrestation.";
				this.ajouterR�cap(s);
			}
		}
	}
	
	/**
	 * Examine les cases autour du voleur. Si une somme d'argent
	 * y est pr�sente, le voleur la r�cup�re. Si plusieurs sommes
	 * sont pr�sentes, le voleur r�cup�re le contenu d'une seule case
	 * @param t le terrain
	 */
	public void prendreArgent(Terrain t){		
		if (!(this.getStatus())){
			for (int i = this.getX() - 1; i <= this.getX() + 1; ++i){
				for (int j = this.getY() - 1; j <= this.getY() + 1; ++j){
					for (Argent a : t.getListeArgents()){
						if (a.getX() == i && a.getY() == j && !(a.estVol�())){
							sommeArgent += t.getArgentWithXY(i, j).getValeur();
							totalArgent += t.getArgentWithXY(i, j).getValeur();
							String s = "Argent vol� en (" + t.getArgentWithXY(i, j).getX() + "," 
									+ t.getArgentWithXY(i, j).getY() + "). Montant: " 
									+ t.getArgentWithXY(i, j).getValeur() + "�.";
							t.getArgentWithXY(i, j).ajouterR�cap(s);
							t.getArgentWithXY(i, j).setValeur(0);
							t.getArgentWithXY(i, j).setStatus(true);
							t.setCase(i, j, ' ');
							/*
							 * On quitte ensuite la boucle, car un voleur
							 * ne peut voler qu'une seule case par tour
							 */
							j = 999;
							i = 999;
						}
					}
				}
			}
		}
	}
	
	public boolean getStatus(){
		return estArr�t�;
	}
	
	public int getTotal(){
		return totalArgent;
	}
	
	public void setStatus(boolean b){
		estArr�t� = b;
	}
	
	public int getSommeArgent(){
		return sommeArgent;
	}
	
	public void setSommeArgent(int a){
		sommeArgent = a;
	}
}
