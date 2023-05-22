package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Aeroport;
import com.vols.gestionvols.entities.Compagnie;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListeCompagnies implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAero;

    @FXML
    private Button btnComp;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPassager;

    @FXML
    private Button btnReser;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnVols;

    @FXML
    private TableColumn<Compagnie, String> colCode;

    @FXML
    private TableColumn<Compagnie, Integer> colId;

    @FXML
    private TableColumn<Compagnie, String> colNom;

    @FXML
    private TableView<Compagnie> listeComp;

    ObservableList<Compagnie> compagnies= FXCollections.observableArrayList();
    @FXML
    public void listVol() {
        try {
            Stage stage = (Stage) btnVols.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeVolAdmin.fxml"));
            primaryStage.setTitle("List des vols");
            primaryStage.setScene(new Scene(root, 1160, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    public void listReservation() {
        try {
            Stage stage = (Stage) btnReser.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeReservationAdmin.fxml"));
            primaryStage.setTitle("List des vols");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    @FXML
    public void logout() {
        try {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/LoginAdmin.fxml"));
            primaryStage.setTitle("Sign in");
            primaryStage.setScene(new Scene(root, 750, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listPassagers() {
        try {
            Stage stage = (Stage) btnPassager.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListePassagers.fxml"));
            primaryStage.setTitle("List des Passagers");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    public void listCompagnies() {
        try {
            Stage stage = (Stage) btnComp.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeCompagnies.fxml"));
            primaryStage.setTitle("List des Compagnies");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    public void listAeroports() {
        try {
            Stage stage = (Stage) btnAero.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeAeroports.fxml"));
            primaryStage.setTitle("List des Aeroports");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void ajouterCompagnie() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AjouterCompagnie.fxml"));
            primaryStage.setTitle("Ajouter Compagnie");
            primaryStage.setScene(new Scene(root, 750, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}


    private ObservableList<Compagnie> getCompagnie() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();
        try{
            st=conn.prepareStatement("SELECT * from compagnie");
            rs= st.executeQuery();
            while (rs.next()){
                Compagnie a= new Compagnie();
                a.setIdComp(rs.getInt("idComp"));
                a.setLibelle(rs.getString("libelle"));
                a.setCode(rs.getString("code"));
                compagnies.add(a);
                System.out.println(a.toString());
            }
            rs.close();
            st.close();
            conn.close();
            return compagnies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ObservableList<Compagnie> comp=getCompagnie();
            listeComp.setItems(comp);
            colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdComp()).asObject());
            colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLibelle()));
            colCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateCompagnie() {
        Compagnie selectedCompagnie=listeComp.getSelectionModel().getSelectedItem();
        if(selectedCompagnie !=null){
            int idComp=selectedCompagnie.getIdComp();
            String nom=selectedCompagnie.getLibelle();
            String code=selectedCompagnie.getCode();
            try {
                Stage stage = (Stage) btnUpdate.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateCompagnie.fxml"));
                Parent root = loader.load();
                ModifierCompagnie modifierCompagnie=loader.getController();
                modifierCompagnie.afficher(idComp,nom,code);
                primaryStage.setTitle("Modifier Compagnie");
                primaryStage.setScene(new Scene(root, 750, 450));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une Compagnie");
            alert.showAndWait();
        }
    }

    public void supprimerCompagnie() throws ClassNotFoundException, SQLException {
        Compagnie selectedCompagnie=listeComp.getSelectionModel().getSelectedItem();
        if(selectedCompagnie !=null){
            int idCom=selectedCompagnie.getIdComp();
            Connection conn = ConnexionDB.getConnectiion();
            PreparedStatement st = conn.prepareStatement("DELETE FROM compagnie WHERE idComp = ?");
            st.setInt(1, idCom);
            int rowsAffected = st.executeUpdate();
            st.close();
            conn.close();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Compagnie supprimée");
                alert.showAndWait();
                listCompagnies();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Erreur de supprission");
                alert.showAndWait();
            }


        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une compagnie");
            alert.showAndWait();
        }
    }


}

