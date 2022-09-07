package com.example.collection;

import com.example.collection.DAO.InteractionBDD;
import com.example.collection.objets.LigneProduit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch();
        /*InteractionBDD BDD = new InteractionBDD();
        List<LigneProduit> listeProduits = new ArrayList<>();
        listeProduits = BDD.getLignesProduits();
        for (LigneProduit ligne : listeProduits) {
            System.out.println(ligne.getIdObjet());
            System.out.println(ligne.getLibelleCaracteristique());
            System.out.println(ligne.getValeur());
            System.out.println(ligne.getTexte());
            System.out.println(ligne.getLibelleReferenciel());
        }
        System.out.println("Test");*/
    }
}