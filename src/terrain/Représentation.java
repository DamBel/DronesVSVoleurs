package terrain;

public class Représentation {
	
	private static final char VOLEUR = 'v';
	private static final char BIG_VOLEUR = 'V';
	private static final char BIG_VOLEUR_DEGUISEMENT = 'E';
	private static final char DRONE = 'd';
	private static final char BIG_DRONE = 'D';
	private static final char POLICIER = 'P';
	private static final int PORTEE_POLICIER = 2;
	
	public Représentation(){}

	public static char getVoleur() {
		return VOLEUR;
	}

	public static char getBigVoleur() {
		return BIG_VOLEUR;
	}

	public static char getBigVoleurDeguisement() {
		return BIG_VOLEUR_DEGUISEMENT;
	}

	public static char getDrone() {
		return DRONE;
	}

	public static char getBigDrone() {
		return BIG_DRONE;
	}

	public static char getPolicier() {
		return POLICIER;
	}
	
	public static int getPortéePolicier() {
		return PORTEE_POLICIER;
	}

}
