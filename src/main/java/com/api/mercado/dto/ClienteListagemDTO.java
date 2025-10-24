package com.api.mercado.models.dto;

import com.api.mercado.models.Cliente;

public record ClienteListagemDTO(Long id, String nome, String cpf, String telefone, String email) {
    
    public ClienteListagemDTO(Cliente cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getContato() != null ? cliente.getContato().getTelefone() : null,
            cliente.getContato() != null ? cliente.getContato().getEmail() : null
        );
    }

}
