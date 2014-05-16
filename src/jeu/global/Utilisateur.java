package jeu.global;

import jeu.global.couleurs.Couleurs;
import jeu.global.difficultes.Niveau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Représente un utilisateur
 *
 * @author Jean-Christophe Isoard
 */
public class Utilisateur {
    private String nom;
    private Niveau niveau;
    private Collection<Couleurs> couleursPreferees;
    private int couleursChoisies;
    private String icone;
    private Map<Niveau, Integer> meilleursScores;

    /**
     * Construit un utilisateur configuré par défaut
     *
     */
    public Utilisateur(String icone) {
        // création d'un utilisateur
        this.nom = "";
        this.couleursPreferees = new ArrayList<Couleurs>();
        this.meilleursScores = new HashMap<>();
        this.couleursChoisies = 0;
        this.icone = icone;
        this.niveau = Niveau.UN;
    }

    /**
     * Renvoie les couleurs préférées de l'utilisateur
     *
     * @return les couleurs que l'utilisateur à choisi dans le menu
     */
    public Collection<Couleurs> getCouleursPreferees() {
        return couleursPreferees;
    }

    /**
     * Change les couleurs préférées de l'utilisateur
     *
     * @param couleursPreferees une collection de Couleurs
     */
    public void setCouleursPreferees(Collection<Couleurs> couleursPreferees) {
        this.couleursChoisies = 0;
        this.couleursPreferees = couleursPreferees;
    }

    /**
     * Renvoie la paire de couleur (un objet Couleurs) que l'utilisateur a choisi d'afficher
     *
     * @return Couleurs les couleurs que l'utilisateur veux utiliser
     */
    public Couleurs getCouleursChoisies() {
        if (couleursPreferees.isEmpty()) {
            return Couleurs.BLEUBLANC;
        }
        return (Couleurs) (couleursPreferees.toArray())[couleursChoisies];
    }

    /**
     * Permet de changer les couleurs choisies par une autre dans la liste des couleurs préférées de l'utilisateur
     */
    public void couleursSuivantes() {
        if (++couleursChoisies >= couleursPreferees.toArray().length) {
            couleursChoisies = 0;
        }
    }

    /**
     * Récupére le niveau de l'utilisateur
     *
     * @return Niveau
     */
    public Niveau getNiveau() {
        return niveau;
    }

    /**
     * Change le niveau de difficulté de l'utilisateur
     *
     * @param niveau enum Niveau
     */
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    /**
     * Augmente d'un niveau le niveau de l'utilisateur
     */
    public void niveauSuivant() {
        niveau = niveau.suivant();
    }

    /**
     * Retourne la liste des scores enregistré à l'utilisateur
     * @return Map Niveau(enum) <-> Valeur(int)
     */
    public Map<Niveau, Integer> getMeilleursScores() {
        return meilleursScores;
    }

    /**
     * Remplace la Map des meilleur par celle passée en paramétre
     * @param meilleursScores la map Niveau(enum)<->Valeur(int) à entrer
     */
    public void setMeilleursScores(Map<Niveau, Integer> meilleursScores) {
        this.meilleursScores = meilleursScores;
    }

    /**
     * Renvoie l'identifiant (généralement un prénom)
     *
     * @return String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Change le nom de l'utilisateur
     * @param nom le nouveau nom à lui donner
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie le nom de l'icone qui a été choisie dans le dossier des icones
     *
     * @return String
     */
    public String getIcone() {
        return icone;
    }

    /**
     * Change le nom de l'icone de l'utilisateur
     *
     * @param icone String
     */
    public void setIcone(String icone) {
        this.icone = icone;
    }
}
