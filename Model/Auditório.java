package model_;

import java.time.Duration;
import java.time.LocalDateTime;

public class Auditorio extends Espaco {

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private static final double TAXA_EVENTO = 100.0;

    public Auditorio() {
        super();
    }

    public Auditorio(String id, String nome, int capacidade, boolean disponivel, double precoPorHora, LocalDateTime inicio, LocalDateTime fim) {
        super(id, nome, capacidade, disponivel, precoPorHora);
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    @Override
    public double calcularCustoReserva(double horas) {
        double custo = horas * getPrecoPorHora();
        custo += TAXA_EVENTO; 
        return custo;
    }

    
    public double calcularCustoReserva() {
        double horas = Duration.between(inicio, fim).toHours();
        return calcularCustoReserva(horas);
    }

	
}
