package dao;

import java.util.List;

public interface Persistencia<T> {
	
	void salvar (T obj);
	void excluir(String id);
    List<T> listar(Class<T> clazz);
    T buscarPorId (String id); 
    void salvarTodos(List<T> lista); //gson salva todo

}
