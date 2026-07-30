package br.com.automacao.functions;

import br.com.automacao.config.ConfigManager;
import br.com.automacao.driver.DriverFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import java.time.Duration;

public class BaseFunctions {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BaseFunctions() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigManager.getTimeout())
        );
    }

    public void acessar(String url) {
        driver.get(url);
    }

    public WebElement aguardarElementoVisivel(WebElement elemento) {
        return wait.until(
                ExpectedConditions.visibilityOf(elemento)
        );
    }

    public WebElement aguardarElementoClicavel(WebElement elemento) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(elemento)
        );
    }

    public void preencher(WebElement elemento, String valor) {
        WebElement elementoVisivel = aguardarElementoVisivel(elemento);
        elementoVisivel.clear();
        elementoVisivel.sendKeys(valor);
    }

    public void clicar(WebElement elemento) {
        aguardarElementoClicavel(elemento).click();
    }

    public String obterTexto(WebElement elemento) {
        return aguardarElementoVisivel(elemento).getText();
    }

    public String obterValor(WebElement elemento) {
        return aguardarElementoVisivel(elemento)
                .getAttribute("value");
    }

    public boolean estaVisivel(WebElement elemento) {
        try {
            return aguardarElementoVisivel(elemento).isDisplayed();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public void aguardarUrlConter(String trechoEsperado) {
        wait.until(
                ExpectedConditions.urlContains(trechoEsperado)
        );
    }

    public List<WebElement> aguardarElementosVisiveis(
            List<WebElement> elementos
    ) {
        return wait.until(
                ExpectedConditions.visibilityOfAllElements(elementos)
        );
    }

    public boolean existemElementosVisiveis(
            List<WebElement> elementos
    ) {
        try {
            return !aguardarElementosVisiveis(elementos).isEmpty();
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public String obterTitulo() {
        return driver.getTitle();
    }

    public String obterUrlAtual() {
        return driver.getCurrentUrl();
    }
}