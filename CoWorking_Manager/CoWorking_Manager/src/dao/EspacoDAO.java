package dao;

import model_.Espaco;

public class EspacoDAO extends BaseDAO<Espaco>{

	public EspacoDAO() {
        super("data/Espaco.json", Espaco.class); 
    }

	
	@Override
    public Espaco buscarPorId(String id) { //buscar por id
        return listar(Espaco.class).stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void excluir(String id) { //excluir
        var lista = listar(Espaco.class);
        lista.removeIf(e -> e.getId().equals(id));
        salvarTodos(lista);
    }

}



