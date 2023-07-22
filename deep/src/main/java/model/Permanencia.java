package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Permanencia {
    private int id;
    private int idCarro;
    private LocalDate dataSaida;
    private LocalTime horaSaida;
    private double valor;

    public Permanencia(int id, int idCarro, LocalDate dataSaida, LocalTime horaSaida, double valor) {
        this.id = id;
        this.idCarro = idCarro;
        this.dataSaida = dataSaida;
        this.horaSaida = horaSaida;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
