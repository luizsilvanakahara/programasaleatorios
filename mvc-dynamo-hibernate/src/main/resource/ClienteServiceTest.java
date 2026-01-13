package com.exemplo.mvc.service;

import com.exemplo.mvc.model.Cliente;
import com.exemplo.mvc.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteRepository repository;
    private ClienteService service;

    @BeforeEach
    void setup() {
        repository = mock(ClienteRepository.class);
        service = new ClienteService(repository);
    }

    @Test
    void criarCliente_deveSalvarCliente() {
        Cliente cliente = new Cliente("1", "Luis", "luis@mail.com");
        service.criarCliente(cliente);
        verify(repository, times(1)).salvar(cliente);
    }

    @Test
    void obterCliente_deveRetornarCliente() {
        Cliente cliente = new Cliente("1", "Luis", "luis@mail.com");
        when(repository.buscarPorId("1")).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = service.obterCliente("1");

        assertTrue(result.isPresent());
        assertEquals("Luis", result.get().getNome());
    }
}
