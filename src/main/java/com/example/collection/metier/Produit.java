package com.example.collection.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Produit {

    private String type;
    private Integer id;
    private String description;
    private List<Object> caracteristiques = new ArrayList();



    public IntegerProperty idProperty()
    {
        return new SimpleIntegerProperty(id);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public StringProperty descriptionProperty()
    {
        return new SimpleStringProperty(description);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


public StringProperty typeProperty(){
        return new SimpleStringProperty(type);
}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getCaracteristiques() {
        return caracteristiques;
    }
    public void addCaracteristiques(Object obj) {
        this.caracteristiques.add(obj);
    }

}
