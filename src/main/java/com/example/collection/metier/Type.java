package com.example.collection.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Type {

    private int id_type;
    private String libelle_type;


    private List<Object> type = new ArrayList();




    public IntegerProperty idProperty()
    {
        return new SimpleIntegerProperty(id_type);
    }
    public Integer getId()
    {
        return id_type;
    }

    public void setId(int id_type) {
        this.id_type = id_type;
    }


    public StringProperty libelleProperty(){
        return new SimpleStringProperty(libelle_type);
    }

    public List<Object> getCaracteristiquesType() {
        return type;
    }
    public String getLibelle()
    {
        return libelle_type;
    }


    public void setLibelle_type(String libelle_type) {
        this.libelle_type = libelle_type;
    }

}
