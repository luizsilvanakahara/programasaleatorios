package com.exemplo.comite.controller;

import com.exemplo.comite.model.Comite;
import com.exemplo.comite.repository.ComiteRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comites")
public class ComiteController {

    private final ComiteRepository repository;

    public ComiteController(ComiteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Comite> criar(@RequestBody Comite comite) {
        comite.setId(UUID.randomUUID().toString());
        repository.save(comite);
        return ResponseEntity.status(HttpStatus.CREATED).body(comite);
    }
}
