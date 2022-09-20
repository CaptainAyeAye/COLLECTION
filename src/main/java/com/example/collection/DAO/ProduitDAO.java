package com.example.collection.DAO;

import com.example.collection.metier.LigneProduit;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import com.example.collection.outils.OutilIsInteger;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends DAO<Produit, Produit> {

    private static final Connection connexion = CollectionConnect.getInstance();

    public ProduitDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Produit getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Produit> getAll() {
        return null;
    }

    @Override
    public ArrayList<Produit> getLike(Produit objet) {
        return null;
    }

    @Override
    public boolean insert(Produit objet) {
        //return false;
        ResultSet rs;
        String procedureStockee = "{call check_type_exists (?)}";
        int idType = 0;
        int idObj = 0;
        int i =0;
        int j=0;
        Produit schema = new Produit();

        idType = TypeDAO.getIdType(objet, procedureStockee, idType);

        if (idType !=0) {
            TypeDAO.remplirSchema(idType, schema);

            idObj = insererObject(objet.getDescription(), idType);

            for (Object caracteristiqueSchema : schema.getCaracteristiques()) {

                procedureStockee = "{call dbo.insert_caracteristique_objet (?, ?, ?, ?)}";
                try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
                    cStmt.setInt(1, idObj);
                    cStmt.setObject(2, objet.getCaracteristiques().get(i++));
                    System.out.println("lecture index : "+i);
                    System.out.println("valeur : "+objet.getCaracteristiques().get(i));
                    if(objet.getCaracteristiques().get(i) == null){
                        System.out.println("inserting "+objet.getCaracteristiques().get(i)+" as null");
                        cStmt.setString(3, null);
                        cStmt.setString(4, null);
                        i++;
                    }
                    else if(!OutilIsInteger.isNotInteger(objet.getCaracteristiques().get(i).toString()) && objet.getCaracteristiques().get(i-1).toString()!= "annee"){
                        System.out.println("inserting "+objet.getCaracteristiques().get(i)+" as numeric");
                        cStmt.setInt(3, Integer.parseInt(objet.getCaracteristiques().get(i++).toString()));
                        cStmt.setString(4, null);
                    }
                    else{
                        System.out.println("inserting "+objet.getCaracteristiques().get(i)+" as varchar");
                        cStmt.setString(3, null);
                        cStmt.setObject(4, objet.getCaracteristiques().get(i++));
                    }

                    cStmt.execute();

                }
                catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }
        return true;
    }

    @Override
    public boolean update(Produit object) {
       // return false;
        int idObject = object.getId();
        Produit ancienProduit = getObjectById(idObject);
        int i = 0;
        LigneProduit oldLigne;
        LigneProduit newLigne;

        if (ancienProduit.getDescription() != object.getDescription()) {
            modifierDescription(object);
        }

        for (i = 0; i < object.getCaracteristiques().size(); i = i + 2) {
            newLigne = new LigneProduit();
            oldLigne = new LigneProduit();
            newLigne.setIdObjet(idObject);
            newLigne.setLibelleCaracteristique(object.getCaracteristiques().get(i).toString());
            oldLigne.setIdObjet(idObject);
            oldLigne.setLibelleCaracteristique(ancienProduit.getCaracteristiques().get(i).toString());


            if (newLigne == null) {

            } else if (!OutilIsInteger.isNotInteger(object.getCaracteristiques().get(i).toString())) {
                System.out.println("Modification : entree de " + object.getCaracteristiques().get(i + 1).toString() + " index " + i + " en double");
                newLigne.setValeur(Double.parseDouble(object.getCaracteristiques().get(i + 1).toString()));
            } else {
                System.out.println("Modification : entree de " + object.getCaracteristiques().get(i + 1).toString() + " index " + i + " en varchar");
                newLigne.setTexte(object.getCaracteristiques().get(i + 1).toString());
            }

            if (newLigne != oldLigne) {
                LigneProduitDAO.modifierLigne(newLigne);
            }
        }
        return true;
    }

    @Override
    public boolean delete(Produit object) {
        //return false;
        ResultSet rs;
        String procedureStockee = "{call dbo.delete_objet (?)}";
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
            cStmt.setInt(1, object.getId());
            cStmt.execute();
            return true;
        }  catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


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

    } //mettre dans get all


    public static Produit getObjectById(int idObj){

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



    private static void modifierDescription(Produit newProduit) {

        String procedureStockee;
        ResultSet rs;

        procedureStockee = "{call modify_ligne (?, ?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {
            cStmt.setInt(1, newProduit.getId());
            cStmt.setString(2, newProduit.getDescription());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


   /*  //à appeller*/

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
    } //pas toucher besoin pour insert
}
