package com.vols.gestionvols.controllers;
import com.vols.gestionvols.ConnexionDB;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierEscale implements Initializable {

    @FXML
    private ChoiceBox<String> aeroportArrivee;

    @FXML
    private ChoiceBox<String> aeroportDepart;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

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

    @FXML
    private Text tnumVol;

    @FXML
    void ajouterEscale(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AjouterVol.fxml"));
            Parent root = loader.load();
            AjouterVol ajouterVol=loader.getController();
            ajouterVol.afficher(tnumVol.getText());
            primaryStage.setTitle("Ajouter Vol");
            primaryStage.setScene(new Scene(root, 880, 580));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
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
    public void ajouterVol() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AjouterVol.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Ajouter Vol");
            primaryStage.setScene(new Scene(root, 880, 580));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    String timePattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    public void modifierEscale() throws SQLException, ClassNotFoundException {
        PreparedStatement st = null;
        Connection conn = null;
        conn = ConnexionDB.getConnectiion();
        List<String> validationMessages = new ArrayList<>();
        String numEscale=tidVol.getText();

        String tDepart=tdepart.getText();
        String tDArrivee=tArrivee.getText();
        String airDepart=aeroportDepart.getValue();
        String airArrivee=aeroportArrivee.getValue();

        if (dateDepart.getValue()==null || dateArrivee.getValue()==null || tDepart.isEmpty() || tDArrivee.isEmpty() || airDepart.isEmpty() || airArrivee.isEmpty()) {
            validationMessages.add("Please fill in all the required fields.");
        }
        else {
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
                        st = conn.prepareStatement("UPDATE escale SET aeroportDepart = ?, aeroportArrivee = ?,dateDepart = ?,dateArrivee = ?,heureDepart = ? ,heureArrivee = ?WHERE idEscale = ?");
                        st.setInt(1, getAeroportId(airDepart));
                        st.setInt(2, getAeroportId(airArrivee));
                        st.setString(3,dDepart);
                        st.setString(4,dDArrivee);
                        st.setString(5,tDepart);
                        st.setString(6,tDArrivee);
                        st.setString(7,numEscale);
                        int rowsAffected = st.executeUpdate();
                        if (rowsAffected > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Airport updated successfully");
                            alert.showAndWait();
                            ajouterVol();
                        }

                    st.close();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


    }






    public void afficher(String numVol,int idEscale,String airDepart,String airArr,String dArr,String dDepart,String hDep,String hArr){

        tnumVol.setText(numVol);
        tidVol.setText(String.valueOf(idEscale));
        aeroportArrivee.setValue(airArr);
        aeroportDepart.setValue(airDepart);
        tdepart.setText(hDep);
        tArrivee.setText(hArr);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
