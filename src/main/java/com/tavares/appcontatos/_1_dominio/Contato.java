package com.tavares.appcontatos._1_dominio;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CONTATOS")
public class Contato {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_contato", nullable = false)
    private Integer tipoContato; 
    /*
    * 0 - Telefone
    * 1 - Celular
    * 2 - E-mail
    * 3- Rede Social
    */

    @Column(name = "contato", nullable = false)
    private String contato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private Long pessoaId;

    public Contato() {
    }
    public Contato(Integer tipoContato, String contato) {
        this.tipoContato = tipoContato;
        this.contato = contato;
    }


    //#region Getters and Setters
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

    public Long getPessoaId() {
        return pessoaId;
    }
    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
    //#endregion
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contato other = (Contato) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString(){
        return String.format("id: %id - tipoContato: %tipo - contato: %contato - pessoaId: %pessoa;", this.id, this.tipoContato, this.contato, this.pessoaId);
    }




}
