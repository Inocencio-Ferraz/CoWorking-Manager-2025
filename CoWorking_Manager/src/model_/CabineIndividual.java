package model_;

import java.time.Duration;
import java.time.LocalDateTime;

public class CabineIndividual extends Espaco {

    private LocalDateTime inicio;
    private LocalDateTime fim;

    public CabineIndividual() {
        super();
    }

    public CabineIndividual(String id, String nome, int capacidade, boolean disponivel, double precoPorHora, LocalDateTime inicio, LocalDateTime fim) {
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
        if (horas > 4) {
            custo *= 0.9; 
        }
        return custo;
    }

    public double calcularCustoReserva() {
        double horas = Duration.between(inicio, fim).toHours();
        return calcularCustoReserva(horas);
    }
}
