package muffinattacks;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Jicé on 24/03/2014.
 */
public class Monde extends JPanel implements ActionListener {
    private Timer timer;
    private final int muffinSpeed = 200; // pixel/grow by seconds
    private Muffin muffin;
    private JLabel status;
    private boolean isStarted;
    private boolean isPaused;
    private boolean isFallingFinished;

    public Monde(JLabel status) {
        this.timer = new Timer(1000/muffinSpeed, this);
        this.isFallingFinished = false;
        this.status = status;
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.addKeyListener(new MuffinAttackCommands());
        this.setFocusable(true);
    }

    public void jouer() {
        if (isPaused) return;
        isStarted = true;
        newMuffin();
        timer.start();
    }

    /**
     * Met en ou sort le jeu de pause
     */
    public void pause() {
        if (!isStarted) return;

        isPaused = !isPaused;
        if(isPaused) {
            timer.stop();
            status.setText("pause");
        } else {
            timer.start();
            status.setText(String.valueOf(timer.getDelay()));
        }
        repaint();
    }

    public void muffinTouchedGround() {
        isFallingFinished = true;
    }

    public void newMuffin() {
        // TODO (random letter)
        if (muffin == null) {
            muffin = new Muffin('A', 40, 500);
        } else {
            muffin.replaceOnTop();
        }
    }

    public void muffinFall() {
        if(muffin.toucheSol((int) this.getSize().getHeight() - 60)) {
            muffinTouchedGround();
        }
        muffin.fallOnce();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (muffin != null) {
            muffin.paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newMuffin();
        } else {
            muffinFall();
        }
    }

    class MuffinAttackCommands extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(!isStarted) return;

            int keycode = e.getKeyCode();

            if (keycode == KeyEvent.VK_PAUSE) {
                System.out.println("Pause attempt");
                pause();
                return;
            }

            if(isPaused) return;

            if(Character.toLowerCase(muffin.getLetter()) == keycode) {
                muffin.kill();
            }
        }
    }
}
