package com.example.collection.DAO;

import com.example.collection.metier.LigneProduit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LigneProduitDAO {

    private static final Connection connexion = CollectionConnect.getInstance();

    public static List<LigneProduit> getLignesProduits(){
        ResultSet rs;
        ArrayList<LigneProduit> listeLignesProduits = new ArrayList<>();
        String procedureStockee = "{call dbo.get_all_objects ()}";
        System.out.println("Lecture des lignes objets");
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {

                LigneProduit newLigne = new LigneProduit();
                newLigne.setIdObjet(rs.getInt(1));
                newLigne.setLibelleCaracteristique(rs.getString(2));
                newLigne.setValeur(rs.getDouble(3));
                newLigne.setTexte(rs.getString(4));
                newLigne.setLibelleReferenciel(rs.getString(5));

                listeLignesProduits.add(newLigne);

            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listeLignesProduits;

    }
}
