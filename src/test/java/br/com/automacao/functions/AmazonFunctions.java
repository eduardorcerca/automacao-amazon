package br.com.automacao.functions;

import br.com.automacao.config.ConfigManager;
import br.com.automacao.pages.AmazonHomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonFunctions extends BaseFunctions {

    private final AmazonHomePage amazonHomePage;

    public AmazonFunctions() {
        this.amazonHomePage = new AmazonHomePage(driver);
    }

    public void acessarPaginaInicial() {
        acessar(ConfigManager.getBaseUrl());

        assertThat(estaVisivel(amazonHomePage.getCampoPesquisa()))
                .as("O campo de pesquisa da Amazon deveria estar visível.")
                .isTrue();
    }

    public void pesquisarProduto(String produto) {
        preencher(amazonHomePage.getCampoPesquisa(), produto);
        clicar(amazonHomePage.getBotaoPesquisar());
        aguardarUrlConter("/s?");
        aguardarElementoVisivel(amazonHomePage.getTermoPesquisado());
    }

    public void validarResultadosDaPesquisa(String produto) {
        String termoPesquisado = obterTexto(amazonHomePage.getTermoPesquisado());
        String urlResultado = obterUrlAtual();
        boolean existemProdutos = existemElementosVisiveis(amazonHomePage.getProdutosEncontrados());

        assertThat(termoPesquisado)
                .as("O termo pesquisado deveria aparecer nos resultados.")
                .containsIgnoringCase(produto);

        assertThat(urlResultado)
                .as("A navegação deveria estar na página de resultados da Amazon.")
                .contains("/s?");

        assertThat(existemProdutos)
                .as("A pesquisa deveria apresentar pelo menos um produto.")
                .isTrue();
    }
}