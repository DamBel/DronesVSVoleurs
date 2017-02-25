package lecture_fichier;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Cette classe permet de lire un fichier texte
 * @author Damien + Gaetan
 *
 */
public class LectureFichier {
	
	/*
	 * Permet de lire un fichier texte
	 * Renvoie son contenu dans une variable
	 * de type stringbuilder
	 * @param: le chemin absolu du fichier texte
	 * @return: le contenu du fichier
	 */
	public static Scanner ouvrirFichier(String chemin){
		try {
			File f = new File(chemin);
			Scanner sc = new Scanner(f);
			return sc;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
		}
	}
	
	public static String nextLine(Scanner sc){
		String s = "";
		try{
			s = sc.nextLine();
		} catch(NoSuchElementException Exception) {
		}
		return s;
	}
	
	public static int nextInt(Scanner sc){
		int n = 0;
		try{
			n = sc.nextInt();
		} catch(NoSuchElementException Exception) {				
		}
		return n;
	}
	
	public static boolean finFichier(Scanner sc){
		return sc.hasNext();
	}
	
	public static void fermerFichier(Scanner sc){
		sc.close();
	}
}
