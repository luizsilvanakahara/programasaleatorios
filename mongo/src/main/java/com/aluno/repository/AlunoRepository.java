package com.exemplo.aluno.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.exemplo.aluno.model.Aluno;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
}
