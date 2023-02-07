package com.example.javafxmultibanco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MultibancoApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        NavigatorController.navigate(stage,
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")))
        );
    }

    public static void main(String[] args) {
        launch();
    }
}