package jeu.intro;

import jeu.configuration.AssistantUtilisateur;
import jeu.intro.Intro;

/**
 * Classe principale
 * Lance l'assistant utilisateur pour la selection/creation d'un nouvel utilisateur
 *
 */
public class LancementIntro {
    public static void main(String args[]) {
        try{
            new Intro();
        }
        catch(Exception e){System.out.print(e);}
    }
}
