package com.example.collection.Controller;

import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
}