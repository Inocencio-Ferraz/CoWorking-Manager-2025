package dao;

import model_.Espaco;

public class EspacoDAO extends BaseDAO<Espaco>{

	public EspacoDAO() {
        super("data/Espaco.json", Espaco.class); 
    }

	
	@Override
	public Espaco buscarPorId(String id) {
	    return listar(Espaco.class).stream()
	            .filter(e -> e != null)            
	            .filter(e -> id.equals(e.getId())) 
	            .findFirst()
	            .orElse(null);
	}


	@Override
	public void excluir(String id) {
	    var lista = listar(Espaco.class);
	    lista.removeIf(e -> e != null && id.equals(e.getId()));  
	    salvarTodos(lista);
	}


}

