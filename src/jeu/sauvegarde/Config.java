/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.sauvegarde;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 *
 * @author Thomas
 */
public class Config {
    /*
     * Variables "final static" pour la création et la récupération du fichier de sauvegarde
     */

    public final static String FILE_NAME = "users.xml";
    public final static Element RACINE_UTILISATEURS = new Element("utilisateur");
    public final static Document UTILISATEUR = new Document(RACINE_UTILISATEURS);
    public final static String COULEUR_UTILISATEUR = "Couleurs";
    public final static String SCORE_UTILISATEUR = "Scores";
    public final static String SCORE_VALUE = "valeur";
    public final static String ICONE_UTILISATEUR = "icone";
}
