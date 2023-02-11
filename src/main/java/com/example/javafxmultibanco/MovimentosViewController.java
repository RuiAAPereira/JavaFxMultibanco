package com.example.javafxmultibanco;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MovimentosViewController implements Initializable {
    private Stage stage;
    private Parent root;
    @FXML
    private TextFlow textFlow;
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
        lblMensagem.setText("ULTIMOS\nMOVIMENTOS");

        List<Movimento> movimentos = DataService.getMovimentos();
        Font font = Font.font("System", FontWeight.NORMAL, 14);
        assert movimentos != null;
        for (Movimento movimento : movimentos) {

            if(movimento.getTipo() == 3){
                Text t = new Text("Data: "+movimento.getData()+" - "+ movimento.getDescricao() +"\n");
                t.setFill(Color.BLUE);
                t.setFont(font);
                textFlow.getChildren().add(t);
            }
            if(movimento.getTipo() == 0){
                Text t = new Text("Data: "+movimento.getData()+" - "+ movimento.getDescricao() +
                        " -"+movimento.getValor()+"€\n");
                t.setFill(Color.RED);
                t.setFont(font);
                textFlow.getChildren().add(t);
            }
            if(movimento.getTipo() == 1){
                Text t = new Text("Data: "+movimento.getData()+" - "+ movimento.getDescricao() +
                        " +"+movimento.getValor()+"€\n");
                t.setFill(Color.BLACK);
                t.setFont(font);
                textFlow.getChildren().add(t);
            }


         }


    }
}
