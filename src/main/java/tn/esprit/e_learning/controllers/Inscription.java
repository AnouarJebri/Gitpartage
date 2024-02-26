package tn.esprit.e_learning.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import tn.esprit.e_learning.models.Utilisateur;
import tn.esprit.e_learning.services.UtilisateurService;
import tn.esprit.e_learning.test.HelloApplication;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class Inscription {

    @FXML
    private RadioButton FemaleButton;

    @FXML
    private RadioButton MaleButton;

    @FXML
    private TextField NomTf;

    @FXML
    private TextField PrenomTf;

    @FXML
    private TextField ProfessionTf;

    @FXML
    private TextField emailTf;

    @FXML
    private ToggleGroup gender;

    @FXML
    private PasswordField mdpTf;
    //@FXML
    //private
    private boolean maleb;
    //Not to lie i have searched for the emailRegex Format
    private boolean isValidEmail(String email) {
        // Simple email validation using regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    public static String doHashing(String mdp){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(mdp.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    @FXML
    void ajouterUtilisateur(ActionEvent event) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();

        //Controle de saisie
        if(NomTf.getText().isEmpty()||PrenomTf.getText().isEmpty()||ProfessionTf.getText().isEmpty()||emailTf.getText().isEmpty()||mdpTf.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }
        if (!isValidEmail(emailTf.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir une adresse e-mail valide");
            alert.showAndWait();
            return;
        }
        try {
            if(utilisateurService.getUtilisateurByEmail(emailTf.getText()) != null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Utilisateur existe déjà");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        //Partie ajout
        utilisateur.setNom(NomTf.getText());
        utilisateur.setPrenom(PrenomTf.getText());
        RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
        if(selectedRadioButton != null)
        {
            if (selectedRadioButton.getId().equals("MaleButton")){
                maleb = true;
            }
            else if (selectedRadioButton.getId().equals("FemaleButton")){
                maleb = false;
            }
        }
        utilisateur.setGender(maleb);
        utilisateur.setProfession(ProfessionTf.getText());
        utilisateur.setEmail(emailTf.getText());
        utilisateur.setMdp(doHashing(mdpTf.getText()));
        utilisateur.setVerif(false);
        utilisateur.setEnligne(false);
        try {
            utilisateurService.ajouterUtilisateur(utilisateur);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Vous êtes inscrits");
            alert.showAndWait();
            NomTf.setText("");
            PrenomTf.setText("");
            gender.selectToggle(null);
            ProfessionTf.setText("");
            emailTf.setText("");
            mdpTf.setText("");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void authentifierUtilisateur(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/e_learning/login.fxml"));
        try {
            ProfessionTf.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
