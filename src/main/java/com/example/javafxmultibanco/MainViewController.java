package com.example.javafxmultibanco;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainViewController {
    private Stage stage;
    private Parent root;

    @FXML
    protected void onBtnlevantamentosClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("levantamentos-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @FXML
    protected void onBtnDepositosClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("depositos-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @FXML
    protected void onBtnPagamentosClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pagamentos-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @FXML
    protected void onBtnMovimentosClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("movimentos-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @FXML
    protected void onBtnRetirarCartaoClick(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    public void onBtnSairClick() {
        Platform.exit();
        System.exit(0);
    }
}
