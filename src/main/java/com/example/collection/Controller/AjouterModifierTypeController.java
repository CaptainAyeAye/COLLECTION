package com.example.collection.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AjouterModifierTypeController {

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @FXML
    public void abandonner() {
        dialogStage.close();

    }
}
