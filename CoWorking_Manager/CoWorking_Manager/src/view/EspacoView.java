package Views;

import java.util.Scanner;

import Program.LimparTela;
import controller.EspacoController;
import controller.ReservaController;

public class ReservaViews{

    private Scanner sc = new Scanner(System.in);
    private EspacoController controller = new EspacoController();
    private ReservaController reserva = new ReservaController();

    public void menuReserva() {
        int op;

        do {
            System.out.println("===== MENU DE RESERVAS =====");
            System.out.println("1 - Reservar Auditório");
            System.out.println("2 - Reservar Cabine Individual");
            System.out.println("3 - Reservar Sala de Reunião");
            System.out.println("4 - Ver Reservas");
            System.out.println("5 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine();

            LimparTela.cls();

            switch (op) {
                case 1 -> reservarAuditorio();
                case 2 -> reservarCabine();
                case 3 -> reservarSalaReuniao();
                case 4 -> visualizarReservas();
                case 5 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida!\n");
            }

        } while (op != 5);
    }

  
    private void reservarAuditorio() {
        System.out.println("\n=== Reserva de Auditório ===");
        String nome = solicitarNome();
        int capacidade = solicitarCapacidade();
        double preco = solicitarPrecoHora();

        String resultado = controller.salvarAuditorio(nome, capacidade, preco);
        System.out.println(resultado + "\n");
    }

    private void reservarCabine() {
        System.out.println("\n=== Reserva de Cabine Individual ===");
        String nome = solicitarNome();
        int capacidade = solicitarCapacidade();
        double preco = solicitarPrecoHora();

        String resultado = controller.salvarCabineIndividual(nome, capacidade, preco);
        System.out.println(resultado + "\n");
    }

    private void reservarSalaReuniao() {
        System.out.println("\n=== Reserva de Sala de Reunião ===");
        String nome = solicitarNome();
        int capacidade = solicitarCapacidade();
        double preco = solicitarPrecoHora();

        String resultado = controller.salvarSalaDeReuniao(nome, capacidade, preco);
        System.out.println(resultado + "\n");
    }

    private void visualizarReservas() {
        System.out.println("\n===== RESERVAS CADASTRADAS =====");
        System.out.println(reserva.listarEspacos()); 
        System.out.println("---------------------------------\n");
    }

    

    private String solicitarNome() {
        System.out.print("Nome: ");
        return sc.nextLine();
    }

    private int solicitarCapacidade() {
        System.out.print("Capacidade: ");
        return sc.nextInt();
    }

    private double solicitarPrecoHora() {
        System.out.print("Preço por hora: ");
        return sc.nextDouble();
    }
}
