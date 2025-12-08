package Views;

import java.util.Scanner;

import controller.PagamentoController;

public class PagamentoViews {
	
	public PagamentoViews(){
	}
	
	public void operacaoPagamentoViews() {
		
		@SuppressWarnings("resource") // parar de reclamar do scanner nao está fechado.
		Scanner sc = new Scanner(System.in);
		
		boolean rodando = true;

		while(rodando) {
			
		System.out.println("\nGERENCIAR PAGAMENTOS");
		System.out.print("\n[1] Registrar Pagamento\n[2] Lista de Pagamento\n[3] Buscar por ID\n[4] Excluir Pagamento\n[5] Voltar ao menu");
		System.out.print("\nOpção desejada: ");
		int op = sc.nextInt();
		sc.nextLine();
		
		if(op == 1) {
			new PagamentoController() ;
			System.out.println("Realizar Pagamento.");
			System.out.println("Qual e o ID da reseva: ");
			int id = sc.nextInt();
			System.out.print("Formas de Pagamento. \nPIX\nCARTAO\nDINHEIRO");
			System.out.print("\nQual e a forma de pagamento? ");
			String metodo = sc.next();
			
			new PagamentoController().registrarPagamento(id, metodo);
		}
		else if(op == 2) {
			
		System.out.println(new PagamentoController().listarPagamentos());
		}
		else if(op == 3) { 
			
			System.out.print("Id da reserva que deseja buscar: ");
			int id = sc.nextInt();
			System.out.println(new PagamentoController().buscarPorId(id));
			
		}
		else if(op == 4) {
			System.out.print("Id da reserva que deseja excluir: ");
			int id = sc.nextInt();
			new PagamentoController().excluirPagamento(id);
			
		}
		else if (op == 5) {
			rodando = false;
			break;
		}
	}

		
	}

}