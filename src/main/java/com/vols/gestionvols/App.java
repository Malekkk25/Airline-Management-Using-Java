package com.vols.gestionvols;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent loader = FXMLLoader.load(getClass().getResource("/Fxml/Acceuil.fxml"));

        stage.setScene(new Scene(loader,750,450));
        stage.show();
    }

    public static void main(String[]args){
        launch(args);
    }
}
