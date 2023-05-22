package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Aeroport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjouterAeroport {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;


    @FXML
    private TextField tnom;

    @FXML
    private TextField tville;


    @FXML
    public void back() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeAeroports.fxml"));
            primaryStage.setTitle("List des Aeroports");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listAeroports() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeAeroports.fxml"));
            primaryStage.setTitle("List des Aeroports");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void ajouterAeroport() {
        PreparedStatement st = null;
        Connection conn = null;
        String nom = tnom.getText();
        String ville = tville.getText();

        if (nom.isEmpty() || ville.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill Data");
            alert.showAndWait();
        } else {
            try {
                conn = ConnexionDB.getConnectiion();
                PreparedStatement checkSt=conn.prepareStatement("SELECT COUNT(*) FROM aeroport WHERE nomAeroport = ?");
                checkSt.setString(1,nom);
                ResultSet checkRt=checkSt.executeQuery();
                checkRt.next();
                int count=checkRt.getInt(1);
                if(count>0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Airport with the same name already exists");
                    alert.showAndWait();
                }
                else {
                    st = conn.prepareStatement("INSERT INTO `aeroport`(`nomAeroport`, `ville`) VALUES (?,?)");
                    st.setString(1, nom);
                    st.setString(2, ville);
                    int rowsAffected = st.executeUpdate();
                    if (rowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Airport added successfully");
                        alert.showAndWait();
                        listAeroports();
                    }}
                checkSt.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void clear() {
        tnom.setText("");
        tville.setText("");
    }


    }





