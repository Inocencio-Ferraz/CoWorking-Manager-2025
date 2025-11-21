package dao;

import model_.Reserva;

public class ReservaDAO extends baseDAO<Reserva>{

	public ReservaDAO() {
		  super("data/Reserva.json", Reserva.class); 
	}

	@Override
    public Reserva buscarPorId(String id) {
        return listar(Reserva.class).stream()
                .filter(r -> r.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void excluir(String id) {
        var lista = listar(Reserva.class);
        lista.removeIf(r -> r.getId().toString().equals(id));
        salvarTodos(lista);
    }

}



