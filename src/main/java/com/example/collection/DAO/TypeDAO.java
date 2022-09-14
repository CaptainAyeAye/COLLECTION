package com.example.collection.DAO;

import com.example.collection.metier.Produit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TypeDAO {

    private static final Connection connexion = CollectionConnect.getInstance();

    public static Produit getObjecType(int id){
        Produit produit = new Produit();
        ResultSet rs;
        String procedureStockee = "{call get_object_type (?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setInt(1, id);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                produit.setDescription(rs.getString(1));
                produit.setType(rs.getString(2));
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return produit;
    }


    public static int getIdType(Produit produit, String procedureStockee, int idType) {
        ResultSet rs;
        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setString(1, produit.getType());

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                idType = rs.getInt(1);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return idType;
    }


    public static void remplirSchema(int idType, Produit schema) {
        String procedureStockee;
        ResultSet rs;

        procedureStockee = "{call dbo.get_caracs_object (?)}";

        try (CallableStatement cStmt = connexion.prepareCall(procedureStockee)) {

            cStmt.setInt(1, idType);

            cStmt.execute();
            rs = cStmt.getResultSet();

            while (rs.next()) {
                schema.addCaracteristiques(rs.getString(1));
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
