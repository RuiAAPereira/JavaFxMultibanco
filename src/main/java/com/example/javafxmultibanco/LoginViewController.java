package com.example.javafxmultibanco;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private TextField txtCartao;
    @FXML
    private PasswordField txtPin;

    @FXML
    protected void onBtnCorrigirClick(){
        txtCartao.clear();
        txtCartao.getStyleClass().remove("txt-error");
        txtPin.clear();
        txtPin.getStyleClass().remove("txt-error");
    }

    @FXML
    protected void onBtnAnularClick(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void onBtnContinuarClick(ActionEvent event) throws IOException {
        try {
            if (DataService.login(txtCartao.getText(),txtPin.getText())){

                DataService.addMovimento(ContaHolder.getInstance().getConta().getId(),
                        "Acesso ao multibanco",
                        0,
                        3);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                NavigatorController.navigate(stage,root);

            }else {
                txtCartao.getStyleClass().add("txt-error");
                txtPin.getStyleClass().add("txt-error");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}