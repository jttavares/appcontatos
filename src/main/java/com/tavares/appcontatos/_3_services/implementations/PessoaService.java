package com.tavares.appcontatos._3_services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._1_repository.PessoaRepository;
import com.tavares.appcontatos._3_services.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface {
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa criarPessoa(Pessoa pessoa) {
       return this.pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> obterPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> listarPessoas() {
       return pessoaRepository.findAll();
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) {
        Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
        if(findPessoa.isPresent()){
            Pessoa updatePessoa = findPessoa.get();
            updatePessoa.setNome(pessoa.getNome());
            updatePessoa.setEndereco(pessoa.getEndereco());
            updatePessoa.setCep(pessoa.getCep());
            updatePessoa.setCidade(pessoa.getCidade());
            updatePessoa.setUf(pessoa.getUf());
            updatePessoa.setContatos(pessoa.getContatos());
            return pessoaRepository.save(pessoa);
        }
        return pessoa;
    }

    @Override
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }


}
