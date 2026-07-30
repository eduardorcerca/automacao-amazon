package br.com.automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonHomePage {

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement campoPesquisa;

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    private WebElement botaoPesquisar;

    @FindBy(xpath = "//span[contains(@class, 'a-color-state')]")
    private WebElement termoPesquisado;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    private List<WebElement> produtosEncontrados;

    public AmazonHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getCampoPesquisa() {
        return campoPesquisa;
    }

    public WebElement getBotaoPesquisar() {
        return botaoPesquisar;
    }

    public WebElement getTermoPesquisado() {
        return termoPesquisado;
    }

    public List<WebElement> getProdutosEncontrados() {
        return produtosEncontrados;
    }
}