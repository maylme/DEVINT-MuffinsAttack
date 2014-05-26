package jeu.global;

import java.awt.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ChangerCurseur {
    private static String curseursSrc = "../ressources/images/icon";
	private Image image;
    private Cursor cursor;
    private static Toolkit toolkit;
    private Component parent;

	public ChangerCurseur(Component parent){
        if(toolkit == null) {
            toolkit = Toolkit.getDefaultToolkit();
        }
        this.parent = parent;
	}

    public void grosseSouris() {
        String img = curseursSrc+"BigMouse.png";
        try {
            image = ImageIO.read(new File(img));
            cursor = toolkit.createCustomCursor(image,new Point(0,0),"BigMouse");
            parent.setCursor(cursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
