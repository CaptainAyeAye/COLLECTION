package com.example.collection.Controller;

import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.service.ServiceCaracteristique;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterModifierCaracteristiqueController {

    @FXML
    private CheckBox listeBox;
    @FXML
    private CheckBox numBox;
    @FXML
    private CheckBox texteBox;


    private Stage dialogStage;

    private Caracteristique caracteristique;

    private MenuApp menuApp;

    @FXML
    private TextField txtCaracteristique;

    private ServiceCaracteristique serviceCaracteristique;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCaracteristique(Caracteristique caracteristique) {
        this.caracteristique = caracteristique;
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }

@FXML
    private void choixListeBox() {
        if (listeBox.isSelected()) {
            numBox.setSelected(false);
            texteBox.setSelected(false);
        }
    }

    @FXML
    private void choixNumBox() {
        if (numBox.isSelected()) {
            listeBox.setSelected(false);
            texteBox.setSelected(false);
        }
    }

@FXML    private void choixTexteBox() {
        if (texteBox.isSelected()) {
            listeBox.setSelected(false);
            numBox.setSelected(false);
        }
    }


    public void remplir(){

        if(caracteristique.getLibelle_caracteristique() != null){

            txtCaracteristique.setText(caracteristique.getLibelle_caracteristique());
            txtCaracteristique.setDisable(true);
            listeBox.setSelected(caracteristique.getListe());
            numBox.setSelected(caracteristique.getNum());
            texteBox.setSelected(caracteristique.getTexte());


        }

    }

@FXML
    public void  confirmer(){

        caracteristique.setLibelle_caracteristique(txtCaracteristique.getText());
        caracteristique.setListe(listeBox.isSelected());
        caracteristique.setNum(numBox.isSelected());
        caracteristique.setTexte(texteBox.isSelected());

        if (dialogStage.getTitle().equals("Modifier article")) {
            serviceCaracteristique.updateCaracteristique(this.caracteristique);
        }
        if (dialogStage.getTitle().equals("Ajouter article")) {
            serviceCaracteristique.insertCaracteristique(this.caracteristique);
        }

        dialogStage.close();


    }


    @FXML
    public void abandonner() {
        dialogStage.close();

    }
}