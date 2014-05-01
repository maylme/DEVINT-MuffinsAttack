package jeu.apprentissage;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;

public class Apprentissage extends FenetreAbstraite {
    Utilisateur utilisateur;

    //un label est une variable d'instance car il doit être accessible dans la méthode changeColor, qui gère les préférences
    private JTextArea lb1;

    //liste des label des lettres:
    private PanelLettres panel;
    private boolean released;
    private Niveau niveau;

    // appel au constructeur de la classe mère
    public Apprentissage(String title) {
        super(title);
    }

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return "../ressources/sons/apprentissage/accueil.wav";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        return "../ressources/sons/apprentissage/aideF1.wav";
    }

    // renvoie le fichier wave contenant l'aide du jeu
    @Override
    protected String wavAide() {
        return "../ressources/sons/apprentissage/aide.wav";
    }

    // définition de la méthode abstraite "init()"
    // initialise le frame 
    @Override
    protected void init() {
        // BorderLayout, voir http://java.sun.com/docs/books/tutorial/uiswing/layout/border.html
        setLayout(new BorderLayout());

        // premier label
        // ce label est géré par les préférences (cf méthode changeColor)
        String text = "Bienvenue dans la fenêtre d'apprentissage du braille.\n"
                + "Ici tu peux t'entrainer à reconnaitre les lettres.\n"
                + "La touche ESC te permet de revenir au menu précedent\n";
        lb1 = new JTextArea(text);
        lb1.setLineWrap(true);
        lb1.setEditable(false);
        lb1.setFont(new Font("Georgia", 1, 30));
        // on récupère les couleurs de base dans la classe Preferences 
        Preferences pref = Preferences.getData();
        Color foregroundColor = pref.getCurrentForegroundColor();
        Color backgroundColor = pref.getCurrentBackgroundColor();
        lb1.setBackground(backgroundColor);
        lb1.setForeground(foregroundColor);

        // on place le premier composant en haut
        this.add(lb1, BorderLayout.NORTH);

        // puis le panel des lettres
        panel = new PanelLettres(this);
        panel.changeColor(pref.getCurrentForegroundColor(), pref.getCurrentBackgroundColor());
        this.add(panel, BorderLayout.CENTER);
        released = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        panel.changeLetterColor(e.getKeyCode());
        released = true;
    }

    public void dire(String s) {
        voix.stop();
        voix.playShortText(s);
    }

    public void direLettre(String lettre) {
        voix.stop();
        String chemin = "../ressources/sons/alphabet/"+lettre.toLowerCase()+".wav";
        System.out.println(chemin);
        voix.playWav(chemin);
    }

    // �v�nements clavier
    @Override
    public void keyPressed(KeyEvent e) {
        // appel à la méthode mère qui gére les événements ESC, F1, F3, F4
        super.keyPressed(e);
        if(released) {
            panel.changeLetterColor(e.getKeyCode());
        }
        released = false;
    }

    /**
     * Pour modifier les couleurs de fond et de premier plan de la fenêtre Cette
     * fonction est appelée par la fonction "changeColor" de la classe
     * "Preferences" à chaque fois que l'on presse F3 on change la couleur du
     * texte principal
     *
     */
    @Override
    public void changeColor() {
        Couleurs c = utilisateur.getCouleursChoisies();
        lb1.setBackground(c.getCouleurFond());
        lb1.setForeground(c.getCouleurTexte());
        panel.changeColor(c.getCouleurTexte(), c.getCouleurFond());
    }

    public void setUtilisateur(Utilisateur u) {
        this.utilisateur = u;
        setNiveau(u.getNiveau());
    }

    public void setNiveau(Niveau n) {
        this.niveau = n;
        panel.afficherLettres(niveau.getNbLettres());
    }

    public int getNbLettresNiveau() {
        return niveau.getNbLettres();
    }
}
