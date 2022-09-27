package com.example.collection.DAO;

import com.example.collection.metier.LigneProduit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LigneProduitDAO {

    private static final Connection connexion = CollectionConnect.getInstance();

    public static ArrayList<LigneProduit> getLignesProduits(){
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

    public static void modifierLigne(LigneProduit newLigne) {
        String procedureStockee;
        ResultSet rs;

        procedureStockee = "{call dbo.modifier_ligne_produit (?, ?, ?, ?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
            System.out.println("try : "+ newLigne.getIdObjet()+" "+newLigne.getLibelleCaracteristique()+" "+newLigne.getValeur()+" "+newLigne.getTexte());

            cStmt.setInt(1, newLigne.getIdObjet());
            cStmt.setString(2, newLigne.getLibelleCaracteristique());
            cStmt.setDouble(3, newLigne.getValeur());
            if(newLigne.getLibelleCaracteristique()=="annee"){
                System.out.println("aaaah");
                cStmt.setString(3, null);
            }
            cStmt.setString(4, newLigne.getTexte());
            if(newLigne.getLibelleCaracteristique()=="annee"){
                System.out.println("aaaah");
                cStmt.setString(4, newLigne.getTexte()+"");
            }

            cStmt.execute();
            rs = cStmt.getResultSet();

            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<LigneProduit> getLignesProduitsById(int idObj) {
        ResultSet rs;
        ArrayList<LigneProduit> listeLignesProduits = new ArrayList<>();
        String procedureStockee = "{call dbo.get_object_by_id (?)}";
        System.out.println("Lecture des lignes objets");
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
            cStmt.setInt(1, idObj);
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
