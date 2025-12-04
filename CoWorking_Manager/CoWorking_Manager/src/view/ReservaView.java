package Views;

import java.util.Scanner;

import com.google.gson.Gson;

import Program.LimparTela;
import controller.EspacoController;

public class ReservaViews {
	
	public ReservaViews() {
		
	}
	
	
	public void realizarRerserva() {
		Scanner sc = new Scanner(System.in);
		Gson gson = new Gson();
		
		System.out.print("\n[1] Auditório\n[2] Cabine Indivídual\n[3] Sala de Reunião\n[4] Ver reservas\n[5] Voltar ao menu");
		System.out.print("\nOpção desejada: ");
		int op = sc.nextInt();
		
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
		System.out.print("horas: ");
		double precoPorHora = sc.nextDouble();
		
		if(op == 1){
			
			EspacoController controller = new EspacoController();
			String salvar = controller.salvarAuditorio(nome, capacidade, precoPorHora);
		    System.out.println(salvar);
		}
		
		else if(op == 2){
			
			EspacoController controller = new EspacoController();
			String salvar = controller.salvarCabineIndividual(nome, capacidade, precoPorHora);
		    System.out.println(salvar);
	   }
		else if (op == 3)	{
			
			EspacoController controller = new EspacoController();
			String salvar = controller.salvarSalaDeReuniao(nome, capacidade, precoPorHora);
		    System.out.println(salvar);
		    
		}
		
	

	}
}
