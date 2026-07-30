package br.com.automacao.steps;

import br.com.automacao.functions.WebFormFunctions;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static org.assertj.core.api.Assertions.assertThat;

public class WebFormSteps {

    private WebFormFunctions webFormFunctions;

    @Dado("que estou no formulário de teste")
    public void queEstouNoFormularioDeTeste() {
        webFormFunctions = new WebFormFunctions();
        webFormFunctions.acessarFormulario();

        assertThat(webFormFunctions.formularioEstaVisivel())
                .as("O formulário de teste deveria estar visível.")
                .isTrue();
    }

    @Quando("preencho o campo de texto com {string}")
    public void preenchoOCampoDeTextoCom(String texto) {
        webFormFunctions.preencherCampoTexto(texto);
    }

    @Quando("preencho a área de texto com {string}")
    public void preenchoAAreaDeTextoCom(String texto) {
        webFormFunctions.preencherAreaTexto(texto);
    }

    @Quando("envio o formulário")
    public void envioOFormulario() {
        webFormFunctions.enviarFormulario();
    }

    @Entao("devo visualizar a mensagem {string}")
    public void devoVisualizarAMensagem(String mensagemEsperada) {
        assertThat(webFormFunctions.obterMensagemResultado())
                .as("A mensagem apresentada pelo formulário está incorreta.")
                .isEqualTo(mensagemEsperada);
    }
}