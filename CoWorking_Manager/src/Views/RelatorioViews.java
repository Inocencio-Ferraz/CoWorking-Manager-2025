package Views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import controller.RelatorioController;
import model_.Reserva;

public class RelatorioViews {

    private RelatorioController controller;
    private Scanner sc = new Scanner(System.in);
   

    public RelatorioViews(RelatorioController controller) {
        this.controller = controller;
    }

    public void menuRelatorios() {
        int op;

        do {
            System.out.println("\n===== MENU DE RELATÓRIOS =====");
            System.out.println("[1] Reservas por espaço específico\n[2] Reservas por período\n[3] Top Espaços\n[4] Relatório financeiro\n"
            		+ "[5]  Voltar");
            System.out.print("Opção: ");
          
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    relatorioUtilizacaoEspacos();
                    break;
                case 2:
                    relatorioReservasPorPeriodo();
                    break;
                case 3:
                	relatorioTopEspacos() ;
                    break;
                case 4:
                    relatorioFaturamentoPorPagamento();
                    break;
                case 5:
                    System.out.println("Retornando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (op != 5);
    }


    private void relatorioUtilizacaoEspacos() {
        System.out.println("\n===== RELATÓRIO - UTILIZAÇÃO POR ESPAÇO =====");

        Map<String, Long> dados = controller.utilizacaoPorEspaco();

        if (dados.isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
            return;
        }

        dados.forEach((espaco, qtd) ->
                System.out.println("Espaço: " + espaco + " | Reservas: " + qtd)
        );
        System.out.println("---------------------------------------------");
    }

    
    private void relatorioReservasPorPeriodo() {
        try {
            System.out.println("\n===== RELATÓRIO - RESERVAS POR PERÍODO =====");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.print("Digite a data inicial (DD/MM/YYYY): ");
            String dataInicioStr = sc.nextLine();

            System.out.print("Digite a data final (DD/MM/YYYY): ");
            String dataFimStr = sc.nextLine();

            LocalDate inicioDate = LocalDate.parse(dataInicioStr, formatter);
            LocalDate fimDate = LocalDate.parse(dataFimStr, formatter);

            LocalDateTime inicio = inicioDate.atStartOfDay();         // 00:00:00
            LocalDateTime fim = fimDate.atTime(23, 59, 59);           // 23:59:59

            var lista = controller.reservasPorPeriodo(inicio, fim);

            if (lista.isEmpty()) {
                System.out.println("Nenhuma reserva encontrada neste período.");
                return;
            }

            for (Reserva r : lista) {
                System.out.println("Espaço: " + r.getEspaco().getNome() +
                        " | Início: " + r.getDataHoraInicio() +
                        " | Fim: " + r.getDataHoraFim());
            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            System.out.println("Formato inválido! Use DD/MM/YYYY e tente novamente.");
        }
    }


   
    private void relatorioFaturamentoPorPagamento() {
        System.out.println("\n===== RELATÓRIO - FATURAMENTO POR MÉTODO DE PAGAMENTO =====");

        Map<String, Double> dados = controller.faturamentoPorMetodoPagamento();

        if (dados.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }

        dados.forEach((metodo, total) ->
                System.out.printf("Método: %-10s | Total: R$ %.2f%n", metodo, total)
        );

        System.out.println("---------------------------------------------");
    }
    
    private void relatorioTopEspacos() {
        System.out.println("\n===== TOP ESPAÇOS MAIS UTILIZADOS =====");
        System.out.print("Quantos espaços deseja listar? ");
        int qtd = sc.nextInt();
        sc.nextLine();

        var mapa = controller.topEspacosMaisUtilizados(qtd);

        if(mapa.isEmpty()){
            System.out.println("Nenhuma reserva registrada.");
            return;
        }

        mapa.forEach((nome, total) -> 
            System.out.println(nome + " | Reservas: " + total)
        );

        System.out.println("-----------------------------------");
    }

}
