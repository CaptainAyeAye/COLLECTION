package com.example.collection;

import com.example.collection.Controller.GestionObjetController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApp extends Application {

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





}

