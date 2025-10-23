package com.api.mercado.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.mercado.models.Cliente;
import com.api.mercado.repositories.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> listarClientesAtivos(Pageable pageable) {
        return clienteRepository.findAllByAtivoTrue(pageable);
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.getReferenceById(id);
    }

    public Optional<Cliente> buscarClientePorId2(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
}
