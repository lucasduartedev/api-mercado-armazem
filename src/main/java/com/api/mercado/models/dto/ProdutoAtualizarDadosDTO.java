package com.api.mercado.models.dto;

import jakarta.validation.constraints.NotNull;

public record ProdutoAtualizarDadosDTO(
    @NotNull
    Long id,
    String nome,
    Double preco,
    String descricao,
    Integer estoque,
    String categoria
) {
    
}
