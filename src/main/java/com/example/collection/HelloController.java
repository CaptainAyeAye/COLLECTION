package com.example.collection;

import com.example.collection.DAO.InteractionBDD;
import com.example.collection.objets.LigneProduit;
import com.example.collection.outils.Affichage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        InteractionBDD BDD = new InteractionBDD();
        /*List<LigneProduit> listeProduits = new ArrayList<>();
        listeProduits = BDD.getLignesProduits();
        Affichage.afficherLignesProduits(listeProduits);*/
    }


}