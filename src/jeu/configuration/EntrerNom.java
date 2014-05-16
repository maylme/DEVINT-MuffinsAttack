package jeu.configuration;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class EntrerNom extends JPanel {
    JTextField textFieldNom;

    public EntrerNom() {
        this.setLayout(new FlowLayout());

        JLabel entrerNom = new JLabel("Entre ton nom :");
        textFieldNom = new JTextField();
        entrerNom.setLabelFor(textFieldNom);

        this.add(textFieldNom, CENTER_ALIGNMENT);

    }

    public String getNom() {
        return textFieldNom.getText();
    }
}
