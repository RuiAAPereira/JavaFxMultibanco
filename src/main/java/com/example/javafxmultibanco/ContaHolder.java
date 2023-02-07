package com.example.javafxmultibanco;

public final class ContaHolder {
    private Conta conta;

    private final static ContaHolder INSTANCE = new ContaHolder();

    private ContaHolder(){}

    public static ContaHolder getInstance() {
        return INSTANCE;
    }

    public void setConta(Conta c){
        this.conta = c;
    }

    public Conta getConta(){
        return this.conta;
    }
}
