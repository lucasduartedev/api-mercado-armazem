package com.api.mercado.models.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoCadastroDTO(

    @NotBlank
    String nome,

    @NotNull
    @DecimalMin(value = "0.01", message = "Valor mínimo de 0.01")
    Double preco,

    @NotBlank
    String descricao,

    @Min(value = 1, message = "Valor mínido de 1")
    Integer estoque,

    @NotBlank
    String categoria

) {
    
}
