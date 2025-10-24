package com.api.mercado.dto;

import jakarta.validation.constraints.NotNull;

public record ClienteAtualizarDadosDTO(
    @NotNull
    Long id,
    String nome,
    String cpf,
    String cep,
    String logradouro,
    String numero,
    String telefone,
    String email
) {

}
