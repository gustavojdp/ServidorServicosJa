package com.example.ServidorServicosJa.controllers;

import com.example.ServidorServicosJa.model.dbMongo;
import com.example.ServidorServicosJa.service.dbMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private dbMongoService userService;

    // Requisição GET para buscar um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<dbMongo> buscarUsuarioPorId(@PathVariable String id) {
        dbMongo usuario = userService.buscarUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Requisição POST para salvar um novo usuário
    @PostMapping
    public ResponseEntity<dbMongo> salvarUsuario(@RequestBody dbMongo usuario) {
        // Validação do campo "status" (S ou N)
        if (!usuario.getStatus().equals("S") && !usuario.getStatus().equals("N")) {
            return ResponseEntity.badRequest().body(null); // Se o status não for "S" ou "N", retorna erro
        }

        // Salva o usuário
        dbMongo usuarioSalvo = userService.salvarUsuario(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }
}
