package com.cielo.clients_pre_register.models;

import jakarta.persistence.Entity;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="CLIENT_PJ")
public class ClientPjModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID idClient;

    private String cnpj;

    private String corporate_reason;

    private int mcc;

    private String contact_cpf;

    private String contact_name;

    private String contact_email;


    public ClientPjModel(UUID idClient, String cnpj, String corporate_reason, int mcc, String contact_cpf, String contact_name, String contact_email) {
        this.idClient = idClient;
        this.cnpj = cnpj;
        this.corporate_reason = corporate_reason;
        this.mcc = mcc;
        this.contact_cpf = contact_cpf;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
    }

    public ClientPjModel(String cnpj, String corporate_reason, int mcc, String contact_cpf, String contact_name, String contact_email) {
        this.cnpj = cnpj;
        this.corporate_reason = corporate_reason;
        this.mcc = mcc;
        this.contact_cpf = contact_cpf;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
    }

    public ClientPjModel() {
    }

    public UUID getIdClient() {
        return idClient;
    }

    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCorporate_reason() {
        return corporate_reason;
    }

    public void setCorporate_reason(String corporate_reason) {
        this.corporate_reason = corporate_reason;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public String getContact_cpf() {
        return contact_cpf;
    }

    public void setContact_cpf(String contact_cpf) {
        this.contact_cpf = contact_cpf;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }
}
