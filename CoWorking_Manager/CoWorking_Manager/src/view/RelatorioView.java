package Views;

import java.util.List;
import java.util.Scanner;

import controller.RelatorioController;
import model_.Espaco;
import model_.Reserva;

public class RelatorioViews{

    private RelatorioController controller;
    private Scanner sc = new Scanner(System.in);

    public RelatorioViews(RelatorioController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        int opc;

        do {
            System.out.println("\n===== RELATÓRIOS - COWORKING MANAGER =====");
            System.out.println("1 - Relatório Geral");
            System.out.println("2 - Relatório de Espaços");
            System.out.println("3 - Relatório Financeiro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();

            switch (opc) {
                case 1 -> gerarRelatorioGeral();
                case 2 -> gerarRelatorioEspacos();
                case 3 -> gerarRelatorioFinanceiro();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }

   
    private void gerarRelatorioGeral() {
        System.out.println("\n===== RELATÓRIO GERAL =====");

		/*
		 * int totalReservas = controller.getTotalReservas(); int totalEspacos =
		 * controller.getTotalEspacos();
		 * 
		 * 
		 * System.out.println("Período: " + controller.getPeriodoRelatorio());
		 * System.out.println("Total de Reservas: " + totalReservas);
		 * System.out.println("Total de Espaços Cadastrados: " + totalEspacos);
		 * System.out.println("------------------------------------------");
		 */    }

   
    private void gerarRelatorioEspacos() {
        System.out.println("\n===== RELATÓRIO DE ESPAÇOS =====");

		/*
		 * List<Espaco> espacos = controller.getEspacos();
		 * 
		 * System.out.printf("%-20s %-15s %-15s %-15s\n", "Espaço", "Reservas",
		 * "Horas Usadas", "Receita (R$)");
		 * 
		 * for (Espaco e : espacos) { int reservas = controller.getReservasPorEspaco(e);
		 * int horas = controller.getHorasUsadas(e); double receita =
		 * controller.getReceitaEspaco(e);
		 * 
		 * System.out.printf("%-20s %-15d %-15d %-15.2f\n", e.getNome(), reservas,
		 * horas, receita); }
		 */
        System.out.println("------------------------------------------");
    }

    
    private void gerarRelatorioFinanceiro() {
        System.out.println("\n===== RELATÓRIO FINANCEIRO =====");

		/*
		 * double faturamento = controller.getFaturamentoTotal(); double gastos =
		 * controller.getDespesasTotais(); // se não existir, posso te ajudar a criar
		 * double lucro = faturamento - gastos;
		 * 
		 * System.out.printf("Faturamento Total: R$ %.2f\n", faturamento);
		 * System.out.printf("Despesas Totais: R$ %.2f\n", gastos);
		 * System.out.printf("Lucro Líquido: R$ %.2f\n", lucro);
		 */
        System.out.println("------------------------------------------");
    }
}
// o que estar comentado estar dando erro, pehuei no chat e dessisti no meio pq eu ia bagunçar o codigo, isso ai e mais pra ter um norte 

