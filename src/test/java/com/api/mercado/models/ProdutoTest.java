package com.api.mercado.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    Produto produto1 = new Produto();

    @Test
    @DisplayName("Deve aceitar alterar o nome do produto")
    public void deveAceitarAlterarNomeDoProduto() {
        produto1.setNome("Mouse Gamer");
        Assertions.assertEquals(produto1.getNome(), "Mouse Gamer");
    }

    @Test
    @DisplayName("Deve deixar produto inativo para listagem de produtos ativos")
    public void deveDeixarProdutoInativoParaListagemDeProdutosAtivos() {
        produto1.exclusaoLogica();
        Assertions.assertEquals( false, produto1.getAtivo());
    }
    
    @Test
    @DisplayName("Deve instaciar novo Produto Ativo")
    public void deveInstaciarNovoProdutoComoAtivo() {
        Assertions.assertEquals(true, produto1.getAtivo());
    }

    @Test
    @DisplayName("Deve inverter a propriedade atual do atributo ATIVO")
    public void deveInverterPropriedadeDoAtributoAtivo() {
        // Atributo ATIVO inicia TRUE
        produto1.alternarStatusAtivo(); // Altera para FALSE
        Assertions.assertNotEquals(true, produto1.getAtivo());
    }

    @Test
    @DisplayName("Deve reativar Produto inativo")
    public void deveReativarProdutoInativo() {
        produto1.exclusaoLogica();
        produto1.ativarProduto();
        Assertions.assertEquals(true, produto1.getAtivo());
    }

    @Test
    @DisplayName("Deve instaciar novo produto com data de cadastro não nula")
    public void deveInstaciarNovoProdutoComDatanaoNula() {
        Assertions.assertNotNull(produto1.getDataCadastro());
    }
    
}
