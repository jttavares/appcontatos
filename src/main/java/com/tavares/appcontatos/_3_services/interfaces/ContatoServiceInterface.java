package com.tavares.appcontatos._3_services.interfaces;

import java.util.List;
import java.util.Optional;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._3_exceptions.ContatoNotFoundException;


public interface ContatoServiceInterface {
    Contato adicionarContato(Contato contato);
    Optional<Contato> obterContatoPorId(Long contatoId) throws ContatoNotFoundException ;
	List<Contato> listarContatos(Pessoa pessoa);
	Contato atualizarContato(Contato contato) throws ContatoNotFoundException;
	void delete(Long id);
}
