package model_;

import java.time.Duration;
import java.time.LocalDateTime;
//import java.util.Date;

import enums.Status;

public class Reserva {

	protected Integer id;
	private Espaco espaco;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private Status status;
	
	public Reserva() {
	}

	public Reserva(Integer id, Espaco espaco, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Status status) {
		super();
		this.id = id;
		this.espaco = espaco;
		this.status = status;
		if(dataHoraInicio.isBefore(dataHoraFim) == false) {
			throw new IllegalArgumentException("Erro de Reserva.");
		}else {
			this.dataHoraInicio = dataHoraInicio;
			this.dataHoraFim = dataHoraFim;
		}
	}
	
	public double calculoHora() {
		Duration duracao = Duration.between(dataHoraInicio, dataHoraFim);
		return (double) duracao.toMinutes()/60; 
	}
	
	public double calculoValor() {
		double horas = calculoHora();
		return espaco.calcularCustoReserva(horas);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Espaco getEspaco() {
		return espaco;
	}

	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
	    return "\n--- RESERVA ---" +
	           "\nID: " + id +
	           "\nEspaço: " + (espaco != null ? espaco.getNome() + " (ID ESPAÇO: " + espaco.getId() + ")" : "N/A") +
	           "\nInício: " + dataHoraInicio +
	           "\nFim: " + dataHoraFim +
	           "\nStatus: " + status +
	           "\n---------------------------";
	}
}
