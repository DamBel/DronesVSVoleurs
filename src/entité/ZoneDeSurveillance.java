package entité;
import java.util.Random;
import terrain.Direction;
import terrain.Terrain;

public class ZoneDeSurveillance {
	
	private static final int RAYON_MAX = 6;
	private static final int RAYON_MIN = 3;
	private int Xcentre; 
	private int Ycentre;
	private int rayon; 
	
	public ZoneDeSurveillance(Terrain t, BigDrone d){
		Random r = new Random();
		do
			this.rayon = r.nextInt(RAYON_MAX + 1);
		while(this.rayon < RAYON_MIN);
		
		this.Xcentre = d.getX();
		this.Ycentre = d.getY();
	}
	
	/**
	 * Vérifie si le drone est dans sa zone
	 * de surveillance
	 * @param d le BigDrone
	 * @return true si le drone est dans sa zone, false sinon
	 */
	public boolean dansZone(BigDrone d){
		return (Xcentre - rayon <= d.getDirection().getDx() + d.getX() 
				&& d.getDirection().getDx() + d.getX() <= Xcentre + rayon
				&& Ycentre - rayon <= d.getDirection().getDy() + d.getY() 
				&& d.getDirection().getDy() + d.getY() <= Ycentre + rayon);
	}
	
	/**
	 * Méthode se chargeant de trouver la position
	 * du drone en fonction de sa zone de surveillance,
	 * pour ensuite renvoyer une direction permettant à
	 * ce dernier de rejoindre sa zone
	 * @param d le bigdrone correspondant
	 * @param t le terrain
	 * @return
	 */
	public Direction posDroneZone(BigDrone d, Terrain t){
		int xmr = Xcentre-rayon;
		int xpr = Xcentre+rayon;
		int ymr = Ycentre-rayon;
		int ypr = Ycentre+rayon;
		if (xmr < 0)
			xmr = 0;
		if (xpr >= t.getNbLigne())
			xpr = t.getNbLigne()-1;
		if (ymr < 0)
			ymr = 0;
		if (ypr >= t.getNbColonne())
			ypr = t.getNbColonne()-1;
		if (d.getX() < xmr && d.getY() < ymr)
			return Direction.NORD_OUEST;
		else if (d.getX() < xmr && d.getY() > ypr)
			return Direction.SUD_OUEST;
		else if (d.getX() > xpr && d.getY() < ymr)
			return Direction.NORD_EST;
		else if (d.getX() > xpr && d.getY() > ypr)
			return Direction.SUD_EST;
		else if (d.getX() < xmr)
			return Direction.EST;
		else if (d.getX() > xpr)
			return Direction.OUEST;
		else if (d.getY() < ymr)
			return Direction.SUD;
		else if (d.getY() > ypr)
			return Direction.NORD;
		return null;
	}
}
