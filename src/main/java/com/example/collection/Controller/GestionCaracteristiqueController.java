package com.example.collection.Controller;

import com.example.collection.DAO.CaracteristiqueDAO;
import com.example.collection.DAO.DAOfactory;
import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import com.example.collection.outils.Affichage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class GestionCaracteristiqueController {

    private Caracteristique caracteristiqueselected;



    @FXML
    private MenuApp menuApp;
    @FXML
    private TableView<Caracteristique> caracteristiqueTable;
    @FXML
    private TableColumn<Caracteristique, Integer> idcolumn;

    @FXML
    private TableColumn<Caracteristique, String> libellecolumn;

    private void initialize(){

        List<Caracteristique> caracteristiqueList = new ArrayList<>();
        caracteristiqueList = DAOfactory.getCaracteristiqueDAO().getAll();
        Affichage.afficherCaracteristique(caracteristiqueList);

        caracteristiqueTable.setItems(FXCollections.observableArrayList(caracteristiqueList));

        idcolumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        libellecolumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
    }
    @FXML
    /*public void ajouter() {

        caracteristiqueselected = null;
        Caracteristique caracteristique = new Caracteristique();
        menuApp.ajouterModifierCaracteristique(caracteristique, "Ajouter un type");
        //serviceArticle.insertArticle(articleSelectionner);
    }*/

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
}
