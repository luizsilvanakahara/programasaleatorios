package com.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.upload.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
