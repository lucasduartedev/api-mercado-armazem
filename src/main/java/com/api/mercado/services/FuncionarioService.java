package com.api.mercado.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.mercado.models.Funcionario;
import com.api.mercado.repositories.FuncionarioRepostiroty;

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

    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepostiroty.getReferenceById(id);
    }

    public Optional<Funcionario> buscarFuncionarioPorId2(Long id) {
        return funcionarioRepostiroty.findById(id);
    }

    public Funcionario atualizFuncionario(Funcionario funcionario) {
        return funcionarioRepostiroty.save(funcionario);
    }
    
}
