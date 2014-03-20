package vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jicé on 20/03/2014.
 */
public class Muffin extends JPanel {
    private static final ImageIcon icon = new ImageIcon("../ressources/images/theo.JPG");
    private char lettre;

    public Muffin(char lettre) {
        this.lettre = lettre;

        JLabel jl = new JLabel(String.valueOf(lettre),icon,JLabel.CENTER);
        this.add(jl);

        this.setPreferredSize(new Dimension(30,30));
    }
}
