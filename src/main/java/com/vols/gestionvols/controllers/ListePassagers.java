package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Passager;
import com.vols.gestionvols.entities.Reservation;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListePassagers implements Initializable {

    @FXML
    private Button btnAero;

    @FXML
    private Button btnComp;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPassager;

    @FXML
    private Button btnReser;

    @FXML
    private Button btnVols;

    @FXML
    private TableColumn <Passager, String>colNom;

    @FXML
    private TableColumn <Passager, String>colNum;

    @FXML
    private TableColumn<Passager, String> colPass;

    @FXML
    private TableView<Passager> listePass;
    @FXML
    private TextField searchVol;
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
    private String getPassport(int idAir) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nomAir="";

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT numPass FROM client WHERE idClient= ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, idAir);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomAir = resultSet.getString("numPass");
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
    ObservableList<Passager> passagers= FXCollections.observableArrayList();
    private ObservableList<Passager> getPassagers() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();

        try{
            st=conn.prepareStatement("SELECT * from reservation WHERE etat='acceptee'");
            rs= st.executeQuery();
            while (rs.next()){
                Passager a= new Passager();
                a.setIdClient(rs.getInt("idClient"));
                a.setIdVol(rs.getInt("idVol"));
                a.setNomPassager(getClientId(rs.getInt("idClient")));
                a.setNumVol(getVolId(rs.getInt("idVol")));
                a.setNumPass(getPassport(rs.getInt("idClient")));

                passagers.add(a);
                System.out.println(a.toString());
            }
            rs.close();
            st.close();
            conn.close();
            return passagers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void search(KeyEvent event) {
        String searchValue = searchVol.getText().trim();
        listePass.setItems(passagers);
        if (!searchValue.isEmpty()) {
            ObservableList<Passager> filteredList = FXCollections.observableArrayList();
            for (Passager reservation : passagers) {
                if (reservation.getNumVol().contains(searchValue) ) {
                    filteredList.add(reservation);
                }
            }
            listePass.setItems(filteredList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            ObservableList<Passager> air = getPassagers();
            listePass.setItems(air);
            colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomPassager()));
            colPass.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumPass()));
            colNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumVol()));


        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
