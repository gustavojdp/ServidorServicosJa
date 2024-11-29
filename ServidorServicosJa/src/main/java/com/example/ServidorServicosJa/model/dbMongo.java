package com.example.ServidorServicosJa.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Professional")  // Nome da coleção no MongoDB
public class dbMongo {

    private String id;
    private String nome;
    private String email;


}
