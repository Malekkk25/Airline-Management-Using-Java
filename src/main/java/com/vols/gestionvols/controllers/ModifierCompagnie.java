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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifierCompagnie{

    private String oldNom;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Label idCom;

    @FXML
    private TextField tCode;

    @FXML
    private TextField tnom;


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

    public void modifierCompagnie() throws SQLException, ClassNotFoundException {
        PreparedStatement st = null;
        Connection conn = null;
        conn = ConnexionDB.getConnectiion();
        String nom = tnom.getText();
        String code = tCode.getText();

        if (nom.isEmpty() || code.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill Data");
            alert.showAndWait();
        } else {
            if(!oldNom.equals(nom)){
                try {

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
                        st = conn.prepareStatement("UPDATE compagnie SET libelle = ?, code = ? WHERE idComp = ?");
                        st.setString(1, nom);
                        st.setString(2, code);
                        st.setString(3,idCom.getText());
                        int rowsAffected = st.executeUpdate();
                        if (rowsAffected > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Compagnie updated successfully");
                            alert.showAndWait();
                            listCompagnies();
                        }}
                    checkSt.close();
                    st.close();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                st = conn.prepareStatement("UPDATE compagnie SET libelle = ?, code = ? WHERE idComp = ?");
                st.setString(1, nom);
                st.setString(2, code);
                st.setString(3,idCom.getText());
                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Airport updated successfully");
                    alert.showAndWait();
                    listCompagnies();
                }}

            st.close();
            conn.close();
        }

    }

    public void afficher(int idAir,String nomAir, String ville){
        idCom.setText(String.valueOf(idAir));
        tnom.setText(nomAir);
        tCode.setText(ville);
        oldNom=nomAir;
    }

    @FXML
    public void clear() {
        tnom.setText("");
        tCode.setText("");
    }

}
