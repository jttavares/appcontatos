package com.tavares.appcontatos._1_dominio;

public enum TipoContato {
    Telefone(0), 
    Celular(1),
    Email(2),
    RedeSocial(3);

    public int tipoNumero;

    TipoContato(int valor) {
        this.tipoNumero = valor;
    }

    public int getTipoContatoValor(){
        return this.tipoNumero;
    }
}
