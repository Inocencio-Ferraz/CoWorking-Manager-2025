package controller;

import java.time.LocalDateTime;
import java.util.List;

import dao.ReservaDAO;
import model_.Espaco;
import model_.Reserva;
import enums.Status;

public class ReservaController {

    private ReservaDAO reservaDAO = new ReservaDAO();

    public String criarReserva(Espaco espaco,   //criando a reserva
                               LocalDateTime inicio,
                               LocalDateTime fim) {

        if (espaco == null) { //validando algumas entradas 
            return "Erro: Espaço inválido.";
        }

        if (inicio == null || fim == null) {
            return "Erro: Datas inválidas.";
        }

        if (!inicio.isBefore(fim)) {
            return "Erro: Data/hora inicial deve ser antes da final.";
        }

        if (temSobreposicao(espaco, inicio, fim)) {
            return "Erro: Já existe reserva desse espaço nesse período.";
        }

        int novoId = gerarNovoId();

        Reserva reserva = new Reserva( //Nova reserva
                novoId,
                espaco,
                inicio,
                fim,
                Status.PENDENTE
        );

        reservaDAO.salvar(reserva);

        return "Reserva criada com sucesso! ID = " + novoId;
    }

    private boolean temSobreposicao(Espaco espaco, //Sobreposição (não sei se era necessário)
                                    LocalDateTime inicio,
                                    LocalDateTime fim) {

        List<Reserva> reservas = reservaDAO.listar();

        return reservas.stream() //vendo se tem sobreposição em uma reserva
                .filter(r -> r.getEspaco().getId().equals(espaco.getId()))
                .filter(r -> r.getStatus() != Status.CANCELADO)
                .anyMatch(r ->
                        inicio.isBefore(r.getDataHoraFim()) &&
                        fim.isAfter(r.getDataHoraInicio())
                );
    }

    private int gerarNovoId() {
        List<Reserva> lista = reservaDAO.listar();
        if (lista.isEmpty()) return 1;

        return lista.stream()
                .mapToInt(Reserva::getId)
                .max()
                .orElse(0) + 1;
    }

    public Reserva buscarPorId(String id) {
        return reservaDAO.buscarPorId(id);
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    public String cancelarReserva(String id) {

        Reserva reserva = reservaDAO.buscarPorId(id);

        if (reserva == null) {
            return "Reserva não encontrada.";
        }

        reserva.setStatus(Status.CANCELADO);

        List<Reserva> lista = reservaDAO.listar();
        lista.removeIf(r -> r.getId().equals(reserva.getId()));
        lista.add(reserva);

        reservaDAO.salvarTodos(lista);

        return "Reserva cancelada com sucesso.";
    }
}
