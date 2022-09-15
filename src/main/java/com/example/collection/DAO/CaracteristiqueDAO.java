package com.example.collection.DAO;

import com.example.collection.metier.Caracteristique;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CaracteristiqueDAO extends DAO<Caracteristique,Caracteristique> {

    private static final Connection connexion = CollectionConnect.getInstance();

    public CaracteristiqueDAO(Connection connexion) {
    super(connexion);
    }

    @Override
    public Caracteristique getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Caracteristique> getAll() {
        return null;
    }

    @Override
    public ArrayList<Caracteristique> getLike(Caracteristique objet) {
        return null;
    }

    @Override
    public boolean insert(Caracteristique objet) {
        return false;
    }

    @Override
    public boolean update(Caracteristique object) {
        return false;
    }

    @Override
    public boolean delete(Caracteristique object) {
        return false;
    }

    public void supprimerCaracteristique(String carac){

        ResultSet rs;

        String procedureStockee = "{call dbo.delete_caracteristique (?)}";


        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, carac);

            cStmt.execute();
            rs = cStmt.getResultSet();

            //while (rs.next()) {   newLigne.setIdObjet(rs.getInt(1));}
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void creerCaracteristique(String carac){

        ResultSet rs;

        String procedureStockee = "{call dbo.insert_caracteristique (?)}";


        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, carac);

            cStmt.execute();
            rs = cStmt.getResultSet();

            //while (rs.next()) {   newLigne.setIdObjet(rs.getInt(1));}
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}