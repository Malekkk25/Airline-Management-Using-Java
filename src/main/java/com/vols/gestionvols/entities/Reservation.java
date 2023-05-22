package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reservation {

    private SimpleIntegerProperty idRes,idVol,idClient;

    private SimpleStringProperty etat,date,numVol,nomClient;


    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Reservation() {
        this.idRes= new SimpleIntegerProperty();
        this.idVol= new SimpleIntegerProperty();
        this.idClient= new SimpleIntegerProperty();
        this.etat = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.numVol = new SimpleStringProperty();
        this.nomClient=new SimpleStringProperty();
    }

    public String getNomClient() {
        return nomClient.get();
    }

    public SimpleStringProperty nomClientProperty() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient.set(nomClient);
    }

    public String getNumVol() {
        return numVol.get();
    }

    public SimpleStringProperty numVolProperty() {
        return numVol;
    }

    public void setNumVol(String numVol) {
        this.numVol.set(numVol);
    }

    public int getIdRes() {
        return idRes.get();
    }

    public SimpleIntegerProperty idResProperty() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes.set(idRes);
    }

    public int getIdVol() {
        return idVol.get();
    }

    public SimpleIntegerProperty idVolProperty() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol.set(idVol);
    }

    public int getIdClient() {
        return idClient.get();
    }

    public SimpleIntegerProperty idClientProperty() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient.set(idClient);
    }

    public String getEtat() {
        return etat.get();
    }

    public SimpleStringProperty etatProperty() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat.set(etat);
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "idRes=" + idRes +
                ", idVol=" + idVol +
                ", idClient=" + idClient +
                ", etat=" + etat +
                '}';
    }
}
