package com.tavares.appcontatos._3_services.interfaces;

import java.util.List;
import java.util.Optional;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._2_dto.PessoaDto;
import com.tavares.appcontatos._2_Infrastructure._3_exceptions.PessoaNotFoundException;

public interface PessoaServiceInterface {
    Pessoa criarPessoa(Pessoa pessoa);
    Optional<Pessoa> obterPessoaPorId(Long id) throws PessoaNotFoundException ;
    Pessoa addContato(Long id, Contato contato) throws PessoaNotFoundException ;
	List<Pessoa> listarPessoas();
	Pessoa atualizar(Pessoa pessoa) throws PessoaNotFoundException ;
	void delete(Long id);
	List<PessoaDto> buildMalaDireta(Long id) throws PessoaNotFoundException ;
}
