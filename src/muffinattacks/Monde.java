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
    private final int nombreMuffins = 1;
    private Muffin muffin;

    public Monde() {
        random = new Random();
        this.setPreferredSize(new Dimension(400, 400));
        muffin = new Muffin('A', 40, 400);
    }

    public void jouer() {
        System.out.println(this.getPreferredSize().getHeight());
        while (!muffin.toucheSol((int) this.getPreferredSize().getHeight())) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            muffin.fallOnce();
            repaint();

        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        int x = (int) muffin.getPosition().getX();
        int y = (int) muffin.getPosition().getY();
        g.drawRect(x, y, muffin.getTaille(), muffin.getTaille());
    }
}
