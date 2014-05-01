/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeu.sauvegarde;

import jeu.global.Utilisateur;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Thomas
 */
public class Restauration {
    public static Collection<Utilisateur> loadUsers() {
        Collection<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(new Utilisateur("User1"));
        utilisateurs.add(new Utilisateur("User2"));
        utilisateurs.add(new Utilisateur("User3"));
        utilisateurs.add(new Utilisateur("User4"));
        utilisateurs.add(new Utilisateur("User5"));
        utilisateurs.add(new Utilisateur("User6"));
        utilisateurs.add(new Utilisateur("User7"));
        utilisateurs.add(new Utilisateur("User8"));
        utilisateurs.add(new Utilisateur("User9"));
        utilisateurs.add(new Utilisateur("User10"));
        utilisateurs.add(new Utilisateur("User11"));
        utilisateurs.add(new Utilisateur("User12"));
        return utilisateurs;
    }
}
