package jeu.global.voix;

import devintAPI.Preferences;
import t2s.SIVOXDevint;

/**
 * Created by Jicé on 09/04/2014.
 */
public class Voix {
    private SIVOXDevint voix;

    public void Voix() {
        // on récupére la voix donnée dans les préférences
        voix = Preferences.getData().getVoice();
    }

    //TODO
}
