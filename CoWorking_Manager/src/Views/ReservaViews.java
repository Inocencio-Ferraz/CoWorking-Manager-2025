package Views;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import controller.EspacoController;
import controller.ReservaController;
import model_.Espaco;
import model_.Reserva;

public class ReservaViews {

    private Scanner sc = new Scanner(System.in);
    private EspacoController controller = new EspacoController();
    private ReservaController reserva = new ReservaController();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm");

    public void menuReserva() {

        while (true) {

            System.out.println("===== MENU DE RESERVAS =====");
            System.out.println("[1] Cadastrar Reserva\n[2] Lista de Reservas\n[3] Cancelar Reserva\n[4] Editar Reserva\n[5] Buscar Reserva\n[6] Voltar ao menu\nOpção: ");
            String entrada = sc.nextLine().trim(); 
            int op;
            try {
                op = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número entre 1 e 6.");
                continue;
            }

            switch (op) {
                case 1:
                    cadastrarReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    editarReserva();
                    break;
                case 5:
                    buscarReserva();
                    break;
                case 6:
                    System.out.println("Voltando ao menu....");
                    return;
                default:
                    System.out.println("Opção invalida");
            }
        }
    }

    private void cadastrarReserva() {
        System.out.println("\n===== ESPAÇOS CADASTRADOS =====");
        List<Espaco> lista = controller.listar();

        for (Espaco e : lista) {
            if (e == null) continue;
            System.out.println("ID: " + e.getId() +
                    " | Nome: " + e.getNome() +
                    " | Capacidade: " + e.getCapacidade() +
                    " | Preço/Hora: " + e.getPrecoPorHora());
        }
        System.out.println("");

        System.out.print("Digite o Id do espaço: ");
        String id = sc.nextLine().trim();

        try {
            System.out.print("Digite a hora de inicio (DD/MM/YYYY H:mm) : ");
            LocalDateTime inicio = LocalDateTime.parse(sc.nextLine().trim(), formatter);

            System.out.print("Digite a hora de término (DD/MM/YYYY H:mm) : ");
            LocalDateTime fim = LocalDateTime.parse(sc.nextLine().trim(), formatter);

            String msg = reserva.salvarReserva(id, inicio, fim);
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println("Erro ao ler datas. Use o formato DD/MM/YYYY H:mm (ex: 30/12/2025 13:00).");
        }
    }

    private void listarReservas() {
        List<Reserva> reservas = reserva.listarReservas();
        if (reservas == null || reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }

        for (Reserva r : reservas) {
            if (r == null) continue;
            System.out.println(
                    "\n--- RESERVA ---" +
                            "\nID: " + r.getId() +
                            "\nEspaço: " + (r.getEspaco() != null ? r.getEspaco().getNome() : "N/A")
                            + " (ID ESPAÇO: " + (r.getEspaco() != null ? r.getEspaco().getId() : "N/A") + ")" +
                            "\nInício: " + r.getDataHoraInicio() +
                            "\nFim: " + r.getDataHoraFim() +
                            "\nStatus: " + r.getStatus() +
                            "\n---------------------------"
            );
        }
    }

    private void cancelarReserva() {
        System.out.print("Digite o ID da reserva: ");
        String id = sc.nextLine().trim();

        String resposta = reserva.cancelarReserva(id);
        System.out.println(resposta);

        Reserva r = reserva.buscarPorId(id);
        if (r != null) {
            System.out.println("Estado atual da reserva:");
            System.out.println("ID: " + r.getId() + " | Status: " + r.getStatus());
        }
    }

    private void editarReserva() {
        System.out.print("Digite o ID da reserva: ");
        String id = sc.nextLine().trim();

        System.out.print("Digite o novo status da reserva (PENDENTE / CONFIRMADO / CANCELADO): ");
        String status = sc.nextLine().trim();

        String resposta = reserva.atualizarReserva(id, status);
        System.out.println(resposta);

        Reserva r = reserva.buscarPorId(id);
        if (r != null) {
            System.out.println("Reserva atualizada:");
            System.out.println("ID: " + r.getId() + " | Status: " + r.getStatus());
        }
    }

    private void buscarReserva() {
        System.out.print("Digite o ID da reserva: ");
        String id = sc.nextLine().trim();
        Reserva r = reserva.buscarPorId(id);
        if (r == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        System.out.println(
                "\n--- RESERVA ---" +
                        "\nID: " + r.getId() +
                        "\nEspaço: " + (r.getEspaco() != null ? r.getEspaco().getNome() : "N/A") +
                        "\nInício: " + r.getDataHoraInicio() +
                        "\nFim: " + r.getDataHoraFim() +
                        "\nStatus: " + r.getStatus() +
                        "\n---------------------------"
        );
    }
}
