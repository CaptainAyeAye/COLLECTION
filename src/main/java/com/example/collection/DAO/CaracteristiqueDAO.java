package com.example.collection.DAO;

import com.example.collection.metier.Caracteristique;
import com.example.collection.metier.Type;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Caracteristique> list = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {
            String strCmd = "SELECT distinct id_caracteristique, libelle_caracteristique from caracteristique";
            ResultSet rs = stmt.executeQuery(strCmd);
            // Couleur couleurLu = new Couleur();
            Caracteristique caracteristiqueLu;
            while (rs.next())
            {
                caracteristiqueLu = new Caracteristique(rs.getInt(1), rs.getString(2));
                list.add(caracteristiqueLu);
            }
            rs.close();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
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

    public static List<Caracteristique> getListCaracteristique(){

        ResultSet rs;
        String Statement = "SELECT * FROM caracteristique";
        List<Caracteristique> listeCaracteristique= new ArrayList<Caracteristique>();
        int i=0;

        try (Statement cStmt = connexion.createStatement()) {

            rs = cStmt.executeQuery(Statement);
            //  rs = cStmt.getResultSet();

            while (rs.next()) {
                listeCaracteristique.add(new Caracteristique());
                listeCaracteristique.get(i).setId_caracteristique(rs.getInt(1));
                listeCaracteristique.get(i++).setLibelle_caracteristique(rs.getString(2));

            }

            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listeCaracteristique;
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
