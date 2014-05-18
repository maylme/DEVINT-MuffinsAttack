package jeu.global.voix;

import devintAPI.Preferences;
import t2s.SIVOXDevint;

/**
 * Created by Jicé on 09/04/2014.
 */
public class Voix {
    private static SIVOXDevint voix = Preferences.getData().getVoice();

    public static void dire(String texte) {
        voix.stop();
        voix.playShortText(texte);
    }
}
