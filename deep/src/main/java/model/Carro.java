package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Carro {
    private int id;
    private String nomeCarro;
    private int idCliente;
    private String placa;
    private LocalDate dataEntrada;
    private LocalTime horaEntrada;
    private double valorAPagar;

    public Carro(int id, String nomeCarro, int idCliente, String placa, LocalDate dataEntrada, LocalTime horaEntrada, double valor) {
        this.id = id;
        this.nomeCarro = nomeCarro;
        this.idCliente = idCliente;
        this.placa = placa;
        this.dataEntrada = dataEntrada;
        this.horaEntrada = horaEntrada;
        this.valorAPagar = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public double getValor() {
        return valorAPagar;
    }

    public void setValor(double valor) {
        this.valorAPagar = valor;
    }
}
