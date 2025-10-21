package com.api.mercado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.mercado.models.Produto;
import com.api.mercado.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.getReferenceById(id);
    }

    public Optional<Produto> buscarPorId2(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Page<Produto> listarProdutosDisponiveis(Pageable paginacao) {
        return produtoRepository.findAllByDisponibilidadeTrue(paginacao);
    }

    // public List<Produto> listarProdutosDisponiveisOrdenadosPorNome() {
    //     return produtoRepository.findAllByDisponibilidadeTrueOrderByNomeAsc();
    // }
    
}
