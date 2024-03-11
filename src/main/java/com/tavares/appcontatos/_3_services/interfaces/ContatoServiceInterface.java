package com.tavares.appcontatos._3_services.interfaces;

import java.util.List;
import java.util.Optional;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;

public interface ContatoServiceInterface {
    Contato adicionarContato(Contato contato);
    Optional<Contato> obterContatoPorId(Long contatoId);
	List<Contato> listarContatos(Pessoa pessoa);
	Contato atualizarContato(Contato contato);
	void delete(Long id);
}
