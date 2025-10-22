package com.api.mercado.models;

import java.time.LocalDateTime;

import com.api.mercado.models.dto.ProdutoAtualizarDadosDTO;
import com.api.mercado.models.dto.ProdutoCadastroDTO;

import jakarta.persistence.Column;
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
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private String descricao;
    private Integer estoque;
    private String categoria;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();
    private Boolean ativo = true;

    public Produto(ProdutoCadastroDTO dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.descricao = dados.descricao();
        this.estoque = dados.estoque();
        this.categoria = dados.categoria();
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
        if(dados.estoque() != null) {
            this.estoque = dados.estoque();
        }
        if(dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
    }

    public void alternarStatusAtivo() {
        this.ativo = !this.ativo;
    }

    public void ativarProduto() {
        this.ativo = true;
    }

    // * Evitar exclusão permanente na base de dados
    public void exclusaoLogica() {
        this.ativo = false;
    }

}
