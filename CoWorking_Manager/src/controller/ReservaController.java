package controller;

import java.time.LocalDateTime;
import java.util.List;

import dao.EspacoDAO;
import dao.ReservaDAO;
import model_.Espaco;
import model_.Reserva;
import enums.Status;

public class ReservaController {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private EspacoDAO espacoDAO = new EspacoDAO();

    
    public String salvarReserva(String idEspaco, LocalDateTime inicio, LocalDateTime fim) {

        Espaco espaco = espacoDAO.buscarPorId(idEspaco);
        if (espaco == null) {
            return "Erro: Espaço não encontrado.";
        }

        return criarReserva(espaco, inicio, fim);
    }

    public int getTotalReservas() {
        return reservaDAO.listar().size();
    }

    public int getTotalEspacos() {
        return espacoDAO.listar().size();
    }

    public String criarReserva(Espaco espaco, LocalDateTime inicio, LocalDateTime fim) {

        if (espaco == null) {
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
        Reserva reserva = new Reserva(novoId, espaco, inicio, fim, Status.PENDENTE);

        reservaDAO.salvar(reserva);
        return "Reserva criada com sucesso! ID = " + novoId;
    }

    private boolean temSobreposicao(Espaco espaco, LocalDateTime inicio, LocalDateTime fim) {
        return reservaDAO.listar().stream()
                .filter(r -> r.getEspaco().getId().equals(espaco.getId()))
                .filter(r -> r.getStatus() != Status.CANCELADO)
                .anyMatch(r -> inicio.isBefore(r.getDataHoraFim()) && fim.isAfter(r.getDataHoraInicio()));
    }

    private int gerarNovoId() {
        List<Reserva> lista = reservaDAO.listar();
        return lista.isEmpty() ? 1 : lista.stream().mapToInt(Reserva::getId).max().orElse(0) + 1;
    }

    public Reserva buscarPorId(String id) {
        try {
            int intId = Integer.parseInt(id);
            return reservaDAO.listar().stream()
                    .filter(r -> r.getId() == intId)
                    .findFirst()
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    public String cancelarReserva(String id) {
        Reserva reserva = buscarPorId(id);
        if (reserva == null) return "Reserva não encontrada.";

        reserva.setStatus(Status.CANCELADO);
        atualizarListaReserva(reserva);
        return "Reserva cancelada com sucesso.";
    }

    public String atualizarReserva(String id, String status) {
        Reserva reserva = buscarPorId(id);
        if (reserva == null) return "Reserva não encontrada!";

        reserva.setStatus(Status.valueOf(status.toUpperCase()));
        atualizarListaReserva(reserva);
        return "Reserva atualizada!";
    }

    private void atualizarListaReserva(Reserva reserva) {
        List<Reserva> lista = reservaDAO.listar();
        lista.replaceAll(r -> r.getId() == reserva.getId() ? reserva : r);
        reservaDAO.salvarTodos(lista);
    }
}
