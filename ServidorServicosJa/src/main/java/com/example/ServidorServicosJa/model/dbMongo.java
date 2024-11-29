package com.example.ServidorServicosJa.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")  // Nome da coleção no MongoDB
public class dbMongo {

    private String id;
    private String nome;
    private String email;

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
