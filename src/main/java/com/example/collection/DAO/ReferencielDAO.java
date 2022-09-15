package com.example.collection.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ReferencielDAO {

    private static final Connection connexion = CollectionConnect.getInstance();

    public void supprimerReferenciel(String ref){

        ResultSet rs;

        String procedureStockee = "{call dbo.delete_referenciel (?)}";


        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, ref);

            cStmt.execute();
            rs = cStmt.getResultSet();

            //while (rs.next()) {   newLigne.setIdObjet(rs.getInt(1));}
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void creerReferenciel(String carac, String ref){

        ResultSet rs;

        String procedureStockee = "{call dbo.insert_referenciel (?, ?)}";


        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, ref);
            cStmt.setString(2, carac);

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
