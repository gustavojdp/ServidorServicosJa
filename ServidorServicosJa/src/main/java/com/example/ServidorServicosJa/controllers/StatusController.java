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

    @GetMapping
    public ResponseEntity getStatusUser(){
        return ResponseEntity.ok("deu ok");
    }

    @Autowired
    private dbMongoService userService;

    @GetMapping
    public ResponseEntity<List<dbMongo>> listarUsuarios() {
        return ResponseEntity.ok(userService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<dbMongo> buscarUsuarioPorId(@PathVariable String id) {
        dbMongo usuario = userService.buscarUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<dbMongo> salvarUsuario(@RequestBody dbMongo usuario) {
        return ResponseEntity.ok(userService.salvarUsuario(usuario));
    }
}
