package top.tonymochel.main;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableauModelVoilier extends AbstractTableModel {
	
	
	private final List<Voilier> donnees = new ArrayList<Voilier>();
	private final String titre[]  = new String[] {"N°Voilier","Catégorie","Nom Skippeur", "Nom Voilier", "Rating"};
	private static final int MAX_PARTICIPANT = 20; 	// Nombre de particpant max pour une course
	//private Integer idCourseCourrent;				// Identifiant de la course courrante
	
	// CONSTRUCTEUR 
	public TableauModelVoilier(){
		super();	
	}
	
	
	/**
	 * @return le nombre de colone du tableau
	 */
	public int getColumnCount(){
		return this.titre.length;
	}
	/**
	 * @return identifiant de la course
	 
	public Integer getIdCourse(){
		return this.idCourseCourrent;
	}
	*/
	/**
	 * @return le nombre de ligne du tableau
	 */
	public int getRowCount(){
		return this.donnees.size();
	}
	
	/**
	 * @param row : l'indice de la ligne
	 * @param col : l'indice de la colonne
	 * @return Un element du tableau en spécifiant la ligne et la colonne
	 */
	public Object getValueAt(int row, int col){
		switch(col){
		case 0:
			return donnees.get(row).getIdVoilier();
		case 1:
			return donnees.get(row).getCategorie();
		case 2:
			return donnees.get(row).getNomSkippeur();
		case 3:
			return donnees.get(row).getNomVoilier();
		case 4:
			return donnees.get(row).getRating();
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
	 * Ajoute un voilier dans la BDD
	 * @param v : un voilier
	 */
	private void ajouteVoilierBDD(Voilier v){
		try{
			// Ouverture d'une connexion
			Connect cx = new Connect();
			// Execution d'une requête
			cx.ajouter("INSERT INTO voilier (ID_CATEGORIE, NOM_SKIPPEUR, NOM_VOILIER, RATING) VALUES ("+
								"" + v.getCategorie() 		+ ""  	+ ", " +
								"'"  + v.getNomSkippeur()  	+ "'"   + ", " +
								"'" + v.getNomVoilier() 	+ "'"  	+ ", " +
								v.getRating() + ")"
			);
			// Déconnexion
			cx.deconnexion();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Permet d'inscrire un particpant à une course
	 * @param idCourse
	 * @param v
	 * @return état de la requête (1 : Tout Bon, 2 : Pas bon)
	 */
	public boolean inscritVoilierBDD(Integer idCourse, Voilier v){
		
		if(getRowCount() <= MAX_PARTICIPANT -1){
			try{
				// Ajoute le voilier dans la BDD
				ajouteVoilierBDD(v);
				
				// Ouverture d'une connexion
				Connect cx = new Connect();
				
				// Execution d'une requête :  atribution d'un voilier à une course
				cx.ajouter("INSERT INTO Classement (ID_COURSE, ID_VOILIER, ID_CATEGORIE, TEMPS_ARRIVER) VALUES ("+
									"" + idCourse 			+ ""  	+ ", " +
									""  + v.getIdVoilier()  + ""   	+ ", " +
									"" + v.getCategorie() 	+ "" 	+ ", " +
									 null + ")"
				);
				
				System.out.println("Inscription du participant : " + v.toString() + " à la course : " + idCourse);
				
				// deconnexion
				cx.deconnexion();
				
				// On ajoute le participant à la liste.
				ajouteUnParticipantToListe(v);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
		}else{
			return false;
		}
			
	}
	
	/**
	 * Ajoute tous les particpants d'une course à la liste de donnees
	 * @param idCourse
	 */
	public void ajouteAllParticipantsToListe(Integer idCourse){
		/*
		if(idCourseCourrent != idCourse){
			// On netoye la liste.
			donnees.clear();
			fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
		}
		*/
		try{				
			// Ouverture d'une connexion
			Connect cx = new Connect();
			// Execution de la requete
			ResultSet rs = cx.recherche("SELECT * FROM Classement c "
					+ "INNER JOIN Voilier v ON v.id_voilier = c.id_voilier "
					+ "WHERE c.id_course = " + idCourse
			);
			
			// Ajoute les participants dans la liste
			while(rs.next()){		
				this.donnees.add(  new Voilier(rs.getInt("id_voilier"), rs.getInt("id_categorie"), rs.getString("nom_skippeur"), rs.getString("nom_voilier"), rs.getDouble("rating")) );
				
				// On notifie l'ajoute d'un nouvelle element dans la liste
				fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
			}
			
			System.out.println("Ajoute tout les participant de la course : " + idCourse);
			
			// Deconnexion
			cx.deconnexion();
					
			// identifiant de la course courrante
			//idCourseCourrent = idCourse;
			
			//afficheToutLesParticipant();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Netoyage de la JTable
	 */
	public void clearDonnees(){
		this.donnees.clear();
		fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
		
	}
	
	/**
	 * Ajoute un participant à la liste
	 * @param v
	 */
	private void ajouteUnParticipantToListe(Voilier v){
		this.donnees.add(v);
		fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
	}
	private void afficheToutLesParticipant(){
		for(int i=0; i<donnees.size();i++){
			System.out.println(donnees.get(i).toString());
		}
	}
}

