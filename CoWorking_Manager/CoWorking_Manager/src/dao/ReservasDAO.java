package dao;

import model_.Reservas;

public class ReservasDAO extends baseDAO<Reservas>{

	public ReservasDAO() {
		  super("data/Reservas.json", Reservas.class); 
	}

	@Override
    public Reservas buscarPorId(String id) {
        return listar(Reservas.class).stream()
                .filter(r -> r.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void excluir(String id) {
        var lista = listar(Reservas.class);
        lista.removeIf(r -> r.getId().toString().equals(id));
        salvarTodos(lista);
    }

}


