package test.global.couleurs;

import jeu.global.couleurs.Couleurs;
import org.junit.Before;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mayl on 13/05/2014.
 */
public class CouleursTest {


    @org.junit.Test
    public void testGetCouleurFond() throws Exception {
        assertEquals(Couleurs.BEIGEBLEU.getCouleurFond(), new Color(200, 173, 127));
        assertEquals(Couleurs.ORANGENOIR.getCouleurFond(), Color.orange);

        assertEquals(Couleurs.ROUGEBLANC.getCouleurFond(), Color.red);
        assertEquals(Couleurs.ROUGEBEIGE.getCouleurFond(), Color.red);
        assertEquals(Couleurs.ROUGEJAUNE.getCouleurFond(), Color.red);

        assertEquals(Couleurs.JAUNENOIR.getCouleurFond(), Color.yellow);
        assertEquals(Couleurs.JAUNEMARRON.getCouleurFond(), Color.yellow);
        assertEquals(Couleurs.JAUNEVERT.getCouleurFond(), Color.yellow);
        assertEquals(Couleurs.JAUNEBLEU.getCouleurFond(), Color.yellow);

        assertEquals(Couleurs.BLEUBLANC.getCouleurFond(), Color.blue) ;
        assertEquals(Couleurs.BLEUBEIGE.getCouleurFond(), Color.blue);
        assertEquals(Couleurs.BLEUJAUNE.getCouleurFond(), Color.blue);

        assertEquals(Couleurs.VERTBLANC.getCouleurFond(), Color.green);
        assertEquals(Couleurs.VERTJAUNE.getCouleurFond(), Color.green);

        assertEquals(Couleurs.MARRONBLANC.getCouleurFond(), new Color(91, 59, 17));
        assertEquals(Couleurs.MARRONBEIGE.getCouleurFond(), new Color(91, 59, 17));
        assertEquals(Couleurs.MARRONJAUNE.getCouleurFond(), new Color(91, 59, 17));

        assertEquals(Couleurs.BEIGENOIR.getCouleurFond(), new Color(200, 173, 127));
        assertEquals(Couleurs.BEIGEMARRON.getCouleurFond(), new Color(200, 173, 127));
        assertEquals(Couleurs.BEIGEROUGE.getCouleurFond(), new Color(200, 173, 127));

        assertEquals(Couleurs.NOIRBLANC.getCouleurFond(), Color.black);
        assertEquals(Couleurs.NOIRBEIGE.getCouleurFond(), Color.black);
        assertEquals(Couleurs.NOIRJAUNE.getCouleurFond(), Color.black);
        assertEquals(Couleurs.NOIRORANGE.getCouleurFond(), Color.black);

    }

    @org.junit.Test
    public void testGetCouleurTexte() throws Exception {

        assertEquals(Couleurs.BEIGEBLEU.getCouleurTexte(), Color.blue);
        assertEquals(Couleurs.ORANGENOIR.getCouleurTexte(), Color.black);

        assertEquals(Couleurs.ROUGEBLANC.getCouleurTexte(), Color.white);
        assertEquals(Couleurs.ROUGEBEIGE.getCouleurTexte(), new Color(200, 173, 127));
        assertEquals(Couleurs.ROUGEJAUNE.getCouleurTexte(), Color.yellow);

        assertEquals(Couleurs.JAUNENOIR.getCouleurTexte(), Color.black);
        assertEquals(Couleurs.JAUNEMARRON.getCouleurTexte(), new Color(91, 59, 17));
        assertEquals(Couleurs.JAUNEVERT.getCouleurTexte(), Color.green);
        assertEquals(Couleurs.JAUNEBLEU.getCouleurTexte(), Color.blue);

        assertEquals(Couleurs.BLEUBLANC.getCouleurTexte(), Color.white) ;
        assertEquals(Couleurs.BLEUBEIGE.getCouleurTexte(), new Color(200, 173, 127));
        assertEquals(Couleurs.BLEUJAUNE.getCouleurTexte(), Color.yellow);

        assertEquals(Couleurs.VERTBLANC.getCouleurTexte(), Color.white);
        assertEquals(Couleurs.VERTJAUNE.getCouleurTexte(), Color.yellow);

        assertEquals(Couleurs.MARRONBLANC.getCouleurTexte(), Color.white);
        assertEquals(Couleurs.MARRONBEIGE.getCouleurTexte(), new Color(200, 173, 127));
        assertEquals(Couleurs.MARRONJAUNE.getCouleurTexte(), Color.yellow);

        assertEquals(Couleurs.BEIGENOIR.getCouleurTexte(), Color.black);
        assertEquals(Couleurs.BEIGEMARRON.getCouleurTexte(), new Color(91, 59, 17));
        assertEquals(Couleurs.BEIGEROUGE.getCouleurTexte(), Color.red);

        assertEquals(Couleurs.NOIRBLANC.getCouleurTexte(), Color.white);
        assertEquals(Couleurs.NOIRBEIGE.getCouleurTexte(), new Color(200, 173, 127));
        assertEquals(Couleurs.NOIRJAUNE.getCouleurTexte(), Color.yellow);
        assertEquals(Couleurs.NOIRORANGE.getCouleurTexte(), Color.orange);
    }

    @org.junit.Test
    public void testGetContraste() throws Exception {

        assertEquals(Couleurs.BEIGEBLEU.getContraste(), 75);
        assertEquals(Couleurs.ORANGENOIR.getContraste(), 76);

        assertEquals(Couleurs.ROUGEBLANC.getContraste(), 84);
        assertEquals(Couleurs.ROUGEBEIGE.getContraste(), 78);
        assertEquals(Couleurs.ROUGEJAUNE.getContraste(), 82);

        assertEquals(Couleurs.JAUNENOIR.getContraste(), 89);
        assertEquals(Couleurs.JAUNEMARRON.getContraste(), 80);
        assertEquals(Couleurs.JAUNEVERT.getContraste(), 76);
        assertEquals(Couleurs.JAUNEBLEU.getContraste(), 79);

        assertEquals(Couleurs.BLEUBLANC.getContraste(), 82) ;
        assertEquals(Couleurs.BLEUBEIGE.getContraste(), 75);
        assertEquals(Couleurs.BLEUJAUNE.getContraste(), 79);

        assertEquals(Couleurs.VERTBLANC.getContraste(),80);
        assertEquals(Couleurs.VERTJAUNE.getContraste(), 76);

        assertEquals(Couleurs.MARRONBLANC.getContraste(), 84);
        assertEquals(Couleurs.MARRONBEIGE.getContraste(),77);
        assertEquals(Couleurs.MARRONJAUNE.getContraste(), 80);

        assertEquals(Couleurs.BEIGENOIR.getContraste(), 87);
        assertEquals(Couleurs.BEIGEMARRON.getContraste(), 77);
        assertEquals(Couleurs.BEIGEROUGE.getContraste(), 78);

        assertEquals(Couleurs.NOIRBLANC.getContraste(), 91);
        assertEquals(Couleurs.NOIRBEIGE.getContraste(), 87);
        assertEquals(Couleurs.NOIRJAUNE.getContraste(), 89);
        assertEquals(Couleurs.NOIRORANGE.getContraste(),76);
    }

    @org.junit.Test
    public void testGetOne() throws Exception {

    }

    @org.junit.Test
    public void testRevertColor() throws Exception {

    }
}
