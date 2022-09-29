package com.example.collection.Controller;

import com.example.collection.DAO.CaracteristiqueDAO;
import com.example.collection.DAO.DAOfactory;
import com.example.collection.DAO.TypeDAO;
import com.example.collection.MenuApp;
import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.ListSelectioncaracteristique;
import com.example.collection.metier.Type;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ListSelectionCaracteristiqueController {

    @FXML
    private ListView<Caracteristique> disponibleListView;

    @FXML
    private TextField fieldRecherche;

    @FXML
    private ListView<Caracteristique> selectedListView;

    private ListSelectioncaracteristique listSelectioncaracteristique;

    private MenuApp menuApp;

    private boolean confirmed;


    @FXML
    public void initialize(){

        listSelectioncaracteristique = new ListSelectioncaracteristique();


    }




    private Type type;

    private Stage dialogStage;


    public void setType(Type type) {
        this.type = type;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void filtre(String newValue) {

        listSelectioncaracteristique.filtrerItems(newValue);

    }

    public ListSelectionCaracteristiqueController() {


    }

    public void loadComposant(){


        List<Caracteristique> car = CaracteristiqueDAO.getListCaracteristique();
        ArrayList<Caracteristique> caracteristiques = new ArrayList<>();
        for (Caracteristique caracteristique : car){
            caracteristiques.add(caracteristique);
        }

        List<Caracteristique> carSelect = TypeDAO.getCaracteristiquesTypes(type);
        ArrayList<Caracteristique> selectedCaracteristique = new ArrayList<>();
        for (Caracteristique caracteristique : carSelect){
            selectedCaracteristique.add(caracteristique);
        }


        this.listSelectioncaracteristique.setLists(caracteristiques,selectedCaracteristique);

        disponibleListView.setItems(listSelectioncaracteristique.getDisponibleFiltred());
        selectedListView.setItems(listSelectioncaracteristique.getSelectedFiltred());


        fieldRecherche.textProperty().addListener((observable,oldValue,newValue)->filtre(newValue));

        gestionDoubleClick();
        dragAndDropStart();
        dragAndDropOver();
        dragAndDropDropped();
        multiSearch();


    }

    private void gestionDoubleClick() {
        disponibleListView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2)
                selectOne();
            if(event.getClickCount() == 3) selectAll();

        });

        selectedListView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2)
                unSelectOne();
            if(event.getClickCount() == 3) unSelectAll();

        });

    }

    @FXML
    public void selectOne(){

        if(!disponibleListView.getSelectionModel().getSelectedItems().isEmpty()){

            listSelectioncaracteristique.select(disponibleListView.getSelectionModel().getSelectedItems());
        }

        disponibleListView.getSelectionModel().clearSelection();

    }
    @FXML
    public void unSelectOne(){

        if(!selectedListView.getSelectionModel().getSelectedItems().isEmpty()){

            listSelectioncaracteristique.unSelect(selectedListView.getSelectionModel().getSelectedItems());
        }

        selectedListView.getSelectionModel().clearSelection();

    }
    @FXML
    private void selectAll() {

        //disponibleListView.getSelectionModel().getSelectedItems();
        listSelectioncaracteristique.setAll();
    }

    @FXML
    private void unSelectAll() {

        disponibleListView.getSelectionModel().getSelectedItems();
        listSelectioncaracteristique.unSelectAll();
    }

    private void dragAndDropStart(){

        disponibleListView.setOnDragDetected(dragEvent -> {
            final Dragboard dragBroard = disponibleListView.startDragAndDrop(TransferMode.MOVE);
            final ClipboardContent content = new ClipboardContent();
            content.putString("Selectionner");
            dragBroard.setContent(content);
            dragEvent.consume();
        });

        selectedListView.setOnDragDetected(dragEvent -> {
            final Dragboard dragBroard = selectedListView.startDragAndDrop(TransferMode.MOVE);
            final ClipboardContent content = new ClipboardContent();
            content.putString("Deselectionner");
            dragBroard.setContent(content);
            dragEvent.consume();
        });

    }

    private void dragAndDropOver(){

        disponibleListView.setOnDragOver(dragEvent -> {

            if(dragEvent.getGestureSource() == selectedListView)
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            dragEvent.consume();
        });

        selectedListView.setOnDragOver(dragEvent -> {
            if(dragEvent.getGestureSource() == disponibleListView)
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            dragEvent.consume();
        });

    }

    private void dragAndDropDropped() {
        selectedListView.setOnDragDropped(dragEvent -> {
            if(dragEvent.getGestureSource() == disponibleListView)
                selectOne();
            dragEvent.consume();
        });

        disponibleListView.setOnDragDropped(dragEvent -> {
            if(dragEvent.getGestureSource() == selectedListView)
                unSelectOne();
            dragEvent.consume();
        });
    }

    private void multiSearch(){

        disponibleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
        confirmed = false;
    }


@FXML
    public void enregistrer(){

        ArrayList listSend = new ArrayList();
        List<Caracteristique> tmp = selectedListView.getItems().stream().toList();
         for (Caracteristique caracteristique : tmp){
             listSend.add(caracteristique);
         }


         type.setCaracteristiquesType(listSend);
        AjouterModifierTypeController controller = new AjouterModifierTypeController();
        controller.setType(this.type);



        this.dialogStage.close();
    }

    @FXML
    public void abandonner() {
        confirmed = false;
        dialogStage.close();

    }


}
