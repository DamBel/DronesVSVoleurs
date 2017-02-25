package entit�;
import terrain.Direction;
import terrain.Terrain;

/**
 * Le BigDrone est un drone plus �labor� qui poss�de une
 * zone de surveillance. Ses d�placements sont limit�s
 * � cette zone
 * @author Damien + Ga�tan
 *
 */
public class BigDrone extends Drone{
	
	private ZoneDeSurveillance zone;

	public BigDrone(int x, int y, char c, Direction d, Terrain t) {
		super(x, y, c, d);
		zone = new ZoneDeSurveillance(t, this);
	}
	
	@Override
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
	
	@Override
	public boolean d�placementPossible(Terrain t, int x, int y){
		return t.caseVide(x, y) && zone.dansZone(this);
	}
}
