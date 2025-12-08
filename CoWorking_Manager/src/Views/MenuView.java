package Views;

import java.util.Scanner;

import controller.RelatorioController;

public class MenuView {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource") //Parar de reclamar do close.sc();
		Scanner sc = new Scanner(System.in);
		boolean rodando = true;
		int op = 0;
		
	while(rodando) {
		
		System.out.print("Bem vindo ao CoWorking Manager!!!\nServiços disponíveis:");
		System.out.print("\n[1] MENU Cadastrar espaço\n[2] MENU Reservar espaço\n[3] MENU Gerencia Pagamentos\n[4] Relatorio\n[5] Fechar Programa\nOpção: ");
		
		
		 try {
             String entrada = sc.nextLine();
             op = Integer.parseInt(entrada);
         } catch (NumberFormatException e) {
             System.out.println("\nErro: Digite APENAS números\n");
             continue; 
         }
		
		
		if(op == 1) {
			
			EspacoViews now = new EspacoViews();
			now.cadastrarEspaco();
			
		}
		else if(op == 2) {
			
			ReservaViews now = new ReservaViews() ;
			now.menuReserva();
			
		}
		else if(op == 3) {
			
			PagamentoViews now = new PagamentoViews();
			now.operacaoPagamentoViews();;
			
		}
		else if(op == 4) {
			
			RelatorioController relatorioController = new RelatorioController();
			RelatorioViews relatorioView = new RelatorioViews(relatorioController);
			relatorioView.menuRelatorios();
			
			
		}
		else if(op == 5) {
			System.out.println();
			System.out.println("------------------------------");
			System.out.println("      PROGRAMA ENCERRADO");
			rodando = false;
			break;
		}
		
	}
  }
}
