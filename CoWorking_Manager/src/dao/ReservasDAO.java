package dao;

import java.util.List;

//import model_.Espaco;
import model_.Reservas;

public class ReservasDAO extends baseDAO<Reservas>{

	public ReservasDAO() {
		  super("data/Reservas.json", Reservas.class); 
	}

	
	public void salvar() {
			
	}


	@Override
	public void excluir(String id) {
		
		
	}

	@Override
	public Reservas buscarPorId(String id) {
		
		return null;
	}

	@Override
	public void salvarTodos(List<Reservas> lista) {
		
		
	}

}


