package br.com.automacao.functions;

import br.com.automacao.config.ConfigManager;
import br.com.automacao.pages.AmazonHomePage;

public class AmazonFunctions extends BaseFunctions {

    private final AmazonHomePage amazonHomePage;

    public AmazonFunctions() {
        this.amazonHomePage = new AmazonHomePage(driver);
    }

    public void acessarPaginaInicial() {
        acessar(ConfigManager.getBaseUrl());
    }

    public boolean paginaInicialEstaVisivel() {
        return estaVisivel(
                amazonHomePage.getCampoPesquisa()
        );
    }

    public void pesquisarProduto(String produto) {
        preencher(
                amazonHomePage.getCampoPesquisa(),
                produto
        );

        clicar(
                amazonHomePage.getBotaoPesquisar()
        );
    }

    public void aguardarCarregamentoDosResultados() {
        aguardarUrlConter("/s?");

        aguardarElementoVisivel(
                amazonHomePage.getTermoPesquisado()
        );
    }

    public String obterTermoPesquisado() {
        return obterTexto(
                amazonHomePage.getTermoPesquisado()
        );
    }

    public boolean existemProdutosNosResultados() {
        return existemElementosVisiveis(
                amazonHomePage.getProdutosEncontrados()
        );
    }

    public String obterUrlDosResultados() {
        return obterUrlAtual();
    }
}