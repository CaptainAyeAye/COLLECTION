package com.example.collection.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class CaracteristiqueDAO {

    private static final Connection connexion = CollectionConnect.getInstance();

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
