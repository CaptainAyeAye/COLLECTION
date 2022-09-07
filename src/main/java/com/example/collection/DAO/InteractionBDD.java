package com.example.collection.DAO;


import com.example.collection.objets.LigneProduit;
import com.example.collection.objets.Produit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InteractionBDD {

    private static final Connection connexion = BDDconnect.getInstance();


    public List<LigneProduit> getLignesProduits(){
        ResultSet rs;
        ArrayList<LigneProduit> listeLignesProduits = new ArrayList<>();
        String procedureStockee = "{call dbo.get_all_objects ()}";
        System.out.println("Lecture des lignes objets");
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {

                LigneProduit newLigne = new LigneProduit();
                newLigne.setIdObjet(rs.getInt(1));
                newLigne.setLibelleCaracteristique(rs.getString(2));
                newLigne.setValeur(rs.getInt(3));
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

    public Produit getObjecType(int id){
        Produit produit = new Produit();
        ResultSet rs;
        String procedureStockee = "{call get_object_type (?)}";

        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {

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


    public List<Produit> getProduits() {

        List<LigneProduit> lignesProduits = new ArrayList<>();
        lignesProduits = this.getLignesProduits();

        List<Produit> listeProduits = new ArrayList<>();

        int idActuel = 0;
        Produit produitActuel = new Produit();
        int i = -1;

        for (LigneProduit ligne : lignesProduits) {

            if(idActuel != ligne.getIdObjet()){
                //System.out.println("Creation nouvel objet");
                produitActuel = new Produit();
                produitActuel.setId(ligne.getIdObjet());
                listeProduits.add(produitActuel);
                produitActuel = getObjecType(ligne.getIdObjet());
                i++;
                idActuel= ligne.getIdObjet();
                listeProduits.get(i).setDescription(produitActuel.getDescription());
                listeProduits.get(i).setType(produitActuel.getType());
            }

            listeProduits.get(i).addCaracteristiques(ligne.getLibelleCaracteristique());
        //produitActuel.addCaracteristiques(ligne.getLibelleCaracteristique());
            //System.out.println(ligne.getLibelleCaracteristique());
        if(ligne.getLibelleReferenciel()!=null){
            listeProduits.get(i).addCaracteristiques(ligne.getLibelleReferenciel());
        }
        else if(ligne.getTexte()!=null){
            listeProduits.get(i).addCaracteristiques(ligne.getTexte());
        }
        else if(ligne.getValeur()!=0){
            listeProduits.get(i).addCaracteristiques(ligne.getValeur());
        }
        else{
            listeProduits.get(i).addCaracteristiques("null");
        }

        }

        return listeProduits;

    }


}
