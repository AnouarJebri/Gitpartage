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
import java.sql.SQLException;

public class ModifierCodePromosI {
    @FXML
    private DatePicker DateModif;

    @FXML
    private TextField PrixModifTf;

    @FXML
    private Button back;

    @FXML
    private TextField codeModifTf;

    @FXML
    void GoBack(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            back.getScene().setRoot(fxmlLoader.load(HelloApplication.class.getResource("/tn/esprit/e_learning/Admin_I.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ModifierCodePromosI(ActionEvent event) throws SQLException {
        CodePromosService codePromosService = new CodePromosService();
        CodePromos codePromos = new CodePromos();
        if(codeModifTf.getText().isEmpty()||DateModif.getValue()==null||PrixModifTf.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INPUT ERROR");
            alert.setContentText("Veuiller remplir tous les champs svp");
        } else if (!codeModifTf.getText().matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INPUT ERROR");
            alert.setContentText("Veuillez entrer un entier de 8 chiffres");
            alert.showAndWait();
        } else if (!PrixModifTf.getText().matches("\\d+(\\.\\d+)?")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INPUT ERROR");
            alert.setContentText("Veuillez entrer un réel svp");
            alert.showAndWait();
        } else if (!codePromosService.verifier(Integer.parseInt(codeModifTf.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CODE PROMOS NOT FOUND");
            alert.setContentText("Ce code promos n'existe pas");
            alert.showAndWait();
        } else {
            codePromos.setCode(Integer.parseInt(codeModifTf.getText()));
            codePromos.setDateExpiration(java.sql.Date.valueOf(DateModif.getValue()));
            codePromos.setValeur(Float.parseFloat(PrixModifTf.getText()));
            codePromos.setActive(true);
            try {
                codePromosService.modifierCodePromos(codePromos);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Code Promos modifié avec succés");
                alert.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
