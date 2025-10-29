package com.api.mercado.models;

import java.time.LocalDateTime;

import com.api.mercado.dto.FuncionarioAtualizarDadosDTO;
import com.api.mercado.dto.FuncionarioDadosCadastroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Pessoa{

    @Column(name = "salario")
    private Double salario;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "data_admissao")
    private LocalDateTime dataAdmissao = LocalDateTime.now();
    
    @Column(name = "data_demissao")
    private LocalDateTime dataDemissao;

    // * Construtores
    public Funcionario(FuncionarioDadosCadastroDTO dados) {
        super(
            dados.nome(),
            dados.cpf(),
            dados.cep(),
            dados.logradouro(),
            dados.numero(),
            dados.telefone(),
            dados.email()
        );
        this.salario = dados.salario();
        this.departamento = dados.departamento();
    }

    public void atualizarInformacoes(FuncionarioAtualizarDadosDTO dados) {
        if(dados.nome() != null) {
            this.setNome(dados.nome());
        }
        if(dados.cpf() != null) {
            this.setCpf(dados.cpf());
        }
        if(dados.salario() != null) {
            this.setSalario(dados.salario());
        }
        if(dados.cep() != null) {
            this.getEndereco().setCep(dados.cep());
        }
        if(dados.logradouro() != null) {
            this.getEndereco().setLogradouro(dados.logradouro());
        }
        if(dados.numero() != null) {
            this.getEndereco().setNumero(dados.numero());
        }
        if(dados.telefone() != null) {
            this.getContato().setTelefone(dados.telefone());
        }
        if(dados.email() != null) {
            this.getContato().setEmail(dados.email());
        }
        if(dados.departamento() != null) {
            this.departamento = dados.departamento();
        }
    }

    public void ativarFuncionario() {
        super.setAtivo(true);
    }

}
