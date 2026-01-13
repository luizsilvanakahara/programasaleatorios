package com.exemplo.mvc.controller;

import com.exemplo.mvc.model.Cliente;
import com.exemplo.mvc.service.ClienteService;

public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    public void criarCliente(String id, String nome, String email) {
        Cliente cliente = new Cliente(id, nome, email);
        service.criarCliente(cliente);
    }

    public void buscarCliente(String id) {
        service.obterCliente(id).ifPresentOrElse(
            c -> System.out.println("Cliente encontrado: " + c.getNome()),
            () -> System.out.println("Cliente não encontrado")
        );
    }
}
