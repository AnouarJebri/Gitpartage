package tn.esprit.e_learning.controllers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Random;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;
import java.io.File;


//import com.sun.javafx.scene.control.Properties;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import tn.esprit.e_learning.models.Mail;
import tn.esprit.e_learning.models.Utilisateur;
import tn.esprit.e_learning.services.UtilisateurService;
import tn.esprit.e_learning.test.HelloApplication;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login {
    @FXML
    private Hyperlink hyperLien;

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField mdpTF;
    private boolean isValidEmail(String email) {
        // Simple email validation using regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    @FXML
    void inscrire(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/e_learning/inscription.fxml"));
        try {
            emailTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public String generateCode() {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            codeBuilder.append(allowedCharacters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
    @FXML
    void LoginUtilisateur(ActionEvent event) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();
        //Controle de saisie
        if(emailTF.getText().isEmpty()||mdpTF.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }
        if (!isValidEmail(emailTF.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez saisir une adresse e-mail valide");
            alert.showAndWait();
            return;
        }
        //utilisateur.setEmail(emailTF.getText());
        //utilisateur.setMdp(mdpTF.getText());
        try{
            utilisateur = utilisateurService.getUtilisateurByEmail(emailTF.getText());
            if (utilisateur != null){
                if(doHashing(mdpTF.getText()).equals(utilisateur.getMdp())){
                    if (utilisateur.isVerif()){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        emailTF.getScene().setRoot(fxmlLoader.load(HelloApplication.class.getResource("/tn/esprit/e_learning/Admin_I.fxml")));
                    }
                    else {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/e_learning/Utilisateur_I.fxml"));
                        emailTF.getScene().setRoot(fxmlLoader.load());
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Mot de passe incorrect");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Aucun utilisateur trouvé avec cet email");
                alert.showAndWait();
            }
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void CodeValidation(ActionEvent event) {
        if (emailTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Remplir le champ du mail svp");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mail sent");
            alert.setContentText("Vous recevrez un email contenant un code de réinitialisation");
            alert.showAndWait();
            String generatedCode = generateCode();
            String userEmail = emailTF.getText();
            Mail mail = new Mail();
            mail.sendRecoveryCode(userEmail,generatedCode);

           /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/e_learning/CodeResetCompte.fxml"));
            CodeResetCompte codeResetCompte = new CodeResetCompte(generatedCode);
            fxmlLoader.setController(codeResetCompte);
            try {
                hyperLien.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
        }
    }
}
