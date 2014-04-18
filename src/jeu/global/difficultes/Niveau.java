package jeu.global.difficultes;

/**
 * Created by Mayl on 18/04/2014.
 */
public enum Niveau {
    UN(5),
    DEUX(10),
    TROIS(15),
    QUATRE(20),
    CINQ(26);

    private int nbLettres;

    private Niveau(int nbLettres) {
        this.nbLettres = nbLettres;
    }

    public int getNbLettres() {
        return nbLettres;
    }
}
