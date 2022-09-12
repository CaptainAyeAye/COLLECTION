package com.example.collection.DAO;

import com.example.collection.objets.LigneProduit;
import com.example.collection.objets.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {


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
}
