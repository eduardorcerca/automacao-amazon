# language: pt

@amazon
Funcionalidade: Pesquisa de produtos na Amazon

  Como usuário da Amazon
  Quero pesquisar produtos
  Para visualizar as opções disponíveis para compra

  @busca @positivo
  Cenário: Pesquisar por whey
    Dado que acesso a página inicial da Amazon
    Quando pesquiso na Amazon pelo produto "whey"
    Então devo visualizar resultados da Amazon para "whey"