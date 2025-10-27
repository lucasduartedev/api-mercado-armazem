package com.api.mercado.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.mercado.dto.ProdutoAtualizarDadosDTO;
import com.api.mercado.exceptions.EntidadeNaoEncontradaException;
import com.api.mercado.models.Produto;
import com.api.mercado.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> listarProdutosDisponiveis(Pageable paginacao) {
        return produtoRepository.findAllByAtivoTrue(paginacao);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // public Produto atualizarProduto(Produto produto) {
    //     return produtoRepository.save(produto);
    // }

    @Transactional
    public Optional<Object> atualizarProduto(ProdutoAtualizarDadosDTO dados) {
        return produtoRepository.findById(dados.id())
            .map(produto -> {
                produto.atualizarInformacoes(dados);
                return produto;
            });
    }

    public void excluirProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado."));
        produto.exclusaoLogica();
    }

    @Transactional
    public void ativarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado."));
        produto.ativarProduto();
    }

}
