package com.tavares.appcontatos._2_Infrastructure._3_exceptions;

import java.time.LocalDateTime;

public class Erro {
     private String erro;
    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    private LocalDateTime time;
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    private String sistema;

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
    
    public Erro() {
    }

    public Erro(String erro,  String sistema) {
        this.erro = erro;
        this.time = LocalDateTime.now();
        this.sistema = sistema;
    }
}
