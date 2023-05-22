package com.vols.gestionvols.controllers;



import com.vols.gestionvols.ConnexionDB;
import com.vols.gestionvols.entities.Aeroport;
import com.vols.gestionvols.entities.Escale;
import com.vols.gestionvols.entities.Vol;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ListeVolAdmin implements Initializable {
    @FXML
    private Button btnComp;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAero;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnFermer;

    @FXML
    private Button btnLogout;

    @FXML
    private Button search;
    @FXML
    private Button btnPassager;

    @FXML
    private Button btnReser;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnVols;

    @FXML
    private TableColumn<Vol, Integer> colCapacite;

    @FXML
    private TableColumn<Vol, Integer>  colId;

    @FXML
    private TableColumn<Vol, String>  colNum;
    @FXML
    private TableColumn<Vol, String> coletat;

    @FXML
    private TableColumn<Vol, String>  colairA;

    @FXML
    private TableColumn<Vol, String>  colairD;

    @FXML
    private TableColumn<Vol, String> coldDep;

    @FXML
    private TableColumn<Vol, String> colhDep;

    @FXML
    private TableView<Vol> listeVol;
    @FXML
    private DatePicker searchVol;

    @FXML
    private TableColumn<Vol, String> dArr;

    @FXML
    private TableColumn<Vol, String> tArr;
    @FXML
    private TableColumn<Vol, String>colComp;

    @FXML
    private TableColumn<Escale, String> airArr;

    @FXML
    private TableColumn<Escale, String>airDepart;

    @FXML
    private TableColumn<Escale, String> dateArr;
    @FXML
    private TableColumn<Escale, String> hArrivee;

    @FXML
    private TableColumn<Escale, String> hDepart;
    @FXML
    private TableColumn<Escale, String> dateDepart;

    @FXML
    private TableColumn<Escale,Integer> idEscale;

    @FXML
    private TableView<Escale> listeEscale;
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
            primaryStage.setTitle("List des Réservations");
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

    public void ajouterVol() {
        try {
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/AjouterVol.fxml"));
            primaryStage.setTitle("Ajouter Vol");
            primaryStage.setScene(new Scene(root, 880, 580));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

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
    private String getComptId(int idAir) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String nomAir="";

        try {
            conn = ConnexionDB.getConnectiion();
            String query = "SELECT libelle FROM compagnie WHERE idComp = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, idAir);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomAir = resultSet.getString("libelle");
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
    private ObservableList<Vol> getVols() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();


        try{
            st=conn.prepareStatement("SELECT * from vol");
            rs= st.executeQuery();
            while (rs.next()){
                Vol a= new Vol();
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
                vols.add(a);
                System.out.println(a.toString());
            }
            rs.close();
            st.close();
            conn.close();
            return vols;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Vol> air = getVols();
            listeVol.setItems(air);
            colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdVol()).asObject());
            colNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumVol()));
            colCapacite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCapacite()).asObject());
            colairD.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAirDep()));
            colairA.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAirArr()));
            coldDep.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdDep()));
            colhDep.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().gettDep()));
            coletat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()));
            dArr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdArr()));
            tArr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().gettArr()));
            colComp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComp()));

            listeVol.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedAeroport) -> {
                if (selectedAeroport != null) {
                    try {
                        ObservableList<Escale> esc = getEscales(selectedAeroport.getIdVol());
                        listeEscale.getItems().clear();
                        listeEscale.setItems(esc);
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
            });

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ObservableList<Escale> escales= FXCollections.observableArrayList();
    private ObservableList<Escale> getEscales(int idVol) throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();
        try{
            st=conn.prepareStatement("SELECT * from escale WHERE idVol=?");
            st.setInt(1, idVol);
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
    public void fermerVol() throws ClassNotFoundException, SQLException {
        Vol selectedAeroport=listeVol.getSelectionModel().getSelectedItem();
        if(selectedAeroport !=null){
            if (selectedAeroport.getEtat().equals("ferme")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Vol deja fermé");
                alert.showAndWait();

            }
            else {
            int idVol=selectedAeroport.getIdVol();
            Connection conn = ConnexionDB.getConnectiion();
            PreparedStatement st = conn.prepareStatement("UPDATE vol SET etat='ferme' WHERE idVol=?");
            st.setInt(1, idVol);
            int rowsAffected = st.executeUpdate();
            st.close();
            conn.close();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Vol fermé");
                alert.showAndWait();
                listVol();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Erreur de supprission");
                alert.showAndWait();
            }


        }}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner un Vol");
            alert.showAndWait();
        }
    }

    public void ouvrirVol() throws ClassNotFoundException, SQLException {
        Vol selectedAeroport=listeVol.getSelectionModel().getSelectedItem();
        if(selectedAeroport !=null){
            if (selectedAeroport.getEtat().equals("ouvert")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Vol deja ouvert");
                alert.showAndWait();

            }
            else {
            int idVol=selectedAeroport.getIdVol();
            Connection conn = ConnexionDB.getConnectiion();
            PreparedStatement st = conn.prepareStatement("UPDATE vol SET etat='ouvert' WHERE idVol=?");
            st.setInt(1, idVol);
            int rowsAffected = st.executeUpdate();
            st.close();
            conn.close();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Vol ouvert");
                alert.showAndWait();
                listVol();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Erreur de supprission");
                alert.showAndWait();
            }


        }}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner un Vol");
            alert.showAndWait();
        }
    }

    @FXML
    void search() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String searchValue = searchVol.getValue().format(formatter);
        listeVol.setItems(vols);
        if (!searchValue.isEmpty()) {
            ObservableList<Vol> filteredList = FXCollections.observableArrayList();
            for (Vol reservation : vols) {
                if (reservation.getdDep().contains(searchValue) ) {
                    filteredList.add(reservation);
                }
            }
            listeVol.setItems(filteredList);
        }
    }

    public void modifierVol() {
        Vol selectedAeroport=listeVol.getSelectionModel().getSelectedItem();
        if(selectedAeroport !=null){
            int idVol=selectedAeroport.getIdVol();
            int capacite=selectedAeroport.getCapacite();
            String numVol=selectedAeroport.getNumVol();

            try {
                Stage stage = (Stage) btnUpdate.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UpdateVol.fxml"));
                //Parent root = FXMLLoader.load(getClass().getResource("/Fxml/UpdateAeroport.fxml"));
                Parent root = loader.load();
                ModifierVol modifierAeroport=loader.getController();
                modifierAeroport.afficher(idVol,capacite,numVol,selectedAeroport.gettDep(), selectedAeroport.gettArr(),selectedAeroport.getAirDep(),selectedAeroport.getAirArr(),selectedAeroport.getComp() );
                modifierAeroport.initialize(null, null);
                primaryStage.setTitle("Modifier Vol");
                primaryStage.setScene(new Scene(root, 880, 580));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner un Vol");
            alert.showAndWait();
        }
    }

}
