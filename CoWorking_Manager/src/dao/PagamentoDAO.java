package dao;

import java.util.List;

import model_.Pagamento;
//import model_.Reservas;

public class PagamentoDAO extends baseDAO<Pagamento> {

	public PagamentoDAO() {
		super("data/Pagamento.json", Pagamento.class); 
	
	}

	
	public void salvar() {
		
	}


	@Override
	public void excluir(String id) {
		
		
	}


	@Override
	public Pagamento buscarPorId(String id) {
		
		return null;
	}


	@Override
	public void salvarTodos(List<Pagamento> lista) {
		
		
	}

	
}

