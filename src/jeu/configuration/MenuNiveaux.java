package jeu.configuration;

import devintAPI.FenetreAbstraite;
import devintAPI.MenuAbstrait;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import jeu.global.voix.Voix;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * @author Jean-Christophe Isoard
 */
public class MenuNiveaux extends MenuAbstrait {
    private Utilisateur utilisateur;

    /**
     * constructeur,
     *
     * @param title : le nom du jeu
     */
    public MenuNiveaux(String title) {
        super(title);
    }

    @Override
    protected String[] nomOptions() {
        Niveau[] nvx = Niveau.values();
        String[] stringNvx = new String[nvx.length];
        int i = 0;
        for(Niveau n:nvx) {
            stringNvx[i++] = "Niveau "+n.name();
        }
        return stringNvx;
    }

    @Override
    protected void lancerOption(int i) {
        if(utilisateur == null) {
            dispose();
            return;
        }
        utilisateur.setNiveau(Niveau.getById(i+1));
        dispose();
    }

    @Override
    protected String wavAccueil() {
        return "../ressources/sons/menus/choixniv.wav";
    }

    @Override
    protected String wavRegleJeu() {
        return "../ressources/sons/menus/choixniv.wav";
    }

    @Override
    public void changeColor() {
        this.setCouleurs(utilisateur.getCouleursChoisies());
    }

    public void setCouleurs(Couleurs c) {
        foregroundColor = c.getCouleurTexte();
        backgroundColor = c.getCouleurFond();

        //entête
        enteteBorder = new LineBorder(foregroundColor,8);
        entete.setForeground(foregroundColor);
        entete.setBackground(backgroundColor);
        entete.setBorder(enteteBorder);
        //label
        lb1.setForeground(foregroundColor);
        lb1.setBackground(backgroundColor);
        //panel
        this.getContentPane().setForeground(foregroundColor);
        this.getContentPane().setBackground(backgroundColor);
        //boutons
        this.buttonBorder = new CompoundBorder(new LineBorder(foregroundColor,5),new LineBorder(backgroundColor,3));
        this.refreshButtons();
    }

    public void setUtilisateur(Utilisateur u) {
        this.utilisateur = u;
    }
}
