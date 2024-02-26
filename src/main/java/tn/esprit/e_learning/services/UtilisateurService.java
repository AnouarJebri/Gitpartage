package tn.esprit.e_learning.services;

import tn.esprit.e_learning.models.CodePromos;
import tn.esprit.e_learning.models.Utilisateur;
import tn.esprit.e_learning.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements IServices{
    private Connection connection;
    public UtilisateurService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur)throws SQLException {
        String req = "INSERT INTO utilisateur (nom,prenom,gender,profession,Enligne,verif,email,mdp) VALUES ( '" + utilisateur.getNom() + "','" + utilisateur.getPrenom() + "'," + utilisateur.isGender() + ",'" + utilisateur.getProfession() + "'," + utilisateur.isEnligne() + "," + utilisateur.isVerif() + ",'" + utilisateur.getEmail() + "','" + utilisateur.getMdp() + "' )";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) throws SQLException {
        String req = "UPDATE utilisateur SET nom = ?, prenom = ?, gender = ?, profession = ?, Enligne = ?, verif = ?, email = ?, mdp = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, utilisateur.getNom());
        ps.setString(2, utilisateur.getPrenom());
        ps.setBoolean(3, utilisateur.isGender());
        ps.setString(4, utilisateur.getProfession());
        ps.setBoolean(5,utilisateur.isEnligne());
        ps.setBoolean(6,utilisateur.isVerif());
        ps.setString(7,utilisateur.getEmail());
        ps.setString(8,utilisateur.getMdp());
        ps.setInt(9,utilisateur.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimerUtilisateur(int id) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public List<Utilisateur> recupererU() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM utilisateur";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setGender(rs.getBoolean("gender"));
            utilisateur.setProfession(rs.getString("profession"));
            utilisateur.setEnligne(rs.getBoolean("EnLigne"));
            utilisateur.setVerif(rs.getBoolean("verif"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setMdp(rs.getString("mdp"));
            utilisateurs.add(utilisateur);
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        String req = "SELECT * FROM utilisateur WHERE email = '" + email + "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(req);
        if (rs.next()) {
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setGender(rs.getBoolean("gender"));
            utilisateur.setProfession(rs.getString("profession"));
            utilisateur.setEnligne(rs.getBoolean("EnLigne"));
            utilisateur.setVerif(rs.getBoolean("verif"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setMdp(rs.getString("mdp"));
        }
        return utilisateur;
    }
    @Override
    public Utilisateur getUtilisateurByID(int id) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        String req = "SELECT * FROM utilisateur WHERE id = '" + id + "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(req);
        if (rs.next()) {
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setGender(rs.getBoolean("gender"));
            utilisateur.setProfession(rs.getString("profession"));
            utilisateur.setEnligne(rs.getBoolean("EnLigne"));
            utilisateur.setVerif(rs.getBoolean("verif"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setMdp(rs.getString("mdp"));
        }
        return utilisateur;
    }

    @Override
    public List<Utilisateur> RecupererUr(String query) throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM utilisateur WHERE nom LIKE ? OR prenom LIKE ? OR email LIKE ?";
        PreparedStatement st = connection.prepareStatement(req);
        for (int i = 1; i <= 3; i++) {
            st.setString(i, "%" + query + "%");
        }
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setGender(rs.getBoolean("gender"));
            utilisateur.setProfession(rs.getString("profession"));
            utilisateur.setEnligne(rs.getBoolean("EnLigne"));
            utilisateur.setVerif(rs.getBoolean("verif"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setMdp(rs.getString("mdp"));
            utilisateurs.add(utilisateur);
        }
        return utilisateurs;
    }

    @Override
    public void ajouterCodePromos(CodePromos codePromos) throws SQLException {

    }

    @Override
    public void modifierCodePromos(CodePromos codePromos) throws SQLException {

    }

    @Override
    public void supprimerCodePromos() throws SQLException {

    }

    @Override
    public List<CodePromos> recupererC() throws SQLException {
        return null;
    }

    @Override
    public Boolean TakeCodePromos() throws SQLException {
        return null;
    }

    @Override
    public Boolean verifier(int code) throws SQLException {
        return null;
    }

    @Override
    public List<CodePromos> RecupererCr(String query) throws SQLException {
        return null;
    }
}
