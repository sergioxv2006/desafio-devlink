# 📦 Analisador de Pedidos

Sistema modular desenvolvido em Java para análise automática de pedidos de clientes, aplicando regras de negócio para definir se um pedido deve ser **aprovado**, **rejeitado** ou **pendente**.

---

## 🚀 Tecnologias Utilizadas

| Ferramenta | Versão |
|---|---|
| Java JDK | 21 |
| IDE | IntelliJ IDEA |

---

## 📁 Estrutura do Projeto

```
📁 AnalisadorDePedidos/
 ├── Modelos.java              # Classes de dados: Item, Cliente, Pedido + enums
 ├── AnalisadorDePedido.java   # Regras de negócio
 └── Empresa.java              # Ponto de entrada (main) com exemplos de teste
```

### Responsabilidade de cada arquivo

- **`Modelos.java`** — Representa as estruturas de dados do sistema (Item, Cliente, Pedido) e os enums `TipoCliente` e `StatusPedido`. Sem nenhuma lógica de negócio.
- **`AnalisadorDePedido.java`** — Contém toda a lógica de análise. Recebe um `Pedido` e atribui o status final com base nas regras definidas.
- **`Empresa.java`** — Classe principal com o método `main`. Monta os pedidos de teste e aciona o analisador.

---

## 🧩 Modelagem de Dados

### `Item`
| Campo | Tipo | Descrição |
|---|---|---|
| `nome` | `String` | Nome do item |
| `preco` | `double` | Preço unitário |
| `quantidade` | `int` | Quantidade solicitada |

### `Cliente`
| Campo | Tipo | Descrição |
|---|---|---|
| `nome` | `String` | Nome do cliente |
| `tipo` | `TipoCliente` | `NORMAL` ou `VIP` |
| `saldo` | `double` | Saldo disponível |

### `Pedido`
| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `int` | Identificador do pedido |
| `cliente` | `Cliente` | Cliente que realizou o pedido |
| `itens` | `List<Item>` | Lista de itens do pedido |
| `status` | `StatusPedido` | `APROVADO`, `REJEITADO` ou `PENDENTE` |

---

## 📋 Regras de Negócio

As regras são aplicadas em ordem pelo `AnalisadorDePedido`:

| # | Condição | Status Resultante |
|---|---|---|
| 1 | Pedido sem itens | ❌ `REJEITADO` |
| 2 | Saldo do cliente menor que o valor total | ❌ `REJEITADO` |
| 3 | Cliente `VIP` com valor total até R$ 2.000,00 | ✅ `APROVADO` |
| 4 | Cliente `NORMAL` com valor total até R$ 1.000,00 | ✅ `APROVADO` |
| 5 | Valor total acima do limite permitido | ⚠️ `PENDENTE` |

---

## ▶️ Como Executar

### Pré-requisitos

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/) instalado
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community ou Ultimate)

### Passos no IntelliJ

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/analisador-de-pedidos.git
   ```
2. Abra o IntelliJ e selecione **File → Open** apontando para a pasta do projeto.
3. Certifique-se de que o JDK 21 está configurado em **File → Project Structure → SDK**.
4. Abra a classe `Empresa.java` e clique no botão ▶️ ao lado do método `main`.

### Saída esperada no console

```
Pedido #1 — João (sem itens):
  ✗ Rejeitado: pedido sem itens.
  Status final: REJEITADO

Pedido #2 — Maria (saldo insuficiente):
  ✗ Rejeitado: saldo insuficiente (saldo: R$100,00 | total: R$800,00)
  Status final: REJEITADO

Pedido #3 — Carlos (valor R$380,00):
  ✓ Aprovado: valor dentro do limite normal (R$380,00).
  Status final: APROVADO

Pedido #4 — Ana (valor R$1400,00, cliente NORMAL):
  ⚠ Pendente: valor acima do limite (R$1400,00), requer revisão manual.
  Status final: PENDENTE

Pedido #5 — Beatriz (valor R$1500,00, cliente VIP):
  ✓ Aprovado: cliente VIP, valor dentro do limite (R$1500,00).
  Status final: APROVADO

Pedido #6 — Roberto (valor R$3000,00, VIP acima do limite):
  ⚠ Pendente: valor acima do limite (R$3000,00), requer revisão manual.
  Status final: PENDENTE
```

---

## 💡 Decisões de Projeto

- **`enum` para tipos fixos** — `TipoCliente` e `StatusPedido` usam `enum` em vez de `String` para evitar erros de digitação e facilitar comparações.
- **`double` para valores monetários** — permite representar centavos, ao contrário de `Integer`.
- **Separação de responsabilidades** — a lógica de negócio fica isolada em `AnalisadorDePedido`, tornando fácil adicionar ou alterar regras sem mexer nos modelos ou no `main`.
