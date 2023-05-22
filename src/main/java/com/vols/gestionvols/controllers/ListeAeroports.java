package com.vols.gestionvols.controllers;



import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Aeroport;
import com.vols.gestionvols.entities.Passager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListeAeroports implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAero;

    @FXML
    private Button btnDel;
    @FXML
    private Button btnComp;
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
    private TableColumn<Aeroport, Integer> colId;

    @FXML
    private TableColumn<Aeroport, String> colNom;

    @FXML
    private TableColumn<Aeroport, String> colVille;

    @FXML
    private TableView<Aeroport> listeAir;
    @FXML
    private TextField searchVol;

    ObservableList<Aeroport> aeroports= FXCollections.observableArrayList();

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

    public void ajouterAeroport() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AjouterAeroport.fxml"));
            primaryStage.setTitle("Ajouter Aéroport");
            primaryStage.setScene(new Scene(root, 750, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}


    private ObservableList<Aeroport> getAeroports() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();
        try{
        st=conn.prepareStatement("SELECT * from aeroport");
        rs= st.executeQuery();
        while (rs.next()){
            Aeroport a= new Aeroport();
            a.setIdAir(rs.getInt("idAir"));
            a.setNomAeroport(rs.getString("nomAeroport"));
            a.setVille(rs.getString("ville"));
            aeroports.add(a);
            System.out.println(a.toString());
        }
        rs.close();
        st.close();
        conn.close();
        return aeroports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ObservableList<Aeroport> air=getAeroports();
            listeAir.setItems(air);
            colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdAir()).asObject());
            colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomAeroport()));
            colVille.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVille()));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateAeroport() {
       Aeroport selectedAeroport=listeAir.getSelectionModel().getSelectedItem();
       if(selectedAeroport !=null){
         int idAir=selectedAeroport.getIdAir();
         String nom=selectedAeroport.getNomAeroport();
         String ville=selectedAeroport.getVille();
        try {
            Stage stage = (Stage) btnUpdate.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateAeroport.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/UpdateAeroport.fxml"));
            Parent root = loader.load();
            ModifierAeroport modifierAeroport=loader.getController();
            modifierAeroport.afficher(idAir,nom,ville);
            primaryStage.setTitle("Modifier Aéroport");
            primaryStage.setScene(new Scene(root, 750, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
       else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText(null);
           alert.setContentText("Sélectionner une airoport");
           alert.showAndWait();
       }
    }

    public void supprimerAeroport() throws ClassNotFoundException, SQLException {
        Aeroport selectedAeroport=listeAir.getSelectionModel().getSelectedItem();
        if(selectedAeroport !=null){
            int idAir=selectedAeroport.getIdAir();
            Connection conn = ConnexionDB.getConnectiion();
            PreparedStatement st = conn.prepareStatement("DELETE FROM aeroport WHERE idAir = ?");
            st.setInt(1, idAir);
            int rowsAffected = st.executeUpdate();
            st.close();
            conn.close();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("aéroport supprimée");
                alert.showAndWait();
                listAeroports();

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
            alert.setContentText("Sélectionner une airoport");
            alert.showAndWait();
        }
    }
    @FXML
    void search(KeyEvent event) {
        String searchValue = searchVol.getText().trim();
        listeAir.setItems(aeroports);
        if (!searchValue.isEmpty()) {
            ObservableList<Aeroport> filteredList = FXCollections.observableArrayList();
            for (Aeroport reservation : aeroports) {
                if (reservation.getVille().contains(searchValue) ) {
                    filteredList.add(reservation);
                }
            }
            listeAir.setItems(filteredList);
        }
    }


}

