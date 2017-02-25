package appli;
import java.util.Scanner;

import entité.FabriqueEntité;
import lecture_fichier.LectureFichier;
import terrain.Terrain;

public class Application {
	
	public static void main(String[] args) {
		
		//TODO : Déplacer les créations d'objets dans une autre classe
		
		Scanner sc = LectureFichier.ouvrirFichier("src/terrain/projet2terrain.txt");
		
		int nblignes = LectureFichier.nextInt(sc);
		int nbcolonnes = LectureFichier.nextInt(sc);
		int duréePause = LectureFichier.nextInt(sc);
		
		Animation a = new Animation(duréePause);
		Terrain t = new Terrain(new FabriqueEntité(), nblignes, nbcolonnes, sc);
		a.animer(t);
	
	}
}
