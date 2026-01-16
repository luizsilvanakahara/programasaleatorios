package com.exemplo.aluno.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.exemplo.aluno.model.Aluno;
import com.exemplo.aluno.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    public List<Aluno> listar() {
        return repository.findAll();
    }

    public Aluno buscarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    public Aluno atualizar(String id, Aluno aluno) {
        aluno.setId(id);
        return repository.save(aluno);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}
