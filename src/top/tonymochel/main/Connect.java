package top.tonymochel.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Connect {
	
	private final String url = "jdbc:mysql://localhost/eole";		// Chemin de la BDD
	private final String user = "tony_java";						// Nom d'utilisateur
	private final String password = "tony_java";					// Mot de passe de l'utilisateur
	private Connection cn = null;						
	private Statement stat = null;
	private ResultSet rs = null;
	
	
	/**
	 * Constructeur de la classe.
	 * Effectue une connexion entre l'application et la Base de données
	 */
	public Connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver"); 								// Chargement du driver
			cn = DriverManager.getConnection(this.url, this.user, this.password); 	// Connexion à la BDD
			stat = cn.createStatement();											// Etat de la connexion
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * Deconnexion à la Base de données
	 */
	public void deconnexion(){
		try{
			this.cn.close();		// Ferme la connexion
			this.stat.close();		// Ferme le Statement
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet d'effectuer une recherche dans la base de donnée (SELECT)
	 * @param req : requete à executer
	 * @return ResultSet - le resultat de la recherche
	 */
	public ResultSet recherche(String req){
		try{	
			rs = stat.executeQuery(req);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return this.rs;
	}
	
	/**
	 * Permet de modifier la base de données (INSERT, UPDATE)
	 * @param req : requête à executer
	 */
	public void ajouter(String req){
		try{
			stat.executeUpdate(req);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
