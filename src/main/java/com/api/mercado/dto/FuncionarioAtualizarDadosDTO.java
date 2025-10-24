package com.api.mercado.models.dto;

import jakarta.validation.constraints.NotNull;

public record FuncionarioAtualizarDadosDTO(
    @NotNull
    Long id,
    String nome,
    String cpf,
    Double salario,
    String cep,
    String logradouro,
    String numero,
    String telefone,
    String email
) {

}
