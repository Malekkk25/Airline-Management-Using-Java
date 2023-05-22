module com.vols.gestionvols {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.vols.gestionvols to javafx.fxml;
    exports com.vols.gestionvols;
    exports com.vols.gestionvols.controllers;
    opens com.vols.gestionvols.controllers to javafx.fxml;
}