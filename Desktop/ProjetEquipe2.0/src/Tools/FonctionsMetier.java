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
import java.util.Date;
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
    try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("insert into region values (?,?,?)");
            ps.setInt(1, regCode);
            ps.setInt(2, secCode);
            ps.setString(3, regNom);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public int GetLastCodeRegion() {
    int lastCode=0;
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select max(regCode)as code from region");
            
           ResultSet rs = ps.executeQuery();
            rs.next();
            lastCode = rs.getInt("code") + 1;
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastCode;
        }

    @Override
    public ArrayList<Secteur> GetAllSecteurs() {
    ArrayList<Secteur> lesSecteurs = new ArrayList<>();
     try {  
       
         Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select secCode, secLibelle from secteur");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Secteur sect = new Secteur(rs.getInt("secCode"), rs.getString("secLibelle"));
                lesSecteurs.add(sect);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesSecteurs;    
    }

    @Override
    public int GetCodeSecteur() {
    int lastCodeSecteur=0;
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select max (secCode)as sect from region");
            
           ResultSet rs = ps.executeQuery();
            rs.next();
            lastCodeSecteur = rs.getInt("sect") + 1;
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastCodeSecteur;
    }    

    @Override
    public ArrayList<Region> GetAllRegions() {
    ArrayList<Region> lesRegions = new ArrayList<>();
        try {    
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select regNom from region");
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);
            while(rs.next()){
                Region reg = new Region(rs.getString(1));
                lesRegions.add(reg);
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesRegions;
    }

    @Override
    public void ModifierRegion(String regNom) {
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("update region set (?) where regNom= '"+regNom+"'");
            ps.setString(1, regNom);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.out.println(ps);
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
            PreparedStatement ps = cnx.prepareStatement("select visMatricule, visNom, visPrenom, visAdresse, visCp, visVille, visDateEmbauche, secCode, laboCode  from visiteur");
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next())
            {
                Visiteur vis = new Visiteur(rs.getInt("visMatricule"),rs.getString("visNom"), rs.getString("visPrenom"), rs.getString("visAdresse"), rs.getString("visCp"), rs.getString("visVille"),rs.getDate("visDateEmbauche"), rs.getInt("secCode"), rs.getInt("laboCode"));
                lesVisiteur.add(vis);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesVisiteur;  
    }    

    @Override
    public void ModifierVisiteur(int visMatricule, String visNom, String visVille, String visAdresse, String visCp, int secCode, int laboCode) {
 try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("update visiteur set (?,?,?,?,?,?) where visMatricule ="+ visMatricule);
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

    @Override
    public ArrayList<Visiteur> GetAllVisiteur2() {
ArrayList<Visiteur> lesVisiteur = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select visMatricule, visNom, visPrenom from visiteur");
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next())
            {
                Visiteur vis = new Visiteur(rs.getInt("visMatricule"),rs.getString("visNom"), rs.getString("visPrenom"));
                lesVisiteur.add(vis);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesVisiteur;     }

    
    
}
