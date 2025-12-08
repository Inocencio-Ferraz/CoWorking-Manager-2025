package model_;

import java.time.LocalDateTime;

public class Pagamento {

	private Integer id;
	private Integer idReserva;
	private Double valor;
	private LocalDateTime data;
	private enums.Pagamento metodo;
	
	public Pagamento() {
		
	}

	public Pagamento(Integer id, Integer idReserva, Double valor, LocalDateTime data, enums.Pagamento metodo) {
		this.id = id;
		this.idReserva = idReserva;
		this.valor = valor;
		this.data = data;
		this.metodo = metodo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public enums.Pagamento getMetodo() {
		return metodo;
	}

	public void setMetodo(enums.Pagamento metodo) {
		this.metodo = metodo;
	}

	@Override
	public String toString() {
	    return "\n--- PAGAMENTO ---" +
	           "\nID: " + id +
	           "\nID da Reserva: " + idReserva +
	           "\nValor: R$ " + String.format("%.2f", valor) +
	           "\nData: " + data +
	           "\nMétodo: " + metodo +
	           "\n---------------------------";
	}
	
}


