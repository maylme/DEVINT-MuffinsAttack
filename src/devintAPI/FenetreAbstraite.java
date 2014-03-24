/** 
Cette classe abstraite est un Frame associ� � une instance de voix 
 * SI_VOX et qui impl�mente KeyListener.
 * Elle peut servir de classe m�re � toutes les fen�tres de vos jeux :
 * il suffit de d�finir la m�thode "init" pour initialiser les �l�ments du Frame
 */


package devintAPI;

import java.awt.event.*;


/** Classe abstraite avec un Frame, une instance de SI_VOX pour parler et 
 * qui �coute les �v�nements clavier avec gestion des pr�f�rences.
 * Par d�faut, un son est lu � l'activation de la fen�tre, 
 * on sort de la fen�tre par ESC et on obtient la r�gle du jeu par F1, l'aide par F2
 * 
 * @author helene
 *
 */
public abstract class FenetreAbstraite extends  DevintFrameListener{
   
    /**
     * @param title : titre de la fen�tre
     */
    public FenetreAbstraite(String title) {
    	super(title);
       	// m�thode init � impl�menter, elle construit ce qui est dans le frame
       	init();
	     // visible
    	this.setVisible(true);
    	// a le focus
    	this.requestFocus();
		voix.playWav(wavAccueil(),true);
    }

    /** m�thode abstraite � impl�menter 
     * pour d�finir ce qu'il y a dans le Frame
     */
    protected abstract void init();
    
    /** m�thode abstraite � impl�menter
     *  
     * @return le fichier wav contenant le message d'aide (activ� par F2)
     */
    protected abstract String wavAide();

    //////////////////////////////////////////////
    // Gestion des �v�nements clavier
    /////////////////////////////////////////////
    public void keyPressed(KeyEvent e) {
    	// gestion de ESC, F1, F3 et F4 dans la classe m�re (DevintFrameListener)
    	super.keyPressed(e);
    	// on ajoute la gestion de l'aide quand on presse F2
    	if (e.getKeyCode()==KeyEvent.VK_F2){
    		voix.playWav(wavAide(),true);
    	}
    }

	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fen�tre
	 * Cette fonction est appel�e par la fonction "changeColor" de la classe "Preferences"
	 * � chaque fois que l'on presse F3 
	 * 
	 * Cette m�thode doit �tre r��crite dans les classes filles 
	 * si cela n'a pas de sens pour votre jeu, vous la red�finissez en la laissant vide
	 **/
	public abstract void changeColor() ;
	
}

