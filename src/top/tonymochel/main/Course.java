package top.tonymochel.main;

import java.sql.ResultSet;
import java.util.Date;


public class Course {
	
	private int idCourse;			// Identifiance de la course
	private String intituler;		// Intituler de la course
	private double distance;		// Distance à parcourir
	private Date dateCourse;		// Date de la course
	private boolean estCloturer;
	
	/**
	 * Constructeur de la course
	 * @param intituler
	 * @param distance
	 * @param dateCourse
	 */
	public Course(String intituler, double distance, Date dateCourse){
		this.idCourse = reqLastIdCourse() + 1;
		this.intituler = intituler;
		this.distance = distance;
		this.dateCourse = dateCourse;
		this.estCloturer = false;
	}
	/**
	 * Constructeur de la course (en spécifiant l'identifiant BDD)
	 * @param idCourse
	 * @param intituler
	 * @param distance
	 * @param dateCourse
	 */
	public Course(int idCourse, String intituler, double distance, Date dateCourse, boolean cloturer){
		this.idCourse = idCourse;
		this.intituler = intituler;
		this.distance = distance;
		this.dateCourse = dateCourse;
		this.estCloturer = cloturer;
	}
	
	/**
	 * Recupère l'identifiant de la dérnière course créée
	 * @return int - l'identifiant
	 */
	private int reqLastIdCourse(){
		int maxIdCourse = 0;
		try{
			Connect cx = new Connect();
			// Cherche le dernier identifiant de la course
			ResultSet rs = cx.recherche("SELECT MAX(id_course) as id_course FROM course");
			
			if(rs.next()){
				// Récuperation de l'identifiant
				maxIdCourse = rs.getInt("id_course");
			}
			cx.deconnexion();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return maxIdCourse;
	}
	
	
	// GETTER(S) et SETTER(S)	
	public int getIdCourse() {
		return this.idCourse;
	}
	public String getIntituler() {
		return this.intituler;
	}
	public double getDistance() {
		return this.distance;
	}
	public Date getDateCourse(){
		return this.dateCourse;
	}
	public boolean getCloture(){
		return this.estCloturer;
	}
	
	
	@Override
	public String toString(){
		String chaine = "[" 
				+ " Identifiant = " 	+ getIdCourse()
				+ ", Intituler = "		+ getIntituler() 
				+ ", Distance = " 		+ getDistance()
				+ ", Date = "			+ getDateCourse()
				+ " ]";
		return chaine;
	}
}
