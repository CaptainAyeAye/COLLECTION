package com.example.collection.DAO;

import com.example.collection.metier.LigneProduit;
import com.example.collection.metier.Produit;
import com.example.collection.outils.OutilIsInteger;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    private static final Connection connexion = CollectionConnect.getInstance();


    public static List<Produit> getProduits() {

        List<LigneProduit> lignesProduits = new ArrayList<>();
        lignesProduits = LigneProduitDAO.getLignesProduits();

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
                produitActuel = TypeDAO.getObjecType(ligne.getIdObjet());
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


    public Produit getObjectById(int idObj){

        List<LigneProduit> lignesProduits = new ArrayList<>();
        lignesProduits = LigneProduitDAO.getLignesProduits();

        Produit produit = new Produit();

        int idActuel = 0;
        Produit produitActuel = new Produit();
        boolean first = false;

        for (LigneProduit ligne : lignesProduits) {
            if(!first){
                produit.setId(ligne.getIdObjet());
                produit = TypeDAO.getObjecType(ligne.getIdObjet());
                idActuel= ligne.getIdObjet();
                first = true;
            }

            produit.addCaracteristiques(ligne.getLibelleCaracteristique());
            if(ligne.getLibelleReferenciel()!=null){
                produit.addCaracteristiques(ligne.getLibelleReferenciel());
            }
            else if(ligne.getTexte()!=null){
                produit.addCaracteristiques(ligne.getTexte());
            }
            else if(ligne.getValeur()!=0){
                produit.addCaracteristiques(ligne.getValeur());
            }
            else{
                produit.addCaracteristiques("null");
            }

        }

        return produit;

    }
/*
    public void modifierObject(Produit newProduit){

        int idObject = newProduit.getId();
        Produit ancienProduit = this.getObjectById(idObject);
        int i=0;
        Object oldLigne;

        for(Object newLigne : newProduit.getCaracteristiques()){
            oldLigne= ancienProduit.getCaracteristiques().get(i++);
            if (newLigne != oldLigne){
                this.modifierLigne(newLigne);
            }
        }

        // si description change, methode changer description

    }
*/

    public void supprimerObject(int idObj){
        ResultSet rs;
        String procedureStockee = "{call dbo.delete_objet (?)}";
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {
            cStmt.setInt(1, idObj);
            cStmt.execute();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void AjouterProduit(Produit produit){

        ResultSet rs;
        String procedureStockee = "{call check_type_exists (?)}";
        int idType = 0;
        int idObj = 0;
        int i =0;
        int j=0;
        Produit schema = new Produit();

        idType = TypeDAO.getIdType(produit, procedureStockee, idType);

        if (idType !=0) {
            TypeDAO.remplirSchema(idType, schema);

            idObj = insererObject(produit.getDescription(), idType);

            for (Object caracteristiqueSchema : schema.getCaracteristiques()) {

                procedureStockee = "{call dbo.insert_caracteristique_objet (?, ?, ?, ?)}";
                try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
                    cStmt.setInt(1, idObj);
                    cStmt.setObject(2, produit.getCaracteristiques().get(i++));
                    System.out.println("lecture index : "+i);
                    System.out.println("valeur : "+produit.getCaracteristiques().get(i));
                    if(produit.getCaracteristiques().get(i) == null){
                        cStmt.setString(3, null);
                        cStmt.setString(4, null);
                        i++;
                    }
                    else if(!OutilIsInteger.isNotInteger(produit.getCaracteristiques().get(i).toString()) && produit.getCaracteristiques().get(i-1).toString()!= "annee"){
                        System.out.println("inserting "+produit.getCaracteristiques().get(i)+" as numeric");
                        cStmt.setInt(3, Integer.parseInt(produit.getCaracteristiques().get(i++).toString()));
                        cStmt.setString(4, null);
                    }
                    else{
                        System.out.println("inserting "+produit.getCaracteristiques().get(i)+" as varchar");
                        cStmt.setString(3, null);
                        cStmt.setObject(4, produit.getCaracteristiques().get(i++));
                    }

                    cStmt.execute();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static int insererObject(String description, int idType) {
        String procedureStockee;
        ResultSet rs;
        int idObj = 0;

        procedureStockee = "{call dbo.insert_object (?, ?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, description);
            cStmt.setInt(2, idType);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                idObj = rs.getInt(1);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return idObj;
    }
}
