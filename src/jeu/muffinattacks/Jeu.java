package jeu.muffinattacks;

import devintAPI.FenetreAbstraite;
import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.muffinattacks.infobar.InfoBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import jeu.sauvegarde.Sauvegarde;

/**
 * @author Jean-Christophe Isoard
 */
public class Jeu extends FenetreAbstraite {
    private Utilisateur utilisateur;

    private Random rand;

    private InfoBar infoBar;
    private Monde monde;

    private int points;
    private int tempsRestant;
    private int tempsTotal;
    private Timer timerPause;
    private boolean timerCancelled;
    private boolean aide;
    private boolean challenge;

    /**
     * @param title : titre de la fenetre
     */
    public Jeu(String title) {
        super(title);
    }

    @Override
    protected void init() {
        rand = new Random();

        aide = true;
        challenge = false;

        infoBar = new InfoBar(this);
        monde = new Monde(this, Couleurs.NOIRBLANC);
        timerPause = new Timer();

        this.setLayout(new BorderLayout());

        this.add(infoBar, BorderLayout.NORTH);
        this.add(monde, BorderLayout.CENTER);

        //dire("Presse la touche ESPACE pour démarrer le jeu.");
        jouerEnregistrementPause("espace_pour_demarrer", 1);
    }

    private void preparerJeu() {
        points = 0;
        tempsTotal = 20000;
        infoBar.setTempsTotal(tempsTotal);
        infoBar.setVies(3);
        infoBar.setNiveau(utilisateur.getNiveau());
    }

    private void preparerMonde() {
        tempsRestant = tempsTotal;
        monde.changerTemps(tempsTotal);
    }

    /**
     * Utilise sivox pour lire la phrase donnée en paramètres
     * <br />La phrase est lue aprés le paramètres secondes
     *
     * @param chaine   la phrase à lire
     * @param secondes le temps à attendre avant la lecture
     */
    public void direPause(final String chaine, double secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                dire(chaine);
            }
        };
        if (timerCancelled) timerUncancel();
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }

    /**
     * Lit la lettre correspondante du fichier audio dans le dossier
     * <br />ressources/sons/alphabet/
     *
     * @param lettre   la lettre à dire
     * @param secondes le temps à attendre avant la lecture
     */
    public void direLettrePause(final String lettre, double secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                direLettre(lettre);
            }
        };
        if (timerCancelled) timerUncancel();
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }

    /**
     * Joue l'enregistrement du dossier donné par le fichier string.wav
     * <br />Après le temps donnée secondes
     *
     * @param string   Enregistrement
     * @param secondes
     */
    public void jouerEnregistrementPause(final String string, double secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                jouerEnregistrement(string);
            }
        };
        if (timerCancelled) timerUncancel();
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }

    private void timerUncancel() {
        timerPause = new Timer();
        timerCancelled = false;
    }

    public void dire(String s) {
        voix.stop();
        voix.playShortText(s);
    }

    public void direLettre(String lettre) {
        voix.stop();
        String chemin = "../ressources/sons/alphabet/" + lettre.toLowerCase() + ".wav";
        voix.playWav(chemin);
    }

    public void jouerEnregistrement(String nom) {
        voix.stop();
        voix.playWav("../ressources/sons/jeu/" + nom + ".wav");
    }

    // renvoie le fichier wave contenant le message d'accueil
    @Override
    protected String wavAccueil() {
        return "";
    }

    // renvoie le fichier wave contenant la règle du jeu
    @Override
    protected String wavRegleJeu() {
        jouerEnregistrement("muffins_attaquent_la_ville");
        jouerEnregistrementPause("tu_as_3_vie", 2);
        jouerEnregistrementPause("Pour_les_detruires", 3);
        return "";
    }

    // renvoie le fichier wave contenant l'aide du jeu
    protected String wavAide() {
        if (!monde.getStarted()) {
            return "../ressources/sons/jeu/espace_pour_demarrer.wav";
        } else {
            return "../ressources/sons/jeu/Pour_les_detruires.wav";
        }
    }

    @Override
    public void changeColor() {
        this.setCouleurs(utilisateur.getCouleursChoisies());
    }

    public void setCouleurs(Couleurs c) {
        this.setBackground(c.getCouleurFond());
        infoBar.changeCouleur(c);
        monde.setColors(c);
    }

    public String getAlphabet() {
        return utilisateur.getNiveau().getAlphabet();
    }

    public boolean isInAlphabet(char lettre) {
        return getAlphabet().contains(String.valueOf(lettre));
    }

    public char getRandomLetter() {
        return getAlphabet().charAt(rand.nextInt(getAlphabet().length()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_F1 || keycode == KeyEvent.VK_F2 || keycode == KeyEvent.VK_F3 || keycode == KeyEvent.VK_F4) {
            return;
        }

        if (keycode == KeyEvent.VK_F12) {
            aide = !aide;
            if (aide) {
                dire("Assistance Vocale activée.");
            } else {
                dire("Assistance Vocale éteinte");
            }
            return;
        }

        if (keycode == KeyEvent.VK_SPACE) {
            demarrerJeu();
        }

        if (!(monde.getStarted())) {
            return;
        }

        if (keycode == KeyEvent.VK_ESCAPE) {
            monde.arreter();
            timerPause.cancel();
            dire("La partie a été interrompue. La reprise n'est pas encore gérée.");
            return;
        }

        if (keycode == KeyEvent.VK_F11 || keycode == KeyEvent.VK_PAUSE) {
            monde.pause();
            if (monde.getPaused()) {
                dire("Le jeu est en pause.");
            } else {
                dire("Le jeu reprends.");
            }
            return;
        }

        /*if(monde.getPaused()) {
            if (keycode == KeyEvent.VK_UP) {
                changerTemps(500);
                return;
            }

            if (keycode == KeyEvent.VK_DOWN) {
                if (tempsTotal > 500) {
                    changerTemps(-500);
                }
                return;
            }
        }*/

        if(monde.getPaused()) {
            dire("Le jeu est en pause.");
            return;
        }

        if (keycode == KeyEvent.VK_SPACE) {
            dire("Tu cherches la lettre " + monde.getMuffin().getLettre());
        } else {
            monde.lettreEntree((char) keycode);
        }
    }

    private void demarrerJeu() {
        if (monde.getStarted() || utilisateur == null)
            return;
        preparerJeu();
        preparerMonde();
        timerPause.cancel();
        timerCancelled = true;
        monde.jouer(tempsTotal);
    }

    private void changerTemps(int i) {
        tempsTotal += i;
        tempsRestant += i;
        monde.changerTemps(tempsTotal);
        infoBar.setTempsTotal(tempsTotal);
    }

    public void ajouterPoint(int i) {
        infoBar.setScore(++points);
        challenge();
    }

    private void challenge() {
        if(challenge) {
            //TODO décider du type d'accelération
            changerTemps(-tempsTotal/20);
        } else {
            verifierCapacitesJoueur();
        }
    }

    private void verifierCapacitesJoueur() {
        // si les points du joueur on dépassé le seuil du niveau
        if(points >= utilisateur.getNiveau().getObjectif()) {
            //TODO Proposer à l'utilisateur de changer de niveau
            // OU on passe le jeu en mode challenge (temps s'accelère)
            modeChallenge();
        }
    }

    private void modeChallenge() {
        this.challenge = true;
        dire("Mode tchallénge !");
    }

    public int getVies() {
        return infoBar.getNbVies();
    }

    public boolean getTimeOut() {
        return tempsRestant <= 0;
    }

    public void timeReset() {
        this.tempsRestant = tempsTotal;
        infoBar.resetTime();
    }

    public void jeuFini() {
        monde.arreter();
        utilisateur.setMeilleurScore(utilisateur.getNiveau(), points);
        Sauvegarde.saveUser(utilisateur);
        jouerEnregistrement("fin");
    }

    public void timeOut() {
        jouerEnregistrement("muffin_tombé");
        infoBar.viePerdue(0);
        // on fait une pause de 3 secondes pour ne pas trop perturber le joueur
        monde.newMuffinPause(3);
    }

    public boolean aideActive() {
        return aide;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void tempsEcoule(int temps) {
        tempsRestant -= temps;
        infoBar.forwardTime(temps);
    }

    /**
     * Attends le temps passé en paramètre avant d'effectuer la perte d'une vie
     * @param wait le temps à attendre en (secondes)
     */
    public void viePerdue(int wait) {
        infoBar.viePerdue(wait);
    }
}
