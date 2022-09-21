package com.example.collection.service;

import com.example.collection.DAO.DAOfactory;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Produit;

public class ServiceCaracteristique {


    public boolean deleteCaracteristique(Caracteristique caracteristique) {
        return DAOfactory.getCaracteristiqueDAO().delete(caracteristique);
    }


    public boolean updateCaracteristique(Caracteristique caracteristique){return DAOfactory.getCaracteristiqueDAO().update(caracteristique);

    }



}
