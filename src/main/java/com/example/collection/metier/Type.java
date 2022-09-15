package com.example.collection.metier;

public class Type {

    private int id_type;
    private String libelle_type;

    public void setId(int id_type) {
        this.id_type = id_type;
    }

    public void setLibelle_type(String libelle_type) {
        this.libelle_type = libelle_type;
    }

    public Type(Integer id, String libelle)
    {
        this.id_type = id;
        this.libelle_type = libelle;
    }




    public Integer getId()
    {
        return id_type;
    }

    public String getLibelle()
    {
        return libelle_type;
    }

    @Override
    public String toString()
    {
        return libelle_type;
    }

}
