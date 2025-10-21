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

import com.api.mercado.models.Produto;
import com.api.mercado.models.dto.ProdutoAtualizarDadosDTO;
import com.api.mercado.models.dto.ProdutoCadastroDTO;
import com.api.mercado.models.dto.ProdutoListagemDTO;
import com.api.mercado.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // @PostMapping
    // @Transactional
    // public void cadastrarProduto(@RequestBody ProdutoCadastroDTO dados) {
    //     produtoService.cadastrarProduto(new Produto(dados));
    // }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrarproduto(@RequestBody @Valid ProdutoCadastroDTO dados) {
        Produto produto = new Produto(dados);
        produtoService.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
    
    // @GetMapping
    // public Page<ProdutoListagemDTO> listarProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
    //     return produtoService.listarProdutosDisponiveis(paginacao).map(ProdutoListagemDTO::new);
    // }

    @GetMapping
    public ResponseEntity<Page<ProdutoListagemDTO>> listarTodosProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<Produto> produtos = produtoService.listarProdutosDisponiveis(paginacao);
        Page<ProdutoListagemDTO> dados = produtos.map(ProdutoListagemDTO::new);
        return ResponseEntity.ok(dados);
    }

    // @GetMapping("/{id}")
    // public Optional<Produto> burcarProdutoPorId(@PathVariable Long id) {
    //     return produtoService.buscarPorId2(id);
    // }
        
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId2(id);
        return produto.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // @PutMapping
    // @Transactional
    // public void atualizarProduto(@RequestBody @Valid ProdutoAtualizarDadosDTO dados) {
    //     var produto = produtoService.buscarPorId(dados.id());
    //     produto.atualizarInformacoes(dados);
    // }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarProduto(@RequestBody @Valid ProdutoAtualizarDadosDTO dados) {
        var produto = produtoService.buscarPorId(dados.id());
        produto.atualizarInformacoes(dados);
        return ResponseEntity.ok().build();
    }

    // @DeleteMapping("/{id}")
    // @Transactional
    // public void excluirProduto(@PathVariable Long id) {
    //     var produto = produtoService.buscarPorId(id);
    //     produto.exclusaoLogica();
    // }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        var produto = produtoService.buscarPorId(id);
        produto.exclusaoLogica();
        return ResponseEntity.noContent().build();
    }

}
