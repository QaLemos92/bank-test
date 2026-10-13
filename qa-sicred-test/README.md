# Sicredi QA API Tests

Projeto de testes automatizados de API desenvolvido para o desafio tecnico.

A suite valida endpoints da API `https://dummyjson.com`, conforme documentacao do desafio Sicredi, usando Java, JUnit 5 e Rest Assured.

## Stack

- Java 21
- Maven
- JUnit 5
- Rest Assured
- Hamcrest
- Jackson Databind

## Como executar

Na raiz do projeto, execute:

```bash
mvn test
```

Tambem e possivel sobrescrever a URL base:

```bash
mvn test -Dbase.uri=https://dummyjson.com
```

## Pipeline

O projeto possui configuracao de GitLab CI em `.gitlab-ci.yml`.

A pipeline executa:

```bash
mvn test
```

Os relatorios JUnit gerados pelo Surefire ficam disponiveis como artifacts da pipeline.

## Estrutura do projeto

```text
src/test/java/br/com/sicredi/desafio
|-- auth          # Testes de autenticacao
|-- client        # Clients responsaveis por chamadas HTTP
|-- config        # Configuracoes da suite
|-- fixture       # Massa de teste controlada
|-- health        # Smoke test da API
|-- model         # Payloads de request
|-- products      # Testes de produtos publicos e autenticados
`-- users         # Testes de usuarios
```

## Bibliotecas de teste

- **Rest Assured**: execucao das requisicoes HTTP e validacao das respostas da API.
- **JUnit 5**: organizacao e execucao dos testes automatizados.
- **Hamcrest**: matchers para assertions mais legiveis.
- **Jackson Databind**: serializacao de objetos Java para payloads JSON usados nas chamadas de API.

## Cenarios cobertos

| Area | Cenario | Resultado esperado |
| --- | --- | --- |
| Health check | Consultar `GET /test` | API responde com status 200 e corpo esperado |
| Usuarios | Consultar `GET /users` | Retorna usuario com credenciais de teste |
| Autenticacao | Login com credenciais validas | Retorna 200, `accessToken` e `refreshToken` |
| Autenticacao | Login com credenciais invalidas | Retorna 400 e mensagem de erro |
| Produtos | Listar produtos em `GET /products` | Retorna lista paginada |
| Produtos | Buscar produto existente | Retorna dados do produto |
| Produtos | Buscar produto inexistente | Retorna 404 |
| Produtos | Criar produto em `POST /products/add` | Retorna 201 com produto simulado |
| Produtos autenticados | Listar com token valido | Retorna lista de produtos |
| Produtos autenticados | Listar sem token | Retorna 401 |
| Produtos autenticados | Listar com token invalido | Retorna 401 |

## Estrategia de teste

A abordagem prioriza cobertura funcional da API, validacao de contrato basico e fluxos de excecao. Os testes foram evoluidos em etapas para simular um ciclo real de desenvolvimento:

1. Criar a estrutura minima e validar o health check.
2. Adicionar cobertura funcional dos principais endpoints.
3. Refatorar para uma arquitetura com clients, models e fixtures.
4. Adicionar fluxos negativos e criacao simulada de massa.
5. Documentar a estrategia, riscos e melhorias.

## Aplicacao de CTFL

- **Particao de equivalencia**: credenciais validas e invalidas; token presente, ausente e invalido.
- **Valores e dados representativos**: produto existente (`id 1`) e produto inexistente (`id 999999`).
- **Testes positivos e negativos**: fluxos de sucesso, recurso nao encontrado, falha de autenticacao e falha de autorizacao.
- **Priorizacao por risco**: autenticacao e autorizacao receberam cenarios negativos por serem pontos sensiveis em contexto financeiro.
- **Rastreabilidade**: os testes foram organizados por dominio da API, facilitando associar endpoint, comportamento esperado e cenario automatizado.

## Aplicacao de CTAL-TAE

- Separacao entre testes e clients HTTP.
- Centralizacao de configuracao da URL base em `ApiConfig`.
- Uso de models para payloads (`LoginRequest`, `ProductRequest`).
- Uso de fixtures para massa de teste (`TestUsers`, `TestProducts`).
- Testes com nomes descritivos via `@DisplayName`.
- Suite preparada para execucao local e futura integracao em pipeline.

## Aplicacao de ISO 25010

- **Adequacao funcional**: validacao dos comportamentos esperados dos endpoints documentados.
- **Confiabilidade**: health check e cenarios principais executados como suite automatizada.
- **Seguranca**: cobertura de token ausente, token invalido e credenciais invalidas.
- **Manutenibilidade**: arquitetura em camadas simples, evitando duplicacao excessiva de chamadas HTTP.
- **Portabilidade**: execucao via Maven e possibilidade de alterar a URL base por propriedade.

## Observacoes e divergencias encontradas

- O endpoint `POST /auth/login` retorna `200` na API real, com `accessToken` e `refreshToken`.
- A criacao em `POST /products/add` e simulada pela API DummyJSON. O produto retorna no response, mas nao e persistido.
- Nenhum teste realiza manipulacao direta em banco de dados. Toda interacao ocorre via API publica.
- Alguns dados retornados por `GET /users` simulam informacoes sensiveis, como senha e dados bancarios ficticios. Em um ambiente financeiro real, esse comportamento exigiria avaliacao rigorosa de seguranca e mascaramento.

## Melhorias futuras

- Adicionar relatorio de testes em HTML, se solicitado.
- Incluir tags JUnit para separar smoke, contrato, regressao e seguranca.
- Aumentar cobertura de contrato validando schema JSON.
- Avaliar testes de tempo de resposta com limites acordados.
- Externalizar dados sensiveis por variaveis de ambiente quando houver credenciais reais.
