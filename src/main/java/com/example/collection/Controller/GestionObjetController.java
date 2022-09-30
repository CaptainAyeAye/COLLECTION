package com.example.collection.Controller;

//import com.example.collection.DAO.InteractionBDD;
import com.example.collection.DAO.DAOfactory;
import com.example.collection.DAO.ProduitDAO;
import com.example.collection.MenuApp;
import com.example.collection.service.ServiceProduit;
import com.example.collection.metier.Produit;
import com.example.collection.outils.Affichage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionObjetController {

    @FXML
    private GridPane detailsShow;
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Produit> articleTable;

    @FXML
    private TableColumn<Produit, String> typeColumn;

    @FXML
    private TableColumn<Produit, Integer> idColumn;

    @FXML
    private TableColumn<Produit, String> descriptionColumn;

    @FXML
    private MenuApp menuApp;


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
    private Label labelDescription;
    @FXML
    private Label labelId;
    @FXML
    private Label labelType;

    @FXML
    private Label labelRepDescription;

    @FXML
    private Label labelRepId;

    @FXML
    private Label labelRepType;

    @FXML
    private Label labelRepCar1;

    @FXML
    private Label labelRepCar2;

    @FXML
    private Label labelRepCar3;

    @FXML
    private Label labelRepCar4;

    @FXML
    private Label labelRepCar5;

    @FXML
    private Label labelRepCar6;

    @FXML
    private Label labelRepCar7;

    @FXML
    private Label labelRepCar8;

    @FXML
    private Label labelRepCar9;

    @FXML
    private Label labelRepCar10;


    private Produit produitselected;



    @FXML
    private ServiceProduit serviceProduit;



    @FXML
    private void initialize() {
        ///affichage dans la table///
        ArrayList<Produit> listeProduits2 = new ArrayList<>();
        listeProduits2 = DAOfactory.getProduitDAO().getAll();
        Affichage.afficherProduits(listeProduits2);

        articleTable.setItems(FXCollections.observableArrayList(listeProduits2));

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        ///affichage du details///
        articleTable.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldValue, newValue) -> afficherDetails(newValue));

        detailDisable(false);

    }

    public void detailDisable(boolean bool) {
        detailsShow.setVisible(bool);
    }

    private void afficherDetails(Produit produit) {



        labelRepId.setText(String.valueOf(produit.getId()));
        labelRepDescription.setText(produit.getDescription());
        labelRepType.setText(produit.getType());
        labelCar1.setText(produit.getCaracteristiques().get(0).toString());
        labelRepCar1.setText(produit.getCaracteristiques().get(1).toString());
        labelCar2.setText(produit.getCaracteristiques().get(2).toString());
        labelRepCar2.setText(produit.getCaracteristiques().get(3).toString());
        labelCar3.setText(produit.getCaracteristiques().get(4).toString());
        labelRepCar3.setText(produit.getCaracteristiques().get(5).toString());
        labelCar4.setText(produit.getCaracteristiques().get(6).toString());
        labelRepCar4.setText(produit.getCaracteristiques().get(7).toString());
        labelCar5.setText(produit.getCaracteristiques().get(8).toString());
        labelRepCar5.setText(produit.getCaracteristiques().get(9).toString());
        labelCar6.setText(produit.getCaracteristiques().get(10).toString());
        labelRepCar6.setText(produit.getCaracteristiques().get(11).toString());
        if (produit.getCaracteristiques().size() > 13) {
            labelCar7.setText(produit.getCaracteristiques().get(12).toString());
            labelRepCar7.setText(produit.getCaracteristiques().get(13).toString());
        } else {
            labelCar7.setText(" ");
            labelRepCar7.setText(" ");
        }
        if (produit.getCaracteristiques().size() > 15) {
            labelCar8.setText(produit.getCaracteristiques().get(14).toString());
            labelRepCar8.setText(produit.getCaracteristiques().get(15).toString());
        } else {
            labelCar8.setText(" ");
            labelRepCar8.setText(" ");
        }
        if (produit.getCaracteristiques().size() > 17) {
            labelCar9.setText(produit.getCaracteristiques().get(16).toString());
            labelRepCar9.setText(produit.getCaracteristiques().get(17).toString());
        } else {
            labelCar9.setText(" ");
            labelRepCar9.setText(" ");
        }
        if (produit.getCaracteristiques().size() > 19) {
            labelCar10.setText(produit.getCaracteristiques().get(18).toString());
            labelRepCar10.setText(produit.getCaracteristiques().get(19).toString());
        } else {
            labelCar10.setText(" ");
            labelRepCar10.setText(" ");
        }
        detailDisable(true);


    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }


    @FXML
    public void ajouter() {

            produitselected = null;
            Produit produit = new Produit();
            menuApp.ajouterModifierArticle(produit, "Ajouter un article");

        }


    @FXML
    public void supprimer() {

        produitselected = articleTable.getSelectionModel().getSelectedItem();
        if (produitselected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.YES);
            alert.getButtonTypes().add(ButtonType.NO);
            alert.setTitle("Fichier modifié");
            alert.setContentText("Voulez vous supprimer le fichier ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                serviceProduit.deleteArticle(produitselected);
                //filterArticle();

            }
        }
    }

    @FXML
    public void modifier() {
        produitselected = articleTable.getSelectionModel().getSelectedItem();
        menuApp.ajouterModifierArticle(produitselected, "Modifier un article");
    }

}