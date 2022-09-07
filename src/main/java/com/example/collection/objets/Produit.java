package com.example.collection.objets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Produit {

    private String type;
    private int id;
    private String description;
    private List<Object> caracteristiques = new ArrayList();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
