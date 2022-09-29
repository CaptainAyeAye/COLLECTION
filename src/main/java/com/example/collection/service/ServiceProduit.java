package com.example.collection.service;

import com.example.collection.DAO.DAOfactory;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceProduit {

    private ArrayList<Type> typeFiltrer;


    public ServiceProduit(){
        typeFiltrer = DAOfactory.getTypeDAO().getAll();
    }

    public boolean deleteArticle(Produit article) {
        return DAOfactory.getProduitDAO().delete(article);
    }

    public boolean updateArticle(Produit article) {
        return DAOfactory.getProduitDAO().update(article);
    }


    public boolean insertArticle(Produit article) throws SQLException {
        return DAOfactory.getProduitDAO().insert(article);
    }


    public ArrayList<Type> getTypeFiltrer() {
        return typeFiltrer;
    }


}
