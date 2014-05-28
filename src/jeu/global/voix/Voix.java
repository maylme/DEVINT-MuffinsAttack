package jeu.global.voix;

import devintAPI.Preferences;
import t2s.SIVOXDevint;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jicé on 09/04/2014.
 */
public class Voix {
    private static SIVOXDevint voix = Preferences.getData().getVoice();
    private static Timer timerPause = new Timer();

    public static void dire(String texte) {
        voix.stop();
        voix.playShortText(texte);
    }

    /**
     * Utilise sivox pour lire la phrase donnée en paramètres
     * <br />La phrase est lue aprés le paramètres secondes
     *
     * @param chaine   la phrase à lire
     * @param secondes le temps à attendre avant la lecture
     */
    public static void direPause(final String chaine, double secondes) {
        TimerTask unpauseTask = new TimerTask() {
            @Override
            public void run() {
                dire(chaine);
            }
        };
        timerPause.schedule(unpauseTask, (int) secondes * 1000);
    }
}
