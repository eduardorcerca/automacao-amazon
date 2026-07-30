package br.com.automacao.pages;

import br.com.automacao.config.ConfigManager;
import org.openqa.selenium.By;

public class WebFormPage extends BasePage {

    private final By campoTexto = By.name("my-text");
    private final By areaTexto = By.name("my-textarea");
    private final By botaoEnviar = By.cssSelector("button[type='submit']");
    private final By mensagemResultado = By.id("message");

    public void acessarPagina() {
        acessar(ConfigManager.getBaseUrl());
    }

    public void preencherCampoTexto(String texto) {
        preencher(campoTexto, texto);
    }

    public void preencherAreaTexto(String texto) {
        preencher(areaTexto, texto);
    }

    public void enviarFormulario() {
        clicar(botaoEnviar);
    }

    public String obterMensagemResultado() {
        return obterTexto(mensagemResultado);
    }

    public boolean formularioEstaVisivel() {
        return estaVisivel(campoTexto)
                && estaVisivel(areaTexto)
                && estaVisivel(botaoEnviar);
    }
}