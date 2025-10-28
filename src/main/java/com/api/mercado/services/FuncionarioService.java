package com.api.mercado.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.mercado.dto.FuncionarioAtualizarDadosDTO;
import com.api.mercado.exceptions.EntidadeNaoEncontradaException;
import com.api.mercado.models.Funcionario;
import com.api.mercado.repositories.FuncionarioRepostiroty;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

    private FuncionarioRepostiroty funcionarioRepostiroty;

    public FuncionarioService(FuncionarioRepostiroty funcionarioRepostiroty) {
        this.funcionarioRepostiroty = funcionarioRepostiroty;
    }

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioRepostiroty.save(funcionario);
    }

    public Page<Funcionario> listarFuncionariosAtivos(Pageable paginacao) {
        return funcionarioRepostiroty.findAllByAtivoTrue(paginacao);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepostiroty.findById(id);
    }

    @Transactional
    public Optional<Funcionario> atualizFuncionario(FuncionarioAtualizarDadosDTO dados) {
        return funcionarioRepostiroty.findById(dados.id())
            .map(funcionario -> {
                funcionario.atualizarInformacoes(dados);
                return funcionario;
            });
    }

    public void excluirFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepostiroty.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario não encontrado"));
        funcionario.exclusaoLogica();
    }

    @Transactional
    public void ativarFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepostiroty.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario não encontrado"));
        funcionario.ativarFuncionario();
    }

}
