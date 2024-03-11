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
    
    TipoContato(String name) {
        switch (name.toUpperCase()) {
            case "TELEFONE":
                this.tipoNumero = 0;                
                break;
            case "CELULAR":
                this.tipoNumero = 1;                
                break;
                case "EMAIL":
                case "E-MAIL":
                this.tipoNumero = 2;                
                break;
                
            case "REDESOCIAL":
            case "REDE-SOCIAL":
            case "REDE SOCIAL":
                this.tipoNumero = 3;                
                break;
        }
    }

    public int getTipoContatoValor(){
        return this.tipoNumero;
    }
}
