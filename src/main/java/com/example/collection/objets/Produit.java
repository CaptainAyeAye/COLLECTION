package com.example.collection.objets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Produit {

    private String nom;
    private List caracteristiques = new ArrayList();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List getCaracteristiques() {
        return caracteristiques;
    }

    public void addCaracteristiques(Object obj) {
        this.caracteristiques.add(obj);
    }
}
