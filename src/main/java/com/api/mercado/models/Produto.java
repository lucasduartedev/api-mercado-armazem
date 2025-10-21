package com.api.mercado.models;

import com.api.mercado.models.dto.ProdutoAtualizarDadosDTO;
import com.api.mercado.models.dto.ProdutoCadastroDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private String descricao;
    private Integer quantidade;
    private Boolean disponibilidade = true;

    public Produto(ProdutoCadastroDTO dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.descricao = dados.descricao();
        this.quantidade = dados.quantidade();
    }

    public void atualizarInformacoes(ProdutoAtualizarDadosDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.preco() != null) {
            this.preco = dados.preco();
        }
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
        }
    }

    public void exclusaoLogica() {
        this.disponibilidade = false;
    }

}
