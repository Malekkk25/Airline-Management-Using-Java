package com.vols.gestionvols.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {
    private SimpleIntegerProperty idClient;

    private SimpleStringProperty username,password,sexe,email,numPass,etat;


    public Client() {
        this.idClient = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.sexe = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.numPass = new SimpleStringProperty();
        this.etat = new SimpleStringProperty();

    }

    public Client(int idClient, String username,  String password,String sexe,String email,String numPass ,String etat) {
        this.idClient = new SimpleIntegerProperty(idClient);
        this.username = new SimpleStringProperty(username);
        this.password= new SimpleStringProperty(password);
        this.sexe= new SimpleStringProperty(sexe);
        this.email= new SimpleStringProperty(email);
        this.numPass= new SimpleStringProperty(numPass);
        this.etat= new SimpleStringProperty(etat);
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

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getSexe() {
        return sexe.get();
    }

    public SimpleStringProperty sexeProperty() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe.set(sexe);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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
        return "Client{" +
                "idClient=" + idClient +
                ", username=" + username +
                ", password=" + password +
                ", sexe=" + sexe +
                ", email=" + email +
                ", numPass=" + numPass +
                ", etat=" + etat +
                '}';
    }
}
