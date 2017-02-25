package appli;
import java.util.Scanner;

import entit�.FabriqueEntit�;
import lecture_fichier.LectureFichier;
import terrain.Terrain;

public class Application {
	
	public static void main(String[] args) {
		
		//TODO : D�placer les cr�ations d'objets dans une autre classe
		
		Scanner sc = LectureFichier.ouvrirFichier("src/terrain/projet2terrain.txt");
		
		int nblignes = LectureFichier.nextInt(sc);
		int nbcolonnes = LectureFichier.nextInt(sc);
		int dur�ePause = LectureFichier.nextInt(sc);
		
		Animation a = new Animation(dur�ePause);
		Terrain t = new Terrain(new FabriqueEntit�(), nblignes, nbcolonnes, sc);
		a.animer(t);
	
	}
}
