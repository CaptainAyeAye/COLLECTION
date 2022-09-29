package com.example.collection.metier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;
import java.util.List;

public class ListSelectioncaracteristique {

    private ObservableList<Caracteristique> disponible;
    private ObservableList<Caracteristique> selected;

    private FilteredList<Caracteristique> disponibleFiltred;
    private FilteredList<Caracteristique> selectedFiltred;

    public ListSelectioncaracteristique() {
        this.disponible = FXCollections.observableArrayList();
        this.selected = FXCollections.observableArrayList();

        this.disponibleFiltred = new FilteredList<>(disponible, critere -> true);
        this.selectedFiltred = new FilteredList<>(selected, critere -> true);
    }

    public void setLists(ArrayList<Caracteristique> disponibleItems, ArrayList<Caracteristique> selectedItems) {


        for (Caracteristique elementLu : disponibleItems) {

                this.disponible.add(elementLu);

        }
        for (Caracteristique elementLu : selectedItems) {

                this.selected.add(elementLu);
                this.disponible.remove(elementLu);

        }


    }

    public FilteredList<Caracteristique> getDisponibleFiltred() {
        return disponibleFiltred;
    }

    public FilteredList<Caracteristique> getSelectedFiltred() {
        return selectedFiltred;
    }

    public void select(List<Caracteristique> list) {
        selected.addAll(list);
        disponible.removeAll(list);
    }

    public void unSelect(List<Caracteristique> list) {
        disponible.addAll(list);
        selected.removeAll(list);
    }


    public void setAll() {

        selected.addAll(disponibleFiltred);
        disponible.removeAll(disponibleFiltred);
    }

    public void unSelectAll() {

        disponible.addAll(selectedFiltred);
        selected.removeAll(selectedFiltred);
    }

    public void filtrerItems(String filtre) {
        disponibleFiltred.setPredicate(t -> isContains(filtre, t));
        selectedFiltred.setPredicate(t -> isContains(filtre, t));
    }

    private boolean isContains(String filtre, Caracteristique element) {

        return element.toString().contains(filtre.toUpperCase());

    }
}
