package test.global.difficultes;

import jeu.global.difficultes.Niveau;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mayl on 13/05/2014.
 */
public class NiveauTest {

    private Niveau n;
    @Before
    public void setUp(){
        n = Niveau.UN;
    }


    @Test
    public void testGetName(){
        assertEquals( n.getName(), "UN");
        n =  n.suivant();
        assertEquals( n.getName(), "DEUX");
        n =  n.suivant();
        assertEquals( n.getName(), "TROIS");
        n =  n.suivant();
        assertEquals( n.getName(), "QUATRE");
        n =  n.suivant();
        assertEquals( n.getName(), "CINQ");
        n =  n.suivant();
        assertEquals( n.getName(), "CINQ");
    }

    @Test
    public void testGetNbLettres() throws Exception {
        assertEquals( n.getNbLettres(), 5);
        n =  n.suivant();
        assertEquals( n.getNbLettres(), 10);
        n =  n.suivant();
        assertEquals( n.getNbLettres(), 15);
        n =  n.suivant();
        assertEquals( n.getNbLettres(), 20);
        n =  n.suivant();
        assertEquals( n.getNbLettres(), 26);

    }

    @Test
    public void testGetAlphabet() throws Exception {
        assertEquals( n.getAlphabet(), "ABCDE");
        n =  n.suivant();
        assertEquals( n.getAlphabet(), "ABCDEFGHIJ");
        n =  n.suivant();
        assertEquals( n.getAlphabet(), "ABCDEFGHIJKLMNO");
        n =  n.suivant();
        assertEquals( n.getAlphabet(), "ABCDEFGHIJKLMNOPQRST");
        n =  n.suivant();
        assertEquals( n.getAlphabet(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    }

    @Test
    public void testGetObjectif() throws Exception {
        assertEquals( n.getObjectif(), 30);
        n =  n.suivant();
        assertEquals( n.getObjectif(), 60);
        n =  n.suivant();
        assertEquals( n.getObjectif(), 90);
        n =  n.suivant();
        assertEquals( n.getObjectif(), 150);
        n =  n.suivant();
        assertEquals( n.getObjectif(), 200);
    }

    @Test
    public void testGetById() throws Exception {
        assertEquals( Niveau.getById(1), n);
        n =  n.suivant();
        assertEquals( Niveau.getById(2), n);
        n =  n.suivant();
        assertEquals( Niveau.getById(3), n);
        n =  n.suivant();
        assertEquals( Niveau.getById(4), n);
        n =  n.suivant();
        assertEquals( Niveau.getById(5), n);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals( n.toString(), "1");
        n =  n.suivant();
        assertEquals( n.toString(), "2");
        n =  n.suivant();
        assertEquals( n.toString(), "3");
        n =  n.suivant();
        assertEquals( n.toString(), "4");
        n =  n.suivant();
        assertEquals( n.toString(), "5");

    }

    @Test
    public void testSuivant() throws Exception {
        assertEquals( n.getName(), "UN");
        n =  n.suivant();
        assertEquals( n.getName(), "DEUX");
        n =  n.suivant();
        assertEquals( n.getName(), "TROIS");
        n =  n.suivant();
        assertEquals( n.getName(), "QUATRE");
        n =  n.suivant();
        assertEquals( n.getName(), "CINQ");
        n =  n.suivant();
        assertEquals( n.getName(), "CINQ");
    }
}
