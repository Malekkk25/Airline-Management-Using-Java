package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Escale {

    private SimpleIntegerProperty idEscale ;
    private SimpleIntegerProperty idVol;

    private SimpleStringProperty dateDepart,dateArrivee,heureDepart,heureArrivee,numVol,airDepart,airArrivee;

    public Escale() {
        this.idEscale = new SimpleIntegerProperty();
        this.airDepart = new SimpleStringProperty();
        this.airArrivee = new SimpleStringProperty();
        this.idVol = new SimpleIntegerProperty();
        this.dateArrivee = new SimpleStringProperty();
        this.dateDepart = new SimpleStringProperty();
        this.heureArrivee = new SimpleStringProperty();
        this.heureDepart = new SimpleStringProperty();
        this.numVol = new SimpleStringProperty();

    }

    public int getIdEscale() {
        return idEscale.get();
    }

    public SimpleIntegerProperty idEscaleProperty() {
        return idEscale;
    }

    public void setIdEscale(int idEscale) {
        this.idEscale.set(idEscale);
    }

    public String getAirDepart() {
        return airDepart.get();
    }

    public SimpleStringProperty airDepartProperty() {
        return airDepart;
    }

    public void setAirDepart(String airDepart) {
        this.airDepart.set(airDepart);
    }

    public String getAirArrivee() {
        return airArrivee.get();
    }

    public SimpleStringProperty airArriveeProperty() {
        return airArrivee;
    }

    public void setAirArrivee(String airArrivee) {
        this.airArrivee.set(airArrivee);
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

    public String getDateDepart() {
        return dateDepart.get();
    }

    public SimpleStringProperty dateDepartProperty() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart.set(dateDepart);
    }

    public String getDateArrivee() {
        return dateArrivee.get();
    }

    public SimpleStringProperty dateArriveeProperty() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee.set(dateArrivee);
    }

    public String getHeureDepart() {
        return heureDepart.get();
    }

    public SimpleStringProperty heureDepartProperty() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart.set(heureDepart);
    }

    public String getHeureArrivee() {
        return heureArrivee.get();
    }

    public SimpleStringProperty heureArriveeProperty() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee.set(heureArrivee);
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

    @Override
    public String toString() {
        return "Escale{" +
                "idEscale=" + idEscale +
                ", airDepart=" + airDepart +
                ", airArrivee=" + airArrivee +
                ", idVol=" + idVol +
                ", dateDepart=" + dateDepart +
                ", dateArrivee=" + dateArrivee +
                ", heureDepart=" + heureDepart +
                ", heureArrivee=" + heureArrivee +
                ", numVol=" + numVol +
                '}';
    }
}
