package com.vols.gestionvols.controllers;

import com.mysql.jdbc.Connection;
import com.vols.gestionvols.ConnexionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField tname;
    public PasswordField tpass;
    public Button btnCon;
    @FXML
    private Button btnBack;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    btnCon.setOnAction(actionEvent -> {
        try {
            login();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    });
    }

    public void login() throws ClassNotFoundException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection con= ConnexionDB.getConnectiion();
        try {
            st = con.prepareStatement("SELECT * FROM admin WHERE USERNAME=? AND PASSWORD=? ");
            st.setString(1,tname.getText());
            st.setString(2,tpass.getText());
            rs=st.executeQuery();
            if(rs.next()){
                /*Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Login succefully", ButtonType.OK);
                alert.show();*/
                try {
                    Stage stage = (Stage) btnCon.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeVolAdmin.fxml"));
                    primaryStage.setTitle("List des vols");
                    primaryStage.setScene(new Scene(root, 1160, 560));
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {
                Alert alert=new Alert(Alert.AlertType.WARNING,"Login ERROR", ButtonType.OK);
                alert.show();
            }
        }
        catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
    @FXML
    public void back() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Acceuil.fxml"));
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(new Scene(root, 750, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
