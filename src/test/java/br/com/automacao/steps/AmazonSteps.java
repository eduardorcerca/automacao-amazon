package br.com.automacao.steps;

import br.com.automacao.functions.AmazonFunctions;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.assertj.core.api.Assertions.assertThat;

public class AmazonSteps {

    private AmazonFunctions amazonFunctions;

    @Dado("que acesso a página inicial da Amazon")
    public void queAcessoAPaginaInicialDaAmazon() {
        amazonFunctions = new AmazonFunctions();
        amazonFunctions.acessarPaginaInicial();

        assertThat(amazonFunctions.paginaInicialEstaVisivel())
                .as("O campo de pesquisa da Amazon deveria estar visível.")
                .isTrue();
    }

    @Quando("pesquiso na Amazon pelo produto {string}")
    public void pesquisoNaAmazonPeloProduto(String produto) {
        amazonFunctions.pesquisarProduto(produto);
        amazonFunctions.aguardarCarregamentoDosResultados();
    }

    @Entao("devo visualizar resultados da Amazon para {string}")
    public void devoVisualizarResultadosDaAmazonPara(String produto) {
        String termoPesquisado = amazonFunctions
                .obterTermoPesquisado();

        String urlResultado = amazonFunctions
                .obterUrlDosResultados();

        assertThat(termoPesquisado)
                .as("O termo pesquisado deveria aparecer nos resultados.")
                .containsIgnoringCase(produto);

        assertThat(urlResultado)
                .as("A página de resultados deveria conter o termo pesquisado.")
                .containsIgnoringCase("whey");

        assertThat(amazonFunctions.existemProdutosNosResultados())
                .as("A pesquisa deveria apresentar pelo menos um produto.")
                .isTrue();
    }
}