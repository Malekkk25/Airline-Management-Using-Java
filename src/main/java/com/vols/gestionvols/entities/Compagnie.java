package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Compagnie {
    private SimpleIntegerProperty idComp;

    private SimpleStringProperty libelle;
    private SimpleStringProperty code;

    public Compagnie() {
        this.idComp = new SimpleIntegerProperty();
        this.libelle = new SimpleStringProperty();
        this.code = new SimpleStringProperty();

    }

    public Compagnie(int idComp, String libelle,  String code) {
        this.idComp = new SimpleIntegerProperty(idComp);
        this.libelle = new SimpleStringProperty(libelle);
        this.code= new SimpleStringProperty(code);

    }


    public int getIdComp() {
        return idComp.get();
    }

    public SimpleIntegerProperty idCompProperty() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp.set(idComp);
    }

    public String getLibelle() {
        return libelle.get();
    }

    public SimpleStringProperty libelleProperty() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle.set(libelle);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    @Override
    public String toString() {
        return "Compagnie{" +
                "idComp=" + idComp +
                ", libelle=" + libelle +
                ", code=" + code +
                '}';
    }
}
