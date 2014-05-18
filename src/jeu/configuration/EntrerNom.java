package jeu.configuration;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jean-Christophe Isoard
 */
public class EntrerNom extends JPanel {
    JTextField textFieldNom;

    public EntrerNom(NouvelUtilisateur parent) {
        this.setLayout(new FlowLayout());

        JLabel entrerNom = new JLabel("Entre ton nom :");
        textFieldNom = new JTextField();
        textFieldNom.setPreferredSize(new Dimension(400,80));
        entrerNom.setLabelFor(textFieldNom);

        this.add(entrerNom, CENTER_ALIGNMENT);
        this.add(textFieldNom, CENTER_ALIGNMENT);
    }

    public String getNom() {
        return textFieldNom.getText();
    }
}
