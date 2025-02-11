# 📌 Nome do Projeto

Breve descrição do projeto, destacando seu propósito e principais funcionalidades.

## 🚀 Tecnologias Utilizadas

- **Java 21**: Linguagem principal do projeto
- **Spring Framework**: Estrutura base para desenvolvimento
- **Liquibase**: Gerenciamento de *migrations* no banco de dados
- **H2 Database**: Banco de dados em memória para testes rápidos
- **Git**: Controle de versão e colaboração
- **JUnit/Testes Automatizados**: Garantia de funcionamento das classes de serviço

## 🏗️ Arquitetura e Princípios

O projeto segue a metodologia **12-Factor**, garantindo escalabilidade e boas práticas:

- **Base de Código**: Uso de Git para versionamento e **86 testes** garantindo a integridade das classes de serviço
- **Dependências Isoladas**: Separação de responsabilidades utilizando *Actions*
- **Ambientes Semelhantes (Dev/Prod)**: Liquibase para controle das *migrations* no banco
- **Logs Estruturados**: Diferentes níveis de logs para monitoramento da aplicação
- **Serviços de Apoio**: Serviço externo para consulta de **contas PF e PJ**

## 🔧 Configuração e Execução

### Pré-requisitos

- **Java 21** instalado
- **Maven** (para gerenciamento de dependências)

