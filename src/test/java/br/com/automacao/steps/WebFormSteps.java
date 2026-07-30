package br.com.automacao.steps;

import br.com.automacao.pages.WebFormPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.assertj.core.api.Assertions.assertThat;

public class WebFormSteps {

    private WebFormPage webFormPage;

    @Dado("que estou no formulário de teste")
    public void queEstouNoFormularioDeTeste() {
        webFormPage = new WebFormPage();
        webFormPage.acessarPagina();

        assertThat(webFormPage.formularioEstaVisivel())
                .as("O formulário de teste deveria estar visível.")
                .isTrue();
    }

    @Quando("preencho o campo de texto com {string}")
    public void preenchoOCampoDeTextoCom(String texto) {
        webFormPage.preencherCampoTexto(texto);
    }

    @Quando("preencho a área de texto com {string}")
    public void preenchoAAreaDeTextoCom(String texto) {
        webFormPage.preencherAreaTexto(texto);
    }

    @Quando("envio o formulário")
    public void envioOFormulario() {
        webFormPage.enviarFormulario();
    }

    @Entao("devo visualizar a mensagem {string}")
    public void devoVisualizarAMensagem(String mensagemEsperada) {
        assertThat(webFormPage.obterMensagemResultado())
                .as("A mensagem apresentada pelo formulário está incorreta.")
                .isEqualTo(mensagemEsperada);
    }
}