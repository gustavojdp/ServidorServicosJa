package com.example.ServidorServicosJa.repository;

import com.example.ServidorServicosJa.model.dbMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface dbMongoRepository extends MongoRepository<dbMongo, String> {

    // Você pode adicionar consultas customizadas aqui, se necessário
}

