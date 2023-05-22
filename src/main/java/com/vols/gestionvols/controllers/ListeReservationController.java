package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Aeroport;
import com.vols.gestionvols.entities.Reservation;
import com.vols.gestionvols.entities.Vol;
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
import javafx.scene.text.Text;
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

public class ListeReservationController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnReser;

    @FXML
    private Button btnSupp;

    @FXML
    private Button btnVols;

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
    private Text tid;

    @FXML
    private Text welcomeText;

    @FXML
    private TextField searchVol;
    @FXML
    public void listVol() {
        try {
            Stage stage = (Stage) btnVols.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeVol.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ListeVol.fxml"));
            Parent root = loader.load();
            ListeVolController listeVolController = loader.getController();
            listeVolController.display(welcomeText.getText(),tid.getText());
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ListeReservation.fxml"));
            Parent root = loader.load();
            ListeReservationController listeResController = loader.getController();
            listeResController.show(welcomeText.getText(),tid.getText());
            primaryStage.setTitle("List des Reservations");
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
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/LoginUser.fxml"));
            primaryStage.setTitle("Sign in");
            primaryStage.setScene(new Scene(root, 750, 450));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
    private ObservableList<Reservation> getReservations(String clientId) throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();

        try{
            st=conn.prepareStatement("SELECT * from reservation WHERE idClient= ?");
            st.setString(1, clientId);
            rs= st.executeQuery();
            while (rs.next()){
                Reservation a= new Reservation();
                a.setIdVol(rs.getInt("idVol"));
                a.setIdRes(rs.getInt("idRes"));
                a.setEtat(rs.getString("etat"));
                a.setDate(rs.getString("date"));
                a.setNumVol(getVolId(rs.getInt("idVol")));
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
            System.out.println(tid.getText()+welcomeText.getText());
            ObservableList<Reservation> air = getReservations(tid.getText());
            listeReservation.setItems(air);
            colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdRes()).asObject());
            colNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumVol()));
            coldate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
            colEtat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void supprimerReservation() throws ClassNotFoundException, SQLException {
        Reservation selectedReservation = listeReservation.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateReser = selectedReservation.getDate();
            LocalDate dateR = LocalDate.parse(dateReser, formatter);
            long daysDifference = ChronoUnit.DAYS.between(dateR, LocalDate.now());

            if (selectedReservation.getEtat().equals("acceptee")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Impossible de supprimer une réservation acceptée.");
                alert.showAndWait();
            } else if (daysDifference > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Impossible de supprimer votre réservation, la date limite est passée.");
                alert.showAndWait();
            } else {
                int idRes = selectedReservation.getIdRes();
                Connection conn = ConnexionDB.getConnectiion();
                PreparedStatement st = conn.prepareStatement("DELETE FROM reservation WHERE idRes = ?");
                st.setInt(1, idRes);
                int rowsAffected = st.executeUpdate();
                st.close();
                conn.close();
                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Réservation supprimée.");
                    alert.showAndWait();
                    resrevations.remove(selectedReservation);
                    listeReservation.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur de suppression.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionnez une réservation.");
            alert.showAndWait();
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

    public void show(String username,String idClient){
        welcomeText.setText(username);
        tid.setText(idClient);
    }
}

