package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aeroport {
    private SimpleIntegerProperty idAir;

    private SimpleStringProperty nomAeroport;
    private SimpleStringProperty ville;

    public Aeroport() {
        this.idAir = new SimpleIntegerProperty();
        this.nomAeroport = new SimpleStringProperty();
        this.ville = new SimpleStringProperty();

    }

    public Aeroport(int idAir, String nomAeroport,  String ville) {
        this.idAir = new SimpleIntegerProperty(idAir);
        this.nomAeroport = new SimpleStringProperty(nomAeroport);
        this.ville= new SimpleStringProperty(ville);

    }


    public int getIdAir() {
        return idAir.get();
    }

    public SimpleIntegerProperty idAirProperty() {
        return idAir;
    }

    public void setIdAir(int idAir) {
        this.idAir.set(idAir);
    }

    public String getNomAeroport() {
        return nomAeroport.get();
    }

    public SimpleStringProperty nomAeroportProperty() {
        return nomAeroport;
    }

    public void setNomAeroport(String nomAeroport) {
        this.nomAeroport.set(nomAeroport);
    }

    public String getVille() {
        return ville.get();
    }

    public SimpleStringProperty villeProperty() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "idAir=" + idAir +
                ", nomAeroport=" + nomAeroport +
                ", ville=" + ville +
                '}';
    }
}
