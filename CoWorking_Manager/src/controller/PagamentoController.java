package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.PagamentoDAO;
import dao.ReservaDAO;

import enums.Status;
import model_.Pagamento;
import model_.Reserva;

public class PagamentoController {

    private PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private ReservaDAO reservaDAO = new ReservaDAO();
    private DateTimeFormatter formatadorData;

    public PagamentoController() {
        pagamentoDAO = new PagamentoDAO();
        reservaDAO = new ReservaDAO();
        formatadorData = DateTimeFormatter.ofPattern("H:m dd/MM/yyyy");
    }

    public String registrarPagamento(Integer idReserva, String metodo) {

        Reserva reserva = reservaDAO.buscarPorId(idReserva.toString());
        if (reserva == null) {
            return "Reserva não encontrada.";
        }

        boolean jaPago = pagamentoDAO.listar().stream()
                .anyMatch(p -> p.getIdReserva().equals(idReserva));
        if (jaPago) {
            return "Erro: Esta reserva já possui pagamento registrado.";
        }

        Double valor = reserva.calculoValor();

        List<Pagamento> lista = pagamentoDAO.listar();
        int novoId = lista.stream()
                .mapToInt(p -> p.getId() == null ? 0 : p.getId())
                .max()
                .orElse(0) + 1;

        Pagamento pagamento = new Pagamento(
                novoId,
                idReserva,
                valor,
                LocalDateTime.now(),
                enums.Pagamento.valueOf(metodo.toUpperCase())
        );

        pagamentoDAO.salvar(pagamento);

        reserva.setStatus(Status.CONFIRMADO);
        atualizarListaReserva(reserva);

        return "Pagamento registrado com sucesso. ID = " + novoId;
    }

    public void atualizarPagamento(String id, double valorPago, String data, String metodo) {

        Pagamento pagamento = pagamentoDAO.buscarPorId(id);
        if (pagamento == null) return;

        pagamento.setValor(valorPago);
        pagamento.setData(LocalDateTime.parse(data, formatadorData));
        pagamento.setMetodo( enums.Pagamento.valueOf(metodo.toUpperCase()));

        pagamentoDAO.salvar(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentoDAO.listar();
    }

    public Pagamento buscarPorId(Integer id) {
        return pagamentoDAO.buscarPorId(id.toString());
    }

    public String excluirPagamento(Integer id) {

        Pagamento pagamento = pagamentoDAO.buscarPorId(id.toString());
        if (pagamento == null) {
            return "Pagamento não encontrado.";
        }

        Reserva reserva = reservaDAO.buscarPorId(pagamento.getIdReserva().toString());
        if (reserva != null) {
            reserva.setStatus(Status.PENDENTE);
            atualizarListaReserva(reserva);
        }

        pagamentoDAO.excluir(id.toString());

        return "Pagamento excluído.";
    }

    private void atualizarListaReserva(Reserva reserva) {
        List<Reserva> todas = reservaDAO.listar();
        todas.replaceAll(r -> r.getId().equals(reserva.getId()) ? reserva : r);
        reservaDAO.salvarTodos(todas);
    }
}
