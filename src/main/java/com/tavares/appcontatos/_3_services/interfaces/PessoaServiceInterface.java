package com.tavares.appcontatos._3_services.interfaces;

import java.util.List;
import java.util.Optional;

import com.tavares.appcontatos._1_dominio.Pessoa;

public interface PessoaServiceInterface {
    Pessoa criarPessoa(Pessoa pessoa);
    Optional<Pessoa> obterPessoaPorId(Long id);
	List<Pessoa> listarPessoas();
	Pessoa atualizar(Pessoa pessoa);
	void delete(Long id);
}
