// Responsabilidade: aplicar as regras de negócio e definir o status do pedido.
// Fica separado dos modelos para que cada arquivo tenha uma única responsabilidade.

public class AnalisadorDePedido {

    // Limite geral para aprovação automática
    private static final double LIMITE_NORMAL = 1000.0;

    // Limite para aprovação automática de clientes VIP
    private static final double LIMITE_VIP = 2000.0;

    /**
     * Analisa o pedido aplicando as regras de negócio em ordem e
     * atribui o status final diretamente no objeto Pedido.
     */
    public void analisar(Pedido pedido) {
        double valorTotal = pedido.getValorTotal();
        Cliente cliente = pedido.getCliente();

        // ── Regra 1: pedido sem itens → REJEITADO ─────────────────────────────
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            pedido.setStatus(StatusPedido.REJEITADO);
            System.out.println(" Rejeitado: pedido sem itens.");
            return;
        }

        // ── Regra 2: saldo insuficiente → REJEITADO ───────────────────────────
        if (cliente.getSaldo() < valorTotal) {
            pedido.setStatus(StatusPedido.REJEITADO);
            System.out.printf(" Rejeitado: saldo insuficiente (saldo: R$%.2f | total: R$%.2f)%n",
                    cliente.getSaldo(), valorTotal);
            return;
        }

        // ── Regra 3: cliente VIP com valor até 2000 → APROVADO ────────────────
        if (cliente.getTipo() == TipoCliente.VIP && valorTotal <= LIMITE_VIP) {
            pedido.setStatus(StatusPedido.APROVADO);
            System.out.printf(" Aprovado: cliente VIP, valor dentro do limite (R$%.2f).%n", valorTotal);
            return;
        }

        // ── Regra 4: valor até 1000 → APROVADO ───────────────────────────────
        if (valorTotal <= LIMITE_NORMAL) {
            pedido.setStatus(StatusPedido.APROVADO);
            System.out.printf(" Aprovado: valor dentro do limite normal (R$%.2f).%n", valorTotal);
            return;
        }

        // ── Regra 5: valor acima de 1000 (não VIP) → PENDENTE ─────────────────
        pedido.setStatus(StatusPedido.PENDENTE);
        System.out.printf(" Pendente: valor acima do limite (R$%.2f), requer revisão manual.%n", valorTotal);
    }
}