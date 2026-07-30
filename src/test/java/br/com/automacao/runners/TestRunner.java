package br.com.automacao.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br.com.automacao",
        monochrome = true,
        tags = "@amazon",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}