package muffinattacks;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Monde extends JPanel {
    private Random random;
    private Timer timer;
    private final int muffinSpeed = 200; // pixel/grow by seconds
    private Muffin muffin;

    public Monde() {
        random = new Random();
    }

    public void jouer() {
        while(true)
           muffinFall();
    }

    public void muffinFall() {
        if(muffin==null) {
            muffin = new Muffin('A', 40, this.getWidth());
        } else {
            muffin.replaceOnTop();
        }
        while (!muffin.toucheSol((int) this.getSize().getHeight()-60)) {
            try {
                Thread.sleep(1000/muffinSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            muffin.fallOnce();
            repaint();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if(muffin!=null) {
            muffin.paint(g);
        }
    }
}
