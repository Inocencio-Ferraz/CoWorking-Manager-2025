import com.google.gson.Gson;
import controller.EspacoController;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Gson gson = new Gson();
		
	while(true) {
		
		System.out.print("Bem vindo ao CoWorking Manager!!!\nServiços disponíveis:");
		System.out.print("\n[1] Auditório\n[2] Cabine Indivídual\n[3] Sala de Reunião\n[4] Ver reservas");
		System.out.print("\nOpção desejada: ");
		int op = sc.nextInt();
		
		if(op == 1){
			LimparTela.cls();
			sc.nextLine();
			
			System.out.print("\n");
			System.out.print("Reserva do Auditório.");
			System.out.print("\n");
			//System.out.print("Id: ");
			
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			
			System.out.print("Capacidade: ");
			int capacidade = sc.nextInt();
			
			//System.out.print("Disponível: ");
			System.out.print("Preço: ");
			double precoPorHora = sc.nextDouble();
			
			EspacoController controller = new EspacoController();
			String salvar = controller.salvarAuditorio(nome, capacidade, precoPorHora);
		    System.out.println(salvar);
		}
		
		else if(op == 2){
			LimparTela.cls();
			sc.nextLine();
			
			System.out.print("\n");
			System.out.print("Cabine Indivídual.");
			
			//System.out.print("Id: ");
			
			System.out.print("\nNome: ");
			String nome = sc.nextLine();
			
			System.out.print("Capacidade: ");
			int capacidade = sc.nextInt();
			
			//System.out.print("Disponível: ");
			System.out.print("Preço: ");
			double precoPorHora = sc.nextDouble();
			
			EspacoController controller = new EspacoController();
			String salvar = controller.salvarCabineIndividual(nome, capacidade, precoPorHora);
		    System.out.println(salvar);
		}
		
		else if(op == 3){
			LimparTela.cls();
			sc.nextLine();
			
			System.out.print("\n");
			System.out.print("Sala de reunião.");
			
			//System.out.print("Id: ");
			
			System.out.print("\nNome: ");
			String nome = sc.nextLine();
			
			System.out.print("Capacidade: ");
			int capacidade = sc.nextInt();
			
			//System.out.print("Disponível: ");
			System.out.print("Preço: ");
			double precoPorHora = sc.nextDouble();
			
			EspacoController controller = new EspacoController();
			String salvar = controller.salvarSalaDeReuniao(nome, capacidade, precoPorHora);
		    System.out.println(salvar);
		}
		
		else if(op == 4) {
			System.out.println("Todas as reservas: ");
		}
		else {
			System.out.println("Opção inválida.");
		}
		System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
        LimparTela.cls();
		
	}
	
	}

}