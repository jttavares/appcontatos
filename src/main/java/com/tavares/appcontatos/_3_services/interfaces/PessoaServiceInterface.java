package com.tavares.appcontatos._3_services.interfaces;

import java.util.List;
import java.util.Optional;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._2_dto.PessoaDto;

public interface PessoaServiceInterface {
    Pessoa criarPessoa(Pessoa pessoa);
    Optional<Pessoa> obterPessoaPorId(Long id);
    Pessoa addContato(Long id, Contato contato);
	List<Pessoa> listarPessoas();
	Pessoa atualizar(Pessoa pessoa);
	void delete(Long id);
	List<PessoaDto> buildMalaDireta(Long id);
}
