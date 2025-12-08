package Views;

import java.util.Scanner;

import Program.LimparTela;
import controller.EspacoController;
import exception.CapacidadeInvalidaException;
import exception.NomeInvalidoException;
import exception.PrecoInvalidoException;

public class EspacoViews {

    private Scanner sc = new Scanner(System.in);
    private EspacoController controller = new EspacoController();

    public EspacoViews() {}

    public void cadastrarEspaco() {
        int op = 0;

        do {
            System.out.println("====== CADASTRAR ESPAÇO ======");
            System.out.println("1 - Cadastrar Auditório");
            System.out.println("2 - Cadastrar Cabine Individual");
            System.out.println("3 - Cadastrar Sala de Reunião");
            System.out.println("4 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número válido!\n");
                continue;
            }

            LimparTela.cls();

            try {
                switch (op) {
                    case 1 -> reservarAuditorio();
                    case 2 -> reservarCabine();
                    case 3 -> reservarSalaReuniao();
                    case 4 -> {
                        System.out.println("Voltando ao menu principal...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!\n");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (op != 4);
    }

    private void reservarAuditorio() {
        System.out.println("\n=== Cadastro de Auditório ===");
        String nome = solicitarNome();
        int capacidade = solicitarCapacidade();
        double preco = solicitarPrecoHora();

        String resultado = controller.salvarAuditorio(nome, capacidade, preco);
        System.out.println(resultado + "\n");
    }

    private void reservarCabine() {
        System.out.println("\n=== Cadastro de Cabine Individual ===");
        String nome = solicitarNome();
        int capacidade = solicitarCapacidade();
        double preco = solicitarPrecoHora();

        String resultado = controller.salvarCabineIndividual(nome, capacidade, preco);
        System.out.println(resultado + "\n");
    }

    private void reservarSalaReuniao() {
        System.out.println("\n=== Cadastro de Sala de Reunião ===");
        String nome = solicitarNome();
        int capacidade = solicitarCapacidade();
        double preco = solicitarPrecoHora();

        String resultado = controller.salvarSalaDeReuniao(nome, capacidade, preco);
        System.out.println(resultado + "\n");
    }

 

    private String solicitarNome() {
        System.out.print("Nome do espaço: ");
        String nome = sc.nextLine();

        if (nome.isBlank()) {
            throw new NomeInvalidoException("O nome não pode estar vazio.");
        }
        return nome;
    }

    private int solicitarCapacidade() {
        System.out.print("Capacidade: ");
        String entrada = sc.nextLine();

        try {
            int capacidade = Integer.parseInt(entrada);
            if (capacidade <= 0) {
                throw new CapacidadeInvalidaException("A capacidade deve ser maior que zero.");
            }
            return capacidade;
        } catch (NumberFormatException e) {
            throw new CapacidadeInvalidaException("Digite um número inteiro para capacidade.");
        }
    }

    private double solicitarPrecoHora() {
        System.out.print("Preço por hora: ");
        String entrada = sc.nextLine();

        try {
            double valor = Double.parseDouble(entrada);
            if (valor <= 0) {
                throw new PrecoInvalidoException("O preço deve ser maior que zero.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new PrecoInvalidoException("Digite um número decimal válido (ex: 12.50).");
        }
    }
}
