package br.com.automacao.functions;

import br.com.automacao.config.ConfigManager;
import br.com.automacao.pages.WebFormPage;

public class WebFormFunctions extends BaseFunctions {

    private final WebFormPage webFormPage;

    public WebFormFunctions() {
        this.webFormPage = new WebFormPage();
    }

    public void acessarFormulario() {
        acessar(ConfigManager.getBaseUrl());
    }

    public void preencherCampoTexto(String texto) {
        preencher(
                webFormPage.getCampoTexto(),
                texto
        );
    }

    public void preencherAreaTexto(String texto) {
        preencher(
                webFormPage.getAreaTexto(),
                texto
        );
    }

    public void enviarFormulario() {
        clicar(
                webFormPage.getBotaoEnviar()
        );
    }

    public boolean formularioEstaVisivel() {
        return estaVisivel(webFormPage.getCampoTexto())
                && estaVisivel(webFormPage.getAreaTexto())
                && estaVisivel(webFormPage.getBotaoEnviar());
    }

    public String obterMensagemResultado() {
        return obterTexto(
                webFormPage.getMensagemResultado()
        );
    }
}