package br.com.automacao.pages;

import br.com.automacao.config.ConfigManager;
import br.com.automacao.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigManager.getTimeout())
        );
    }

    protected void acessar(String url) {
        driver.get(url);
    }

    protected WebElement aguardarElementoVisivel(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    protected WebElement aguardarElementoClicavel(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    protected void preencher(By locator, String valor) {
        WebElement elemento = aguardarElementoVisivel(locator);
        elemento.clear();
        elemento.sendKeys(valor);
    }

    protected void clicar(By locator) {
        aguardarElementoClicavel(locator).click();
    }

    protected String obterTexto(By locator) {
        return aguardarElementoVisivel(locator).getText();
    }

    protected boolean estaVisivel(By locator) {
        try {
            return aguardarElementoVisivel(locator).isDisplayed();
        } catch (RuntimeException exception) {
            return false;
        }
    }

    protected String obterTitulo() {
        return driver.getTitle();
    }

    protected String obterUrlAtual() {
        return driver.getCurrentUrl();
    }
}