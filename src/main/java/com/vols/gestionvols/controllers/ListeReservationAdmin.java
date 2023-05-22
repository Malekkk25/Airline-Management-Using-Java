package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Reservation;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ListeReservationAdmin implements Initializable {


    @FXML
    private Button btnAccept;

    @FXML
    private Button btnAero;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPassager;

    @FXML
    private Button btnReser;

    @FXML
    private Button btnRefuser;

    @FXML
    private Button btnVols;
    @FXML
    private Button btnComp;

    @FXML
    private TableColumn<Reservation, String> colEtat;

    @FXML
    private TableColumn<Reservation, Integer> colId;

    @FXML
    private TableColumn<Reservation, String> colNum;

    @FXML
    private TableColumn<Reservation, String> coldate;

    @FXML
    private TableView<Reservation> listeReservation;


    @FXML
    private TableColumn<Reservation, String> colNom;

    @FXML
    private TextField searchVol;

    ObservableList<Reservation>listReservation;
    ObservableList<Reservation>dataList;



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
    private String getVolId(int idAir) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nomAir="";

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT numVol FROM vol WHERE idVol = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, idAir);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomAir = resultSet.getString("numVol");
            }
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return nomAir;
    }

    private String getClientId(int idAir) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nomAir="";

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT username FROM client WHERE idClient= ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, idAir);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomAir = resultSet.getString("username");
            }
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return nomAir;
    }


    private int getClient(String idAir) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int nomAir = 0;

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT idClient FROM client WHERE username= ? ";
            statement = conn.prepareStatement(query);
            statement.setString(1, idAir);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomAir = resultSet.getInt("idClient");
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return nomAir;
    }



    ObservableList<Reservation> resrevations= FXCollections.observableArrayList();
    private ObservableList<Reservation> getReservations() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();

        try{
            st=conn.prepareStatement("SELECT * from reservation WHERE etat='en attente'");
            rs= st.executeQuery();
            while (rs.next()){
                Reservation a= new Reservation();
                a.setIdVol(rs.getInt("idVol"));
                a.setIdRes(rs.getInt("idRes"));
                a.setEtat(rs.getString("etat"));
                a.setDate(rs.getString("date"));
                a.setNumVol(getVolId(rs.getInt("idVol")));
                a.setIdClient(rs.getInt("idClient"));
                a.setNomClient(getClientId(rs.getInt("idClient")));
                resrevations.add(a);
                System.out.println(a.toString());
            }
            rs.close();
            st.close();
            conn.close();
            return resrevations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            ObservableList<Reservation> air = getReservations();
            listeReservation.setItems(air);
            colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdRes()).asObject());
            colNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumVol()));
            coldate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
            colEtat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()));
            colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomClient()));

        } catch (NumberFormatException e) {
            // Handle the case where tid.getText() is not a valid integer
            // Display an error message or handle it in an appropriate way
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void search(KeyEvent event) {
        String searchValue = searchVol.getText().trim();
        listeReservation.setItems(resrevations);
        if (!searchValue.isEmpty()) {
            ObservableList<Reservation> filteredList = FXCollections.observableArrayList();
            for (Reservation reservation : resrevations) {
                if (reservation.getNumVol().contains(searchValue) ) {
                    filteredList.add(reservation);
                }
            }
            listeReservation.setItems(filteredList);
        }
    }
    @FXML
    void accepterReser() throws ClassNotFoundException, SQLException {
        Reservation selectedAeroport = listeReservation.getSelectionModel().getSelectedItem();
        if (selectedAeroport != null) {
            int idAir = selectedAeroport.getIdRes();
            Connection conn = ConnexionDB.getConnectiion();

            PreparedStatement st = conn.prepareStatement("UPDATE reservation SET etat='acceptee' WHERE idRes=?");
            st.setInt(1, idAir);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {


                PreparedStatement st1 = conn.prepareStatement("UPDATE client SET etat='passager' WHERE idClient=?");
                st1.setInt(1, getClient(selectedAeroport.getNomClient()));
                int rowsAffected1 = st1.executeUpdate();
                st1.close();
                st.close();
                conn.close();
                if (rowsAffected1 > 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Réservation Acceptée");
                    alert.showAndWait();
                    listReservation();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Erreur de suppression");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une Réservation");
            alert.showAndWait();
        }
    }

    @FXML
    void refuserReser() throws ClassNotFoundException, SQLException {
        Reservation selectedAeroport=listeReservation.getSelectionModel().getSelectedItem();
        if(selectedAeroport !=null){
            int idAir = selectedAeroport.getIdRes();
            Connection conn = ConnexionDB.getConnectiion();

            PreparedStatement st = conn.prepareStatement("UPDATE reservation SET etat='refusée' WHERE idRes=?");
            st.setInt(1, idAir);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Réservation Refusée");
                    alert.showAndWait();
                    listReservation();


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Erreur de suppression");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une Réservation");
            alert.showAndWait();
        }
    }


}
