package top.tonymochel.main;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableauModelCourse extends AbstractTableModel {
	
	
	private List<Course> donnees = new ArrayList<Course>();
	private final String titre[] = new String[] {"N°Course","Intituler", "Distance", "Date"};
	private int idMax;
	
	/**
	 * Constructeur crée un tableau de course avec (identifiant, intutuler, distance)
	 */
	public TableauModelCourse(){
		super();
		remplisageTableau();		
	}
	
	/**
	 * @return - Nombre de colonnes
	 */
	public int getColumnCount(){
		return this.titre.length;
	}
	
	/**
	 * @return - Nombre de lignes
	 */
	public int getRowCount(){
		return this.donnees.size();
	}
	
	/**
	 * @return - Titre de la colonne
	 */
	public String getColumnName(int col){
		return this.titre[col];
	}
	
	/**
	 * retourne un element du tableau en spécifiant :
	 * @param row : l'indice de la ligne
	 * @param col : l'indice de la colonne
	 * @return - element à l'indice [row][col]
	 */
	public Object getValueAt(int row, int col){
		switch(col){
		case 0:
			return donnees.get(row).getIdCourse();
		case 1:
			return donnees.get(row).getIntituler();
		case 2:
			return donnees.get(row).getDistance();
		case 3:
			return donnees.get(row).getDateCourse();
	
		default:
			return null;
		}
	}
	
	
	
	/**
	 *  Remplie la JTable avec les donnée  des course en BDD
	 */
	public void remplisageTableau(){
		try{
			// Ouverture d'une connexion
			Connect cx = new Connect();
			
			// Execution d'une requête
			ResultSet rs = cx.recherche("SELECT * FROM Course");
			
			//Traitement des résultat optenu : On remplie la Liste de course
			while(rs.next()){
				this.donnees.add( new Course(rs.getInt("id_course"), rs.getString("intituler"), rs.getDouble("distance"), rs.getDate("date_course"), rs.getBoolean("cloturer")) );
				// Notifi la model du chamgement
				fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
			}
			
			
			//Déconnexion
			cx.deconnexion();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 *	Ajoute une course dans la liste 
	 * @param c - Course
	 * @see Course, Connect, relaodData
	 * 
	 */
	public void ajouterCourse(Course c){
		
		try{
			Connect cx = new Connect();
			
			// Modification du format de la date en YYYY MM DD
			String dateCourse = "";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			dateCourse = format.format(c.getDateCourse());
			
			// Insertion dans la base de donnée d'une nouvelle course
			cx.ajouter("INSERT INTO course (INTITULER, DISTANCE, DATE_COURSE, CLOTURER) VALUES ("+
								"'" + c.getIntituler() + "'"  	+ ", " +
								""  + c.getDistance()  + ""   	+ ", " +
								"'" + dateCourse 	   + "'"	+ ", " +
								""	+ c.getCloture()
								+ ")"
								
			);
			
			
			idMax = donnees.size();
			//System.out.println(idMax);
			// Mes à jours les donnée de la liste
			relaodData();
			
			cx.deconnexion();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Mes à jour la liste des course
	 */
	private void relaodData(){
		try{
			// Connexion à la BDD
			Connect cx = new Connect();
			
			// Cherche la dernière course créée 
			ResultSet rs = cx.recherche("SELECT * FROM course WHERE id_course > " + idMax);
			
			while(rs.next()){
				this.donnees.add( new Course(rs.getInt("id_course"), rs.getString("intituler"), rs.getDouble("distance"), rs.getDate("date_course"), rs.getBoolean("cloturer")) );
				// Notifi la model du chamgement
				fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
			}
			
			
			cx.deconnexion();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Permet de cloturer un course
	 * @param idCourse - identifiant de la course
	 * @return
	 */
	public boolean estCloturer(int idCourse){
		boolean cloturer = false;
		
		try{
			Connect cx = new Connect();
			ResultSet rs = cx.recherche("SELECT cloturer FROM course WHERE id_course = "+ idCourse);
			
			while(rs.next()){
				cloturer = rs.getBoolean("cloturer");
				
			}
			cx.deconnexion();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return cloturer;
	}
	
	/**
	 * Permet de cloturer une course (SETTER)
	 * @param idCourse - identifiant de la course
	 * @param cloture - false/true
	 */
	public void clotureUneCourse(int idCourse, boolean cloture){
		try{
			Connect cx = new Connect();
			cx.ajouter("UPDATE course SET cloturer = "+ cloture +" WHERE id_course = "+ idCourse);
			
			
			cx.deconnexion();
		}catch(Exception e){
			
		}
	}
	/**
	 * Netoyage de la JTable
	 */
	public void clearDonnees(){
		this.donnees.clear();
		fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
		
	}
}
