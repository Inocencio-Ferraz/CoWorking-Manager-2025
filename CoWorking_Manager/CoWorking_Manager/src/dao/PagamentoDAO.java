package dao;

import model_.Pagamento;
import model_.Reservas;

public class PagamentoDAO extends baseDAO<Pagamento> {

	public PagamentoDAO() {
		super("data/Pagamento.json", Pagamento.class); 
	
	}

	@Override
	public void salvar() {
		
	}

	@Override
	public void excluir() {
		
		
	}

	
}

