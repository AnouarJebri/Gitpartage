package tn.esprit.e_learning.services;

import tn.esprit.e_learning.models.CodePromos;
import tn.esprit.e_learning.models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IServices {
    void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException;
    void modifierUtilisateur(Utilisateur utilisateur) throws SQLException;
    void supprimerUtilisateur(int id) throws SQLException;
    List<Utilisateur> recupererU() throws SQLException;
    public Utilisateur getUtilisateurByEmail(String email) throws SQLException;
    public Utilisateur getUtilisateurByID(int id) throws SQLException;
    public List<Utilisateur> RecupererUr(String query) throws SQLException;
    void ajouterCodePromos(CodePromos codePromos) throws SQLException;
    void modifierCodePromos(CodePromos codePromos) throws SQLException;
    void  supprimerCodePromos() throws SQLException;
    List<CodePromos> recupererC() throws SQLException;
    Boolean TakeCodePromos() throws SQLException;
    public Boolean verifier(int code) throws SQLException;
    List<CodePromos> RecupererCr(String query) throws SQLException;
}
