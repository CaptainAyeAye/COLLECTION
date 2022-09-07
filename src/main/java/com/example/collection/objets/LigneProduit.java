package com.example.collection.objets;

public class LigneProduit {

    private int idObjet;
    private String libelleCaracteristique;
    private int valeur;
    private String texte;
    private String libelleReferenciel;

    public int getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(int idObjet) {
        this.idObjet = idObjet;
    }

    public String getLibelleCaracteristique() {
        return libelleCaracteristique;
    }

    public void setLibelleCaracteristique(String libelleCaracteristique) {
        this.libelleCaracteristique = libelleCaracteristique;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getLibelleReferenciel() {
        return libelleReferenciel;
    }

    public void setLibelleReferenciel(String libelleReferenciel) {
        this.libelleReferenciel = libelleReferenciel;
    }
}
