package com.example.collection.service;

import com.example.collection.DAO.DAOfactory;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;

import java.util.ArrayList;

public class ServiceType {

    public boolean deleteType(Type type) {
        return DAOfactory.getTypeDAO().delete(type);
    }


    public boolean updateType(Type type) {
        return DAOfactory.getTypeDAO().update(type);
    }


    public boolean insertType(Type type) {
        return DAOfactory.getTypeDAO().insert(type);
    }


}
