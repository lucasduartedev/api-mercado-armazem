package com.api.mercado.models.dto;

import com.api.mercado.models.Produto;

public record ProdutoListagemDTO(Long id, String nome, Double preco, String descricao, Integer estoque, String categoria) {

    public ProdutoListagemDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getEstoque(), produto.getCategoria());
    }

}
