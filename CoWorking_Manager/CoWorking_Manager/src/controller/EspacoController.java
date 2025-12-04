package controller;

//import java.time.LocalDateTime;

import dao.EspacoDAO;
import dao.PagamentoDAO;
import model_.Auditorio;
import model_.CabineIndividual;
import model_.Espaco;
import model_.Pagamento;
import model_.SalaDeReuniao;


public class EspacoController {
	
	EspacoDAO espacoDAO = new EspacoDAO();
	PagamentoDAO pagamentoDAO = new PagamentoDAO();
	
	public void salvarSalaDeReuniao(String name, Integer capacidade, Double precoPorHora) {	
		Espaco espaco = new SalaDeReuniao();	
		espacoDAO.salvar(espaco);
	}
	
	public void salvarCabineIndividual(String name, Integer capacidade, Double precoPorHora) {	
		Espaco espaco = new CabineIndividual();	
		espacoDAO.salvar(espaco);
	}
	
	public void salvarAuditorio(String name, Integer capacidade, Double precoPorHora) {
		Espaco espaco = new Auditorio();
		espacoDAO.salvar(espaco);
	}

}

