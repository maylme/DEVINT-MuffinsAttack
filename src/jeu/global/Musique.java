package jeu.global;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Jean-Christophe Isoard
 */
public class Musique implements Runnable {
    private final String musiquesSrc = "../ressources/musiques/";
    private Player player;
    private int reading = 0;
    private Thread playerThread;

    private String recupererMusique(int n) {
        File repertoire = new File(musiquesSrc);
        if (!repertoire.isDirectory()) return null;
        String[] listeFichiers = repertoire.list();
        if (listeFichiers.length == 0)
            return null;
        for (String fichier : listeFichiers) {
            if (fichier.contains(".mp3") && fichier.contains(String.valueOf(n))) {
                return fichier;
            }
        }
        return null;
    }

    public void demarrer() {
        String mp3 = recupererMusique(++reading);
        if(mp3 == null) {
            reading = 0;
            demarrer();
        }
        try {
            player = new Player(new FileInputStream(musiquesSrc+mp3));
        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
        }

        playerThread = new Thread(this);
        playerThread.start();
    }

    public void arreter() {
        player.close();
    }

    @Override
    public void run() {
        try {
            player.play();
            while(!player.isComplete());
            demarrer();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
