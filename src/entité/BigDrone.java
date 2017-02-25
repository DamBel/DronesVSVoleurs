package entité;
import terrain.Direction;
import terrain.Terrain;

/**
 * Le BigDrone est un drone plus élaboré qui possède une
 * zone de surveillance. Ses déplacements sont limités
 * à cette zone
 * @author Damien + Gaëtan
 *
 */
public class BigDrone extends Drone{
	
	private ZoneDeSurveillance zone;

	public BigDrone(int x, int y, char c, Direction d, Terrain t) {
		super(x, y, c, d);
		zone = new ZoneDeSurveillance(t, this);
	}
	
	@Override
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
	
	@Override
	public boolean déplacementPossible(Terrain t, int x, int y){
		return t.caseVide(x, y) && zone.dansZone(this);
	}
}
