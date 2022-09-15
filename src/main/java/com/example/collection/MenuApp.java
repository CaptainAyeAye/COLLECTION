package com.example.collection;

import com.example.collection.Controller.AjouterModifierProduitController;
import com.example.collection.Controller.GestionObjetController;
import com.example.collection.Controller.GestionTypeController;
import com.example.collection.metier.Produit;
import com.example.collection.metier.Type;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApp extends Application {
    private Stage dialogStage;
    private Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion Objet");
        showArticle();
    }

    private void showArticle() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionObjet.fxml"));
            GridPane menuLayout = loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);

            GestionObjetController controller = loader.getController();
            controller.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterModifierArticle(Produit articleSelected, String titre) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("FormulaireObjet.fxml"));
            AnchorPane ajouterModifierOverview = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(ajouterModifierOverview);

            AjouterModifierProduitController ajouterModifierController = loader.getController();

            ajouterModifierController.setDialogStage(dialogStage);
           // ajouterModifierController.setTitle(titre);

            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showType() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionType.fxml"));
            GridPane menuLayout = loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);

            GestionTypeController controller = loader.getController();
            controller.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* void ajouterModifierType(Type typeSelected, String titre) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("FormulaireType.fxml"));
            AnchorPane ajouterModifierOverview = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(ajouterModifierOverview);

            AjouterModifierTypeController ajouterModifierController = loader.getController();

            ajouterModifierController.setDialogStage(dialogStage);
            // ajouterModifierController.setTitle(titre);

            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

