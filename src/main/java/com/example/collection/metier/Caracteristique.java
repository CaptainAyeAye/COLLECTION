package com.example.collection.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Caracteristique {

    private Integer id_caracteristique;
    private String libelle_caracteristique;
    private Boolean liste;
    private Boolean num;
    private Boolean texte;


    public Caracteristique(Integer id_caracteristique, String libelle_caracteristique) {
        this.id_caracteristique = id_caracteristique;
        this.libelle_caracteristique = libelle_caracteristique;
    }

    public Caracteristique() {

    }

    public Integer getId_caracteristique() {
        return id_caracteristique;
    }

    public void setId_caracteristique(Integer id_caracteristique) {
        this.id_caracteristique = id_caracteristique;
    }

    public String getLibelle_caracteristique() {
        return libelle_caracteristique;
    }

    public void setLibelle_caracteristique(String libelle_caracteristique) {
        this.libelle_caracteristique = libelle_caracteristique;
    }

    public Boolean getListe() {
        return liste;
    }

    public void setListe(Boolean liste) {
        this.liste = liste;
    }

    public Boolean getNum() {
        return num;
    }

    public void setNum(Boolean num) {
        this.num = num;
    }

    public Boolean getTexte() {
        return texte;
    }

    public void setTexte(Boolean texte) {
        this.texte = texte;
    }
    public IntegerProperty idProperty()
    {
        return new SimpleIntegerProperty(id_caracteristique);}
    public StringProperty libelleProperty(){
        return new SimpleStringProperty(libelle_caracteristique);
    }

}
