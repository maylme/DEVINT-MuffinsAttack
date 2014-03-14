/**
 * @author LOGRE Ivan, MULLER Stephane, GUYADER Erwan
 * El�ves SI3 en 2010-2011
 **/

package devintAPI;

import java.awt.Color;
//import java.io.File;
import java.util.ArrayList;
import java.util.List;


import t2s.SIVOXDevint;

/**
 * classe pour stocker les pr�f�rences de couleurs et de voix SIVOX
 * 
 * On change de jeu de couleurs en tapant F3.
 * On choisi la voix en tapant 
 *
 */
public class Preferences {

	/** The instance of the singleton. */
	private static Preferences instance;

	/** liste des classes (i.e. fen�tres) qui vont utiliser ces pr�f�rences
	 *  ces classes h�ritent de MenuAbstrait ou DevintFrame
	 *  */
	private List<DevintFrameListener> openedWinndows;
	
	/** les valeurs choisies */
	// la voix
	private int currentVoice;
	private SIVOXDevint voix;
	
	// le jeu de couleurs
	private int currentSetOfColor;
	private Color foregroundColor;
	private Color backgroundColor;
	
	/** pour cr�er l'instance de Data */
	public static Preferences getData() {
		if (instance == null) {
			instance = new Preferences();
		}
		return instance;
	}

	/**
	 * Instantiates a new data.
	 */
	private Preferences() {
		openedWinndows = new ArrayList<DevintFrameListener>();
		foregroundColor = new Color(10, 0, 150);
		backgroundColor = Color.WHITE;
		currentSetOfColor = 0;
		currentVoice = 4;
		voix = new SIVOXDevint(currentVoice);
	}

	/**
	 * pour changer la couleur
	 * on passe d'un jeu pr�d�fini � un autre
	 */
	public void changeColor() {
		if (currentSetOfColor == 0) {
			// jeu de couleurs gris/rose
			this.foregroundColor = new Color(49,79,79); // Dark Slate Gray
			this.backgroundColor = new Color(255,182,193); // hot pink
			currentSetOfColor = 1;
		} else if (currentSetOfColor == 1) {
			// jeu de couleurs noir/jaune
			this.foregroundColor = Color.BLACK;
			this.backgroundColor = Color.YELLOW;
			currentSetOfColor = 2;
		} else if (currentSetOfColor == 2) {
			// jeu de couleur de base
			this.foregroundColor = new Color(10, 0, 150); //bleu 
			this.backgroundColor = Color.WHITE;
			currentSetOfColor = 0;
		}
		// on met � jour toutes les menus avec ce nouveau jeu de couleurs
		for (DevintFrameListener fenetre : openedWinndows) {
			fenetre.changeColor();
		}

	}

	// pour changer la voix en passant � la suivante
	public void changeVoice() {
		if(currentVoice == 7)
			currentVoice = 0;
		else
			currentVoice++;
		this.voix.setVoix(currentVoice);
	}

	// la couleur de devant
	public Color getCurrentForegroundColor() {
		return this.foregroundColor;
	}

	// la couleur de fond
	public Color getCurrentBackgroundColor() {
		return this.backgroundColor;
	}
	
	// pour ajouter une fen�tre � la liste des fen�tres dont on peut fixer les pr�f�rences
	public void addDevintFrame(DevintFrameListener frame) {
		if (!openedWinndows.contains(frame))
			this.openedWinndows.add(frame);
	}

	// la voix
	public SIVOXDevint getVoice() {
		return voix;
	}


}
