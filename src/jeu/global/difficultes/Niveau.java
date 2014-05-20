package jeu.global.difficultes;

/**
 * Created by Mayl on 18/04/2014.
 */
public enum Niveau {

    UN(1, 5, 30),
    DEUX(2, 10, 60),
    TROIS(3, 15, 90),
    QUATRE(4, 20, 150),
    CINQ(5, 26, 200);

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int id;
    private int nbLettres;
    private int objectif;

    private Niveau(int id, int nbLettres, int objectifScore) {
        this.id = id;
        this.nbLettres = nbLettres;
        this.objectif = objectifScore;
    }

    public String getName() {
        return this.name();
    }

    public int getNbLettres() {
        return nbLettres;
    }

    public String getAlphabet() {
        return alphabet.substring(0, nbLettres);
    }

    public int getObjectif() {
        return objectif;
    }

    public int getId() {
        return id;
    }

    public static Niveau getById(int id) {
        for (Niveau n : Niveau.values()) {
            if (Integer.compare(n.id, id) == 0) {
                return n;
            }
        }
        return CINQ;
    }

    public String toString() {
        return String.valueOf(id);
    }

    public Niveau suivant() {
        return getById(id + 1);
    }
}
