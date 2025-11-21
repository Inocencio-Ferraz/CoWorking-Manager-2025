package dao;

import model_.Pagamento;


public class PagamentoDAO extends baseDAO<Pagamento> {

	public PagamentoDAO() {
		super("data/Pagamento.json", Pagamento.class); 
	
	}

	 @Override
	    public Pagamento buscarPorId(String id) { //buscaar por id
	        return listar(Pagamento.class).stream()
	                .filter(p -> p.getId().toString().equals(id))
	                .findFirst()
	                .orElse(null);
	    }

	    @Override
	    public void excluir(String id) { //excluir
	        var lista = listar(Pagamento.class);
	        lista.removeIf(p -> p.getId().toString().equals(id));
	        salvarTodos(lista);
	    }

	
}

