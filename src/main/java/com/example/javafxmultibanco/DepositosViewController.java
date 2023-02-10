package com.example.javafxmultibanco;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class DepositosViewController implements Initializable {
    public static DecimalFormat df2 = new DecimalFormat("#.00€");
    public Stage stage;
    private Parent root;
    ContaHolder holder = ContaHolder.getInstance();
    Conta conta;
    private Double saldo;
    @FXML
    protected Label lblSaldo;

    @FXML
    protected TextField txtValor;

    @FXML
    protected void onBtnVoltarClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @FXML
    protected void onBtnCorrigirClicked() {
        txtValor.clear();
    }

    @FXML
    protected void onBtnConfirmarClicked(MouseEvent event){
        try{
            double valor =  Double.parseDouble(txtValor.getText());
            txtValor.getStyleClass().remove("txt-error");
            if (valor > 0){
                saldo += valor;
                int id = conta.getId();
                DataService.updateSaldo(id, saldo);
                DataService.addMovimento(id, "Depósito", valor, 1);
                holder.setConta(DataService.getById(id));
                NavigatorController.setMensagem("SUCESSO.\nSALDO: "+
                        df2.format(saldo) +"");
                mostraSucesso(event);
            }
            else if (valor < 0){
                NavigatorController.setMensagem("ERRO!.\nVALOR INVÁLIDO.");
                mostraErro(event);
            }
            else {
                NavigatorController.setMensagem("ERRO DESCONHECIDO.");
                mostraErro(event);
            }
        }
        catch (NullPointerException | NumberFormatException | SQLException ex){
            txtValor.getStyleClass().add("txt-error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected void mostraSucesso(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sucesso-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    protected void mostraErro(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("erro-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        NavigatorController.navigate(stage,root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conta = holder.getConta();
        saldo = conta.getSaldo();
        lblSaldo.setText(df2.format(saldo));
    }
}
