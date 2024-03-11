package com.tavares.appcontatos._1_dominio;

import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="CONTATOS")
public class Contato {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_contato", nullable = false)
    @NotNull(message = "Obrigatório informar o tipo de contato")
    private Integer tipoContato; 
    /*
    * 0 - Telefone
    * 1 - Celular
    * 2 - E-mail
    * 3- Rede Social
    */

    @Column(name = "contato", nullable = false)
    @NotNull(message = "Obrigatório informar  o contato.")
    private String contato;

    @JsonBackReference
    // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Contato() {
    }
    public Contato(Integer tipoContato, String contato) {
        // this.id = id;
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

    
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        return String.format("id: %s - tipoContato: %s - contato: %s;", this.id, this.tipoContato, this.contato);
    }




}
