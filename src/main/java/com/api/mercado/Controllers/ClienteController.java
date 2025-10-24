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

import com.api.mercado.dto.ClienteAtualizarDadosDTO;
import com.api.mercado.dto.ClienteDadosCadastroDTO;
import com.api.mercado.dto.ClienteListagemDTO;
import com.api.mercado.models.Cliente;
import com.api.mercado.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDadosCadastroDTO dados) {
        Cliente cliente = new Cliente(dados);
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteListagemDTO>> listarClientesAtvios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Cliente> clientes = clienteService.listarClientesAtivos(paginacao);
        Page<ClienteListagemDTO> dados = clientes.map(ClienteListagemDTO::new);
        return ResponseEntity.ok(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId2(id);
        return cliente.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarCliente(@RequestBody @Valid ClienteAtualizarDadosDTO dados) {
        var cliente = clienteService.buscarClientePorId(dados.id());
        try {
            cliente.atualizarInformacoes(dados);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativarCliente(@PathVariable Long id) {
        var cliente = clienteService.buscarClientePorId(id);
        cliente.ativarCliente();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        var cliente = clienteService.buscarClientePorId(id);
        cliente.exclusaoLogica();
        return ResponseEntity.noContent().build();
    }
    
}
