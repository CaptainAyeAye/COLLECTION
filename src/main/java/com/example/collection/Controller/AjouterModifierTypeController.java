package com.example.collection.Controller;

import com.example.collection.DAO.DAOfactory;
import com.example.collection.DAO.TypeDAO;
import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Type;
import com.example.collection.outils.Affichage;
import com.example.collection.service.ServiceType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterModifierTypeController {

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }

    private MenuApp menuApp;

    private Type type;

    private Stage dialogStage;

    private ServiceType serviceType;

    @FXML
    private TextField txtType;


    @FXML
    private ListView<Caracteristique> listviewCaracteristique;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @FXML
    public void initialize(){
        serviceType = new ServiceType();
    }

    @FXML
    public void ajouterCaracteristique () {

        menuApp.ajouterModifierCaracteristiqueType(type);
       listviewCaracteristique.setItems(FXCollections.observableArrayList(type.getCaracteristiquesType()));

    }

    @FXML
    public void abandonner() {
        dialogStage.close();

    }

    public void  confirmer(){

        type.setLibelle_type(txtType.getText());

        if (dialogStage.getTitle().equals("Modifier article")) {
            serviceType.updateType(this.type);
        }
        if (dialogStage.getTitle().equals("Ajouter article")) {
            serviceType.insertType(this.type);
        }

        dialogStage.close();


    }





    public void remplir(){

        if(type.getLibelle() != null){

            txtType.setText(type.getLibelle());
            txtType.setDisable(true);

            listviewCaracteristique.setItems(FXCollections.observableArrayList(type.getCaracteristiquesType()));

        }

    }


}
