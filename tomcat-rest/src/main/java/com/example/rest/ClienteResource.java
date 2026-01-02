package com.example.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/clientes")
public class ClienteResource {

    private static final List<Cliente> clientes = new ArrayList<>();

    static {
        clientes.add(new Cliente("Luis"));
        clientes.add(new Cliente("Maria"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listar() {
        return clientes;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluir(Cliente cliente) {
        clientes.add(cliente);
        return Response.ok(cliente).build();
    }

    // Classe interna Cliente simples
    public static class Cliente {
        private String nome;

        public Cliente() {} // necessário para JSON-B

        public Cliente(String nome) { this.nome = nome; }

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }
}
