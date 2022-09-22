package com.example.collection.Controller;

import com.example.collection.DAO.ProduitDAO;
import com.example.collection.DAO.TypeDAO;
import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import com.example.collection.outils.Affichage;
import com.example.collection.service.ServiceType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionTypeController {
    @FXML
    private MenuApp menuApp;
    private Type typeSelected;

    private ArrayList<Caracteristique> listCaracteristique;

    @FXML
    private TableColumn<Type, Integer> idcolumn;

    @FXML
    private TableColumn<Type, String> libellecolumn;

    @FXML
    private TableView<Type> typeTable;
    @FXML
    private GridPane detailsShow;
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
    private Label labelRepId;

    @FXML
    private Label labelRepLibelle;

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
    private ServiceType serviceType;

@FXML
    private void initialize(){

        List<Type> typeList = new ArrayList<>();
        typeList = TypeDAO.getListType();
        Affichage.afficherType(typeList);

        typeTable.setItems(FXCollections.observableArrayList(typeList));

    //listCaracteristique.add(FXCollections.observableArrayList(serviceType.getCaracByType(typeSelected)));

        idcolumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        libellecolumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());

    typeTable.getSelectionModel().selectedItemProperty()
            .addListener((observableValue, oldValue, newValue) -> afficherDetails(newValue));

    detailDisable(false);
    }

    private void afficherDetails(Type type) {

            labelRepId.setText(String.valueOf(type.getId()));
            labelRepLibelle.setText(type.getLibelle());

            labelRepCar1.setText(type.getCaracteristiquesType().get(0).toString());
            /*labelCar2.setText(type.getCaracteristiquesType().get(2).toString());
            labelRepCar2.setText(type.getCaracteristiquesType().get(3).toString());
            labelCar3.setText(type.getCaracteristiquesType().get(4).toString());
            labelRepCar3.setText(type.getCaracteristiquesType().get(5).toString());
            labelCar4.setText(type.getCaracteristiquesType().get(6).toString());
            labelRepCar4.setText(type.getCaracteristiquesType().get(7).toString());
            labelCar5.setText(type.getCaracteristiquesType().get(8).toString());
            labelRepCar5.setText(type.getCaracteristiquesType().get(9).toString());
            labelCar6.setText(type.getCaracteristiquesType().get(10).toString());
            labelRepCar6.setText(type.getCaracteristiquesType().get(11).toString());
            if (type.getCaracteristiquesType().size() > 13) {
                labelCar7.setText(type.getCaracteristiquesType().get(12).toString());
                labelRepCar7.setText(type.getCaracteristiquesType().get(13).toString());
            } else {
                labelCar7.setText(" ");
                labelRepCar7.setText(" ");
            }
            if (type.getCaracteristiquesType().size() > 15) {
                labelCar8.setText(type.getCaracteristiquesType().get(14).toString());
                labelRepCar8.setText(type.getCaracteristiquesType().get(15).toString());
            } else {
                labelCar8.setText(" ");
                labelRepCar8.setText(" ");
            }
            if (type.getCaracteristiquesType().size() > 17) {
                labelCar9.setText(type.getCaracteristiquesType().get(16).toString());
                labelRepCar9.setText(type.getCaracteristiquesType().get(17).toString());
            } else {
                labelCar9.setText(" ");
                labelRepCar9.setText(" ");
            }
            if (type.getCaracteristiquesType().size() > 19) {
                labelCar10.setText(type.getCaracteristiquesType().get(18).toString());
                labelRepCar10.setText(type.getCaracteristiquesType().get(19).toString());
            } else {
                labelCar10.setText(" ");
                labelRepCar10.setText(" ");
            }*/
            detailDisable(true);


        }
    public void detailDisable(boolean bool) {
        detailsShow.setVisible(bool);
    }


    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }


    @FXML
    public void ajouter() {

        typeSelected = null;
        Type type = new Type();
        menuApp.ajouterModifierType(type, "Ajouter un type");
    }


    @FXML
    public void supprimer() {

        typeSelected = typeTable.getSelectionModel().getSelectedItem();
        if (typeSelected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.YES);
            alert.getButtonTypes().add(ButtonType.NO);
            alert.setTitle("Fichier modifié");
            alert.setContentText("Voulez vous supprimer le fichier ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                serviceType.deleteType(typeSelected);
                //filterArticle();

            }
        }
    }

    @FXML
    public void modifier() {

        typeSelected = typeTable.getSelectionModel().getSelectedItem();
        menuApp.ajouterModifierType(typeSelected, "Modifier un type");


        /* TEST
         Type type = new Type();
        type.setId(1);
        type.setLibelle_type("pouet");
        filterArticle();
        */

    }

}
