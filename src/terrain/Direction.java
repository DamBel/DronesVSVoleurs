package terrain;
import java.util.Random;
public enum Direction {
  NORD(0, 1), 
  EST(1, 0),
  SUD(0, -1),
  OUEST(-1, 0),
  NORD_EST(1, 1),
  NORD_OUEST(-1, 1),
  SUD_OUEST(-1, -1),
  SUD_EST(1, -1);
 
  private int dx;
  private int dy;
  
  private Direction(int x, int y){
	  this.dx = x;
	  this.dy = y;
  }
  
  public Direction randomDirection(){
	  Random rd = new Random();
	  int i = rd.nextInt(Direction.values().length);
	  return Direction.values()[i];
  }
  
  public Direction randomOrthoDirection(){
	  Random rd = new Random();
	  int i = rd.nextInt((Direction.values().length)/2);
	  return Direction.values()[i];
  }
  
  public int getDx(){
	  return dx;
  }
  
  public int getDy(){
	  return dy;
  }
}

