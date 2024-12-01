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

    public List<dbMongo> listarUsuarios() {
        List<dbMongo> usuarios = mongoRepository.findAll();
        System.out.println("Usuários encontrados: " + usuarios.size());

        // Teste com MongoTemplate
        List<dbMongo> usuariosTemplate = mongoTemplate.findAll(dbMongo.class);
        System.out.println("Usuários encontrados no MongoTemplate: " + usuariosTemplate.size());

        return usuarios;
    }


    public dbMongo buscarUsuarioPorId(String id) {
        return mongoRepository.findById(id).orElse(null);
    }

    public dbMongo salvarUsuario(dbMongo usuario) {
        return mongoRepository.save(usuario);
    }
}

