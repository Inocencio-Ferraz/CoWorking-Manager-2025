package model_;

import java.time.Duration;
import java.time.LocalDateTime;

import exception.CustoInvalidoException;

public class SalaDeReuniao extends Espaco {

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private boolean usaProjetor;

    public SalaDeReuniao() {
        super();
    }

    public SalaDeReuniao(String id, String nome, int capacidade, boolean disponivel, double precoPorHora,LocalDateTime inicio, LocalDateTime fim, boolean usaProjetor) {
        super(id, nome, capacidade, disponivel, precoPorHora);
        this.inicio = inicio;
        this.fim = fim;
        this.usaProjetor = usaProjetor;
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

    public boolean isUsaProjetor() {
        return usaProjetor;
    }

    public void setUsaProjetor(boolean usaProjetor) {
        this.usaProjetor = usaProjetor;
    }

    @Override
    public double calcularCustoReserva(double horas) {
        if (horas <= 0 || getPrecoPorHora() <= 0) {
            throw new CustoInvalidoException("Horas e preço por hora devem ser maiores que zero.");
        }
        double custoBase = horas * getPrecoPorHora();
        if (usaProjetor) {
            custoBase += 15.0;
        }
        return custoBase;
    }


    
    public double calcularCustoReserva() {
        double horas = Duration.between(inicio, fim).toHours();
        return calcularCustoReserva(horas);
    }
}
