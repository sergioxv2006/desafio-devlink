// Contém todas as classes de dados (modelos) do sistema.
// Responsabilidade: apenas representar as estruturas de dados, sem lógica de negócio.

import java.util.List;

// ── Status possíveis de um pedido ─────────────────────────────────────────────
enum StatusPedido {
    APROVADO,
    REJEITADO,
    PENDENTE
}

// ── Tipo do cliente ────────────────────────────────────────────────────────────
enum TipoCliente {
    NORMAL,
    VIP
}

// ── Item do pedido ─────────────────────────────────────────────────────────────
class Item {
    private String nome;
    private double preco;
    private int quantidade;

    public Item(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Valor total deste item (preço × quantidade)
    public double getValorTotal() {
        return preco * quantidade;
    }

    public String getNome()       { return nome; }
    public double getPreco()      { return preco; }
    public int getQuantidade()    { return quantidade; }
}

// ── Cliente ────────────────────────────────────────────────────────────────────
class Cliente {
    private String nome;
    private TipoCliente tipo;   // NORMAL ou VIP
    private double saldo;

    public Cliente(String nome, TipoCliente tipo, double saldo) {
        this.nome = nome;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public String getNome()      { return nome; }
    public TipoCliente getTipo() { return tipo; }
    public double getSaldo()     { return saldo; }
}

// ── Pedido ─────────────────────────────────────────────────────────────────────
class Pedido {
    private int id;
    private Cliente cliente;
    private List<Item> itens;
    private StatusPedido status;

    public Pedido(int id, Cliente cliente, List<Item> itens) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.status = null; // será definido após análise
    }

    // Soma o valor total de todos os itens do pedido
    public double getValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValorTotal();
        }
        return total;
    }

    public int getId()               { return id; }
    public Cliente getCliente()      { return cliente; }
    public List<Item> getItens()     { return itens; }
    public StatusPedido getStatus()  { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
}