package tn.esprit.e_learning.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.e_learning.models.CodePromos;
import tn.esprit.e_learning.services.CodePromosService;
import tn.esprit.e_learning.test.HelloApplication;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GenerateCodePromosI {

    @FXML
    private DatePicker selectedDate;

    @FXML
    private TextField selectedPrix;

    @FXML
    private Button back;

    @FXML
    void GenrerCodePromos(ActionEvent event) throws SQLException {
        CodePromosService codePromosService = new CodePromosService();
        CodePromos codePromos = new CodePromos();
        if(selectedPrix.getText().isEmpty()||selectedDate.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir les champs svp");
            alert.showAndWait();
        } else if (!selectedPrix.getText().matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INPUT ERROR");
            alert.setContentText("Veuillez entrer un réel svp");
            alert.showAndWait();
        } else {
            codePromos.setValeur(Float.parseFloat(selectedPrix.getText()));
            codePromos.setActive(true);
            codePromos.setDateExpiration(java.sql.Date.valueOf(selectedDate.getValue()));
            int cd = codePromos.generateRandomCode();
            while (codePromosService.verifier(cd)) {
                cd = codePromos.generateRandomCode();
                try {
                    codePromosService.verifier(cd);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            codePromos.setCode(cd);

            try {
                codePromosService.ajouterCodePromos(codePromos);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Code promos ajouté avec succés");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
    @FXML
    void GoBack(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/e_learning/Admin_I.fxml"));
        try {
            back.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}