package vue;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jicé on 20/03/2014.
 */
public class Game extends JFrame {
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        World world = new World("abcdefghijklmnopqrstuvwxyz");
        this.add(world);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800,600));
        this.setVisible(true);

        world.fall();
    }
}
