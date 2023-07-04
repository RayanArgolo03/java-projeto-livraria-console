
# Projeto de Gerenciamento de Livraria em Java

Este projeto é um exemplo simples que demonstra o uso de injeção de dependência em Java para o gerenciamento de uma livraria. Ele permite adicionar livros, organizar a lista de livros e enviar notificações.


## Conceitos utilizados nesse projeto
- Composição de entidades
- Composição de serviços
- Interfaces
- Inversão de controle / injeção de dependência
- Programação defensiva
- Orientação a objetos
- Tratamento de exceções
- Arquitetura de pacotes (Modelo, serviços, constantes)

## Funcionalidades

O projeto consiste em classes e interfaces que realizam as seguintes funcionalidades:

- A classe `Livro` representa um livro com informações como título, autor e número de páginas.
- A interface `Comparavel` define os métodos `comparar` para comparar livros e `comparar` com parâmetros adicionais para definir a forma e organização da comparação.
- A classe `LivrariaException` é uma exceção personalizada para lidar com erros específicos da livraria.
- A interface `ServicoEnvioMensagem` define o método `enviarMensagem` para enviar notificações.
- A classe `ServicoNotificacao` implementa a interface `ServicoEnvioMensagem` e envia notificações para a saída do console.
- A interface `ServicoOrdenacao` define os métodos `ordenar` para ordenar objetos comparáveis e `ordenar` como um método padrão para a ordenação básica.
- A classe `ServicoOrdenarLivros` implementa a interface `ServicoOrdenacao` e ordena a lista de livros com base em diferentes critérios, como autor, título e número de páginas.
- A classe `ServicoGerenciarLivraria` é responsável por gerenciar a livraria, permitindo adicionar livros, organizar a lista de livros e enviar notificações.

