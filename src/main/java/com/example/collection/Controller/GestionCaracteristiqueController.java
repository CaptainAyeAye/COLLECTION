package com.example.collection.Controller;

import com.example.collection.DAO.CaracteristiqueDAO;
import com.example.collection.DAO.TypeDAO;
import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import com.example.collection.outils.Affichage;
import com.example.collection.service.ServiceCaracteristique;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionCaracteristiqueController {

    private Caracteristique caracteristiqueselected;

    @FXML
    private TableView<Caracteristique> caracteristiqueTable;

    @FXML
    private MenuApp menuApp;

    private ServiceCaracteristique serviceCaracteristique;
    @FXML
    private TableColumn<Caracteristique, Integer> idcolumn;

    @FXML
    private TableColumn<Caracteristique, String> libellecolumn;

    @FXML
    private void initialize(){

        List<Caracteristique> caracteristiqueList = new ArrayList<>();
        caracteristiqueList = CaracteristiqueDAO.getListCaracteristique();
        Affichage.afficherCaracteristique(caracteristiqueList);

        caracteristiqueTable.setItems(FXCollections.observableArrayList(caracteristiqueList));

        //listCaracteristique.add(FXCollections.observableArrayList(serviceType.getCaracByType(typeSelected)));

        idcolumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        libellecolumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
    }

    /*@FXML
    public void ajouter() {

        caracteristiqueselected = null;
        Caracteristique caracteristique = new Caracteristique();
        menuApp.ajouterModifierCaracteristique(caracteristique, "Ajouter un type");
        //serviceArticle.insertArticle(articleSelectionner);
    }*/

    @FXML
    public void supprimer() {

        caracteristiqueselected = caracteristiqueTable.getSelectionModel().getSelectedItem();
        if (caracteristiqueselected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.YES);
            alert.getButtonTypes().add(ButtonType.NO);
            alert.setTitle("Fichier modifié");
            alert.setContentText("Voulez vous supprimer le fichier ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                serviceCaracteristique.deleteCaracteristique(caracteristiqueselected);
                //filterArticle();

            }
        }
    }

    @FXML
    public void modifier() {

        caracteristiqueselected = caracteristiqueTable.getSelectionModel().getSelectedItem();
        menuApp.ajouterModifierCaracteristique(caracteristiqueselected, "Modifier un article");
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
}
