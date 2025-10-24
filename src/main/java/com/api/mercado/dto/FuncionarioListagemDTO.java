package com.api.mercado.dto;

import com.api.mercado.models.Funcionario;

public record FuncionarioListagemDTO(Long id, String nome, String cpf, String telefone, String email) {

    public FuncionarioListagemDTO(Funcionario funcionario) {
        this(
            funcionario.getId(),
            funcionario.getNome(),
            funcionario.getCpf(),
            funcionario.getContato() != null ? funcionario.getContato().getTelefone() : null,
            funcionario.getContato() != null ? funcionario.getContato().getEmail() : null
        );
    }
    
}
