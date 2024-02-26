package tn.esprit.e_learning.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import tn.esprit.e_learning.test.HelloApplication;

import java.io.IOException;

public class ResetPassword {
    @FXML
    private Button back;

    @FXML
    void GoBack(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            back.getScene().setRoot(fxmlLoader.load(HelloApplication.class.getResource("/tn/esprit/e_learning/login.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
