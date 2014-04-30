package jeu.global.difficultes;

/**
 * Created by Mayl on 18/04/2014.
 */
public enum Niveau {
    UN(5, 30),
    DEUX(10, 60),
    TROIS(15, 90),
    QUATRE(20, 150),
    CINQ(26, 200);

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int nbLettres;
    private int objectif;

    private Niveau(int nbLettres, int objectifScore) {
        this.nbLettres = nbLettres;
        this.objectif = objectifScore;
    }

    public int getNbLettres() {
        return nbLettres;
    }

    public String getAlphabet() {
        return alphabet.substring(0,nbLettres);
    }

    public int getObjectif() {
        return objectif;
    }
}
