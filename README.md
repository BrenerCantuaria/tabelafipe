# Projeto de Consumo da API Tabela FIPE

## Descrição

Este projeto, desenvolvido em Java com o uso do Spring Framework, tem como objetivo consumir a API da Tabela FIPE para buscar informações sobre veículos, como motos, carros e caminhões. A aplicação interage com a API fornecida pelo serviço [Tabela FIPE Paralellum]([https://parallelum.com.br/fipe/api/v1/](https://parallelum.com.br/fipe/api/v1/carros/marcas)), permitindo que o usuário consulte marcas, modelos, anos e valores de veículos.

O projeto está estruturado em três principais camadas: **Model**, **Service** e a aplicação principal, organizando a lógica de negócio e as chamadas de API.

## Estrutura do Projeto

- **Model**: Define os objetos de dados que correspondem às informações retornadas pela API da FIPE.
- **Service**: Contém as classes responsáveis por fazer as requisições à API e converter os dados JSON para objetos Java.
- **Principal**: Contém a lógica da aplicação principal e interações com o usuário.
-  **Jackson ObjectMapper** (para serialização e desserialização de JSON)
- **HTTP Client** para chamadas de API

## Tecnologias Utilizadas

- Java 11+
- Spring Framework
- Jackson (para serialização e desserialização de JSON)
- HTTP Client para chamadas de API

## Exemplo de Uso

Digite o número que corresponde a sua pesquisa:

1 - Motos
2 - Carros
3 - Caminhões

Digite o código do veículo que você deseja:



## Estrutura de Pastas

```bash
src/
├── main/
│   ├── java/
│   │   ├── br/com/spring/tabelafipe/model/
│   │   │   └── Veiculo.java
│   │   ├── br/com/spring/tabelafipe/service/
│   │   │   ├── API.java
│   │   │   └── TransformaDados.java
│   │   └── br/com/spring/tabelafipe/principal/
│   │       └── Principal.java


