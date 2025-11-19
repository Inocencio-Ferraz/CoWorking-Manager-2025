package dao;

import model_.Espaco;
import model_.Reservas;

public class ReservasDAO extends baseDAO<Reservas>{

	public ReservasDAO() {
		  super("data/Reservas.json", Reservas.class); 
	}

	@Override
	public void salvar() {
		
		
	}

	@Override
	public void excluir() {
		
		
	}

}


