package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Aeroport;
import com.vols.gestionvols.entities.Escale;
import com.vols.gestionvols.entities.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterEscale1 implements Initializable {

    @FXML
    private ChoiceBox<String> aeroportArrivee;

    @FXML
    private ChoiceBox<String> aeroportDepart;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnBack;

    @FXML
    private DatePicker dateArrivee;

    @FXML
    private DatePicker dateDepart;

    @FXML
    private TextField tArrivee;

    @FXML
    private TextField tdepart;

    @FXML
    private Text tidVol;

    ObservableList<Aeroport> aeroports= FXCollections.observableArrayList();
    ObservableList<String> aeroportNames = FXCollections.observableArrayList();
    private ObservableList<String> getAeroports() throws ClassNotFoundException, SQLException {

        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();
        try{
            st=conn.prepareStatement("SELECT * from aeroport");
            rs= st.executeQuery();
            while (rs.next()){
                aeroportNames.add(rs.getString("nomAeroport"));

            }
            rs.close();
            st.close();
            conn.close();
            return aeroportNames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAeroportId(int idAir) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nomAir="";

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT nomAeroport FROM aeroport WHERE idAir = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, idAir);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomAir = resultSet.getString("nomAeroport");
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
    ObservableList<Vol> vols= FXCollections.observableArrayList();
    private Vol getVols() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();


        try {
            st = conn.prepareStatement("SELECT * from vol WHERE numVol=?");
            st.setString(1, tidVol.getText());
            rs = st.executeQuery();
            Vol a = null;
            while (rs.next()) {
                a = new Vol();
                a.setIdVol(rs.getInt("idVol"));
                a.setCapacite(rs.getInt("capacite"));
                a.setNumVol(rs.getString("numVol"));
                a.setEtat(rs.getString("etat"));
                a.setdDep(rs.getString("dateDepart"));
                a.setdArr(rs.getString("dateArrivee"));
                a.setAirDep(getAeroportId(rs.getInt("aeroportDepart")));
                a.setAirArr(getAeroportId(rs.getInt("aeroportArrivee")));
                a.setComp(getAeroportId(rs.getInt("compagnie")));
                a.settDep(rs.getString("heureDepart"));
                a.settArr(rs.getString("heureArrivee"));

            }
            rs.close();
            st.close();
            conn.close();
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void back(ActionEvent event) {
        try {
            Vol a=getVols();
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateVol.fxml"));
            Parent root = loader.load();
            ModifierVol modifierAeroport=loader.getController();
            modifierAeroport.afficher(a.getIdVol(),a.getCapacite(),a.getNumVol(),a.gettDep(), a.gettArr(),a.getAirDep(),a.getAirArr(),a.getComp() );
            primaryStage.setTitle("Modifer Vol");
            primaryStage.setScene(new Scene(root, 880, 580));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void effacer(ActionEvent event) {
        dateArrivee.setValue(null);
        dateDepart.setValue(null);
        tArrivee.setText(null);
        tdepart.setText(null);
        tidVol.setText(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<String> aeroportNames = getAeroports();
            aeroportDepart.setItems(aeroportNames);
            aeroportArrivee.setItems(aeroportNames);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private int getAeroportId(String nomAeroport) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int aeroportId = -1;

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT idAir FROM aeroport WHERE nomAeroport = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, nomAeroport);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                aeroportId = resultSet.getInt("idAir");
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

        return aeroportId;
    }


    String timePattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    public void ajouterEscale() {
        PreparedStatement st = null;
        Connection conn = null;
        List<String> validationMessages = new ArrayList<>();
        String numVl=tidVol.getText();

        String tDepart=tdepart.getText();
        String tDArrivee=tArrivee.getText();
        String airDepart=aeroportDepart.getValue();
        String airArrivee=aeroportArrivee.getValue();

        if (dateDepart.getValue()==null || dateArrivee.getValue()==null || tDepart.isEmpty() || tDArrivee.isEmpty() || airDepart.isEmpty() || airArrivee.isEmpty()) {
            validationMessages.add("Please fill in all the required fields.");
        }
        else{
        if(!tDepart.matches(timePattern) || !tDArrivee.matches(timePattern)){
            validationMessages.add("Incorrect time format. Please use HH:mm format.");
        }
        if(dateDepart.getValue().compareTo(dateArrivee.getValue())>0){
            validationMessages.add("Please make sure the departure date is before the arrival date.");
        }
        if(dateDepart.getValue().compareTo(dateArrivee.getValue())==0 && (LocalTime.parse(tdepart.getText()).compareTo(LocalTime.parse(tArrivee.getText()))>0) ){
            validationMessages.add("Please make sure the departure time is before the arrival time.");
        }
        if(airArrivee.equals(airDepart)){
            validationMessages.add("The departure and arrival airports cannot be the same.");
        }}
        if (!validationMessages.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(String.join("\n", validationMessages));
            alert.showAndWait();

        }
        else {
            try {
                String dDepart = dateDepart.getValue().toString();
                String dDArrivee=dateArrivee.getValue().toString();
                int airDep=getAeroportId(airDepart);
                int airArr=getAeroportId(airArrivee);

                conn = ConnexionDB.getConnectiion();
                st = conn.prepareStatement("INSERT INTO `escale`(`idVol`,`aeroportDepart`, `aeroportArrivee`, `dateDepart`, `dateArrivee`, `heureDepart`, `heureArrivee`, `numVol`) VALUES (?,?,?,?,?,?,?,?)");
                st.setNull(1,  java.sql.Types.INTEGER);
                st.setInt(2, airDep);
                st.setInt(3, airArr);
                st.setString(4, dDepart);
                st.setString(5, dDArrivee);
                st.setString(6, tDepart);
                st.setString(7, tDArrivee);
                st.setString(8, numVl);
                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Escale ajoutée avec succées");
                    alert.showAndWait();
                    ajouterVol();
                }

                st.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }}

    public void ajouterVol() {
        try {
            Vol a=getVols();
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateVol.fxml"));
            Parent root = loader.load();
            ModifierVol modifierAeroport=loader.getController();
            modifierAeroport.afficher(a.getIdVol(),a.getCapacite(),a.getNumVol(),a.gettDep(), a.gettArr(),a.getAirDep(),a.getAirArr(),a.getComp() );
            primaryStage.setTitle("Modifier Vol");
            primaryStage.setScene(new Scene(root, 880, 580));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficher(String idVol){
        tidVol.setText(idVol);
    }
}
