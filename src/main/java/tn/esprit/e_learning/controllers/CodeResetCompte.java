package tn.esprit.e_learning.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Check;
import tn.esprit.e_learning.test.HelloApplication;

import java.io.IOException;

public class CodeResetCompte {
    @FXML
    private Button Check;
    @FXML
    private TextField resetCodeTextField;
    private String generatedCode;
    public CodeResetCompte(String generatedCode) {
        this.generatedCode = generatedCode;
    }
    public String getGeneratedCode() {
        return generatedCode;
    }
    @FXML
    void checkResetCode(ActionEvent event) {
        String enteredCode = resetCodeTextField.getText();
        if (enteredCode.equals(generatedCode)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Matches");
            alert.setContentText("Code matches");
            alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/e_learning/ResetPassword.fxml"));
            try {
                Check.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Code doesn't match");
            alert.showAndWait();
        }
    }
}
