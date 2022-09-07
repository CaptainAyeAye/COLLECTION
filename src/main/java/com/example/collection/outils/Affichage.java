package com.example.collection.outils;

import com.example.collection.objets.LigneProduit;

import java.util.List;

public class Affichage {

    public static void afficherLignesProduits(List<LigneProduit> listeProduits) {
        for (LigneProduit ligne : listeProduits) {
            System.out.print(ligne.getIdObjet());
            System.out.print("," + ligne.getLibelleCaracteristique());
            System.out.print("," + ligne.getValeur());
            System.out.print("," + ligne.getTexte());
            System.out.print("," + ligne.getLibelleReferenciel() + "\n");
        }
    }

}
