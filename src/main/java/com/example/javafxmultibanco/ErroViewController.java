package com.example.javafxmultibanco;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ErroViewController  implements Initializable {
    private Stage stage;
    private Parent root;

    @FXML
    private Label lblMensagem;

    @FXML
    protected void onBtnRetirarCartaoClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }
    @FXML
    protected void onBtnOutrasOperacoesClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblMensagem.setText(NavigatorController.getMensagem());
    }
}
