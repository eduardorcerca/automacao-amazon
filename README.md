# Automação Mercado Livre

Projeto de automação web do Mercado Livre desenvolvido com Java, Selenium WebDriver, Cucumber, Gherkin, JUnit 5 e Page Object Model.

## Tecnologias

- Java 17
- Maven
- Selenium WebDriver
- Cucumber
- Gherkin
- JUnit 5
- AssertJ
- PicoContainer
- Selenium Manager

## Estrutura

```text
src/test
├── java/br/com/automacao
│   ├── config
│   ├── driver
│   ├── hooks
│   ├── pages
│   ├── runners
│   └── steps
└── resources
    ├── config
    ├── features
    └── junit-platform.properties
```

## Pré-requisitos

- Java 17 ou superior
- Maven
- Google Chrome, Microsoft Edge ou Mozilla Firefox

Verifique as instalações:

```powershell
java -version
mvn -version
```

## Configuração

As configurações estão em:

```text
src/test/resources/config/config.properties
```

Exemplo:

```properties
base.url=https://www.selenium.dev/selenium/web/web-form.html
browser=chrome
headless=false
timeout=10
```

Navegadores suportados:

```text
chrome
edge
firefox
```

## Executar todos os testes

```powershell
mvn clean test
```

## Executar por tag

```powershell
mvn clean test -Dcucumber.filter.tags="@web"
```

```powershell
mvn clean test -Dcucumber.filter.tags="@positivo"
```

```powershell
mvn clean test -Dcucumber.filter.tags="@web and @positivo"
```

## Executar em outro navegador

```powershell
mvn clean test -Dbrowser=edge
```

```powershell
mvn clean test -Dbrowser=firefox
```

## Executar em modo headless

```powershell
mvn clean test -Dheadless=true
```

## Combinar configurações

```powershell
mvn clean test -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@positivo"
```

## Relatório

Depois da execução, o relatório HTML estará em:

```text
target/cucumber-report.html
```

Para abrir no Windows:

```powershell
Start-Process "target\cucumber-report.html"
```

## Page Object Model

Os Page Objects ficam em:

```text
src/test/java/br/com/automacao/pages
```

As classes de steps não devem acessar elementos com `driver.findElement` diretamente. Seletores e interações devem permanecer nos Page Objects.