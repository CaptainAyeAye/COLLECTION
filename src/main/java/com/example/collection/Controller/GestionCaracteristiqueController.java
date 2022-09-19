package com.example.collection.Controller;

import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Type;
import javafx.fxml.FXML;

public class GestionCaracteristiqueController {

    private Caracteristique caracteristiqueselected;

    @FXML
    private MenuApp menuApp;

    @FXML
    public void ajouter() {

        caracteristiqueselected = null;
        Caracteristique caracteristique = new Caracteristique();
        menuApp.ajouterModifierCaracteristique(caracteristique, "Ajouter un type");
        //serviceArticle.insertArticle(articleSelectionner);
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
}
