package top.tonymochel.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.JTextComponent;

public class Outils {
	
	/**
	 * Permet de définir si un composant de type JTextComponent est vide
	 * @param composant (JTextField, ...)
	 * 
	 * @return 
	 * 		false : le champs est remplie
	 * 		true : le champs est vide
	 * 	
	 */
	public static boolean estVide(JTextComponent composant){
		if(composant.getText().equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Vérifie si le contenu d'une ligne dans une list à un index et vide
	 * @param tabCategorie
	 * @param index
	 * @return true si le contenu d'une ligne est null, false sinon
	 */
	public static boolean elementEstNull(ArrayList<Object[]> tabCategorie, int index){
		boolean verif = false;
		
		for(Object[] obj :  tabCategorie){
			if(obj[index] == null || obj[index] == ""){
				verif = true;
				break;
			}
		}
		return verif;
	}
		
	/**
	 * Convertie un nombre de seconde en Chaine HH:MM:SS
	 * @param pSeconde : nombre de seconde
	 * @return une chaine de type (exemple 00:03:40 au format HH:MM:SS)
	 */
	public static String convertHMS(int pSeconde){
		
		int heure 	= (int) pSeconde/3600;
		int minute 	= (int) ((pSeconde % 3600) /60);
		int seconde	= (int) ((pSeconde % 3600) %60);
		
		// Mise ne place de l'affichage
		String res = ( ( heure < 10 ) 	? "0" + heure : heure )		+ ":" +
					 ( ( minute < 10 ) ? "0" + minute : minute )	+ ":" +
					 ( ( seconde < 10 ) ? "0" + seconde : seconde );
		return res;
		
	}
	/**
	 * Convertie une date au format HH:MM:SS en seconde
	 * @param date
	 * @return une nombre de seconde
	 */
	
	@SuppressWarnings("deprecation")
	public static int convertHMStoS(Date date){
		return ( (date.getSeconds()) + (date.getMinutes()*60) + (date.getHours()*60*60) );
	}
	
}
