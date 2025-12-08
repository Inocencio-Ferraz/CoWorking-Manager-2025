package dao;

import model_.Pagamento;


public class PagamentoDAO extends BaseDAO<Pagamento> {

	public PagamentoDAO() {
		super("data/Pagamento.json", Pagamento.class); 
	
	}

	 @Override
	    public Pagamento buscarPorId(String id) { 
	        return listar(Pagamento.class).stream()
	                .filter(p -> p.getId().toString().equals(id))
	                .findFirst()
	                .orElse(null);
	    }

	    @Override
	    public void excluir(String id) {
	        var lista = listar(Pagamento.class);
	        lista.removeIf(p -> p.getId().toString().equals(id));
	        salvarTodos(lista);
	    }

	
}
