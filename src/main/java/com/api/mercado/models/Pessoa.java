package com.api.mercado.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @Embedded
    private Endereco endereco = new Endereco();

    @Embedded
    private Contato contato = new Contato();
    
    private Boolean ativo = true;

    // * Constructors
    public Pessoa(String nome, String cpf, String cep, String logradrouro, String numero, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = new Endereco(cep, logradrouro, numero);
        this.contato = new Contato(telefone, email);
        this.ativo = true;
    }

    // * Construtor simples
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = new Endereco();
        this.contato = new Contato();
        this.ativo = true;
    }

    // * Métodos
    public void alternarStatusAtivo() {
        this.ativo = !this.ativo;
    }

    public void ativarPessoa() {
        this.ativo = true;
    }
    
    // * Evitar exclusão permanente na base de dados
    public void exclusaoLogica() {
        this.ativo = false;
    }

}
