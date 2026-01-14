package com.upload.controller;

import com.upload.model.Usuario;
import com.upload.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/importar")
    public ResponseEntity<String> importar(@RequestParam MultipartFile file) {
        service.importarCSV(file);
        return ResponseEntity.ok("Arquivo importado com sucesso");
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }
}
