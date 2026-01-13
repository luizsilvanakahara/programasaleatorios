package com.exemplo.mvc.repository;

import com.exemplo.mvc.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ClienteRepository {

    private static final Logger logger = LogManager.getLogger(ClienteRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(Cliente cliente) {
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        logger.info("Cliente salvo: {}", cliente.getId());
    }

    public Optional<Cliente> buscarPorId(String id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        return Optional.ofNullable(cliente);
    }
}
