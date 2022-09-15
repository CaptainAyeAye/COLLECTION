package com.example.collection.Controller;

import com.example.collection.DAO.ProduitDAO;
import com.example.collection.DAO.TypeDAO;
import com.example.collection.MenuApp;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class GestionTypeController {
    @FXML
    private MenuApp menuApp;
    private Type typeSelected;

    @FXML
    private TableView<Type> typeTable;
    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }


    @FXML
    public void ajouter() {

        typeSelected = null;
        //Type type = new Type();
       // menuApp.ajouterModifierArticle(type, "Ajouter un type");
        //serviceArticle.insertArticle(articleSelectionner);
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
                //serviceArticle.supprimer(articleSelected);
              //  TypeDAO.supprimerObject(typeSelected.getId());
                //filterArticle();

            }
        }
    }

    @FXML
    public void modifier() {

        typeSelected = typeTable.getSelectionModel().getSelectedItem();
      //  Type type = new Type();
      //  type.setId(1);
       // type.setLibelle_type("pouet");

        //menuApp.ajouterModifierArticle(produitselected, "Modifier article");
        //TypeDAO.modifierType(type);
        //filterArticle();

    }

}
