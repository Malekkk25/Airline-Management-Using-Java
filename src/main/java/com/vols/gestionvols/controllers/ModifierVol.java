package com.vols.gestionvols.controllers;

import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Escale;
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

public class ModifierVol implements Initializable {
    @FXML
    private Button addEscale;

    @FXML
    private TableColumn<Escale, String> airArr;

    @FXML
    private TableColumn<Escale, String>airDepart;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<Escale, String> dateArr;

    @FXML
    private TableColumn<Escale, String> dateDepart;

    @FXML
    private Button deleteEscale;

    @FXML
    private TableColumn<Escale,Integer> idEscale;

    @FXML
    private TableView<Escale> listeEscale;

    @FXML
    private TextField tArrivee;

    @FXML
    private TextField tCapacite;

    @FXML
    private TextField tdepart;

    @FXML
    private TextField tnum;

    @FXML
    private Button btnClear;

    @FXML
    private Button updateEscale;

    @FXML
    private ChoiceBox<String> tComp;

    @FXML
    private ChoiceBox<String> airDep;
    @FXML
    private ChoiceBox<String> arrivee;

    @FXML
    private DatePicker dArr;

    @FXML
    private DatePicker dDepart;
    @FXML
    private TableColumn<Escale, String> hArrivee;

    @FXML
    private TableColumn<Escale, String> hDepart;
    @FXML
    private Text tid;

    ObservableList<Escale> escales= FXCollections.observableArrayList();
    @FXML
    public void back() {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeVolAdmin.fxml"));
            primaryStage.setTitle("Liste des Vols");
            primaryStage.setScene(new Scene(root, 1160, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ajouterVoll(String num) {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AjouterVol.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateVol.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/UpdateAeroport.fxml"));
            Parent root = loader.load();
            ModifierVol modifierAeroport=loader.getController();
            modifierAeroport.afficher(tnum.getText());
            primaryStage.setTitle("Modifier Vol");
            primaryStage.setScene(new Scene(root, 880, 580));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    public void ajouterEscale() {
        Connection conn = null;
        if(!tnum.getText().isEmpty()){
            try {
                conn = ConnexionDB.getConnectiion();
                    Stage stage = (Stage) addEscale.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AjouterEscale.fxml"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AjouterEscale1.fxml"));
                    Parent root = loader.load();
                    AjouterEscale1 ajouterEscale=loader.getController();
                    ajouterEscale.afficher(tnum.getText());
                    primaryStage.setTitle("Ajouter Escale");
                    primaryStage.setScene(new Scene(root, 750, 450));
                    primaryStage.show();
                } catch (IOException e) {
                e.printStackTrace();
            }  catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saisir le numéro du vol");
            alert.showAndWait();
        }}



    public void afficher(String idVol){
        tnum.setText(idVol);
        tnum.setEditable(false);

    }
    public void show(String idVol){
        tnum.setText(idVol);
        tnum.setEditable(false);

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
    private ObservableList<Escale> getEscales() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();
        try{
            st=conn.prepareStatement("SELECT * from escale WHERE numVol=? ");
            st.setString(1,tnum.getText());
            rs= st.executeQuery();
            while (rs.next()){
                Escale a= new Escale();
                a.setIdEscale(rs.getInt("idEscale"));
                a.setAirArrivee(getAeroportId(rs.getInt("aeroportArrivee")));
                a.setAirDepart(getAeroportId(rs.getInt("aeroportDepart")));
                a.setDateDepart(rs.getString("dateDepart"));
                a.setDateArrivee(rs.getString("dateArrivee"));
                a.setHeureDepart(rs.getString("heureDepart"));
                a.setHeureArrivee(rs.getString("heureArrivee"));
                escales.add(a);
                System.out.println(a.toString());
            }
            rs.close();
            st.close();
            conn.close();
            return escales;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
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


    ObservableList<String> compagnieNames = FXCollections.observableArrayList();
    private ObservableList<String> getCompagnies() throws ClassNotFoundException, SQLException {

        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();
        try{
            st=conn.prepareStatement("SELECT * from compagnie");
            rs= st.executeQuery();
            while (rs.next()){
                compagnieNames.add(rs.getString("libelle"));

            }
            rs.close();
            st.close();
            conn.close();
            return compagnieNames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    private int getCompagnieId(String libelle) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int compagnieId = -1;

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT idComp FROM compagnie WHERE libelle = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, libelle);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                compagnieId = resultSet.getInt("idComp");
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

        return compagnieId;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ObservableList<String> aeroportNames = getAeroports();
            airDep.setItems(aeroportNames);
            arrivee.setItems(aeroportNames);
            ObservableList<String> compagnieNames = getCompagnies();
            tComp.setItems(compagnieNames);
            ObservableList<Escale> air=getEscales();
            listeEscale.setItems(air);
            idEscale.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdEscale()).asObject());
            airDepart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAirDepart()));
            airArr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAirArrivee()));
            dateDepart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateDepart()));
            dateArr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateArrivee()));
            hArrivee.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeureArrivee()));
            hDepart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeureDepart()));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    String timePattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    public void ajouterVol() {
        List<String> validationMessages = new ArrayList<>();
        PreparedStatement st = null;
        Connection conn = null;
        String numVl=tnum.getText();
        int capacite = Integer.parseInt(tCapacite.getText());

        String tDepart=tdepart.getText();
        String tDArrivee=tArrivee.getText();
        String airDepart=airDep.getValue();
        String airArrivee=arrivee.getValue();
        String comp =tComp.getValue();
        if (dDepart.getValue()==null|| dArr.getValue()==null || tDepart.isEmpty() || tDArrivee.isEmpty() || airDepart.isEmpty() || airArrivee.isEmpty() || numVl.isEmpty()|| tCapacite.equals("") ) {
            validationMessages.add("Please fill in all the required fields.");
        }
        else{
        if(!tDepart.matches(timePattern) || !tDArrivee.matches(timePattern)){
            validationMessages.add("Incorrect time format. Please use HH:mm format.");
        }
        if(dDepart.getValue().compareTo(dArr.getValue())>0){
            validationMessages.add("Please make sure the departure date is before the arrival date.");
        }
        if(dDepart.getValue().compareTo(dArr.getValue())==0 && (LocalTime.parse(tdepart.getText()).compareTo(LocalTime.parse(tArrivee.getText()))>0) ){
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
                conn = ConnexionDB.getConnectiion();
                String dDep = dDepart.getValue().toString();
                String dDArrivee=dArr.getValue().toString();
                    int airDep=getAeroportId(airDepart);
                    int airArr=getAeroportId(airArrivee);
                    int compagnie=getCompagnieId(comp);
                    st = conn.prepareStatement("UPDATE `vol`SET`capacite`=?,  `dateDepart`=?,   `aeroportDepart`=?, `aeroportArrivee`=?, `dateArrivee`=?, `compagnie`=?,`heureDepart`=?,`heureArrivee`=? WHERE numVol=?");
                    st.setInt(1,  capacite);
                    st.setString(2, dDep);
                    st.setInt(3, airDep);
                    st.setInt(4, airArr);
                    st.setString(5, dDArrivee);
                    st.setInt(6, compagnie);
                    st.setString(7, tDepart);
                    st.setString(8, tDArrivee);
                    st.setString(9, numVl);
                    int rowsAffected = st.executeUpdate();
                    if (rowsAffected > 0) {
                        updateEscale();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Vol added successfully");
                        alert.showAndWait();
                        listVol();
                    }

                    st.close();
                    conn.close();
                } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void updateEscale() {
        Escale selectedEscale=listeEscale.getSelectionModel().getSelectedItem();
        if(selectedEscale !=null){
            int idEscale=selectedEscale.getIdEscale();
            String airDepart=selectedEscale.getAirDepart();
            String airArr=selectedEscale.getAirArrivee();
            try {
                Stage stage = (Stage) updateEscale.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateEscale1.fxml"));
                //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/UpdateAeroport.fxml"));
                Parent root = loader.load();
                ModiferEscale1 modifierAeroport=loader.getController();
                modifierAeroport.afficher(tnum.getText(),idEscale,airDepart,airArr,selectedEscale.getDateArrivee(),selectedEscale.getDateDepart(),selectedEscale.getHeureDepart(),selectedEscale.getHeureArrivee());

                primaryStage.setTitle("Modifier Escale");
                primaryStage.setScene(new Scene(root, 750, 450));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une escale");
            alert.showAndWait();
        }
    }



    public void listVol() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ListeVolAdmin.fxml"));
            primaryStage.setTitle("List des vols");
            primaryStage.setScene(new Scene(root, 900, 560));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void supprimerEscale() throws ClassNotFoundException, SQLException {
        Escale selectedEscale=listeEscale.getSelectionModel().getSelectedItem();
        if(selectedEscale !=null){
            int idEscale=selectedEscale.getIdEscale();
            Connection conn = ConnexionDB.getConnectiion();
            PreparedStatement st = conn.prepareStatement("DELETE FROM escale WHERE idEscale = ?");
            st.setInt(1, idEscale);
            int rowsAffected = st.executeUpdate();
            st.close();
            conn.close();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Escale supprimée");
                alert.showAndWait();
                ajouterVoll(tnum.getText());

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
            alert.setContentText("Sélectionner une Escale");
            alert.showAndWait();
        }
    }
public void afficher(int idVol,int capacite,String numVol,String hDepart,String hArr,String airDepart,String airArr,String compa){
        tid.setText(String.valueOf(idVol));
        tnum.setText(numVol);
        tnum.setEditable(false);
        tCapacite.setText(String.valueOf(capacite));
        tdepart.setText(hDepart);
        tArrivee.setText(hArr);
        airDep.setValue(airDepart);
        arrivee.setValue(airArr);
        tComp.setValue(compa);

}

}
