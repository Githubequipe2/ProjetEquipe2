/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Laboratoire;
import Entity.Region;
import Entity.Secteur;
import Entity.Visiteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alexk
 */
public class FonctionsMetier implements IMetier
{

    @Override
    public void InsererRegion(int regCode, int secCode, String regNom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetLastCodeRegion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Secteur> GetAllSecteurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetCodeSecteur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Region> GetAllRegions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ModifierRegion(String regNom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String GetNomRegion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void InsererVisiteur(int visMat, String visNom, String visPrenom, String visAdresse, String visCp, String visVille, String visDateEmbauche, int secCode, int laboCode) {
         try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("insert into visiteur values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, visMat );
            ps.setString(2, visNom );
            ps.setString(3, visPrenom);
            ps.setString(4, visAdresse);
            ps.setString(5, visCp);
            ps.setString(6, visVille);
            ps.setString(7, visDateEmbauche);
            ps.setInt(8,secCode);
            ps.setInt(9,laboCode);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public int GetLastMatricule() {
        
        int lastmatricule = 0;
        try {
            Connection cnx = ConnexionBDD.getCnx();
           
            PreparedStatement ps = cnx.prepareStatement("Select max(visMatricule) as mat from visiteur"); //Selectionne la plus grande mat de la table visiteur
            ResultSet rs = ps.executeQuery();
            rs.next();
            lastmatricule = rs.getInt("mat") +1 ;
            rs.close();
           
       
        } catch (SQLException ex) {
           
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return lastmatricule;
    }

    @Override
    public ArrayList<Laboratoire> GetAllLaboratoire() {
      ArrayList<Laboratoire> lesLaboratoire = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select labCode, labNom, labChefvente from labo");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Laboratoire lab = new Laboratoire(rs.getInt("labCode"),rs.getString("labNom"), rs.getString("labChefVente"));
                lesLaboratoire.add(lab);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesLaboratoire;  
    }

    @Override
    public ArrayList<Visiteur> GetAllVisiteur() {

        ArrayList<Visiteur> lesVisiteur = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select visMatricule, visNom, visPrenom, visAdresse, visCp, visVille, visDateEmbauche, secCode, laboCode from visiteur");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Visiteur vis = new Visiteur(rs.getInt("visMatricule"),rs.getString("visNom"), rs.getString("visPrenom"), rs.getString("visAdresse"), rs.getString("visCp"), rs.getString("visVille"),rs.getDate("visDateEmbauche"),rs.getInt("secCode"),rs.getInt("laboCode"));
                lesVisiteur.add(vis);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesVisiteur;  
    }    

    @Override
    public void ModifierVisiteur(String visNom, String visVille, String visAdresse, String visCp, int secCode, int laboCode) {
 try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("update visiteur set (?,?,?,?,?,?)");
            ps.setString(1, visNom);
            ps.setString(2, visVille);
            ps.setString(3, visAdresse);
            ps.setString(4, visCp);
            ps.setInt(5, secCode);
            ps.setInt(6, laboCode);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

    @Override
    public void VisiteurInsererRegion(int visMatricule, String JJMMAA, int regCode, String traRole) {
    try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("insert into visiteur values (?,?,?,?)");
            ps.setInt(1, visMatricule );
            ps.setString(2, JJMMAA );
            ps.setInt(3, regCode);
            ps.setString(4, traRole);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
