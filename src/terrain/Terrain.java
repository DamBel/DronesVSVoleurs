package terrain;
import java.util.LinkedList;
import java.util.Scanner;
import lecture_fichier.LectureFichier;
import entité.Argent;
import entité.Drone;
import entité.FabriqueEntité;
import entité.Voleur;

/**
 * Le terrain est composé du plan de l'entrepôt et ses dimensions.
 * Il stocke également les voleurs, drones et sommes d'argent dans des listes.
 * @author Damien + Gaetan
 *
 */
public class Terrain {
	
	private char[][] plan; //plan de l'entrepôt
	private LinkedList<Voleur> voleurs; //liste des voleurs
	private LinkedList<Drone> drones; //liste des drones
	private LinkedList<Argent> argents; //liste des sommes d'argents
	private int nbligne;
	private int nbcolonne;
	
	public Terrain(FabriqueEntité fabrique, int nblignes, int nbcolonnes, Scanner sc){
		
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
	
				//TODO : si possible transformer cet énorme if en switch
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
	 * Met à jour une case de coordonnées (x,y)
	 * @param x abscisse
	 * @param y ordonnée
	 * @param c nouveau contenu de la case
	 */
	public void setCase(int x, int y, char c){
		assert(x<0 || nbligne < x || y<0 || nbcolonne < y);
		plan[x][y] = c;
	}
	
	/**
	 * Renvoie si oui ou non la case de
	 * coordonnées (x,y) est vide
	 * @param x abscisse
	 * @param y ordonnée
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
	 * Renvoie la somme d'argent présente sur la case de coordonnée (x,y)
	 * seulement si elle existe. Si aucune somme n'y est présente, "null" est renvoyé
	 * @param x abscisse
	 * @param y ordonnée
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
	 * Renvoie le voleur présent sur la case de coordonnée (x,y)
	 * seulement s'il existe. Si aucun voleur est présent, "null" est renvoyé
	 * @param x abscisse
	 * @param y ordonnée
	 * @return le voleur correspondant s'il existe, "null" sinon
	 */
	public Voleur getVoleurWithXY(int x, int y){
		for(Voleur v : voleurs)
			if (this.estVoleur(x,y))
				return v;
		return null;
	}
	
	/**
	 * Confirme si la case de coordonnée (x,y) est
	 * bien occupée par une somme d'argent
	 * @param x abscisse
	 * @param y ordonnée
	 * @return si la case est occupée
	 */
	public boolean estArgent(int x, int y){				
		for (Argent a : argents)
			if (a.getX() == x && a.getY() == y && !a.estVolé())
				return true;
		return false;
	}
	
	/**
	 * Confirme si la case de coordonnée (x,y) est
	 * bien occupée par un voleur
	 * @param x abscisse
	 * @param y ordonnée
	 * @return true si elle est occupée par un voleur, false sinon
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
				System.out.println("Le voleur '" + v.getId() + "' est arrêté: encore " + 
						(v.getSommeArgent()) + " tour(s). ");
				System.out.print("Somme actuellement possédée: 0€.");
			}
			else{
				System.out.print("Le voleur '" + v.getId() + "' est libre. ");
				if (this.getCase(v.getX(), v.getY()) == 'E')
					System.out.println("Il est actuellement déguisé. ");
				else
					System.out.println();
				System.out.print("Somme actuellement possédée: " + v.getSommeArgent() + "€.");
			}
			System.out.println(" Argent volé (au total): " + v.getTotal() + "€.");
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
