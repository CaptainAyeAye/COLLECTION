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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
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
    }

    private void afficherDetails(Type type) {


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
