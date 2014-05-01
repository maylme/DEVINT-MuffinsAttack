package jeu.configuration.selection.choix;

import devintAPI.Preferences;
import jeu.configuration.selection.Selection;

import javax.swing.*;
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
        File image = new File(Preferences.getIconsdirectory() + getIcone() + ".ico");
        if(image.isFile())
            label = new JLabel(new ImageIcon(String.valueOf(image)));
        else
            label = new JLabel("ERROR");
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
