package com.api.mercado.models;

import java.time.LocalDateTime;

import com.api.mercado.dto.ClienteAtualizarDadosDTO;
import com.api.mercado.dto.ClienteDadosCadastroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now();

    // * Constructors
    public Cliente(ClienteDadosCadastroDTO dados) {
        super(
            dados.nome(),
            dados.cpf(),
            dados.cep(),
            dados.logradouro(),
            dados.numero(),
            dados.telefone(),
            dados.email()
        );
    }

    public void atualizarInformacoes(ClienteAtualizarDadosDTO dados) {
        if(dados.nome() != null) {
            this.setNome(dados.nome());
        }
        if(dados.cpf() != null) {
            this.setCpf(dados.cpf());
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
    }

    public void ativarCliente() {
        super.setAtivo(true);
    }

}
