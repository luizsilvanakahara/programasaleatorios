package com.upload.service;

import com.opencsv.CSVReader;
import com.upload.model.Usuario;
import com.upload.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void importarCSV(MultipartFile file) {
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(file.getInputStream()))) {

            String[] linha;
            while ((linha = reader.readNext()) != null) {
                Usuario u = new Usuario();
                u.setNome(linha[0]);
                u.setEmail(linha[1]);
                repository.save(u);
                log.info("Usuário salvo: {}", u.getEmail());
            }

        } catch (Exception e) {
            log.error("Erro ao importar arquivo", e);
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }
}
