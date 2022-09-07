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


    public List<Produit> getProduits() {

        List<LigneProduit> lignesProduits = new ArrayList<>();
        lignesProduits = this.getLignesProduits();

        List<Produit> listeProduits = new ArrayList<>();

        int idActuel = 0;
        String caracteristique;
        Produit produitActuel = new Produit();

        for (LigneProduit ligne : lignesProduits) {

            if(idActuel != ligne.getIdObjet() && idActuel != 0){
                listeProduits.add(produitActuel);
                produitActuel = new Produit();
            }

        caracteristique = ligne.getLibelleCaracteristique();
        caracteristique += " : ";
        if(ligne.getLibelleReferenciel()!=null){
            caracteristique += ligne.getLibelleReferenciel();
        }
        else if(ligne.getTexte()!=null){
            caracteristique += ligne.getTexte();
        }
        else if(ligne.getValeur()!=0){
            caracteristique += ligne.getValeur();
        }
        else{
            caracteristique += "-";
        }
        caracteristique +=", ";
        produitActuel.addCaracteristiques(caracteristique);

        }

        return listeProduits;

    }


}
