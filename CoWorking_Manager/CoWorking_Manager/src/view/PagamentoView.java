package view;

import java.util.Scanner;

import com.google.gson.Gson;

import controller.PagamentoController;

public class PagamentoView {
	
	public PagamentoView(){
	}
	
	public void operacaoPagamentoViews() {
		
		Scanner sc = new Scanner(System.in);
		Gson gson = new Gson();

		
		System.out.print("\n[1] Registrar Pagamento\n[2] Lista de Pagamento\n[3] Buscar por ID\n[4] Excluir Pagamento\n[5] Voltar ao menu");
		System.out.print("\nOpção desejada: ");
		int op = sc.nextInt();
		
		if(op == 1) {
			new PagamentoController() ;
			System.out.println("Realizar Pagamento.");
			System.out.println("Qual e o ID da reseva: ");
			int id = sc.nextInt();
			System.out.print("Formas de Pagamento. \nPIX\nCARTAO\nDINHEIRO");
			System.out.print("Qual e a forma de pagamento? ");
			String metodo = sc.next();
			new PagamentoController().registrarPagamento(id, metodo);
		}
		else if(op == 2) {
			
			new PagamentoController().listarPagamentos();
		}
		else if(op == 3) { 
			
			System.out.print("Id da reserva que deseja buscar: ");
			int id = sc.nextInt();
			new PagamentoController().buscarPorId(id);
			
		}
		else if(op == 4) {
			System.out.print("Id da reserva que deseja excluir: ");
			int id = sc.nextInt();
			new PagamentoController().excluirPagamento(id);
			
		}
		else if (op == 5) {
			return;	
		}
	}
	

}
