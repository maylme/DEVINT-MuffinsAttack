package jeu.apprentissage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import devintAPI.DevintFrameListener;
import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

public class Apprentissage extends FenetreAbstraite{
		
		// un label
		// est une variable d'instance car il doit �tre accessible 
		// dans la m�thode changeColor, qui g�re les pr�f�rences
		private JTextArea lb1;
		
		//liste des label des lettres:
		private PanelLettres panel;
		
		// appel au constructeur de la classe m�re
	    public Apprentissage(String title) {
	    	super(title);
	     }
	    

		// renvoie le fichier wave contenant le message d'accueil
		protected  String wavAccueil() {
			return "../ressources/sons/apprentissage/accueil.wav";
		}
		
		// renvoie le fichier wave contenant la r�gle du jeu
		protected  String wavRegleJeu() {
			return "../ressources/sons/apprentissage/aideF1.wav";
		}
		
		// renvoie le fichier wave contenant la r�gle du jeu
		protected  String wavAide() {
			return "../ressources/sons/apprentissage/aide.wav";
		}

	    // d�finition de la m�thode abstraite "init()"
	    // initialise le frame 
	    protected void init() {
	    	// BorderLayout, voir http://java.sun.com/docs/books/tutorial/uiswing/layout/border.html
	    	setLayout(new BorderLayout());
	 
	    	// premier label
	    	// ce label est g�r� par les pr�f�rences (cf m�thode changeColor)
	    	String text = "Bienvenu dans la fenêtre d'apprentissage du Brail.\n";
	    	text += "Ici tu peux t'entrainer à reconnaitre les lettres.\n";
	    	text += "La touche ESC te permet de revenir au menu précedent\n"; 
	     	lb1 = new JTextArea (text); 
	    	lb1.setLineWrap(true);
	    	lb1.setEditable(false);
	    	lb1.setFont(new Font("Georgia",1,30));
	    	// on r�cup�re les couleurs de base dans la classe Preferences 
			Preferences pref = Preferences.getData();
			Color foregroundColor = pref.getCurrentForegroundColor();
			Color backgroundColor = pref.getCurrentBackgroundColor();
			lb1.setBackground(backgroundColor);
			lb1.setForeground(foregroundColor);
	    	
	    	// on place le premier composant en haut
	    	this.add(lb1,BorderLayout.NORTH);

	    	// deuxi�me label, qui n'est pas g�r� par les pr�f�rences
	    	panel = new PanelLettres(this);
	       	this.add(panel,BorderLayout.CENTER);
	   }

	    
	    public void keyReleased(KeyEvent e){
	    	super.keyReleased(e);
	    	panel.releaseLetter(e.getKeyCode());
	    }
	    
	    public void dire (String s){
	    	voix.playShortText(s);
	    }
	    
	    // �v�nements clavier
	    public void keyPressed(KeyEvent e) {
	    	// appel � la m�thode m�re qui g�re les �v�nements ESC, F1, F3, F4
	    	super.keyPressed(e);
	    	panel.presseLetter(e.getKeyCode());
	    }
	    
	    
	    
		/**
		 * Pour modifier les couleurs de fond et de premier plan de la fen�tre
		 * Cette fonction est appel�e par la fonction "changeColor" de la classe "Preferences"
		 * � chaque fois que l'on presse F3 
		 * 
		 * on change la couleur du texte principal
		 **/
		public  void changeColor() {
	    	// on r�cup�re les couleurs de base dans la classe Preferences 
			Preferences pref = Preferences.getData();
			lb1.setBackground(pref.getCurrentBackgroundColor());
			lb1.setForeground(pref.getCurrentForegroundColor());
		}

}
