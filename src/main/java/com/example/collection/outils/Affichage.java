package com.example.collection.outils;

import com.example.collection.objets.LigneProduit;
import com.example.collection.objets.Produit;

import java.util.List;

public class Affichage {

    public static void afficherLignesProduits(List<LigneProduit> listeLignesProduits) {
        for (LigneProduit ligne : listeLignesProduits) {
            System.out.print(ligne.getIdObjet());
            System.out.print("," + ligne.getLibelleCaracteristique());
            System.out.print("," + ligne.getValeur());
            System.out.print("," + ligne.getTexte());
            System.out.print("," + ligne.getLibelleReferenciel() + "\n");
        }
    }

    public static void afficherProduits(List<Produit> listeProduits){

        System.out.println("Affichage produits");

        for (Produit produit : listeProduits) {
            System.out.print("ID produit : " + produit.getId() + " ");
            System.out.print("Type produit : " + produit.getType() + " ");
            System.out.print("Description produit : " + produit.getDescription() + " ");
            for (Object caracteristique : produit.getCaracteristiques()) {
                System.out.print(caracteristique + " , ");
            }
        }


    }

}
