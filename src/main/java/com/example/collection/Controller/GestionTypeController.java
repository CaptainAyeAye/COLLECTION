package com.example.collection.Controller;

import com.example.collection.DAO.DAOfactory;
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
import java.sql.SQLOutput;
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
        typeList = DAOfactory.getTypeDAO().getAll();
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
    clear();
    type.setCaracteristiquesType(TypeDAO.getCaracteristiquesTypes(type));

            labelRepId.setText(String.valueOf(type.getId()));
            labelRepLibelle.setText(type.getLibelle());
            labelRepCar1.setText(type.getCaracteristiquesType().get(1).getLibelle_caracteristique().toString());
            labelCar1.setText("Caracteristique 1:");
            labelRepCar2.setText(type.getCaracteristiquesType().get(2).getLibelle_caracteristique().toString());
            labelCar2.setText("Caracteristique 2:");
            labelRepCar3.setText(type.getCaracteristiquesType().get(3).getLibelle_caracteristique().toString());
            labelCar3.setText("Caracteristique 3:");
            labelRepCar4.setText(type.getCaracteristiquesType().get(4).getLibelle_caracteristique().toString());
            labelCar4.setText("Caracteristique 4:");
            if (type.getCaracteristiquesType().size() > 5) {
                labelRepCar5.setText(type.getCaracteristiquesType().get(5).getLibelle_caracteristique().toString());
                labelCar5.setText("Caracteristique 5:");
            }
            if (type.getCaracteristiquesType().size() > 6){
                labelRepCar6.setText(type.getCaracteristiquesType().get(6).getLibelle_caracteristique().toString());
                labelCar6.setText("Caracteristique 6:");
            }
            if (type.getCaracteristiquesType().size() > 7) {
                labelRepCar7.setText(type.getCaracteristiquesType().get(7).getLibelle_caracteristique().toString());
                labelCar7.setText("Caracteristique 7:");
            }
            if (type.getCaracteristiquesType().size() > 8) {
                labelRepCar8.setText(type.getCaracteristiquesType().get(8).getLibelle_caracteristique().toString());
                labelCar8.setText("Caracteristique 8:");
            }
            if (type.getCaracteristiquesType().size() > 9) {
                labelRepCar9.setText(type.getCaracteristiquesType().get(9).getLibelle_caracteristique().toString());
                labelCar9.setText("Caracteristique 9:");
            }
            if (type.getCaracteristiquesType().size() > 10) {
                labelRepCar10.setText(type.getCaracteristiquesType().get(10).getLibelle_caracteristique().toString());
                labelCar10.setText("Caracteristique 10:");
            }
            detailDisable(true);


        }

        public void clear(){

            labelCar1.setText("");
            labelRepCar1.setText("");
            labelCar2.setText("");
            labelRepCar2.setText("");
            labelCar3.setText("");
            labelRepCar3.setText("");
            labelCar4.setText("");
            labelRepCar4.setText("");
            labelCar5.setText("");
            labelRepCar5.setText("");
            labelCar6.setText("");
            labelRepCar6.setText("");
            labelCar7.setText("");
            labelRepCar7.setText("");
            labelCar8.setText("");
            labelRepCar8.setText("");
            labelCar9.setText("");
            labelRepCar9.setText("");
            labelCar10.setText("");
            labelRepCar10.setText("");

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
