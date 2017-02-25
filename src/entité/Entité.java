package entit�;
import terrain.Direction;
import terrain.Terrain;

/**
 * Classe abstraite "entit�" pouvant se d�placer dans
 * des directions al�atoires
 * @author Damien + Gaetan
 */
public abstract class Entit� {
	
	private int xpos; //abscisse
	private int ypos; //ordonn�e
	private char id; //identifiant
	private Direction direction; //direction actuelle de l'entit�
	private StringBuilder r�cap;
	
	public Entit�(int x, int y, char c, Direction d){
		this.xpos = x;
		this.ypos = y;
		this.id = c;
		this.direction = d;
		this.r�cap = new StringBuilder();
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
	
	public void ajouterR�cap(String s){
		r�cap.append(s);
	}
	
	public void effacerR�cap(){
		r�cap.setLength(0);
	}
	
	public void Afficher(){
		if (r�cap.length() != 0)
			System.out.println(r�cap.toString());
		effacerR�cap();
	}
	
	public Direction getDirection(){
		return direction;
	}
	/**
	 * G�n�re une direction al�atoire
	 * @param d
	 */
	public void choisirDirection(Direction d){
		this.setDirection(d.randomDirection());
	}
	/**
	 * D�place l'entit� dans une direction al�atoire
	 * @param t le terrain
	 */
	public void d�placer(Terrain t){
		boolean estD�plac� = false;		
		while (!estD�plac�){				
			this.choisirDirection(this.getDirection());		
			if (d�placementPossible
					(t, this.getX() + this.getDirection().getDx(), 
							this.getY() + this.getDirection().getDy())){					
				t.setCase(getX(), getY(), ' ');
				setX(this.getX() + getDirection().getDx());
				setY(this.getY() + getDirection().getDy());
				dessiner(t);
				estD�plac� = true;
			}
			else
				continue;
		}
	}
	/**
	 * Renvoie si le d�placement est possible 
	 * � la case de coordonn�es (x,y)
	 * @param t le terrain
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return true si le d�placement est possible, false sinon
	 */
	public boolean d�placementPossible(Terrain t, int x, int y){
		return t.caseVide(x, y);
	}
	
	public void dessiner(Terrain t){
		t.setCase(xpos, ypos, id);
	}

}
