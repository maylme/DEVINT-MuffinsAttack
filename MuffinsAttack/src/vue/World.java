package vue;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.lang.Thread;
import java.util.Random;

/**
 * Created by Jicé on 20/03/2014.
 */
public class World extends JPanel {
    private static final int DISTANCEOFGROUND = 20;
    private static final int FALLHEIGHT = 10;

    private static Muffin muffin;
    private static Random r;
    private String alphabet;
    private Collection<Muffin> muffins;
    private Point position;

    public World(String alphabet) {
        this.alphabet = alphabet;
        r = new Random();
        position = new Point(350,0);

        muffin = new Muffin(getRandomLetter());
        this.add(muffin);
    }

    public boolean touchTheGround() {
        return position.getY() > this.getHeight()-DISTANCEOFGROUND;
    }

    public void fall() {
        while(!touchTheGround()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fallOnce();
        }
    }

    public void fallOnce() {
        position.move((int) position.getX(), (int) position.getY()+FALLHEIGHT);
        System.out.println("New position: " + position);
        touchTheGround();
        updateMuffinPosition();
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public char getRandomLetter() {
        return alphabet.charAt(r.nextInt(alphabet.length()));
    }

    public void updateMuffinPosition() {
        muffin.move((int) position.getX(), (int) position.getY());
    }
}
