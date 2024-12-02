package com.example.ServidorServicosJa.service;

import com.example.ServidorServicosJa.model.dbMongo;
import com.example.ServidorServicosJa.repository.dbMongoRepository;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dbMongoService {

    @Autowired
    private dbMongoRepository mongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate; // MongoTemplate para verificar a conexão

    @PostConstruct
    public void verificarConexao() {
        try {
            // Tenta fazer uma operação simples para verificar se a conexão foi bem-sucedida
            mongoTemplate.getDb().listCollections();  // Exemplo de operação para verificar a conexão
            System.out.println("Conexão com o MongoDB estabelecida com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar no MongoDB: " + e.getMessage());
        }
    }

    // Método para buscar um usuário por ID
    public dbMongo buscarUsuarioPorId(String id) {
        return mongoRepository.findById(id).orElse(null);
    }

    // Método para salvar um novo usuário
    public dbMongo salvarUsuario(dbMongo usuario) {
        return mongoRepository.save(usuario);
    }
}

