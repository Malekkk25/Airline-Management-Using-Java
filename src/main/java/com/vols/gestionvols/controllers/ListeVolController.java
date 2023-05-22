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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ListeVolController implements Initializable {
    ObservableList<Vol> vols= FXCollections.observableArrayList();

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnReser;

    @FXML
    private Button search;

    @FXML
    private Button btnVols;
    @FXML
    private Text tid;
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
    private TableColumn<Vol, String> dArr;

    @FXML
    private TableColumn<Vol, String> tArr;
    @FXML
    private TableColumn<Vol, String>colComp;

    @FXML
    private TableView<Vol> listeVol;
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
    private Text welcomeText;

    @FXML
    private DatePicker searchVol;

    private ObservableList<Vol> getVols() throws ClassNotFoundException, SQLException {
        PreparedStatement st=null;
        ResultSet rs=null;
        Connection conn= ConnexionDB.getConnectiion();


        try{
            st=conn.prepareStatement("SELECT * from vol WHERE etat='ouvert' AND capacite>0");
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
                a.setComp(getCompagnieId(rs.getInt("compagnie")));
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


    private String getCompagnieId(int idAir) throws ClassNotFoundException, SQLException {
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
            listeResController.initialize(null, null);
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
    public void reserverVol() throws ClassNotFoundException, SQLException {
        Vol selectedAeroport=listeVol.getSelectionModel().getSelectedItem();
        PreparedStatement st = null;
        Connection conn = null;
        if(selectedAeroport !=null){
            conn = ConnexionDB.getConnectiion();

            try {
                conn = ConnexionDB.getConnectiion();
                PreparedStatement checkSt=conn.prepareStatement("SELECT COUNT(*) FROM reservation WHERE idVol = ?");
                checkSt.setInt(1,selectedAeroport.getIdVol());
                ResultSet checkRt=checkSt.executeQuery();
                checkRt.next();
                int count=checkRt.getInt(1);
                if(count>0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Reservation deja existe");
                    alert.showAndWait();
                }
            else {
                int idVol=selectedAeroport.getIdVol();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                st = conn.prepareStatement("INSERT INTO `reservation`(`idClient`, `idVol`, `etat`, `date`) VALUES (?,?,?,?)");
                st.setInt(1,  Integer.parseInt(tid.getText()));
                st.setInt(2, idVol);
                st.setString(3, "en attente");
                st.setString(4, LocalDate.now().format(formatter));
                int rowsAffected = st.executeUpdate();

                st.close();
                conn.close();
                if (rowsAffected > 0) {

                    conn = ConnexionDB.getConnectiion();
                    PreparedStatement st1 = conn.prepareStatement("UPDATE vol SET capacite = (SELECT capacite FROM vol WHERE idVol = ?) - 1 WHERE idVol = ?");
                    st1.setInt(1, idVol);
                    st1.setInt(2, idVol);
                    int rowsAffected1 = st1.executeUpdate();
                    st1.close();

                    if (rowsAffected1 > 0) {
                        // Capacity updated successfully
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Réservation effectuée");
                        alert.showAndWait();
                        listVol();
                    } else {
                        // Error updating capacity
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Erreur lors de la mise à jour de la capacité");
                        alert.showAndWait();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur d'ajout");
                    alert.showAndWait();
                }


            }}catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner un Vol");
            alert.showAndWait();
        }
    }

    public void display(String username ,String idClient){
        welcomeText.setText(username);
        tid.setText(idClient);
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

}
