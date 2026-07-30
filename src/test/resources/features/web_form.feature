# language: pt

@web
Funcionalidade: Preenchimento do formulário de demonstração

  Como usuário do sistema
  Quero preencher e enviar o formulário
  Para validar o funcionamento da automação

  @positivo
  Cenário: Enviar o formulário com sucesso
    Dado que estou no formulário de teste
    Quando preencho o campo de texto com "Projeto de automação"
    E preencho a área de texto com "Selenium, Cucumber e Page Object"
    E envio o formulário
    Então devo visualizar a mensagem "Received!"