package dao;

import java.util.List;

public interface  Persistencia {
	
	void salvar ();
	void excluir();
    List<T> listar(Class<T> clazz);

}
