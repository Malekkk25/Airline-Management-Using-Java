package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Passager {

    private SimpleIntegerProperty idClient,idVol;

    private SimpleStringProperty numVol,numPass,nomPassager;


    public Passager() {
        this.idClient = new SimpleIntegerProperty();
        this.idVol = new SimpleIntegerProperty();
        this.numVol = new SimpleStringProperty();
        this.numPass = new SimpleStringProperty();
        this.nomPassager = new SimpleStringProperty();

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

    public String getNumVol() {
        return numVol.get();
    }

    public SimpleStringProperty numVolProperty() {
        return numVol;
    }

    public void setNumVol(String numVol) {
        this.numVol.set(numVol);
    }

    public String getNumPass() {
        return numPass.get();
    }

    public SimpleStringProperty numPassProperty() {
        return numPass;
    }

    public void setNumPass(String numPass) {
        this.numPass.set(numPass);
    }

    public String getNomPassager() {
        return nomPassager.get();
    }

    public SimpleStringProperty nomPassagerProperty() {
        return nomPassager;
    }

    public void setNomPassager(String nomPassager) {
        this.nomPassager.set(nomPassager);
    }

    @Override
    public String toString() {
        return "Passager{" +
                "idClient=" + idClient +
                ", numVol=" + numVol +
                ", numPass=" + numPass +
                ", nomPassager=" + nomPassager +
                '}';
    }
}
