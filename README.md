# Automação Amazon

Projeto de automação web da Amazon Brasil desenvolvido com Java, Selenium WebDriver, Cucumber, Gherkin, TestNG e Page Object Model.

O primeiro cenário acessa a Amazon Brasil, pesquisa pelo produto `whey` e valida a apresentação dos resultados.

## Tecnologias

- Java 17
- Maven
- Selenium WebDriver
- Selenium Manager
- Cucumber
- Gherkin
- TestNG
- AssertJ
- PicoContainer
- Page Factory
- Page Object Model

## Arquitetura

O projeto separa elementos, interações e cenários nas seguintes camadas:

```text
Feature
  → Steps
    → Functions
      → Pages
        → Selenium WebDriver
```

### Pages

Contêm somente o mapeamento dos elementos da página.

Todos os elementos seguem o padrão XPath com `@FindBy`:

```java
@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
private WebElement campoPesquisa;
```

As Pages não devem conter cliques, preenchimentos, validações ou regras do cenário.

### Functions

Contêm toda a lógica de interação com o Selenium:

- Acessar páginas
- Aguardar elementos
- Preencher campos
- Clicar em elementos
- Ler textos
- Aguardar URLs
- Validar visibilidade
- Trabalhar com listas de elementos

Exemplo:

```java
public void pesquisarProduto(String produto) {
    preencher(amazonHomePage.getCampoPesquisa(), produto);

    clicar(amazonHomePage.getBotaoPesquisar());
}
```

### Steps

Fazem a ligação entre os passos escritos em Gherkin e as Functions.

Exemplo:

```java
@Quando("pesquiso na Amazon pelo produto {string}")
public void pesquisoNaAmazonPeloProduto(String produto) {
    amazonFunctions.pesquisarProduto(produto);
    amazonFunctions.aguardarCarregamentoDosResultados();
}
```

### Features

Descrevem o comportamento esperado em linguagem natural utilizando Gherkin.

Exemplo:

```gherkin
Cenário: Pesquisar por whey
  Dado que acesso a página inicial da Amazon
  Quando pesquiso na Amazon pelo produto "whey"
  Então devo visualizar resultados da Amazon para "whey"
```

### Hooks

Controlam o início e o encerramento de cada cenário:

- Inicialização do navegador
- Captura de screenshot em caso de falha
- Encerramento do WebDriver

### Runner

O projeto utiliza um único runner TestNG.

As tags que serão executadas são definidas diretamente em:

```text
src/test/java/br/com/automacao/runners/TestRunner.java
```

## Estrutura do projeto

```text
automacao-mercadolivre
├── pom.xml
├── README.md
├── .gitignore
└── src
    └── test
        ├── java
        │   └── br
        │       └── com
        │           └── automacao
        │               ├── config
        │               │   └── ConfigManager.java
        │               ├── driver
        │               │   └── DriverFactory.java
        │               ├── functions
        │               │   ├── BaseFunctions.java
        │               │   └── AmazonFunctions.java
        │               ├── hooks
        │               │   └── Hooks.java
        │               ├── pages
        │               │   └── AmazonHomePage.java
        │               ├── runners
        │               │   └── TestRunner.java
        │               └── steps
        │                   └── AmazonSteps.java
        └── resources
            ├── config
            │   └── config.properties
            └── features
                └── pesquisa_amazon.feature
```

## Pré-requisitos

- Java 17 ou superior
- Maven
- IntelliJ IDEA
- Google Chrome, Microsoft Edge ou Mozilla Firefox
- Plugins Cucumber for Java e Gherkin no IntelliJ

Verifique Java e Maven:

```powershell
java -version
mvn -version
```

## Configurações

As configurações estão em:

```text
src/test/resources/config/config.properties
```

Configuração padrão:

```properties
base.url=https://www.amazon.com.br/
browser=chrome
headless=false
timeout=20
```

### Navegadores suportados

```text
chrome
edge
firefox
```

O navegador precisa estar instalado no computador.

O Selenium Manager localiza ou baixa automaticamente o driver compatível durante a execução.

## Executar pelo Runner

Abra:

```text
src/test/java/br/com/automacao/runners/TestRunner.java
```

Clique no triângulo verde ao lado da classe e selecione:

```text
Run 'TestRunner'
```

O runner utiliza TestNG:

```java
public class TestRunner extends AbstractTestNGCucumberTests {
}
```

## Selecionar tags pelo Runner

A tag é definida em `@CucumberOptions`:

```java
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "br.com.automacao",
        monochrome = true,
        tags = "@busca",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        publish = false
)
```

### Executar cenários da Amazon

```java
tags = "@amazon",
```

### Executar cenários de busca

```java
tags = "@busca",
```

### Executar cenários positivos

```java
tags = "@positivo",
```

### Combinar tags

Executar cenários que tenham as duas tags:

```java
tags = "@amazon and @positivo",
```

Executar uma tag ou outra:

```java
tags = "@busca or @login",
```

Excluir uma tag:

```java
tags = "not @ignorado",
```

Para executar todos os cenários, remova a propriedade `tags` de `@CucumberOptions`.

## Executar pelo Maven

Como a tag está configurada diretamente no runner, execute:

```powershell
mvn clean test
```

## Trocar o navegador pelo terminal

Chrome:

```powershell
mvn clean test "-Dbrowser=chrome"
```

Firefox:

```powershell
mvn clean test "-Dbrowser=firefox"
```

Edge:

```powershell
mvn clean test "-Dbrowser=edge"
```

## Executar em modo headless

```powershell
mvn clean test "-Dbrowser=chrome" "-Dheadless=true"
```

As propriedades informadas pelo terminal substituem temporariamente os valores do `config.properties`.

## Relatórios

Após a execução, os relatórios serão gerados em:

```text
target/cucumber-report.html
target/cucumber.json
```

Para abrir o relatório HTML no Windows:

```powershell
Start-Process "target\cucumber-report.html"
```

## Evidências de falha

Quando um cenário falha, o hook captura automaticamente uma imagem do navegador e anexa a evidência ao relatório do Cucumber.

O navegador é encerrado após o cenário, independentemente do resultado.

## Cenário implementado

Feature:

```text
src/test/resources/features/pesquisa_amazon.feature
```

Tags:

```gherkin
@amazon
@busca
@positivo
```

Fluxo:

1. Acessar `https://www.amazon.com.br/`.
2. Confirmar que o campo de pesquisa está visível.
3. Pesquisar pelo produto `whey`.
4. Aguardar a página de resultados.
5. Validar o termo pesquisado.
6. Validar a presença de produtos.
7. Encerrar o navegador.

## Boas práticas

- Utilizar XPath em todos os elementos das Pages.
- Não colocar lógica de interação nas Pages.
- Não utilizar `Thread.sleep`.
- Utilizar esperas explícitas em `BaseFunctions`.
- Manter os Steps curtos.
- Escrever Features com foco no comportamento.
- Não armazenar dados sensíveis no repositório.
- Utilizar tags para organizar as execuções.
- Criar uma Function para cada fluxo ou página relevante.