/*  Classe de menu de lancement de l'exemple de jeu.
 *  Cette classe hérite de la classe abstraite MenuAbstrait en définissant les méthodes :
 *     - nomOptions qui renvoie la liste des options possibles pour le menu 
 *     - lancerOption qui associe une action à chaque option du menu
 *     - wavAccueil() qui renvoie le nom du fichier wav lu lors de l'accueil dans le menu
 *     - wavAide() qui renvoie le nom du fichier wav lu lors de l'activation de la touche F1
 */
package jeu;

import devintAPI.MenuAbstrait;
import jeu.apprentissage.Apprentissage;
import jeu.configuration.MenuNiveaux;
import jeu.global.couleurs.Couleurs;
import jeu.intro.Intro;
import jeu.muffinattacks.Jeu;
import jeu.global.Utilisateur;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.util.Collection;

public class MenuJeu extends MenuAbstrait {
    private Utilisateur utilisateur;
    private String[] noms;
    private Thread cinematique;
    private Intro introFrame;

    /**
     * constructeur
     *
     * @param title : le nom du jeu
     */
    public MenuJeu(String title) {
        super(title);
    }

    /**
     * renvoie le nom des options du menu vous pouvez définir autant d'options
     * que vous voulez
     *
     * @return
     */
    @Override
    protected String[] nomOptions() {
        noms = new String[]{"Jouer", "Apprentissage","Voir la cinématique", "Couleurs", "Choisir niveau", "Quitter"};
        return noms;
    }

    /**
     * lance l'action associée au bouton n°i la numérotation est celle du
     * tableau renvoyé par nomOption
     *
     * @param i
     */
    @Override
    protected void lancerOption(int i) {
        switch (i) {
            case 0:
                Jeu j = new Jeu(nomJeu + " : le jeu");
                j.setUtilisateur(utilisateur);
                j.setCouleurs(utilisateur.getCouleursChoisies());
                break;
            case 1:
                Apprentissage a = new Apprentissage(nomJeu + ": apprentissage");
                a.setUtilisateur(utilisateur);
                a.changeColor();
                break;
            case 2:
                try{
                    introFrame = new Intro(this);
                    cinematique = new Thread(introFrame);
                    cinematique.start();
                }
                catch(Exception e){System.out.print(e);}
                break;
            case 3:
                (new OptionCouleurs(nomJeu + ": choix couleurs")).setUtilisateur(utilisateur);
                break;
            case 4:
                MenuNiveaux menuNiveaux = new MenuNiveaux(nomJeu + ": choix niveau");
                menuNiveaux.setUtilisateur(utilisateur);
                menuNiveaux.setCouleurs(utilisateur.getCouleursChoisies());
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.err.println("action non définie");
        }
    }

    /**
     * renvoie le fichier wave contenant le message d'accueil ces fichiers
     * doivent être placés dans ressources/sons/
     *
     * @return
     */
    @Override
    protected String wavAccueil() {
        return "../ressources/sons/accueil.wav";
    }

    /**
     * renvoie le fichier wave contenant la règle du jeu
     *
     * @return
     */
    @Override
    protected String wavRegleJeu() {
        return "../ressources/sons/accueil.wav";
    }

    private void actualiserCouleurs() {
        Couleurs c = Couleurs.NOIRBLANC;
        if(utilisateur != null) {
            Collection<Couleurs> couleurs = utilisateur.getCouleursPreferees();
            c = ((Couleurs[]) couleurs.toArray())[0];
        }
       this.setBackground(c.getCouleurFond());
       this.setForeground(c.getCouleurTexte());
    }

    public void setUtilisateur(Utilisateur u) {
        this.utilisateur = u;
        changeColor();
    }

    @Override
    public void changeColor() {
        utilisateur.couleursSuivantes();
        Couleurs couleursUtilisateur = utilisateur.getCouleursChoisies();
        foregroundColor = couleursUtilisateur.getCouleurTexte();
        backgroundColor = couleursUtilisateur.getCouleurFond();

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

    public void stopCinematique() {
        while(!introFrame.isFinished()) {
            cinematique.interrupt();
        }
    }
}
