# fiap-lanchonete-api

![Turma](https://img.shields.io/badge/👨🏻‍🏫_Turma-SOAT2-blue?style=for-the-badge)
![Equipe](https://img.shields.io/badge/🧑🏻‍💻_equipe-20-blue?style=for-the-badge)

[![Spring](https://img.shields.io/badge/Spring-%236DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white)](https://docs.spring.io/spring-framework/docs/5.2.0.M1/spring-framework-reference/index.html)
[![Kotlin](https://img.shields.io/badge/Kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/getting-started.html)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

## 📋 Descrição

API Responsável pela gestão de pedidos de uma lanchonete, envolvendo desde a realização do pedido pelo cliente
até o preparo do pedido pela cozinha.



![Diagrama da arquitetura do projeto](./docs/arquitetura.png)

Arquitetura dos microsserviços

![soat-lanchonete-architecture-final (1).jpg](docs%2Fsoat-lanchonete-architecture-final%20%281%29.jpg)

Os seguintes repositórios também fazem parte desse projeto:

Microsserviços:

> [fiap-lanchonete-api-customer-totem](https://github.com/MarcosPrata/fiap-lanchonete-api-customer-totem) - Serviço responsável pela criação do pedido no Totem da Lanchonete.

> [fiap-lanchonete-api-payment](https://github.com/MarcosPrata/fiap-lanchonete-api-payment) - Serviço responsável pela gestão do pagamento do pedido.

> [fiap-lanchonete-api-kitchen](https://github.com/MarcosPrata/fiap-lanchonete-api-kitchen) - Serviço responsável por exibir os pedidos confirmados para a cozinha.

> [fiap-lanchonete-api-orders-display](https://github.com/MarcosPrata/fiap-lanchonete-api-orders-display) - Serviço responsável por exibir os pedidos para os clientes (PREPARANDO E PRONTO).

> [fiap-lanchonete-api-erp](https://github.com/MarcosPrata/fiap-lanchonete-api-erp) - Serviço responsável pelo gerenciamento dos produtos.

Infra:

> [fiap-lanchonete-terraform](https://github.com/MarcosPrata/fiap-lanchonete-terraform) - Reponsável por provisionar a infra na AWS.

> [fiap-lanchonete-lambda-authorizer](https://github.com/MarcosPrata/fiap-lanchonete-lambda-authorizer) - Reponsável por autenticar e autorizar as chamadas requests dos usuários.


## 🚦Pre-requisitos

- **Kotlin** versão 1.4.10
- **Docker**
- **Gradle** versão 6.6.1
- **Java** 17 ou superior

## 🚀 Quick Start
- Rodar usando o Docker:
    - Em cada serviço execute o comando abaixo para subir as app's, os bancos de dados e o rabbitMQ
      (necessário subir primeiro o customer-totem, por conta da network compartilhada dos containers)
        - `$ docker-compose up`
    - Lembre-se de ter as seguintes portas disponiveis: Serviços (80, 81, 82, 83), Postgres (5432, 5433, 5434, 5435) e RabbitMQ (5672, 15672)


## SAGA

- O padrão escolhido foi o **Coreografado**, onde cada nó sabe qual o próximo nó a ser chamado, seja seguindo na ação ou na compensação.
- A Mensageria escolhida foi o RabbitMQ, pela facilidade no deploy e poucas configurações necessárias (acessado via localhost:15672 -u guest -p guest)

## LGPD

- [Relatório de Impacto à Proteção de Dados Pessoais (RIPD).pdf](docs%2FRelat%F3rio%20de%20Impacto%20%E0%20Prote%E7%E3o%20de%20Dados%20Pessoais%20%28RIPD%29.pdf)
- ROTA responsável por exclusão dos dados pessoais dos clientes pode ser encontrado na collection da aplicação (Delete customer).

## Postman collection

* Acesse o arquivo em formato JSON que está salvo nesse repositório [Lanchonete.postman_collection.json](docs/Lanchonete.postman_collection.json)

## 💻 Insomnia Collections

1 — Crie um projeto no insomnia ou selecione um já existente

2 — Crie um documento no projeto selecionado com a opção git clone, faça ‘login’ no git caso necessário e adicione o link do repositório

3 — Após isso as collections já estarão disponíveis na aba debug

<img src="./docs/clonar.gif"/>

## 🎓 Integrantes / Devs

> Nome: Marcos Henrique Prata Junior
>
> Matrícula: RM349949
>
> [![Linkedin](https://img.shields.io/badge/Linkedin-0E76A8.svg?style=for-the-badge&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/marcos-henrique-prata-junior/)
> [![GitHub](https://img.shields.io/badge/GitHub-333.svg?style=for-the-badge&logo=GitHub&logoColor=white)](https://github.com/MarcosPrata)

> Nome: Marcos Moreira
>
> Matrícula: RM349544
>
> [![Linkedin](https://img.shields.io/badge/Linkedin-0E76A8.svg?style=for-the-badge&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/moreira-dev/)
> [![GitHub](https://img.shields.io/badge/GitHub-333.svg?style=for-the-badge&logo=GitHub&logoColor=white)](https://github.com/MarcosPotato)


> Nome: Marcelo Gonçalves de Barros
>
> Matrícula: RM349535
>
> [![Linkedin](https://img.shields.io/badge/Linkedin-0E76A8.svg?style=for-the-badge&logo=Linkedin&logoColor=white)]()
> [![GitHub](https://img.shields.io/badge/GitHub-333.svg?style=for-the-badge&logo=GitHub&logoColor=white)]()


> Nome: Henrique de Paula Leite
>
> Matrícula: RM350046
>
> [![Linkedin](https://img.shields.io/badge/Linkedin-0E76A8.svg?style=for-the-badge&logo=Linkedin&logoColor=white)]()
> [![GitHub](https://img.shields.io/badge/GitHub-333.svg?style=for-the-badge&logo=GitHub&logoColor=white)]()
