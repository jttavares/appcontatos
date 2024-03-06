package com.tavares.appcontatos._1_dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="CONTATOS")
public class Contato {
private Long id;
private Integer tipoContato; 
/*
 * 0 - Telefone
 * 1 - Celular
 * 2 - E-mail
 * 3- Rede Social
 */
private String contato;

public Contato() {
}
public Contato(Integer tipoContato, String contato) {
    this.tipoContato = tipoContato;
    this.contato = contato;
}



public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public Integer getTipoContato() {
    return tipoContato;
}
public void setTipoContato(Integer tipoContato) {
    this.tipoContato = tipoContato;
}

public String getContato() {
    return contato;
}
public void setContato(String contato) {
    this.contato = contato;
}


}
