package jeu.global.couleurs;

import java.awt.*;

/**
 * Created by Jicé on 25/03/2014.
 */
public enum Couleurs {

    ORANGENOIR(Color.ORANGE, Color.BLACK, 76),
    ROUGEBLANC(Color.RED, Color.WHITE, 84),
    ROUGEBEIGE(Color.RED, CouleurAjoutee.BEIGE, 78),
    ROUGEJAUNE(Color.RED, Color.YELLOW, 82),
    JAUNENOIR(Color.YELLOW, Color.BLACK, 89),
    JAUNEMARRON(Color.YELLOW, CouleurAjoutee.MARRON, 80),
    JAUNEVERT(Color.YELLOW, Color.GREEN, 76),
    JAUNEBLEU(Color.YELLOW, Color.BLUE, 79),
    BLEUBLANC(Color.BLUE, Color.WHITE, 82),
    BLEUBEIGE(Color.BLUE, CouleurAjoutee.BEIGE, 75),
    BLEUJAUNE(Color.BLUE, Color.YELLOW, 79),
    VERTBLANC(Color.GREEN, Color.WHITE, 80),
    VERTJAUNE(Color.GREEN, Color.YELLOW, 76),
    MARRONBLANC(CouleurAjoutee.MARRON, Color.WHITE, 84),
    MARRONBEIGE(CouleurAjoutee.MARRON, CouleurAjoutee.BEIGE, 77),
    MARRONJAUNE(CouleurAjoutee.MARRON, Color.YELLOW, 80),
    BEIGENOIR(CouleurAjoutee.BEIGE, Color.BLACK, 87),
    BEIGEMARRON(CouleurAjoutee.BEIGE, CouleurAjoutee.MARRON, 77),
    BEIGEBLEU(CouleurAjoutee.BEIGE, Color.BLUE, 75),
    BEIGEROUGE(CouleurAjoutee.BEIGE, Color.RED, 78),
    NOIRBLANC(Color.BLACK, Color.WHITE, 91),
    NOIRBEIGE(Color.BLACK, CouleurAjoutee.BEIGE, 87),
    NOIRJAUNE(Color.BLACK, Color.YELLOW, 89),
    NOIRORANGE(Color.BLACK, Color.ORANGE, 76);

    private Color couleurFond;
    private Color couleurTexte;
    private int contraste;

    private Couleurs(Color fond, Color texte, int contraste) {
        this.couleurFond = fond;
        this.couleurTexte = texte;
        this.contraste = contraste;
    }

    /**
     * Classe decrivant les couleurs n'existant pas dans la classe Color
     */
    private static class CouleurAjoutee {

        private static final Color BEIGE = new Color(200, 173, 127);
        private static final Color MARRON = new Color(91, 59, 17);
    }

    /**
     * Retourne la couleur du fond de la fenetre
     * @return Color la couleur du fond de la fenetre
     */
    public Color getCouleurFond() {
        return couleurFond;
    }

    /**
     * Retourne la couleur du texte de la fenetre
     * @return Color la couleur du texte de la fenetre
     */
    public Color getCouleurTexte() {
        return couleurTexte;
    }

    /**
     * Retourne le contraste
     * @return contraste
     */
    public int getContraste() {
        return contraste;
    }

    /**
     * Fonction pour recurer le ième couple de couleur
     * @param i le ième configuration de couleur
     * @return l'objet Couleurs demandé
     */
    public static Couleurs getOne(int i) {
        return Couleurs.values()[i];
    }


    /**
     * Renvoie la couleur 'inverse' (negatif)
     * @param c la couleur à inverser
     * @return la couleur en negatif
     */
    public static Color revertColor(Color c) {
        int r = 255-c.getRed();
        int g = 255-c.getGreen();
        int b = 255-c.getBlue();
        return new Color(r,g,b);
    }
}
