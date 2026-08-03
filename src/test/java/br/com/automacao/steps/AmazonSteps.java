package br.com.automacao.steps;

import br.com.automacao.functions.AmazonFunctions;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class AmazonSteps {

    private AmazonFunctions amazonFunctions;

    @Dado("que acesso a página inicial da Amazon")
    public void queAcessoAPaginaInicialDaAmazon() {
        amazonFunctions = new AmazonFunctions();
        amazonFunctions.acessarPaginaInicial();
    }

    @Quando("pesquiso na Amazon pelo produto {string}")
    public void pesquisoNaAmazonPeloProduto(String produto) {
        amazonFunctions.pesquisarProduto(produto);
    }

    @Entao("devo visualizar resultados da Amazon para {string}")
    public void devoVisualizarResultadosDaAmazonPara(String produto) {
        amazonFunctions.validarResultadosDaPesquisa(produto);
    }
}