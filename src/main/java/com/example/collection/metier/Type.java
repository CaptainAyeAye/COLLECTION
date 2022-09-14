package com.example.collection.metier;

public class Type {

    private int id;
    private String libelle;

    public Type(Integer id, String libelle)
    {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId()
    {
        return id;
    }

    public String getLibelle()
    {
        return libelle;
    }

    @Override
    public String toString()
    {
        return libelle;
    }

}
