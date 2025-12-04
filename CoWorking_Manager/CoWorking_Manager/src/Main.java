import com.google.gson.Gson;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Gson gson = new Gson();
		
	while(true) {
		
		System.out.print("Bem vindo ao CoWorking Manager!!!\nServiços disponíveis:");
		System.out.print("\n[1] Auditório\n[2] Cabine Indivídual\n[3] Sala de Reunião");
		System.out.print("\nOpção desejada: ");
		int op = sc.nextInt();
		
		if(op == 1){
			LimparTela.cls();
			System.out.print("\n");
			System.out.print("Reserva do Auditório.");
			System.out.print("Id: ");
			System.out.print("Nome: ");
			//System.out.print("Capacidade: ");
			//System.out.print("Disponível: ");
			//System.out.print("Preço por hora: ");
			
			
		}
		
	}
	
	}

}