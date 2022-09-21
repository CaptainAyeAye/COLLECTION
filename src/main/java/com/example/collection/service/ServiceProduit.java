package com.example.collection.service;

import com.example.collection.DAO.DAOfactory;
import com.example.collection.metier.Produit;

import java.sql.SQLException;

public class ServiceProduit {

    public boolean deleteArticle(Produit article) {
        return DAOfactory.getProduitDAO().delete(article);
    }

    public boolean updateArticle(Produit article) {
        return DAOfactory.getProduitDAO().update(article);
    }


    public boolean insertArticle(Produit article) throws SQLException {
        return DAOfactory.getProduitDAO().insert(article);
    }
}
