package top.tonymochel.main;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Chrono
{

	private int iHours 		= 0;			// Nombre d'heures
	private int iMinutes 	= 0;			// Nombre de minute
	private int iSeconds 	= 0;			// Nombre de seconde	
	private static final int DELAIS = 1000;	// Initialise le délais (représente 1s)
	
	private static Timer tTimer;			
	private JLabel lblChrono;
	private static ActionListener alTimer;
	
	/**
	 * Contructeur de la classe
	 */
	public Chrono(){
		// Defini le text du Label  (HH:MM:SS)
		lblChrono = new JLabel(
				( ( iHours < 10 ) 	? "0" + iHours : iHours )		+ ":" +
				( ( iMinutes < 10 ) ? "0" + iMinutes : iMinutes )	+ ":" +
				( ( iSeconds < 10 ) ? "0" + iSeconds : iSeconds )
		);
		
		// Action à executer en continu en fonction du DELAIS
		alTimer = new ActionListener(){
			public void actionPerformed( ActionEvent eEvent ){
				
				iSeconds++;				// Incrementation des secondes
				
				if ( iSeconds == 60 ){
					iSeconds = 0;
					iMinutes++;			// Incrementation des minutes
				}
				if ( iMinutes == 60 ){
					iMinutes = 0;
					iHours++;			// Incrementation des heures
				}
				
				// Modifie le text du JLabel
				lblChrono.setText(						
						( ( iHours < 10 ) 	? "0" + iHours : iHours )		+ ":" +
						( ( iMinutes < 10 ) ? "0" + iMinutes : iMinutes )	+ ":" +
						( ( iSeconds < 10 ) ? "0" + iSeconds : iSeconds )
				);
			}
		};
		createChrono();
	}
	
	/**
	 * Methode executant tout les seconde (DELAI) l'evenement alTimer
	 */
	public void createChrono(){
		// Execution tout les seconde de l'evenement alTimer
		tTimer = new Timer(DELAIS, alTimer );
	}
	
	public JLabel getJLabel(){
		lblChrono.setFont( new Font( "serif" , Font.PLAIN, 24 ) );
		return lblChrono;
	}
	
	public String getText(){
		return lblChrono.getText();
	}
	
	/**
	 * Arrete l'execution du Timer
	 */
	public void StopTimer(){
		tTimer.stop();
	}
	/**
	 * Démarre l'execution du Timer
	 */
	public void StartTimer(){
		tTimer.start();
	}
	/**
	 * Réinitialise le Timer
	 */
	public void ResetTimer(){
		if ( !tTimer.isRunning() )
			lblChrono.setText( "00:00:00" );
			this.iSeconds = 0;
			this.iMinutes = 0;
			this.iHours = 0;
			
	}
}

