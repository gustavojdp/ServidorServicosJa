package com.example.ServidorServicosJa.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "isProfessional") // Mapeia para a coleção "User" no MongoDB
public class dbMongo {

    @Id
    private String id; // Mapeia para "_id"
    private String name; // Nome do usuário
    private String email; // E-mail do usuário
    private String status; // Status (S para profissional, N para não-profissional)

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
