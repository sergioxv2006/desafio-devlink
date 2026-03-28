// Ponto de entrada do sistema. Responsabilidade: montar os pedidos e acionar o analisador.
// Aqui ficam os exemplos que cobrem todos os cenários das regras de negócio.

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Empresa {

    public static void main(String[] args) {

        AnalisadorDePedido analisador = new AnalisadorDePedido();

        // ── Cenário 1: Pedido sem itens → deve ser REJEITADO ──────────────────
        Cliente joao = new Cliente("João", TipoCliente.NORMAL, 500.0);
        Pedido pedido1 = new Pedido(1, joao, Collections.emptyList());
        System.out.println("Pedido #1 — " + joao.getNome() + " (sem itens):");
        analisador.analisar(pedido1);
        System.out.println("  Status final: " + pedido1.getStatus());
        System.out.println();

        // ── Cenário 2: Saldo insuficiente → deve ser REJEITADO ────────────────
        Cliente maria = new Cliente("Maria", TipoCliente.NORMAL, 100.0);
        List<Item> itensCenario2 = Arrays.asList(
                new Item("Notebook", 800.0, 1)
        );
        Pedido pedido2 = new Pedido(2, maria, itensCenario2);
        System.out.println("Pedido #2 — " + maria.getNome() + " (saldo insuficiente):");
        analisador.analisar(pedido2);
        System.out.println("  Status final: " + pedido2.getStatus());
        System.out.println();

        // ── Cenário 3: Cliente NORMAL, valor ≤ 1000 → deve ser APROVADO ───────
        Cliente carlos = new Cliente("Carlos", TipoCliente.NORMAL, 2000.0);
        List<Item> itensCenario3 = Arrays.asList(
                new Item("Teclado", 150.0, 2),
                new Item("Mouse",    80.0, 1)
        );
        Pedido pedido3 = new Pedido(3, carlos, itensCenario3);
        System.out.println("Pedido #3 — " + carlos.getNome() + " (valor R$380,00):");
        analisador.analisar(pedido3);
        System.out.println("  Status final: " + pedido3.getStatus());
        System.out.println();

        // ── Cenário 4: Cliente NORMAL, valor > 1000 → deve ser PENDENTE ───────
        Cliente ana = new Cliente("Ana", TipoCliente.NORMAL, 5000.0);
        List<Item> itensCenario4 = Arrays.asList(
                new Item("Monitor", 700.0, 2)
        );
        Pedido pedido4 = new Pedido(4, ana, itensCenario4);
        System.out.println("Pedido #4 — " + ana.getNome() + " (valor R$1400,00, cliente NORMAL):");
        analisador.analisar(pedido4);
        System.out.println("  Status final: " + pedido4.getStatus());
        System.out.println();

        // ── Cenário 5: Cliente VIP, valor entre 1000 e 2000 → deve ser APROVADO
        Cliente vip = new Cliente("Beatriz", TipoCliente.VIP, 5000.0);
        List<Item> itensCenario5 = Arrays.asList(
                new Item("Cadeira Gamer", 1500.0, 1)
        );
        Pedido pedido5 = new Pedido(5, vip, itensCenario5);
        System.out.println("Pedido #5 — " + vip.getNome() + " (valor R$1500,00, cliente VIP):");
        analisador.analisar(pedido5);
        System.out.println("  Status final: " + pedido5.getStatus());
        System.out.println();

        // ── Cenário 6: Cliente VIP, valor > 2000 → deve ser PENDENTE ──────────
        Cliente vip2 = new Cliente("Roberto", TipoCliente.VIP, 10000.0);
        List<Item> itensCenario6 = Arrays.asList(
                new Item("Servidor", 3000.0, 1)
        );
        Pedido pedido6 = new Pedido(6, vip2, itensCenario6);
        System.out.println("Pedido #6 — " + vip2.getNome() + " (valor R$3000,00, VIP acima do limite):");
        analisador.analisar(pedido6);
        System.out.println("  Status final: " + pedido6.getStatus());
    }
}