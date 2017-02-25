package terrain;
import java.util.LinkedList;
import java.util.Scanner;
import lecture_fichier.LectureFichier;
import entit�.Argent;
import entit�.Drone;
import entit�.FabriqueEntit�;
import entit�.Voleur;

/**
 * Le terrain est compos� du plan de l'entrep�t et ses dimensions.
 * Il stocke �galement les voleurs, drones et sommes d'argent dans des listes.
 * @author Damien + Gaetan
 *
 */
public class Terrain {
	
	private char[][] plan; //plan de l'entrep�t
	private LinkedList<Voleur> voleurs; //liste des voleurs
	private LinkedList<Drone> drones; //liste des drones
	private LinkedList<Argent> argents; //liste des sommes d'argents
	private int nbligne;
	private int nbcolonne;
	
	public Terrain(FabriqueEntit� fabrique, int nblignes, int nbcolonnes, Scanner sc){
		
		Direction d = Direction.NORD;
		
		String currentLine = new String();
	
		this.nbligne = nblignes;
		this.nbcolonne = nbcolonnes;
		
		plan = new char[nbligne][nbcolonne];
		
		drones = new LinkedList<Drone>();
		voleurs = new LinkedList<Voleur>();
		argents = new LinkedList<Argent>();	
		
		currentLine = LectureFichier.nextLine(sc);
		int i = 0;
		
		while((LectureFichier.finFichier(sc))){			
			
			currentLine = LectureFichier.nextLine(sc);			
			
			for (int j = 0; j < nbligne; ++j){				
				
				char c = currentLine.charAt(j);				
	
				//TODO : si possible transformer cet �norme if en switch
				if (c == 'v'){
					voleurs.add(fabrique.getVoleur(j,i,c,d.randomDirection()));
					plan[j][i] = c;
				}				
				else if (c == 'V'){
					voleurs.add(fabrique.getBigVoleur(j,i,c,d.randomDirection()));
					plan[j][i] = c;
				}				
				else if (c == 'd'){
					drones.add(fabrique.getDrone(j,i,c,d.randomDirection()));
					plan[j][i] = c;
				}
				else if(c == 'D'){
					drones.add(fabrique.getBigDrone(j,i,c,d.randomDirection(), this));
					plan[j][i] = c;
				}
				else if ('0' <= c && c <= '9'){
					argents.add(fabrique.getArgent(j,i,c));
					plan[j][i] = c;
				}				
				else if (c == 'X')
					plan[j][i] = 'X';				
				else
					plan[j][i] = ' ';
			}
			++i;
		}		
		LectureFichier.fermerFichier(sc);
	}			
	
	/**
	 * Met � jour une case de coordonn�es (x,y)
	 * @param x abscisse
	 * @param y ordonn�e
	 * @param c nouveau contenu de la case
	 */
	public void setCase(int x, int y, char c){
		assert(x<0 || nbligne < x || y<0 || nbcolonne < y);
		plan[x][y] = c;
	}
	
	/**
	 * Renvoie si oui ou non la case de
	 * coordonn�es (x,y) est vide
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return true si la case est vide, false sinon
	 */
	public boolean caseVide(int x, int y){
		if (plan[x][y] == 'X' || 
				plan[x][y] == 'V' ||  
				plan[x][y] == 'v' || 
				plan[x][y] == 'D' ||
				plan[x][y] == 'E' ||
				plan[x][y] == 'd')
			return false;
		else
			return true;
	}
	
	public char getCase(int x, int y){
		return plan[x][y];
	}
	
	public int getNbLigne(){
		return nbligne;
	}
	
	public int getNbColonne(){
		return nbcolonne;
	}
	
	public Argent getArgent(int i){
		return argents.get(i);
	}
	
	public Voleur getVoleur(int i){
		return voleurs.get(i);
	}
	
	public Drone getDrone(int i){
		return drones.get(i);
	}
	
	/**
	 * Renvoie la somme d'argent pr�sente sur la case de coordonn�e (x,y)
	 * seulement si elle existe. Si aucune somme n'y est pr�sente, "null" est renvoy�
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return la somme d'argent correspondante si elle existe, "null" sinon
	 */
	public Argent getArgentWithXY(int x, int y){
		for(Argent a : argents){
			if (a.getX() == x && a.getY() == y)
				return a;
		}
		return null;
	}
	
	/**
	 * Renvoie le voleur pr�sent sur la case de coordonn�e (x,y)
	 * seulement s'il existe. Si aucun voleur est pr�sent, "null" est renvoy�
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return le voleur correspondant s'il existe, "null" sinon
	 */
	public Voleur getVoleurWithXY(int x, int y){
		for(Voleur v : voleurs)
			if (this.estVoleur(x,y))
				return v;
		return null;
	}
	
	/**
	 * Confirme si la case de coordonn�e (x,y) est
	 * bien occup�e par une somme d'argent
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return si la case est occup�e
	 */
	public boolean estArgent(int x, int y){				
		for (Argent a : argents)
			if (a.getX() == x && a.getY() == y && !a.estVol�())
				return true;
		return false;
	}
	
	/**
	 * Confirme si la case de coordonn�e (x,y) est
	 * bien occup�e par un voleur
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return true si elle est occup�e par un voleur, false sinon
	 */
	public boolean estVoleur(int x, int y){
		for (Voleur v : voleurs)
			if (plan[x][y] == 'v' || plan[x][y] == 'V' && !v.getStatus())
				return true;
		return false;
	}
	
	public LinkedList<Voleur> getListeVoleurs(){
		return voleurs;
	}
	
	public LinkedList<Drone> getListeDrones(){
		return drones;
	}
	
	public LinkedList<Argent> getListeArgents(){
		return argents;
	}
	
	public void Afficher(){
		for(int i = 0; i < nbcolonne; ++i){
			for(int j = 0; j < nbligne; ++j){
				System.out.print(this.getCase(j, i));
			}
			System.out.println();
		}		
		for (Voleur v : voleurs){
			if (v.getStatus()){
				System.out.println("Le voleur '" + v.getId() + "' est arr�t�: encore " + 
						(v.getSommeArgent()) + " tour(s). ");
				System.out.print("Somme actuellement poss�d�e: 0�.");
			}
			else{
				System.out.print("Le voleur '" + v.getId() + "' est libre. ");
				if (this.getCase(v.getX(), v.getY()) == 'E')
					System.out.println("Il est actuellement d�guis�. ");
				else
					System.out.println();
				System.out.print("Somme actuellement poss�d�e: " + v.getSommeArgent() + "�.");
			}
			System.out.println(" Argent vol� (au total): " + v.getTotal() + "�.");
		}
		System.out.println();
		for (Voleur v : voleurs)
			v.Afficher();
		for (Drone d : drones)
			d.Afficher();
		for(Argent a : argents)
			a.Afficher();
		System.out.println();
	}
}
