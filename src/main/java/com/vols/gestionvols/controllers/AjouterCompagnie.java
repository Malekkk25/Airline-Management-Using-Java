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

public class AjouterCompagnie {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;


    @FXML
    private TextField tnom;

    @FXML
    private TextField tCode;


    @FXML
    public void back() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeCompagnies.fxml"));
            primaryStage.setTitle("List des Compagnies");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listCompagnies() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeCompagnies.fxml"));
            primaryStage.setTitle("List des Compagnies");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void ajouterCompagnie() {
        PreparedStatement st = null;
        Connection conn = null;
        String nom = tnom.getText();
        String code = tCode.getText();

        if (nom.isEmpty() || code.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill Data");
            alert.showAndWait();
        } else {
            try {
                conn = ConnexionDB.getConnectiion();
                PreparedStatement checkSt=conn.prepareStatement("SELECT COUNT(*) FROM compagnie WHERE libelle = ?");
                checkSt.setString(1,nom);
                ResultSet checkRt=checkSt.executeQuery();
                checkRt.next();
                int count=checkRt.getInt(1);
                if(count>0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Compagnie with the same name already exists");
                    alert.showAndWait();
                }
                else {
                    st = conn.prepareStatement("INSERT INTO `compagnie`(`libelle`, `code`) VALUES (?,?)");
                    st.setString(1, nom);
                    st.setString(2, code);
                    int rowsAffected = st.executeUpdate();
                    if (rowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Compagnie added successfully");
                        alert.showAndWait();
                        listCompagnies();
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
        tCode.setText("");
    }


}






