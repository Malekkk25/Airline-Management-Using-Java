package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vol {
    private SimpleIntegerProperty idVol;
    private SimpleStringProperty numVol,etat,dDep,dArr,tDep,tArr,comp,airDep,airArr;
    private SimpleIntegerProperty capacite;

    public Vol() {
        this.idVol = new SimpleIntegerProperty();
        this.numVol = new SimpleStringProperty();
        this.capacite = new SimpleIntegerProperty();
        this.etat=new SimpleStringProperty();
        this.dDep=new SimpleStringProperty();
        this.dArr=new SimpleStringProperty();
        this.tDep=new SimpleStringProperty();
        this.comp=new SimpleStringProperty();
        this.airArr=new SimpleStringProperty();
        this.airDep=new SimpleStringProperty();
        this.tArr=new SimpleStringProperty();
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

    public String getNumVol() {
        return numVol.get();
    }

    public SimpleStringProperty numVolProperty() {
        return numVol;
    }

    public void setNumVol(String numVol) {
        this.numVol.set(numVol);
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

    public String getdDep() {
        return dDep.get();
    }

    public SimpleStringProperty dDepProperty() {
        return dDep;
    }

    public void setdDep(String dDep) {
        this.dDep.set(dDep);
    }

    public String getdArr() {
        return dArr.get();
    }

    public SimpleStringProperty dArrProperty() {
        return dArr;
    }

    public void setdArr(String dArr) {
        this.dArr.set(dArr);
    }

    public String gettDep() {
        return tDep.get();
    }

    public SimpleStringProperty tDepProperty() {
        return tDep;
    }

    public void settDep(String tDep) {
        this.tDep.set(tDep);
    }

    public String gettArr() {
        return tArr.get();
    }

    public SimpleStringProperty tArrProperty() {
        return tArr;
    }

    public void settArr(String tArr) {
        this.tArr.set(tArr);
    }

    public String getComp() {
        return comp.get();
    }

    public SimpleStringProperty compProperty() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp.set(comp);
    }

    public String getAirDep() {
        return airDep.get();
    }

    public SimpleStringProperty airDepProperty() {
        return airDep;
    }

    public void setAirDep(String airDep) {
        this.airDep.set(airDep);
    }

    public String getAirArr() {
        return airArr.get();
    }

    public SimpleStringProperty airArrProperty() {
        return airArr;
    }

    public void setAirArr(String airArr) {
        this.airArr.set(airArr);
    }

    public int getCapacite() {
        return capacite.get();
    }

    public SimpleIntegerProperty capaciteProperty() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite.set(capacite);
    }

    @Override
    public String toString() {
        return "Vol{" +
                "idVol=" + idVol +
                ", numVol=" + numVol +
                ", etat=" + etat +
                ", dDep=" + dDep +
                ", dArr=" + dArr +
                ", tDep=" + tDep +
                ", tArr=" + tArr +
                ", comp=" + comp +
                ", airDep=" + airDep +
                ", airArr=" + airArr +
                ", capacite=" + capacite +
                '}';
    }
}