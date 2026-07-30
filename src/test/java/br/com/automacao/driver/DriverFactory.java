package br.com.automacao.driver;

import br.com.automacao.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void createDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        String browser = ConfigManager
                .getBrowser()
                .trim()
                .toLowerCase(Locale.ROOT);

        WebDriver webDriver = switch (browser) {
            case "chrome" -> createChromeDriver();
            case "edge" -> createEdgeDriver();
            case "firefox" -> createFirefoxDriver();
            default -> throw new IllegalArgumentException(
                    "Navegador não suportado: " + browser
            );
        };

        DRIVER.set(webDriver);

        if (!ConfigManager.isHeadless()) {
            webDriver.manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = DRIVER.get();

        if (webDriver == null) {
            throw new IllegalStateException(
                    "O WebDriver ainda não foi inicializado."
            );
        }

        return webDriver;
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }

    public static void quitDriver() {
        WebDriver webDriver = DRIVER.get();

        if (webDriver != null) {
            try {
                webDriver.quit();
            } finally {
                DRIVER.remove();
            }
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--start-maximized"
        );

        if (ConfigManager.isHeadless()) {
            options.addArguments(
                    "--headless=new",
                    "--window-size=1920,1080"
            );
        }

        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();

        options.addArguments(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--start-maximized"
        );

        if (ConfigManager.isHeadless()) {
            options.addArguments(
                    "--headless=new",
                    "--window-size=1920,1080"
            );
        }

        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigManager.isHeadless()) {
            options.addArguments(
                    "-headless",
                    "--width=1920",
                    "--height=1080"
            );
        }

        return new FirefoxDriver(options);
    }
}