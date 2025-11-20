package dao;

import model_.Espaco;

public abstract class EspacoDAO extends baseDAO<Espaco>{

	private EspacoDAO() {
        super("data/Espaco.json", Espaco.class); 
    }
	
	
	public void salvar() {
		
	}

	
	public void excluir() {
		
		
	}


	@Override
	public Espaco buscarPorId(String id) { 
		return null;
	}


	
	public void salvarTodos() { 
	}

}


