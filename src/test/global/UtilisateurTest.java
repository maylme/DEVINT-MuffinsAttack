package test.global;

import jeu.global.Utilisateur;
import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mayl on 13/05/2014.
 */
public class UtilisateurTest {

    private Utilisateur u;

    @Before
    public void setUp(){
        u = new Utilisateur ("Utilisateur_Test");
    }

    @Test
    public void getCouleursChoisiesTest(){

        //par defaut:
        assertEquals(u.getCouleursChoisies(), Couleurs.BLEUBLANC);
    }

    @Test
    public void getNiveauTest(){
        assertEquals(u.getNiveau(), Niveau.UN);

        u.setNiveau(Niveau.DEUX);
        assertEquals(u.getNiveau(), Niveau.DEUX);

        u.setNiveau(Niveau.TROIS);
        assertEquals(u.getNiveau(), Niveau.TROIS);

        u.setNiveau(Niveau.QUATRE);
        assertEquals(u.getNiveau(), Niveau.QUATRE);

        u.setNiveau(Niveau.CINQ);
        assertEquals(u.getNiveau(), Niveau.CINQ);
    }

    @Test
    public void niveauSuivantTest(){
        assertEquals(u.getNiveau(), Niveau.UN);

        u.niveauSuivant();
        assertEquals(u.getNiveau(), Niveau.DEUX);

        u.niveauSuivant();
        assertEquals(u.getNiveau(), Niveau.TROIS);

        u.niveauSuivant();
        assertEquals(u.getNiveau(), Niveau.QUATRE);

        u.niveauSuivant();
        assertEquals(u.getNiveau(), Niveau.CINQ);

        u.niveauSuivant();
        assertEquals(u.getNiveau(), Niveau.CINQ);
    }


    @Test
    public void setNiveauTest(){
        assertEquals(u.getNiveau(), Niveau.UN);

        u.setNiveau(Niveau.DEUX);
        assertEquals(u.getNiveau(), Niveau.DEUX);

        u.setNiveau(Niveau.TROIS);
        assertEquals(u.getNiveau(), Niveau.TROIS);

        u.setNiveau(Niveau.QUATRE);
        assertEquals(u.getNiveau(), Niveau.QUATRE);

        u.setNiveau(Niveau.CINQ);
        assertEquals(u.getNiveau(), Niveau.CINQ);
    }


}
