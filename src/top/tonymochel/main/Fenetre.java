package top.tonymochel.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Fenetre extends JFrame{
	
	private JFrame maFrame;
	private JPanel ongletAcceuil;
	private JPanel ongletInscription;
	private JPanel ongletClassement;
	
	private JPanel panelAcceuil;
	
	private JPanel panelCourse;
	private JPanel panelInscription;
	private JPanel panelClassementLeft;
	private JPanel panelClassementRight;
	
	private JPanel panelCourseTop;
	private JPanel panelCourseCenter;
	private JPanel panelCourseBottom;

	private JPanel panelInscriptionTop;
	private JPanel panelInscriptionCenter;
	private JPanel panelInscriptionBottom;
	
	private JPanel panelClassementRightTop;
	private JPanel panelClassementRightCenter;
	private JPanel panelClassementRightBottom;

	private JPanel panelClassementLeftTop;
	private JPanel panelClassementLeftCenter;
	private JPanel panelClassementLeftBottom;
	
	private JTextField tfIntitulerCourse;
	private JTextField tfDistanceCourse;
	private JTextField tfDateCourse;
	private JButton btnCreerCourse;
	
	private JTextField tfNomSkippeur;
	private JTextField tfNomVoilier;
	private JComboBox<Integer> cbCategorieVoilier;
	private JComboBox<String> cbChoixCourse;
	private JTextField tfRating;
	private JButton btnCreerParticipant;
	
	private Chrono chronometre;
	private JButton btnFichier;
	private JButton btnAjoutClassement;
	private JButton btnAbandonner;
	
	private JComboBox<String> cbClassementChoixCourse;
	private JButton btnDemarrer;
	private JButton btnArreter;
	
	private JTabbedPane onglets;
	
	
	// VARAIBLES DE TRAVAIL
	private int lastCourseCree = 0;
	private Integer idCourseInscription = null;
	private Integer idClassementChoixCourse = null;
	private Boolean courseADemarrer = false;
	
	/**
	 * Constructeur de la fenêtre
	 */
	public Fenetre(){
		maFrame = new JFrame();
		maFrame.setSize(1100, 700);								
		maFrame.setTitle("Administration d'une course");			
		maFrame.setLocationRelativeTo(null);						
		maFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialise les composants de la fenêtre
		initComposants();
		
		
		maFrame.setVisible(true);	
	}
	
	/**
	 * Initialiser les composants de la fenêtres
	 */
	private void initComposants(){
		
		onglets = new JTabbedPane();
		ongletAcceuil = new JPanel();
		ongletInscription = new JPanel();
		ongletClassement = new JPanel();
		// this.setBackground(Color.WHITE);
		//onglets.setBackground(Color.LIGHT_GRAY);
		//	onglets.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.RED));
		
		/*** INITIALISATION DES PANEL : ***/
		
		
		panelAcceuil = new JPanel();
		panelAcceuil.setLayout(new BorderLayout());
		
		
		panelCourse = new JPanel();
		panelCourse.setLayout(new BorderLayout());
		
		panelInscription = new JPanel();
		panelInscription.setLayout(new BorderLayout());
		
		panelClassementLeft = new JPanel();
		panelClassementLeft.setLayout(new BorderLayout());
		
		panelClassementRight = new JPanel();
		panelClassementRight.setLayout(new BorderLayout());
		
		panelCourseTop = new JPanel();
		//panelCourseTop.setBackground(Color.WHITE);
		panelCourseCenter = new JPanel();
		panelCourseBottom = new JPanel();
		panelCourseBottom.setBackground(Color.LIGHT_GRAY);
		
		panelInscriptionTop = new JPanel();
		//panelInscriptionTop.setBackground(Color.WHITE);
		panelInscriptionCenter = new JPanel();
		panelInscriptionBottom = new JPanel();
		panelInscriptionBottom.setBackground(Color.LIGHT_GRAY);
		
		panelClassementLeftTop = new JPanel();
		//panelClassementLeftTop.setBackground(Color.WHITE);
		panelClassementLeftCenter = new JPanel();
		panelClassementLeftBottom = new JPanel();
		panelClassementLeftBottom.setBackground(Color.LIGHT_GRAY);
		
		panelClassementRightTop = new JPanel();
		//panelClassementRightTop.setBackground(Color.WHITE);
		panelClassementRightCenter = new JPanel();
		panelClassementRightBottom = new JPanel();
		panelClassementRightBottom.setBackground(Color.LIGHT_GRAY);
		
		
		/**************************************/
		/*** INITIALISATION DES JCOMPOSANTS ***/
		/**************************************/
		
		// Icons
		
		ImageIcon icAcceuil = new ImageIcon("./img/accueil-48.png");
		Image imAcceuil 	= icAcceuil.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
		icAcceuil 			= new ImageIcon(imAcceuil);
		ImageIcon icUser 	= new ImageIcon("./img/user-48.png");
		Image imUser 		= icUser.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
		icUser 				= new ImageIcon(imUser);
		ImageIcon icVoilier = new ImageIcon("./img/bateau-48.png");
		Image imVoilier 	= icVoilier.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
		icVoilier 			= new ImageIcon(imVoilier);
		
		
		// Composants Acceuil
		
		ImageIcon icCourse 	= new ImageIcon("./img/logoEole.png");
		Image imCourse 		= icCourse.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
		icCourse 			= new ImageIcon(imCourse);
		JLabel lbIconAcceuil = new JLabel(icCourse);
		JLabel lbTitre = new JLabel("Gestion des régates");
		lbTitre.setFont( new Font( "sans-serif" , Font.PLAIN, 32 ) );
		
		
		// Composants création d'une course
		
		JLabel lbIntitulerCourse 		= 	new JLabel("Inutitulé :");
		lbIntitulerCourse.setPreferredSize(new Dimension(120,25));
		JLabel lbDistanceCourse 		= 	new JLabel("Distance (mills) :");
		lbDistanceCourse.setPreferredSize(new Dimension(120,25));
		JLabel lbDateCourse 			= 	new JLabel("Date (AAAA-MM-DD) :");
		lbDateCourse.setPreferredSize(new Dimension(120,25));
		tfIntitulerCourse 				= 	new JTextField();
		tfIntitulerCourse.setPreferredSize(new Dimension(150,25));
		tfDistanceCourse 				= 	new JTextField();
		tfDistanceCourse.setPreferredSize(new Dimension(100,25));
		tfDateCourse 					= 	new JTextField();
		tfDateCourse.setPreferredSize(new Dimension(100,25));
		btnCreerCourse 					= 	new JButton("Créer");
		
		
		// Composant création d'un participant (avec affectation : Cours/Participant)
		
		JLabel lbNomSkippeur 			= new JLabel("Nom du Skipper :");
		lbNomSkippeur.setPreferredSize(new Dimension(120,25));
		JLabel lbNomVoilier 			= new JLabel("Nom du Voilier :");
		lbNomVoilier.setPreferredSize(new Dimension(120,25));
		JLabel lbCategorieVoilier 		= new JLabel("Catégorie :");
		lbCategorieVoilier.setPreferredSize(new Dimension(120,25));
		JLabel lbChoixCourse 			= new JLabel("Choix Course");
		lbChoixCourse.setPreferredSize(new Dimension(120,25));
		JLabel lbRating 				= new JLabel("Rating :");
		lbRating.setPreferredSize(new Dimension(120,25));
		tfNomSkippeur 			= new JTextField();
		tfNomSkippeur.setPreferredSize(new Dimension(150,25));
		tfNomVoilier 			= new JTextField();
		tfNomVoilier.setPreferredSize(new Dimension(150,25));
		Integer[] tabCategorie 	= {1, 2, 3, 4};
		cbCategorieVoilier 		= new JComboBox<Integer>(tabCategorie);
		cbCategorieVoilier.setPreferredSize(new Dimension(150,25));
		cbChoixCourse 			= new JComboBox<String>();						 
		cbChoixCourse.setPreferredSize(new Dimension(150,25));
		tfRating  				= new JTextField();
		tfRating.setPreferredSize(new Dimension(150,25));
		btnCreerParticipant  	= new JButton("Inscrire");
		
		
		// Composant classement des participants
		
		chronometre			= new Chrono();		
		JLabel lbChronoTime	= chronometre.getJLabel();
		btnFichier 			= new JButton("Exporter les résultats");
		btnFichier.setPreferredSize(new Dimension(120, 25));
		btnAjoutClassement 	= new JButton("Ajouter");
		btnAjoutClassement.setPreferredSize(new Dimension(120, 25));
		btnAjoutClassement.setEnabled(false);
		btnAbandonner 		= new JButton("Abandonner");
		btnAbandonner.setPreferredSize(new Dimension(120, 25));
		btnAbandonner.setEnabled(false);
		
		
		// Composant démarrage d'une course
		
		JLabel lbClassementCourse 	= new JLabel("Choix course");
		cbClassementChoixCourse 	= new JComboBox<String>();
		cbClassementChoixCourse.setPreferredSize(new Dimension(150,25));
		cbClassementChoixCourse.setSelectedItem(null);
		btnDemarrer 				= new JButton("Démarrer la Course");
		btnDemarrer.setPreferredSize(new Dimension(120, 25));
		btnArreter 					= new JButton("Arreter la Course");
		btnArreter.setPreferredSize(new Dimension(120, 25));
		
				
		/*********************************/
		/*** Initialisation des JTable ***/
		/*********************************/
		
		// JTABLE CLASSEMENT GAUCHE
		TableauModelClassement modelClassementLeft 		= new TableauModelClassement();
		JTable tableauClassementLeft 					= new JTable(modelClassementLeft);
		JScrollPane scrollPaneClassementLeft 			= new JScrollPane(tableauClassementLeft);
		scrollPaneClassementLeft.setPreferredSize(new Dimension (500, 300));
		panelClassementLeftBottom.add(scrollPaneClassementLeft);
		
		
		// JTABLE CLASSEMENT DROIT
		TableauModelClassement modelClassementRight 	= new TableauModelClassement();
		JTable tableauClassementRight					= new JTable(modelClassementRight);
		JScrollPane scrollPaneClassementRight			= new JScrollPane(tableauClassementRight);
		scrollPaneClassementRight.setPreferredSize(new Dimension (500, 300));
		panelClassementRightBottom.add(scrollPaneClassementRight);
		
		
		// JTABLE COURSE
		TableauModelCourse modelCourse 					= new TableauModelCourse();
		JTable tableauCourse 							= new JTable(modelCourse);
		JScrollPane ScrollPaneCourse 					= new JScrollPane(tableauCourse);
		ScrollPaneCourse.setPreferredSize(new Dimension(500, 300));
		panelCourseBottom.add(ScrollPaneCourse);

		
		// JTABLE INSCRIPTION 
		TableauModelVoilier modelInscription 			= new TableauModelVoilier();
		JTable tableauInscription 						= new JTable(modelInscription);
		JScrollPane ScrollPaneInscription 				= new JScrollPane(tableauInscription);
		ScrollPaneInscription.setPreferredSize(new Dimension(500, 300));
		panelInscriptionBottom.add(ScrollPaneInscription);
			
		
		/*********************/
		/*** PANELE COURSE ***/
		/*********************/
		
		// Titre 
		JLabel lbTitreCourse = new JLabel("Création d'une course");
		lbTitreCourse.setFont(new Font( "sans-serif" , Font.PLAIN, 20 ));	
		panelCourseTop.add(lbTitreCourse);	
		// DatePicker
		UtilDateModel modelDate 		= new UtilDateModel();
		JDatePanelImpl datePanel 		= new JDatePanelImpl(modelDate, new Properties());
		JDatePickerImpl datePicker 		= new JDatePickerImpl(datePanel, new DateLabelFormatter());
		modelDate.setSelected(true);
			
		// Positionnement des comopsants (GridBagLayout)
		panelCourseCenter.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
	
		gbc1.insets = new Insets(5,5,5,5); 				// ESAPCE ENTRE LES COMPOSANTS
		gbc1.fill = GridBagConstraints.HORIZONTAL;		
		gbc1.gridx = 0;									
		gbc1.gridy = 0;
		panelCourseCenter.add(lbIntitulerCourse, gbc1);
		gbc1.gridwidth = GridBagConstraints.REMAINDER; 	// FIN DE LIGNE
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		panelCourseCenter.add(tfIntitulerCourse, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		panelCourseCenter.add(lbDistanceCourse, gbc1);
		gbc1.gridwidth = GridBagConstraints.REMAINDER;
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		panelCourseCenter.add(tfDistanceCourse, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 2;
		panelCourseCenter.add(lbDateCourse, gbc1);
		gbc1.gridwidth = GridBagConstraints.REMAINDER; 
		gbc1.gridx = 1;
		gbc1.gridy = 2;
		panelCourseCenter.add(datePicker, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy = 3;
		panelCourseCenter.add(btnCreerCourse, gbc1);
	
		
		
		/**************************/
		/*** PANELE INSCRIPTION ***/
		/**************************/
		
		// Titre
		JLabel lbTitreInscription = new JLabel("Inscription des participants");
		lbTitreInscription.setFont(new Font( "sans-serif" , Font.PLAIN, 20 ));
		panelInscriptionTop.add(lbTitreInscription);
		
		// Positionnement des comopsants (GridBagLayout)
		panelInscriptionCenter.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		
		gbc2.insets = new Insets(5,5,5,5); 				// ESPACE ENTRE LES COMPOSANTS
		gbc2.fill = GridBagConstraints.HORIZONTAL;		
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		panelInscriptionCenter.add(lbNomSkippeur, gbc2);
		gbc2.gridwidth = GridBagConstraints.REMAINDER; 	// FIN DE LIGNE
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		panelInscriptionCenter.add(tfNomSkippeur, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		panelInscriptionCenter.add(lbNomVoilier, gbc2);
		gbc2.gridwidth = GridBagConstraints.REMAINDER; 	// FIN DE LIGNE 
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		panelInscriptionCenter.add(tfNomVoilier, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 2;
		panelInscriptionCenter.add(lbCategorieVoilier, gbc2);
		gbc2.gridwidth = GridBagConstraints.REMAINDER; 	// FIN DE LIGNE
		gbc2.gridx = 1;
		gbc2.gridy = 2;
		panelInscriptionCenter.add(cbCategorieVoilier, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 3;
		panelInscriptionCenter.add(lbRating, gbc2);
		gbc2.gridwidth = GridBagConstraints.REMAINDER; 	// FIN DE LIGNE
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		panelInscriptionCenter.add(tfRating, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 4;
		panelInscriptionCenter.add(lbChoixCourse, gbc2);
		gbc2.gridwidth = GridBagConstraints.REMAINDER; 	// FIN DE LIGNE
		gbc2.gridx = 1;
		gbc2.gridy = 4;
		panelInscriptionCenter.add(cbChoixCourse, gbc2);
		gbc2.gridx = 0;
		gbc2.gridy = 5;
		panelInscriptionCenter.add(btnCreerParticipant, gbc2);
		
		/********************************/
		/*** PANELE CLASSEMENT GAUCHE ***/
		/********************************/
		
		
		panelClassementLeftCenter.setLayout(new GridBagLayout());
		GridBagConstraints gbc3 = new GridBagConstraints();
		
		// Positionnement des composant dans le GridBagLayout
		gbc3.insets = new Insets(5,5,5,5); 				// espace entre les composants
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 0;
		gbc3.gridy = 0;
		gbc3.gridwidth = 1;
		panelClassementLeftCenter.add(lbClassementCourse, gbc3);
		gbc3.gridwidth = GridBagConstraints.REMAINDER; // Fin de ligne
		gbc3.gridx = 1;
		gbc3.gridy = 0;
		panelClassementLeftCenter.add(cbClassementChoixCourse, gbc3);
		gbc3.gridwidth = GridBagConstraints.REMAINDER; // Fin de ligne
		gbc3.gridx = 0;
		gbc3.gridy = 1;
		panelClassementLeftCenter.add(btnDemarrer, gbc3);
		gbc3.gridwidth = GridBagConstraints.REMAINDER; // Fin de ligne
		gbc3.gridx = 0;
		gbc3.gridy = 2;
		panelClassementLeftCenter.add(btnArreter, gbc3);
		
		 
		/********************************/
		/*** PANELE CLASSEMENT DROITE ***/
		/********************************/
		
		panelClassementRightCenter.setLayout(new GridBagLayout());
		GridBagConstraints gbc4 = new GridBagConstraints();
		
		gbc4.insets = new Insets(5,5,5,5); 				// espace entre les composants
		gbc4.fill = GridBagConstraints.HORIZONTAL;
		gbc4.gridx = 0;
		gbc4.gridy = 0;
		gbc4.gridwidth = GridBagConstraints.REMAINDER; // Fin de ligne
		panelClassementRightCenter.add(lbChronoTime, gbc4);
		gbc4.gridx = 0;
		gbc4.gridy = 1;
		gbc4.gridwidth = 1;
		panelClassementRightCenter.add(btnAjoutClassement, gbc4);
		
		gbc4.gridx = 1;
		gbc4.gridy = 1;
		panelClassementRightCenter.add(btnAbandonner, gbc4);
		gbc4.gridwidth = GridBagConstraints.REMAINDER; // Fin de ligne
		gbc4.gridx = 0;
		gbc4.gridy = 2;
		panelClassementRightCenter.add(btnFichier, gbc4);
		
		/***********************************/
		/*** ATTRIBUTION DE CHAQUE PANEL ***/
		/***********************************/
		
		// AJOUTER AU PANEL
		//panelAcceuil.add(lbTitre, BorderLayout.NORTH);
		panelAcceuil.add(lbIconAcceuil, BorderLayout.CENTER);
		panelCourse.add(panelCourseTop, BorderLayout.NORTH);
		panelCourse.add(panelCourseCenter, BorderLayout.CENTER);
		panelCourse.add(panelCourseBottom, BorderLayout.SOUTH);
		
		panelInscription.add(panelInscriptionTop, BorderLayout.NORTH);
		panelInscription.add(panelInscriptionCenter, BorderLayout.CENTER);
		panelInscription.add(panelInscriptionBottom, BorderLayout.SOUTH);
		
		panelClassementLeft.add(panelClassementLeftTop, BorderLayout.NORTH);
		panelClassementLeft.add(panelClassementLeftCenter, BorderLayout.CENTER);
		panelClassementLeft.add(panelClassementLeftBottom, BorderLayout.SOUTH);
		
		panelClassementRight.add(panelClassementRightTop, BorderLayout.NORTH);
		panelClassementRight.add(panelClassementRightCenter, BorderLayout.CENTER);
		panelClassementRight.add(panelClassementRightBottom, BorderLayout.SOUTH);
		
		
		ongletAcceuil.add(panelAcceuil);
		
		ongletInscription.setLayout(new GridLayout(0,2));
		ongletInscription.add(panelCourse);
		ongletInscription.add(panelInscription);
		
		ongletClassement.setLayout(new GridLayout(0,2));
		ongletClassement.add(panelClassementLeft);
		ongletClassement.add(panelClassementRight);
		
		 
		
		onglets.add("Acceuil", ongletAcceuil);
		onglets.add("Inscription", ongletInscription);		
		onglets.add("Classement", ongletClassement);		
		
		onglets.setIconAt(0, icAcceuil);
		onglets.setIconAt(1, icUser);
		onglets.setIconAt(2, icVoilier);
		maFrame.getContentPane().add(onglets);
		
		
		
		/*************************************/
		/*** AU CHARGMENT DE L'APPLICATION ***/
		/*************************************/
			
		
		// ON CHARGE LES ELEMENTS DES COMBOBOX (choixCourse et classementChoixCourse)
		try{
			Connect cx = new Connect(); 							// Connexion à la BDD
			ResultSet rs = cx.recherche("SELECT * FROM Course"); 	// Cherche toute les courses dans la BDD			
			// On compléte les ComboBox avec les courses déjà créées
			while(rs.next()){
				cbChoixCourse.addItem			("" + rs.getInt("id_course") + " : " + rs.getString("intituler") );
				cbClassementChoixCourse.addItem	("" + rs.getInt("id_course") + " : " + rs.getString("intituler") );
			}
			cx.deconnexion(); // Deconnexion à la BDD
		}catch(Exception e){e.printStackTrace();}
		
		
		
		//  Valeur par défaut : IDENTIFIANT DE LA COURSE (inscription) 
		if(cbChoixCourse.getSelectedIndex() == -1){
			idCourseInscription = null;
		}else{
			idCourseInscription = cbChoixCourse.getSelectedIndex()+1;			// On change l'identifiant de la course
			modelInscription.ajouteAllParticipantsToListe(idCourseInscription);	// On charge la nouvelle liste des participants
			btnCreerParticipant.setEnabled(		((modelCourse.estCloturer(idCourseInscription)) ? false : true)		);
			
		}	
		
		//  Valeur par défaut : IDENTIFIANT DE LA COURSE (classement)
		if(cbClassementChoixCourse.getSelectedIndex() == -1){	
			idClassementChoixCourse = null;
		}else{
			idClassementChoixCourse = cbClassementChoixCourse.getSelectedIndex()+1; // On change l'identifiant de la course
			modelClassementLeft.rempliTableau(idClassementChoixCourse);				// On charge la nouvelle liste des participants
			btnDemarrer.setEnabled(		((modelCourse.estCloturer(idClassementChoixCourse)) ? false : true)		);
			btnArreter.setEnabled (		((modelCourse.estCloturer(idClassementChoixCourse)) ? false : true)		);
		}
		
		
		
		/**********************/
		/*** LES EVENEMENTS ***/ 
		/**********************/
		
		btnCreerCourse.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(Outils.estVide(tfIntitulerCourse) || Outils.estVide(tfDistanceCourse) ){
					JOptionPane.showMessageDialog(maFrame,
							"Course non créée \nLes champs de saisies sont vide!",
							"Avertissement",
					         JOptionPane.WARNING_MESSAGE);
				}else{
					
					// ON TENTE DE CREER UNE COURSE
					try{
						// INIT VARAIBLES
						String intitulerCourse 	= "";
						Double distanceCourse 	= 0.0;
						String strDateCourse 	= "";
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date dateCourse 		= new Date();
						
						// AFFECTATION DES VALEURS
						intitulerCourse 	= tfIntitulerCourse.getText();
						distanceCourse 		= Double.parseDouble(tfDistanceCourse.getText());
						strDateCourse 		= datePicker.getJFormattedTextField().getText();
						
						// CONVERTIE LA DATE DE : STRING ==> DATE
						dateCourse 			= format.parse(strDateCourse);
						
						// AJOUTE UNE COURSE AU MODEL
						modelCourse.ajouterCourse( new Course(intitulerCourse, distanceCourse, dateCourse));
					
						tfIntitulerCourse.setText("");
						tfDistanceCourse.setText("");
					
						
						
						// L'IDENTIFIANT DE LA DERNIERE COURSE CREEE
						lastCourseCree = modelCourse.getRowCount();
						
						
						// MISE A JOUR DE LA COMBOBOX
						try{
							// Connexion à la BDD
							Connect cx = new Connect();
							// Exectution de la requête : récupèration des élement d'une course 
							ResultSet rs = cx.recherche("SELECT * FROM Course WHERE id_course = "+ lastCourseCree);
							
							// Traitement du résultat : Ajoute la course au comboBox choix course
							while(rs.next()){
								cbChoixCourse.addItem("" + rs.getInt("id_course") + " : " + rs.getString("intituler") );
								cbClassementChoixCourse.addItem("" + rs.getInt("id_course") + " : " + rs.getString("intituler") );
							}			
							cx.deconnexion();
						}catch(Exception e){e.printStackTrace();}
						
						
					}catch(NumberFormatException e){
						
						JOptionPane.showMessageDialog(maFrame,
								"Course non créée \nLa distance n'est pas un décimal",
								"Avertissement",
						         JOptionPane.WARNING_MESSAGE);
						
					}catch (ParseException e1) {
						
						JOptionPane.showMessageDialog(maFrame,
								"Course non créée \nLe format de la date est non valide",
								"Avertissement",
						         JOptionPane.WARNING_MESSAGE);
					}					
				}
			}
		});		
		
		
		// REMPLISAGE DU TABLEAU SUIVANT LA COURSE SELECTIONNER
		cbClassementChoixCourse.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {					
				
				// On change l'identifiant de la course
				idClassementChoixCourse = cbClassementChoixCourse.getSelectedIndex()+1;
				
				// On charge la nouvelle liste des participants
				modelClassementLeft.clearDonnees();
				modelClassementRight.clearDonnees();
				modelClassementLeft.rempliTableau(idClassementChoixCourse);
				System.out.println("Remplisage");
				
				
				btnDemarrer.setEnabled(		((modelCourse.estCloturer(idClassementChoixCourse)) ? false : true)		);
				btnArreter.setEnabled (		((modelCourse.estCloturer(idClassementChoixCourse)) ? false : true)		);
				
			}
			
		});
		
		
		// REMPLISAGE DU TABLEAU SUIVANT LA COURSE SELECTIONNER
		cbChoixCourse.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {					
				// On change l'identifiant de la course
				idCourseInscription = cbChoixCourse.getSelectedIndex()+1;
				
				// On charge la nouvelle liste des participants
				modelInscription.clearDonnees();
				modelInscription.ajouteAllParticipantsToListe(idCourseInscription);
				
				btnCreerParticipant.setEnabled(		((modelCourse.estCloturer(idCourseInscription)) ? false : true)		);
			}
			
		});
		
		
		// INSCIPTION D'UN PARTICPANT A UNE COURSE
		btnCreerParticipant.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				// On verifi si les champs ont été correctement remplies
				if(Outils.estVide(tfNomSkippeur) || Outils.estVide(tfNomVoilier) 
						|| Outils.estVide(tfRating) || cbChoixCourse.getSelectedItem() == null 
						|| cbCategorieVoilier.getSelectedItem() == null){
					
					// Affiche un message d'avertissement
					JOptionPane.showMessageDialog(maFrame,
							"Participant non créée \nCertain champs de saisies sont vides",
							"Avertissement",
					         JOptionPane.WARNING_MESSAGE);
				}else{
					try{
						String nomSkippeur = "";
						String nomVoilier = "";
						Double ratingCourse;
						int categorieVoilier = 0;
						boolean verifInscription;
						
						// récupération du l'identifiant de la course  suivant la valeur du comboBox choisi
						idCourseInscription = cbChoixCourse.getSelectedIndex() + 1;
						// récupération du rating
						ratingCourse = Double.parseDouble(tfRating.getText());
						// récupération de l'id de la catégorie
						categorieVoilier = (int) cbCategorieVoilier.getSelectedItem();
						
						// récupération du nom du skippeur
						nomSkippeur = tfNomSkippeur.getText().replaceAll( "\\s *" , " ");	// remplace une suite d'espace par un seul espace
						//nomSkippeur = tfNomSkippeur.getText().replaceAll( "\'" , " ");	
						//nomSkippeur = tfNomSkippeur.getText().replaceAll( "\"" , " ");
						
						// récupération du nom de voilier
						nomVoilier = tfNomVoilier.getText().replaceAll	( "\\s *" , " ");
						//nomVoilier = tfNomVoilier.getText().replaceAll	( "\'" , " ");
						//nomVoilier = tfNomVoilier.getText().replaceAll	( "\"" , "\" ");
						

						
						
						System.out.println(" -----> Click btn Inscription ");
						System.out.println();
						
						Voilier monParticipant =  new Voilier(categorieVoilier, nomSkippeur, nomVoilier, ratingCourse);
						System.out.println(monParticipant.toString());
						
					
						
						// On ajoute au modele un nouveau voilier
						verifInscription = modelInscription.inscritVoilierBDD(idCourseInscription, monParticipant);
						
						// On vérifi si l'inscription c'est bien passée
						if(verifInscription != true){
							
							JOptionPane.showMessageDialog(maFrame,
									"Participant non créée \nIl y a trop de participant dans la course (Max 20 participant par course)",
									"Avertissement",
							         JOptionPane.WARNING_MESSAGE);
						}
						
						
						tfNomSkippeur.setText("");
						tfNomVoilier.setText("");
						tfRating.setText("");
						
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(maFrame,
								"Participant non créée \nLe rating n'est pas un nombre décimal",
								"Avertissement",
						         JOptionPane.WARNING_MESSAGE);
					}
					
				}
			}
			
		});
		
		btnDemarrer.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event ){
				// LANCE CHRONO !
				courseADemarrer = true;
				chronometre.StartTimer();
				btnDemarrer.setEnabled(false);
				cbClassementChoixCourse.setEnabled(false);
				btnAbandonner.setEnabled(true);
				btnAjoutClassement.setEnabled(true);
				btnFichier.setEnabled(false);
			}
		});
		
		btnAjoutClassement.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				
				if(courseADemarrer == true){
					final int COLONNE_TEMPS = 4;
					final int ERREUR_LIGNE_SELECTIONNER = -1;
					// On récupère la l'index de la ligne selectionner (dans la JTable)
					int indexSelected = tableauClassementLeft.getSelectedRow();
					
					// Si une ligne à bien été séléctionner
					if(indexSelected != ERREUR_LIGNE_SELECTIONNER){
						
						// On crée un tableau pour récupérer les elements d'une ligne de la JTable
						Object[] tab = new Object[5];
						
						// On récupère les information de chaque colonne d'une ligne de la JTable
						for(int col = 0; col<modelClassementLeft.getColumnCount(); col++){
							if(col == COLONNE_TEMPS){
								tab[col] = (lbChronoTime.getText());
							}else{
								tab[col] = (modelClassementLeft.getValueAt(indexSelected, col));
							}
						}
						
						// On ajoute à la JTable Right une nouvelle ligne
						modelClassementRight.ajouteLigne(tab);
						// On supprimer la ligne de la JTable Left
						modelClassementLeft.supprimerLigne(indexSelected);	
					}else{}
				}else{}
				
			}
			
		});
		btnAbandonner.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				if(courseADemarrer == true){
					final int COLONNE_TEMPS = 4;
					final int ERREUR_LIGNE_SELECTIONNER = -1;
					// ligne selectionner
					int indexSelected = tableauClassementLeft.getSelectedRow();
					
					if(indexSelected != ERREUR_LIGNE_SELECTIONNER){
						Object[] tab = new Object[5];
						
						for(int col = 0; col<modelClassementLeft.getColumnCount(); col++){
							if(col == COLONNE_TEMPS){
								tab[col] = ("00:00:00");
							}else{
								tab[col] = (modelClassementLeft.getValueAt(indexSelected, col));
							}
						}
						
						modelClassementRight.ajouteLigne(tab);
						modelClassementLeft.supprimerLigne(indexSelected);
						
					}else{}

				}
			}
			
		});
		
		btnArreter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// Verifie si tout les participant sont arriver
				if(modelClassementLeft.getRowCount() != 0){
					
					// Confirmation de de l'utilisateur
					int option = JOptionPane.showConfirmDialog(null,  "Des participants ne sont pas encore arrivée, Voulez-vous continuer ?", "Avertissement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
					
					// SI réponse = oui
					if(option == 0){
						
						courseADemarrer = false;
						chronometre.StopTimer();
						chronometre.ResetTimer();
						cbClassementChoixCourse.setEnabled(true);
						btnAbandonner.setEnabled(false);
						btnAjoutClassement.setEnabled(false);
						btnFichier.setEnabled(true);
						chronometre.ResetTimer();
						btnDemarrer.setEnabled(false);
						
						//modelClassementRight.setIdCourseCourrant( Integer.parseInt( ( (String) cbClassementChoixCourse.getSelectedItem()).substring(0,1) ));
						
						modelClassementRight.updateClassement(idClassementChoixCourse); //  /!\
						modelCourse.clotureUneCourse(idClassementChoixCourse, true);
						
					}
				}else{
					courseADemarrer = false;
					chronometre.StopTimer();
					chronometre.ResetTimer();
					cbClassementChoixCourse.setEnabled(true);
					btnAbandonner.setEnabled(false);
					btnAjoutClassement.setEnabled(false);
					btnFichier.setEnabled(true);
					chronometre.ResetTimer();
					btnDemarrer.setEnabled(false);
				
					//modelClassementRight.setIdCourseCourrant( Integer.parseInt( ( (String) cbClassementChoixCourse.getSelectedItem()).substring(0,1) ));
					
					modelClassementRight.updateClassement(idClassementChoixCourse); //  /!\
					modelCourse.clotureUneCourse(idClassementChoixCourse, true);
				}
					
						
					//modelCourse.clearDonnees();
					//modelCourse.remplisageTableau();
					
			}
			
		});
		btnFichier.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(modelCourse.getRowCount() != 0){	
					
					Object[] tabCourse = null;
					ArrayList<Object[]> tabCategorie1 = new ArrayList<Object[]>();
					ArrayList<Object[]> tabCategorie2 = new ArrayList<Object[]>();
					ArrayList<Object[]> tabCategorie3 = new ArrayList<Object[]>();
					ArrayList<Object[]> tabCategorie4 = new ArrayList<Object[]>();
					
					final int ID_COURSE 	= 0;
					final int INTITULER	 	= 1;
					final int DISTANCE		= 2;
					final int DATE_COURSE 	= 3;
					
					final int NOM_VOILIER 	= 0;
					final int NOM_SKIPPEUR 	= 1;
					final int TEMPS_ARRIVER = 2;
					final int RATING 		= 3;
					final int TEMPS_TOTAL 	= 4;
					
					//int idCourse = 0;
					//String intitulerCourse = "";
					//Date dateCourse;
					
					Date dtTempsParticpant 		= null;
					int handicap 				= 0;
					String sTempsDuParticpant 	= "";
					SimpleDateFormat sdf 		= new SimpleDateFormat("HH:mm:ss");
					boolean classementEstValide = false;
					
					try{
						Connect cx = new Connect();
						// Cherche tout les participant d'une course
						ResultSet rs = cx.recherche("SELECT * FROM Classement c INNER JOIN Voilier v ON v.id_voilier = c.id_voilier INNER JOIN Course cr ON cr.id_course = c.id_course WHERE c.id_course = "+ idClassementChoixCourse );
						
						// Pour tout les participants
						while(rs.next())
						{							
							// initialise tabCourse pour garder les information d'une course (traitement non optimiser)
							tabCourse = new Object[] {
									rs.getInt	("id_course"),
									rs.getString("intituler"),
									rs.getString("distance"),
									rs.getString("date_course")
							};
							
							
							// Recupère le temps du participant courrant
							sTempsDuParticpant 	= rs.getString("temps_arriver");
							
							
							if(sTempsDuParticpant == null || sTempsDuParticpant == ""){
								classementEstValide = false;
								break;
							}else{

								dtTempsParticpant		= sdf.parse(sTempsDuParticpant);
								// Double.parseDouble( (String) tabCourse[DISTANCE] )
								if(Outils.convertHMStoS(dtTempsParticpant) != 0){
									handicap = (int) Math.round( (5143 / (Math.sqrt(rs.getDouble("rating")) + 3.5)) * rs.getDouble("distance"));				
								}else{
									handicap = 0;
								}
								classementEstValide = true;
							}
							
							
							// initialise tabParticipant pour garder les information d'un participant						
							Object[] tabParticipant = new Object[] {									
									rs.getString("nom_voilier"),
									rs.getString("nom_skippeur"),
									rs.getString("temps_arriver"),
									rs.getString("rating"),
									Outils.convertHMStoS(dtTempsParticpant) + handicap
							};							
							
							// On trie les participants suivant leur categorie
							switch(rs.getInt("id_categorie")){
								case 1:
									tabCategorie1.add(tabParticipant);
									break;
								case 2:
									tabCategorie2.add(tabParticipant);
									break;
								case 3:
									tabCategorie3.add(tabParticipant);
									break;
								case 4:
									tabCategorie4.add(tabParticipant);
									break;
								default:
									System.out.println("Erreur : identifiant categorie non valide");
							}
						}
						// Deconnexion
						cx.deconnexion();
							
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(classementEstValide == false){

						JOptionPane.showMessageDialog(maFrame,
								"Fichier non générer \nUn ou plusieurs participants n'ont pas un temps valide !",
								"Avertissement",
						         JOptionPane.WARNING_MESSAGE);
					}else{
						
						// Condition du trie des participant (en fonction du temps total
						Comparator<Object[]> ComparatorTemps = new Comparator<Object[]>(){
							public int compare(Object[] obj1, Object[] obj2) {
								return (int)obj1[TEMPS_TOTAL] - (int) obj2[TEMPS_TOTAL];
							}
						};
						
						// On tire le contenue des tableaux (par rapport au TEMPS_TOTAL)
						Collections.sort(tabCategorie1, ComparatorTemps);
						Collections.sort(tabCategorie2, ComparatorTemps);
						Collections.sort(tabCategorie3, ComparatorTemps);
						Collections.sort(tabCategorie4, ComparatorTemps);
						
						
						try{
							// Définition du chemin du fichier
							String cheminFichier = "./" + tabCourse[ID_COURSE] + "" + tabCourse[INTITULER] + "_"+ tabCourse[DATE_COURSE] + ".csv";
							
							
							File monfichier 	= new File(cheminFichier);				// Création du fichier
							FileWriter fw 		= new FileWriter(monfichier, false);	// Désactive l'ecriture à la suite
							BufferedWriter bw 	= new BufferedWriter(fw);				// Définition du buffer d'écriture
							
							
							
							bw.write("## CLASSEMENT DE LA COURSE : " + tabCourse[INTITULER] + " DU " + tabCourse[DATE_COURSE] + "\r\n");
							
							bw.write("\r\n");
							bw.write("\r\n");
							
							//CLASSEMENT DE LA PREMIèRE CATEGORIE
							bw.write("## CLASSMENT DE LA CATEGORIE : 1" + ";" +"\r\n");
							bw.write("POSITION"+ ";" +"NOM_VOILIER"+ ";"+ "NOM_SKIPPEUR" +";" + "RATING "+ ";" + "TEMPS_ARRIVER"+ ";" + "TEMPS_TOTAL" +";"+ "\r\n");
							
							int position;
							
							position = 1;
							for(Object[] obj : tabCategorie1){
								if( (int) obj[TEMPS_TOTAL] == 0 ){
									bw.write("(Abandon)" + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";"+ obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
								}else{
									bw.write(position + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";"+ obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
									position++;
								}
								
							}				
							
							
							bw.write("\r\n");
														
							
							// CLASSEMENT DE LA DEUXIèME CATEGORIE
							bw.write("## CLASSMENT DE LA CATEGORIE : 2" + ";" +"\r\n");
							bw.write("POSITION"+ ";" +"NOM_VOILIER"+ ";"+ "NOM_SKIPPEUR" +";" + "RATING "+ ";" + "TEMPS_ARRIVER"+ ";" + "TEMPS_TOTAL" +";"+ "\r\n");
							
							position = 1;
							for(Object[] obj : tabCategorie2){
								if( (int) obj[TEMPS_TOTAL] == 0 ){
									bw.write("(Abandon)" + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";" + obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
								}else{
									bw.write(position + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";" + obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
									position++;
								}
							}	
							
							
							bw.write("\r\n");
							
							
							// CLASSEMENT DE LA TROISIèME CATEGORIE
							bw.write("## CLASSMENT DE LA CATEGORIE : 3" + ";" +"\r\n");
							bw.write("POSITION"+ ";" +"NOM_VOILIER"+ ";"+ "NOM_SKIPPEUR" +";" + "RATING "+ ";" + "TEMPS_ARRIVER"+ ";" + "TEMPS_TOTAL" +";"+ "\r\n");
							
							position = 1;
							for(Object[] obj : tabCategorie3){
								
								if( (int) obj[TEMPS_TOTAL] == 0 ){
									bw.write("(Abandon)" + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";" + obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
								}else{
									bw.write(position + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";" + obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
									position++;
								}
							}
							
							
							bw.write("\r\n");
							
							
							// CLASSEMENT DE LA QUATRIèME CATEGORIE
							bw.write("## CLASSMENT DE LA CATEGORIE : 4" + ";" +"\r\n");
							bw.write("POSITION"+ ";" +"NOM_VOILIER"+ ";"+ "NOM_SKIPPEUR" +";" + "RATING "+ ";" + "TEMPS_ARRIVER"+ ";" + "TEMPS_TOTAL" +";"+ "\r\n");
							
							position = 1;
							for(Object[] obj : tabCategorie4){
								
								if( (int) obj[TEMPS_TOTAL] == 0 ){
									bw.write("(Abandon)" + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";" + obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
								}else{
									bw.write(position + ";" + obj[NOM_VOILIER] + ";" + obj[NOM_SKIPPEUR] + ";" + obj[RATING] + ";" + obj[TEMPS_ARRIVER] + ";" + Outils.convertHMS((int) obj[TEMPS_TOTAL]) + ";" + "\r\n");
									position++;
								}
							}
							
							
							bw.close();
							fw.close();
						}catch(Exception e){
							e.printStackTrace();
						}	
					}
				}
			}
		});
	}	
}
