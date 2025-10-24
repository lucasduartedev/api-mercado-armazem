package com.api.mercado.Controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.mercado.models.Funcionario;
import com.api.mercado.models.dto.FuncionarioAtualizarDadosDTO;
import com.api.mercado.models.dto.FuncionarioDadosCadastroDTO;
import com.api.mercado.models.dto.FuncionarioListagemDTO;
import com.api.mercado.services.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Funcionario> cadastrarFuncinario(@RequestBody @Valid FuncionarioDadosCadastroDTO dados) {
        var funcionario = new Funcionario(dados);
        funcionarioService.cadastrarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioListagemDTO>> listarFuncionariosAtivos(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        Page<Funcionario> funcionario = funcionarioService.listarFuncionariosAtivos(paginacao);
        Page<FuncionarioListagemDTO> dados = funcionario.map(FuncionarioListagemDTO::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorId2(id);
        return funcionario.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarFuncionario(@RequestBody @Valid FuncionarioAtualizarDadosDTO dados) {
        var funcionario = funcionarioService.buscarFuncionarioPorId(dados.id());
        try {
            funcionario.atualizarInformacoes(dados);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativarFuncionario(@PathVariable Long id) {
        var funcionario = funcionarioService.buscarFuncionarioPorId(id);
        funcionario.ativarPessoa();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        var funcionario = funcionarioService.buscarFuncionarioPorId(id);
        funcionario.exclusaoLogica();
        return ResponseEntity.ok().build();
    }
    
}
