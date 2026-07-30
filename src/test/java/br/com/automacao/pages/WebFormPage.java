package br.com.automacao.pages;

import org.openqa.selenium.By;

public class WebFormPage {

    private final By campoTexto = By.name("my-text");
    private final By areaTexto = By.name("my-textarea");
    private final By botaoEnviar = By.cssSelector("button[type='submit']");
    private final By mensagemResultado = By.id("message");

    public By getCampoTexto() {
        return campoTexto;
    }

    public By getAreaTexto() {
        return areaTexto;
    }

    public By getBotaoEnviar() {
        return botaoEnviar;
    }

    public By getMensagemResultado() {
        return mensagemResultado;
    }
}