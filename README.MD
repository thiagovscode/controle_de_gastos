# Controle de Gastos — Arquitetura e Estrutura

## Visão Geral

Este projeto é um **controle de gastos pessoais** construído com foco em **clareza de regras de negócio**, **baixo acoplamento** e **facilidade de evolução**, seguindo princípios de **Arquitetura Limpa (Clean Architecture / Hexagonal)**.

A organização do código prioriza **intenção de negócio**, e não apenas reutilização técnica. Cada camada possui uma responsabilidade bem definida e limitada.

---

## Princípios Fundamentais

* Regras de negócio não dependem de frameworks
* Decisões de negócio ficam concentradas nos casos de uso
* Infraestrutura é isolada
* Conversões de dados são centralizadas
* Controllers não contêm lógica de negócio

---

## Estrutura Geral do Projeto

```
com.br.controledegastos
├── domain
├── usecase
│   └── impl
├── gateway
│   └── impl
├── entrypoint
│   └── api
│       └── model
├── mapper
└── config
```

---

## Camada Domain

### Responsabilidade

A camada `domain` representa os **conceitos centrais do negócio**.
Ela define **estado e comportamento válido**, sem depender de banco de dados, HTTP ou Spring.

### Características

* Não possui anotações de framework
* Contém apenas regras que fariam sentido fora de um sistema
* Define como os objetos podem mudar de estado

### Conceitos existentes

#### Categoria

Representa a classificação principal de uma movimentação financeira.

* Possui identidade própria (UUID)
* Possui estado ativo/inativo
* Pode ser ativada ou inativada de forma idempotente
* Não conhece persistência nem requisições HTTP

#### Tag

Representa um rótulo opcional para contextualizar movimentações.

* É independente de categoria
* Pode existir ou não em uma movimentação
* Pode ser ativada ou inativada
* Não influencia regras financeiras diretamente

---

## Camada Use Case

### Responsabilidade

A camada `usecase` define **o que o sistema permite fazer**.
Cada caso de uso representa **uma intenção clara de negócio**.

### Características

* Orquestra o fluxo da ação
* Aplica validações
* Decide o que pode ou não acontecer
* Não contém detalhes técnicos de banco ou HTTP

### Organização

* Interfaces ficam no pacote `usecase`
* Implementações ficam em `usecase.impl`

### Exemplos de intenções modeladas

* Criar categoria
* Listar categorias ativas
* Listar todas as categorias
* Ativar categoria
* Inativar categoria
* Criar tag
* Listar tags
* Ativar ou inativar tag

Mesmo quando dois casos de uso executam operações técnicas semelhantes, eles permanecem separados se a **intenção de negócio for diferente**.

---

## Camada Gateway

### Responsabilidade

A camada `gateway` define **portas de saída** do sistema, ou seja, como o domínio se comunica com o mundo externo.

### Características

* Interfaces definem contratos
* Implementações lidam com detalhes técnicos
* Converte domínio para persistência e vice-versa

### Organização

* Interfaces em `gateway`
* Implementações em `gateway.impl`

O domínio e os casos de uso dependem apenas das interfaces, nunca das implementações.

---

## Camada Repository (Persistência)

Os repositórios são responsáveis exclusivamente por **armazenar e recuperar dados**.

### Características

* Não contêm regra de negócio
* Não validam estado
* Apenas executam operações de persistência

---

## Camada Mapper

### Responsabilidade

A camada `mapper` centraliza todas as **conversões entre modelos**.

### Tipos de conversão

* Request (API) → Domain
* Domain → Entity
* Domain → Response (API)

### Características

* Não possui lógica de negócio
* Não valida regras
* Apenas traduz formatos

Os mappers são implementados automaticamente pelo MapStruct, e injetados pelo Spring.

---

## Camada Entrypoint (API)

### Responsabilidade

A camada `entrypoint.api` expõe o sistema via HTTP.

### Características

* Recebe requisições
* Extrai dados do request
* Chama o caso de uso adequado
* Retorna a resposta apropriada

Controllers não:

* Acessam banco
* Tomam decisões de negócio
* Contêm regras

---

## Inativação vs Exclusão

* Categorias são inativadas para preservar histórico
* Tags podem ser inativadas ou excluídas, dependendo da regra de negócio
* A decisão entre inativar ou excluir sempre pertence ao caso de uso
* Controllers nunca decidem isso

---

## Injeção de Dependência

* O Spring é responsável por criar e gerenciar objetos (beans)
* Os casos de uso, gateways e mappers são injetados automaticamente
* O código não instancia dependências manualmente

---

## Filosofia de Evolução

* Clareza é priorizada em relação à abstração precoce
* Casos de uso representam intenções, não reutilização técnica
* O domínio é protegido contra mudanças externas
* O sistema pode crescer sem quebra estrutural

---

## Resumo Conceitual

* Domain representa a verdade do negócio
* UseCase decide o que pode acontecer
* Gateway executa ações externas
* Repository armazena dados
* Mapper traduz formatos
* Controller apenas orquestra chamadas
