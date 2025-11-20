package dao;
//teste
import java.util.List;

public interface Persistencia<T> {
	
	void salvar ();
	void excluir();
    List<T> listar(Class<T> clazz);

}
