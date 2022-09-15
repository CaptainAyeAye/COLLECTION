package com.example.collection.DAO;

import com.example.collection.metier.LigneProduit;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import com.example.collection.outils.OutilIsInteger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeDAO extends DAO<Type, Type> {

    private static final Connection connexion = CollectionConnect.getInstance();

    public TypeDAO(Connection connexion) {
super(connexion);
    }

    @Override
    public Type getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Type> getAll() {
        return null;
    }

    @Override
    public ArrayList<Type> getLike(Type objet) {
        return null;
    }

    @Override
    public boolean insert(Type objet) {
        return false;
    }

    @Override
    public boolean update(Type object) {
        return false;
    }

    @Override
    public boolean delete(Type object) {
        return false;
    }

    public static Produit getObjecType(int id){
        Produit produit = new Produit();
        ResultSet rs;
        String procedureStockee = "{call get_object_type (?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setInt(1, id);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                produit.setDescription(rs.getString(1));
                produit.setType(rs.getString(2));
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return produit;
    }


    public static int getIdType(Produit produit, String procedureStockee, int idType) {
        ResultSet rs;
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, produit.getType());

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                idType = rs.getInt(1);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return idType;
    }


   /* public static void remplirSchema(int idType, Produit schema) {
        String procedureStockee;
        ResultSet rs;

       procedureStockee = "{call dbo.get_caracs_object (?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setInt(1, idType);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                schema.addCaracteristiques(rs.getString(1));
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifierType(Type newProduit) {

        int idObject = newProduit.getId();
        Produit ancienProduit = getIdType(idType);
        int i = 0;
        LigneProduit oldLigne;
        LigneProduit newLigne;

        if (ancienProduit.getDescription() != newProduit.getLibelle()) {
            modifierDescription(newProduit);
        }

        for (i = 0; i < newProduit.getCaracteristiques().size(); i = i + 2) {
            newLigne = new LigneProduit();
            oldLigne = new LigneProduit();
            newLigne.setIdObjet(idObject);
            newLigne.setLibelleCaracteristique(newProduit.getCaracteristiques().get(i).toString());
            oldLigne.setIdObjet(idObject);
            oldLigne.setLibelleCaracteristique(ancienProduit.getCaracteristiques().get(i).toString());


            if (newLigne == null) {

            } else if (!OutilIsInteger.isNotInteger(newProduit.getCaracteristiques().get(i).toString())) {
                System.out.println("Modification : entree de " + newProduit.getCaracteristiques().get(i + 1).toString() + " index " + i + " en double");
                newLigne.setValeur(Double.parseDouble(newProduit.getCaracteristiques().get(i + 1).toString()));
            } else {
                System.out.println("Modification : entree de " + newProduit.getCaracteristiques().get(i + 1).toString() + " index " + i + " en varchar");
                newLigne.setTexte(newProduit.getCaracteristiques().get(i + 1).toString());
            }

            if (newLigne != oldLigne) {
                LigneProduitDAO.modifierLigne(newLigne);
            }
        }
    }*/
}
