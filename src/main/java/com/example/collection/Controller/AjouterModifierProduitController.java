package com.example.collection.Controller;

import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Referenciel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.BreakIterator;

public class AjouterModifierProduitController {
    private Produit produit;

    @FXML
    private TextField modifAnnee;

    @FXML
    private ComboBox<?> modifCouleur;

    @FXML
    private TextField modifDentelure;

    @FXML
    private ComboBox<?> modifDevise;

    @FXML
    private ComboBox<?> modifForme;

    @FXML
    private ComboBox<?> modifLangue;

    @FXML
    private TextField modifLibelle;

    @FXML
    private ComboBox<?> modifMarque;

    @FXML
    private ComboBox<?> modifMatiere;

    @FXML
    private ComboBox<?> modifPays;

    @FXML
    private ComboBox<?> modifPeriode;

    @FXML
    private TextField modifPrix;

    @FXML
    private TextField modifTheme;

    @FXML
    private ComboBox<?> modifVariete;

    @FXML
    private Label labelCar1;

    @FXML
    private Label labelCar10;

    @FXML
    private Label labelCar11;

    @FXML
    private Label labelCar12;

    @FXML
    private Label labelCar13;

    @FXML
    private Label labelCar14;

    @FXML
    private Label labelCar2;

    @FXML
    private Label labelCar3;

    @FXML
    private Label labelCar4;

    @FXML
    private Label labelCar5;

    @FXML
    private Label labelCar6;

    @FXML
    private Label labelCar7;

    @FXML
    private Label labelCar8;

    @FXML
    private Label labelCar9;

    private Label titre;

    private Stage dialogStage;



    @FXML
    private void initialize(){

    }

    public void SelectType(){

    }


    public void modifierArticle(Produit produit) {

        ///a voir plutard car pas fini///////

        this.produit = produit;
        if (produit != null) {
            labelCar1.setText(produit.getCaracteristiques().get(1).toString());
            labelCar2.setText(produit.getCaracteristiques().get(2).toString());
            labelCar3.setText(produit.getCaracteristiques().get(3).toString());
            labelCar4.setText(produit.getCaracteristiques().get(4).toString());
            labelCar5.setText(produit.getCaracteristiques().get(5).toString());
            labelCar6.setText(produit.getCaracteristiques().get(6).toString());
            labelCar7.setText(produit.getCaracteristiques().get(7).toString());
            labelCar8.setText(produit.getCaracteristiques().get(8).toString());
            labelCar9.setText(produit.getCaracteristiques().get(9).toString());
            labelCar10.setText(produit.getCaracteristiques().get(10).toString());
            labelCar11.setText(produit.getCaracteristiques().get(11).toString());
            labelCar12.setText(produit.getCaracteristiques().get(12).toString());
            labelCar13.setText(produit.getCaracteristiques().get(13).toString());
            labelCar14.setText(produit.getCaracteristiques().get(14).toString());

        }
    }



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTitle(String titre) {
        this.titre.setText(titre);
    }
}
