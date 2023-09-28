package com.cielo.clients_pre_register.models;


import com.cielo.clients_pre_register.utils.Util;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="CLIENT_PF")

public class ClientPfModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID idClient;
    private String name;
    private String email;
    private int mcc;
    private String cpf;

    public ClientPfModel(String name, String email, int mcc, String cpf) {
        this.name = name;
        this.email = email;
        this.mcc = mcc;
        this.cpf = cpf;
    }

    public ClientPfModel(UUID idClient, String name, String email, int mcc, String cpf) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.mcc = mcc;
        this.cpf = cpf;
    }

    public UUID getIdClient() {
        return idClient;
    }

    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }

    public ClientPfModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Util.cpfFormat(cpf);
    }
}
