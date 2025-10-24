package com.api.mercado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDadosCadastroDTO(

    @NotBlank
    String nome,
    @NotNull
    String cpf,
    String cep,
    String logradouro,
    String numero,
    String telefone,
    String email
) {

}
