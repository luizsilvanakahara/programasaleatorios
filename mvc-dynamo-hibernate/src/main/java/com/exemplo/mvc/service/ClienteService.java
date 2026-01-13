package com.exemplo.mvc.service;

import com.exemplo.mvc.model.Cliente;
import com.exemplo.mvc.repository.ClienteRepository;

import java.util.Optional;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void criarCliente(Cliente cliente) {
        repository.salvar(cliente);
    }

    public Optional<Cliente> obterCliente(String id) {
        return repository.buscarPorId(id);
    }
}
