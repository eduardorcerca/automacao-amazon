package br.com.automacao.functions;

import br.com.automacao.config.ConfigManager;
import br.com.automacao.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public WebElement aguardarElementoVisivel(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public WebElement aguardarElementoClicavel(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public void preencher(By locator, String valor) {
        WebElement elemento = aguardarElementoVisivel(locator);
        elemento.clear();
        elemento.sendKeys(valor);
    }

    public void clicar(By locator) {
        aguardarElementoClicavel(locator).click();
    }

    public String obterTexto(By locator) {
        return aguardarElementoVisivel(locator).getText();
    }

    public boolean estaVisivel(By locator) {
        try {
            return aguardarElementoVisivel(locator).isDisplayed();
        } catch (RuntimeException exception) {
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