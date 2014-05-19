package test.global.couleurs;

import jeu.global.couleurs.ChoixCouleurs;
import jeu.global.couleurs.Couleurs;
import org.junit.After;
import org.junit.Before;

import static junit.framework.TestCase.*;

/**
 * Created by Mayl on 13/05/2014.
 */
public class ChoixCouleursTest {

    ChoixCouleurs c;
    @Before
    private void setUp(){
        c = new ChoixCouleurs(Couleurs.BEIGEBLEU);

    }

    @After
    private void end(){
        System.exit(0);
    }

    @org.junit.Test
    public void testOverlay() throws Exception {
        assertFalse(c.isSelected());
        c.overlay();
        assertTrue (c.isSelected());

    }

    @org.junit.Test
    public void testRefresh() throws Exception {

    }

    @org.junit.Test
    public void testGetCouleurs() throws Exception {

    }

    @org.junit.Test
    public void testIsCouleurs() throws Exception {

    }

    @org.junit.Test
    public void testSetSelected() throws Exception {

    }

    @org.junit.Test
    public void testIsSelected() throws Exception {

    }

    @org.junit.Test
    public void testGetCouleur() throws Exception {

    }
}
