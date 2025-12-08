package controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map; //Map substitui valores por outros valores, tipo 'neto' por 1, 'clebio' por 2....
import java.util.stream.Collectors; //junta os valores do map

//import dao.EspacoDAO;
import dao.PagamentoDAO;
import dao.ReservaDAO;
import model_.Pagamento;
import model_.Reserva;

public class RelatorioController {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private PagamentoDAO pagamentoDAO = new PagamentoDAO();
   
    
    public List<Reserva> reservasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) { 
        return reservaDAO.listar(Reserva.class).stream()
                .filter(r -> !r.getDataHoraInicio().isAfter(fim)
                          && !r.getDataHoraFim().isBefore(inicio))
                .collect(Collectors.toList());
    }

    public Map<String, Double> faturamentoPorMetodoPagamento() { 
        return pagamentoDAO.listar(Pagamento.class).stream()
                .collect(Collectors.groupingBy(
                        p -> p.getMetodo().name(),
                        Collectors.summingDouble(Pagamento::getValor)
                ));
    }

    public Map<String, Long> utilizacaoPorEspaco() { 
        return reservaDAO.listar(Reserva.class).stream()
                .collect(Collectors.groupingBy(
                        r -> r.getEspaco().getNome(),
                        Collectors.counting()
                ));
    }
    
    public Map<String, Long> topEspacosMaisUtilizados(int limite) {
        return utilizacaoPorEspaco().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
                .limit(limite) 
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,                
                        LinkedHashMap::new          
                ));
    }


   
}