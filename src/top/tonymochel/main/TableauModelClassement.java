package top.tonymochel.main;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class TableauModelClassement extends AbstractTableModel {

	private final List <Object[]> donnees = new ArrayList<Object[]>();
	private final String titre[] = new String[] {"N°Voilier","Catégorie","Nom Skippeur", "Nom Voilier", "Temps Arriver"};
	
	
	private final int ID_VOILIER 	= 0;
	private final int CATEGORIE 	= 1;
	private final int NOM_SKIPPEUR 	= 2;
	private final int NOM_VOILIER 	= 3;
	private final int TEMPS	 		= 4;

	
	/**
	 * Constructeur
	 */
	public TableauModelClassement(){
		super();
	}
	
	/**
	 * @return le nombre de colone du tableau
	 */
	public int getColumnCount(){
		return this.titre.length;
	}


	/**
	 * @return le nombre de ligne du tableau
	 */
	public int getRowCount(){
		return this.donnees.size();
	}
	
	/**
	 * @param row : l'indice de la ligne
	 * @param col : l'indice de la colonne
	 * @return Un element du tableau en spécifiant
	 */
	public Object getValueAt(int row, int col){
		switch(col){
		case 0:
			// id participant
			return donnees.get(row)[ID_VOILIER];
		case 1:
			// Categorie
			return donnees.get(row)[CATEGORIE];
		case 2:
			// Nomskippeur
			return donnees.get(row)[NOM_SKIPPEUR];
		case 3:
			// NomVoilier
			return donnees.get(row)[NOM_VOILIER];
		case 4:
			// Temps
			return donnees.get(row)[TEMPS];
		default:
			return null;
		}
	}
	
	/**
	 * @param col : l'indice de la colonne
	 * @return le nom de la colonne
	 */
	public String getColumnName(int col){
		return this.titre[col];
	}

	
	/**
	 * Ajoute une ligne à la JTable
	 * @param tab - Liste d'object (ici : idParticipant, 
	 */
	public void ajouteLigne(Object[] tab){
		this.donnees.add(tab);
		fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
	}
	/**
	 * Supprimer une ligne de la JTable
	 * @param rowindex - index de la ligne
	 */
	public void supprimerLigne(int rowindex){
		this.donnees.remove(rowindex);
		fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
		
	}
	
	/**
	 * Remplie la JTable des participants à  la course (idCourse)
	 * @param idCourse - identifiant de la course
	 */
	public void rempliTableau(int idCourse){
		try{
						
			// Ouverture d'une connexion
			Connect cx = new Connect();
			// Execution de la requete
			ResultSet rs = cx.recherche("SELECT * FROM Classement c "
					+ "INNER JOIN Voilier v ON v.id_voilier = c.id_voilier "
					+ "WHERE c.id_course = " + idCourse
			);
			
			// Ajoute les participants dans la liste
			System.out.println("Chargement des participants ");
			while(rs.next()){
				
				this.donnees.add(new Object[] {
					rs.getInt("id_voilier"),
					rs.getInt("id_categorie"),
					rs.getString("nom_skippeur"), 
					rs.getString("nom_voilier"), 
					rs.getString("temps_arriver")
				});
				
				// On notifie l'ajoute d'un nouvelle element dans la liste
				fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
				
			}
			
			// Deconnexion
			cx.deconnexion();	
			
			System.out.println("Nombre de participant à la course : " + donnees.size());	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Met à jour le classement des participants
	 */
	public void updateClassement(int idCourse){
		try{
			Connect cx = new Connect();
			
			for(Object[] obj : donnees){
				cx.ajouter("UPDATE classement SET `TEMPS_ARRIVER` = \"" + obj[TEMPS] + "\" WHERE `ID_COURSE` = "+ idCourse +" AND `ID_VOILIER` = "+ obj[ID_VOILIER]);
			}
			cx.deconnexion();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Supprime les element de la JTable (Netoyage de la table)
	 */
	public void clearDonnees(){
		this.donnees.clear();
		fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
		
	}
}
