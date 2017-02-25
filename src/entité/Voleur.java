package entité;
import terrain.Direction;
import terrain.Terrain;

/**
 * Le voleur hérite de la classe "entité". En plus de pouvoir se
 * déplacer, il peux également voler le contenu d'une case "Argent"
 * @author Damien + Gaetan
 *
 */
public class Voleur extends Entité{
	
	private int sommeArgent; //Argent actuellement possédé
	private int totalArgent; //Argent volé au total
	private boolean estArrêté; //Statut actuel du voleur: libre ou arrêté
	
	public Voleur(int x, int y, char c, Direction d){
		super(x,y,c, d.randomDirection());
		sommeArgent = 0;
		estArrêté = false;
	}
	
	@Override
	public void déplacer(Terrain t){		
		if (!estArrêté){		
			super.déplacer(t);
		}		
		else{
			--sommeArgent;
			if (sommeArgent <= 0){
				estArrêté = false;
				String s = "Le voleur " + this.getId() + " en (" + getX() + ","
						+ getY() + ") est sorti de son état d'arrestation.";
				this.ajouterRécap(s);
			}
		}
	}
	
	/**
	 * Examine les cases autour du voleur. Si une somme d'argent
	 * y est présente, le voleur la récupère. Si plusieurs sommes
	 * sont présentes, le voleur récupère le contenu d'une seule case
	 * @param t le terrain
	 */
	public void prendreArgent(Terrain t){		
		if (!(this.getStatus())){
			for (int i = this.getX() - 1; i <= this.getX() + 1; ++i){
				for (int j = this.getY() - 1; j <= this.getY() + 1; ++j){
					for (Argent a : t.getListeArgents()){
						if (a.getX() == i && a.getY() == j && !(a.estVolé())){
							sommeArgent += t.getArgentWithXY(i, j).getValeur();
							totalArgent += t.getArgentWithXY(i, j).getValeur();
							String s = "Argent volé en (" + t.getArgentWithXY(i, j).getX() + "," 
									+ t.getArgentWithXY(i, j).getY() + "). Montant: " 
									+ t.getArgentWithXY(i, j).getValeur() + "€.";
							t.getArgentWithXY(i, j).ajouterRécap(s);
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
		return estArrêté;
	}
	
	public int getTotal(){
		return totalArgent;
	}
	
	public void setStatus(boolean b){
		estArrêté = b;
	}
	
	public int getSommeArgent(){
		return sommeArgent;
	}
	
	public void setSommeArgent(int a){
		sommeArgent = a;
	}
}
