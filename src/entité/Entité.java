package entité;
import terrain.Direction;
import terrain.Terrain;

/**
 * Classe abstraite "entité" pouvant se déplacer dans
 * des directions aléatoires
 * @author Damien + Gaetan
 */
public abstract class Entité {
	
	private int xpos; //abscisse
	private int ypos; //ordonnée
	private char id; //identifiant
	private Direction direction; //direction actuelle de l'entité
	private StringBuilder récap;
	
	public Entité(int x, int y, char c, Direction d){
		this.xpos = x;
		this.ypos = y;
		this.id = c;
		this.direction = d;
		this.récap = new StringBuilder();
	}
	
	public int getX(){
		return xpos;
	}
	
	public int getY(){
		return ypos;
	}
	
	public char getId(){
		return id;
	}
	
	public void setX(int x){
		xpos = x;
	}
	
	public void setY(int y){
		ypos = y;
	}
	
	public void setDirection(Direction d){
		direction = d;
	}
	
	public void setId(char c){
		id = c;
	}
	
	public void ajouterRécap(String s){
		récap.append(s);
	}
	
	public void effacerRécap(){
		récap.setLength(0);
	}
	
	public void Afficher(){
		if (récap.length() != 0)
			System.out.println(récap.toString());
		effacerRécap();
	}
	
	public Direction getDirection(){
		return direction;
	}
	/**
	 * Génère une direction aléatoire
	 * @param d
	 */
	public void choisirDirection(Direction d){
		this.setDirection(d.randomDirection());
	}
	/**
	 * Déplace l'entité dans une direction aléatoire
	 * @param t le terrain
	 */
	public void déplacer(Terrain t){
		boolean estDéplacé = false;		
		while (!estDéplacé){				
			this.choisirDirection(this.getDirection());		
			if (déplacementPossible
					(t, this.getX() + this.getDirection().getDx(), 
							this.getY() + this.getDirection().getDy())){					
				t.setCase(getX(), getY(), ' ');
				setX(this.getX() + getDirection().getDx());
				setY(this.getY() + getDirection().getDy());
				dessiner(t);
				estDéplacé = true;
			}
			else
				continue;
		}
	}
	/**
	 * Renvoie si le déplacement est possible 
	 * à la case de coordonnées (x,y)
	 * @param t le terrain
	 * @param x abscisse
	 * @param y ordonnée
	 * @return true si le déplacement est possible, false sinon
	 */
	public boolean déplacementPossible(Terrain t, int x, int y){
		return t.caseVide(x, y);
	}
	
	public void dessiner(Terrain t){
		t.setCase(xpos, ypos, id);
	}

}
