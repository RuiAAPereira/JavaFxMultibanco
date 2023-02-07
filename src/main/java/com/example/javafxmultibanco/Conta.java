package com.example.javafxmultibanco;

import javafx.beans.property.*;

public class Conta {
    private final IntegerProperty id;
    private final IntegerProperty numero;
    private final IntegerProperty pin;
    private final StringProperty nome;
    private final DoubleProperty saldo;

    public Conta() {
        this.id = new SimpleIntegerProperty();
        this.numero = new SimpleIntegerProperty();
        this.pin = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.saldo = new SimpleDoubleProperty();
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getNumero() {
        return numero.get();
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public int getPin() {
        return pin.get();
    }

    public IntegerProperty pinProperty() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin.set(pin);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public double getSaldo() {
        return saldo.get();
    }

    public DoubleProperty saldoProperty() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo.set(saldo);
    }
}
