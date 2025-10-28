package com.api.mercado.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.mercado.dto.ClienteAtualizarDadosDTO;
import com.api.mercado.exceptions.EntidadeNaoEncontradaException;
import com.api.mercado.models.Cliente;
import com.api.mercado.repositories.ClienteRepository;

import jakarta.transaction.Transactional;

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

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Optional<Object> atualizarCliente(ClienteAtualizarDadosDTO dados) {
        return clienteRepository.findById(dados.id())
            .map(cliente -> {
                cliente.atualizarInformacoes(dados);
                return cliente;
            });
    }

    public void excluirCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        cliente.exclusaoLogica();
    }

    public void ativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        cliente.ativarCliente();
    }
    
}
