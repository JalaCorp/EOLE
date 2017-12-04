package top.tonymochel.main;

import java.sql.ResultSet;

public class Voilier {

	private int idVoilier;
	private String nomVoilier;
	private String nomSkippeur;
	private int categorie;
	private double rating;
	
	/**
	 * 
	 * @param idVoilier
	 * @param categorie
	 * @param nomSkippeur
	 * @param nomVoilier
	 * @param rating
	 */
	public Voilier(int idVoilier, int categorie, String nomSkippeur, String nomVoilier, double rating){

		this.idVoilier = idVoilier;
		this.categorie = categorie;
		this.nomVoilier = nomVoilier;
		this.nomSkippeur = nomSkippeur;
		this.rating = rating;
	}
	/**
	 * 
	 * @param categorie
	 * @param nomSkippeur
	 * @param nomVoilier
	 * @param rating
	 */
	public Voilier(int categorie, String nomSkippeur, String nomVoilier, double rating){
		this.nomSkippeur = nomSkippeur;
		this.nomVoilier = nomVoilier;
		this.categorie = categorie;
		this.rating = rating;
		this.idVoilier = reqLastIdVoilier() +1;
	}

	/**
	 * 
	 * @return - l'id du dérnier voilier créée
	 */
	public int reqLastIdVoilier(){
		int maxIdVoilier = 0;
		try{
			Connect cx = new Connect();
			ResultSet rs = cx.recherche("SELECT MAX(id_voilier) as id_voilier FROM voilier");
			if(rs.next()){
				maxIdVoilier = rs.getInt("id_voilier");
			}
			cx.deconnexion();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return maxIdVoilier;
	}
	
	
	public int getIdVoilier(){
		return this.idVoilier;
	}
	public String getNomSkippeur(){
		return this.nomSkippeur;
	}
	public String getNomVoilier() {
		return this.nomVoilier;
	}
	public int getCategorie() {
		return this.categorie;
	}
	public double getRating() {
		return this.rating;
	}	
	
	@Override
	public String toString(){
		String chaine = "[" 
					+ "Identifiant = " 		+ getIdVoilier()
					+ ", Nomskippeur = "	+ getNomSkippeur() 
					+ ", NomVoilier = " 	+ getNomVoilier()
					+ ", Categorie = " 		+ getCategorie()
					+ ", Rating = "			+ getRating()
					+ "]";
		
		
		return chaine;
	}
}
