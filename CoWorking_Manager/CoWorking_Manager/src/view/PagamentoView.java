package Views;

import java.util.Scanner;

import com.google.gson.Gson;

import controller.PagamentoController;
import dao.PagamentoDAO;
import model_.Pagamento;

public class PagamentoViews {
	
	public PagamentoViews(){
	}
	
	public void operacaoPagamentoViews() {
		
		Scanner sc = new Scanner(System.in);
		//Gson gson = new Gson();

		while(true) {
			
		System.out.println("GERENCIAR PAGAMENTOS");
		System.out.print("\n[1] Registrar Pagamento\n[2] Lista de Pagamento\n[3] Buscar por ID\n[4] Excluir Pagamento\n[5] Voltar ao menu");
		System.out.print("\nOpção desejada: ");
		int op = sc.nextInt();
		
		if(op == 1) {
			new PagamentoController() ;
			System.out.println("Realizar Pagamento.");
			System.out.println("Qual e o ID da reseva: ");
			int id = sc.nextInt();
			System.out.print("Formas de Pagamento. \nPIX\nCARTAO\nDINHEIRO");
			System.out.print("\nQual e a forma de pagamento? ");
			String metodo = sc.next();
			/* jeito certo de salvar o pagamento 
			 * PagamentoDAO dao3 = new PagamentoDAO(); dao3.salvar(new Pagamento(4, 12,
			 * 20.0, java.time.LocalDateTime.now(), enums.Pagamento.PIX));
			 */
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
			
            break;
			
		}
	}

		sc.close();
	}

}
