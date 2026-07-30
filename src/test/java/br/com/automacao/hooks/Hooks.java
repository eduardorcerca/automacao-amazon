package br.com.automacao.hooks;

import br.com.automacao.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before(order = 0)
    public void iniciarNavegador() {
        DriverFactory.createDriver();
    }

    @After(order = 0)
    public void finalizarNavegador(Scenario scenario) {
        try {
            anexarScreenshotEmCasoDeFalha(scenario);
        } finally {
            DriverFactory.quitDriver();
        }
    }

    private void anexarScreenshotEmCasoDeFalha(Scenario scenario) {
        if (!scenario.isFailed() || !DriverFactory.hasDriver()) {
            return;
        }

        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        scenario.attach(
                screenshot,
                "image/png",
                "Evidência da falha"
        );
    }
}