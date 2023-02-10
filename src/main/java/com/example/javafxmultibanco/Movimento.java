package com.example.javafxmultibanco;

import javafx.beans.property.*;

public class Movimento {

    private final IntegerProperty id;
    private final IntegerProperty cartao_id;
    private final StringProperty data;
    private final StringProperty descricao;
    private final DoubleProperty valor;
    private final IntegerProperty tipo;

    public Movimento() {
        this.id = new SimpleIntegerProperty();
        this.cartao_id = new SimpleIntegerProperty();
        this.data = new SimpleStringProperty();
        this.descricao = new SimpleStringProperty();
        this.valor = new SimpleDoubleProperty();
        this.tipo = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setCartao_id(int cartao_id) {
        this.cartao_id.set(cartao_id);
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public double getValor() {
        return valor.get();
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public int getTipo() {
        return tipo.get();
    }

    public void setTipo(int tipo) {
        this.tipo.set(tipo);
    }
}
