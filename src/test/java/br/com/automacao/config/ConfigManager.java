package br.com.automacao.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_FILE = "config/config.properties";

    static {
        loadProperties();
    }

    private ConfigManager() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Arquivo de configuração não encontrado: " + CONFIG_FILE
                );
            }

            PROPERTIES.load(inputStream);
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Não foi possível carregar o arquivo de configuração.",
                    exception
            );
        }
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);

        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }

        String propertyValue = PROPERTIES.getProperty(key);

        if (propertyValue == null || propertyValue.isBlank()) {
            throw new IllegalArgumentException(
                    "Configuração não encontrada: " + key
            );
        }

        return propertyValue;
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static int getTimeout() {
        return Integer.parseInt(get("timeout"));
    }
}