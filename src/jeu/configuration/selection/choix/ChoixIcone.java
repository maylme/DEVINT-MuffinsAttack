package jeu.configuration.selection.choix;

import devintAPI.Preferences;
import jeu.configuration.selection.Selection;
import jeu.global.ChargerAvatar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.IconView;
import java.awt.*;
import java.io.File;

/**
 * @author Jean-Christophe Isoard
 */
public class ChoixIcone extends Choix<String> {
    private JLabel label;

    public ChoixIcone(Selection<String> n, String i) {
        super(n, i);
    }

    @Override
    public void init() {
        label = new JLabel(ChargerAvatar.charger(getObjetChoix()));
        this.setPreferredSize(label.getSize());
        this.add(label, BorderLayout.CENTER);
    }

    @Override
    public void refresh() {
        if (isSelected()) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            this.setBorder(null);
        }
    }

    public String getIcone() {
        return getObjetChoix();
    }
}
