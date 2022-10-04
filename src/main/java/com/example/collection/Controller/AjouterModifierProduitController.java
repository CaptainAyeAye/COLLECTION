package com.example.collection.Controller;

import com.example.collection.MenuApp;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import com.example.collection.service.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AjouterModifierProduitController {
    private Produit produit;

    @FXML
    private Label labelCar1;

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

    @FXML
    private Label labelCar10;

    @FXML
    private Label labelCar11;

    @FXML
    private Label labelCar12;

    @FXML
    private Label labelCar13;

    @FXML
    private TextField txtfild1;

    @FXML
    private TextField txtfild10;

    @FXML
    private TextField txtfild11;

    @FXML
    private TextField txtfild12;

    @FXML
    private TextField txtfild13;

    @FXML
    private TextField txtfild2;

    @FXML
    private TextField txtfild3;

    @FXML
    private TextField txtfild4;

    @FXML
    private TextField txtfild5;

    @FXML
    private TextField txtfild6;

    @FXML
    private TextField txtfild7;

    @FXML
    private TextField txtfild8;

    @FXML
    private TextField txtfild9;

    private Stage dialogStage;

    private ServiceProduit serviceProduit;

    @FXML
    private ComboBox<Type> cbxType;

    private MenuApp menuApp;


    @FXML
    private void initialize(){

        serviceProduit = new ServiceProduit();
        cbxType.setItems(FXCollections.observableArrayList(serviceProduit.getTypeFiltrer()));



    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }



    public void setArticle(Produit produit) {
        ///a voir plutard car pas fini///////

        this.produit = produit;
        if (produit != null) {

            //cbxType.getSelectionModel().select(produit.getType());
            labelCar1.setText(produit.getCaracteristiques().get(1).toString());
            txtfild1.setText(produit.getCaracteristiques().get(2).toString());
            labelCar2.setText(produit.getCaracteristiques().get(3).toString());
            txtfild2.setText(produit.getCaracteristiques().get(4).toString());
            labelCar3.setText(produit.getCaracteristiques().get(5).toString());
            txtfild3.setText(produit.getCaracteristiques().get(6).toString());
            labelCar4.setText(produit.getCaracteristiques().get(7).toString());
            txtfild4.setText(produit.getCaracteristiques().get(8).toString());
            labelCar5.setText(produit.getCaracteristiques().get(9).toString());
            txtfild5.setText(produit.getCaracteristiques().get(10).toString());
            labelCar6.setText(produit.getCaracteristiques().get(11).toString());
            txtfild6.setText(produit.getCaracteristiques().get(12).toString());
            labelCar7.setText(produit.getCaracteristiques().get(13).toString());
            txtfild7.setText(produit.getCaracteristiques().get(14).toString());
            labelCar8.setText(produit.getCaracteristiques().get(15).toString());
            txtfild8.setText(produit.getCaracteristiques().get(16).toString());
            labelCar9.setText(produit.getCaracteristiques().get(17).toString());
            txtfild9.setText(produit.getCaracteristiques().get(18).toString());
            labelCar10.setText(produit.getCaracteristiques().get(19).toString());
            txtfild10.setText(produit.getCaracteristiques().get(20).toString());
            labelCar11.setText(produit.getCaracteristiques().get(21).toString());
            txtfild11.setText(produit.getCaracteristiques().get(22).toString());
            labelCar12.setText(produit.getCaracteristiques().get(23).toString());
            txtfild12.setText(produit.getCaracteristiques().get(24).toString());
            labelCar13.setText(produit.getCaracteristiques().get(25).toString());
            txtfild13.setText(produit.getCaracteristiques().get(26).toString());


        }
    }

    @FXML
    public void abandonner() {
        dialogStage.close();

    }

    public void  confirmer() throws SQLException {

        //produit.setDescription(txtType.getText());

        if (dialogStage.getTitle().equals("Modifier article")) {
            serviceProduit.updateArticle(this.produit);
        }
        if (dialogStage.getTitle().equals("Ajouter article")) {
            serviceProduit.insertArticle(this.produit);
        }

        dialogStage.close();


    }



}
