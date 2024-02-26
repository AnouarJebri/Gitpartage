package tn.esprit.e_learning.services;

import tn.esprit.e_learning.models.CodePromos;
import tn.esprit.e_learning.models.Utilisateur;
import tn.esprit.e_learning.utils.MyDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CodePromosService implements IServices{
    private Connection connection;
    public CodePromosService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public void supprimerUtilisateur(int id) throws SQLException {

    }

    @Override
    public List<Utilisateur> recupererU() throws SQLException {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurByID(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Utilisateur> RecupererUr(String query) throws SQLException {
        return null;
    }

    @Override
    public void ajouterCodePromos(CodePromos codePromos) throws SQLException {
        String req = "INSERT INTO codepromos (code,valeur,DateExpiration,active) VALUES ( '"+ codePromos.getCode() +"','" + codePromos.getValeur() + "','" + codePromos.getDateExpiration() + "'," + codePromos.isActive() + ")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifierCodePromos(CodePromos codePromos) throws SQLException {
        String req = "UPDATE codepromos SET valeur = ?, DateExpiration = ?, active = ? WHERE code = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setFloat(1,codePromos.getValeur());
        ps.setDate(2, Date.valueOf(String.valueOf(codePromos.getDateExpiration())));
        ps.setBoolean(3,codePromos.isActive());
        ps.setInt(4,codePromos.getCode());
        ps.executeUpdate();
    }

    @Override
    public void supprimerCodePromos() throws SQLException {
        String req = "DELETE FROM codepromos WHERE DateExpiration < ? OR active = 0";
        LocalDate currentDate = LocalDate.now();
        PreparedStatement ps = connection.prepareStatement(req);
        //ps.setInt(1,code);
        ps.setDate(1, java.sql.Date.valueOf(currentDate));
        ps.executeUpdate();
    }
    @Override
    public Boolean verifier(int code) throws SQLException {
        String req = "SELECT * FROM codepromos WHERE code = ?";
        try (PreparedStatement st = connection.prepareStatement(req)) {
            st.setInt(1, code);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<CodePromos> RecupererCr(String query) throws SQLException {
        List<CodePromos> codePromosS = new ArrayList<>();
        String req = "SELECT * FROM codepromos WHERE code LIKE ? OR valeur LIKE ?";
        PreparedStatement st = connection.prepareStatement(req);
        for (int i = 1; i <= 2; i++) {
            st.setString(i, "%" + query + "%");
        }
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            CodePromos codePromos = new CodePromos();
            codePromos.setCode(rs.getInt("code"));
            codePromos.setValeur(rs.getFloat("valeur"));
            codePromos.setDateExpiration(Date.valueOf(rs.getDate("DateExpiration").toLocalDate()));
            codePromos.setActive(rs.getBoolean("active"));
            codePromosS.add(codePromos);
        }
        return codePromosS;
    }

    @Override
    public List<CodePromos> recupererC() throws SQLException {
        List<CodePromos> codePromos = new ArrayList<>();
        String req = "SELECT * FROM codepromos";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            CodePromos codePromo = new CodePromos();
            codePromo.setCode(rs.getInt("code"));
            codePromo.setValeur(rs.getFloat("valeur"));
            codePromo.setDateExpiration(Date.valueOf(rs.getDate("DateExpiration").toLocalDate()));
            codePromo.setActive(rs.getBoolean("active"));
            codePromos.add(codePromo);
        }
        return codePromos;
    }

    @Override
    public Boolean TakeCodePromos() throws SQLException {
        List<CodePromos> codePromos = new ArrayList<>();
        String req = "SELECT * FROM codepromos WHERE DateExpiration < ? OR active = 0";
        LocalDate currentDate = LocalDate.now();
        PreparedStatement ps = connection.prepareStatement(req);
        //ps.setInt(1,code);
        ps.setDate(1, java.sql.Date.valueOf(currentDate));
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int count = rs.getInt(1);
            return count > 0;
        }
        return false;
    }
}
