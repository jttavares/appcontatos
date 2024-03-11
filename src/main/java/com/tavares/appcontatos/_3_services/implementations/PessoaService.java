package com.tavares.appcontatos._3_services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._1_repository.PessoaRepository;
import com.tavares.appcontatos._2_Infrastructure._2_dto.PessoaDto;
import com.tavares.appcontatos._3_services.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface {
    private PessoaRepository pessoaRepository;
    private ContatoService contatoService;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, ContatoService contatoService) {
        this.pessoaRepository = pessoaRepository;
        this.contatoService = contatoService;
    }

    @Override
    public Pessoa criarPessoa(Pessoa pessoa) {
        System.out.println(">>>>>>>>>>>> PessoaService.criarPessoa() >>>>>>>>>>>");
        Pessoa pessoaIn = new Pessoa(pessoa.getNome(), pessoa.getEndereco(), pessoa.getCep(), pessoa.getCidade(), pessoa.getUf());

        List<Contato> contatos = new ArrayList<>();
        for (Contato contatoIn : pessoa.getContatos()) {
            Contato contato = new Contato(contatoIn.getTipoContato(), contatoIn.getContato());
            contato.setPessoa(pessoaIn);
            contatos.add(contato);
        }
        pessoaIn.setContatos(contatos);
        Pessoa pessoaOut = pessoaRepository.save(pessoaIn);
        System.out.println(String.format("pessoaOut = %s", pessoaOut));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        return pessoaOut;
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
            List<Contato> contatos = buildContatos(pessoa, updatePessoa);
            updatePessoa.setContatos(contatos);
            return pessoaRepository.save(pessoa);
        }
        return pessoa;
    }

    private List<Contato> buildContatos(Pessoa pessoa, Pessoa updatePessoa) {
        List<Contato> contatos = new ArrayList<>();
        for (Contato contatoIn : pessoa.getContatos()) {
            Contato contato = new Contato(contatoIn.getTipoContato(), contatoIn.getContato());
            contato.setId(contatoIn.getId());
            contato.setPessoa(updatePessoa);
            contatos.add(contato);
        }
        return contatos;
    }

    @Override
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public List<PessoaDto> buildMalaDireta(Long id) {
        List<PessoaDto> listPessoaDTOs = pessoaRepository.buildMalaDireta(id);
		if(listPessoaDTOs.size() > 0)
			return listPessoaDTOs;
		return null;
    }

    @Override
    public Pessoa addContato(Long id, Contato contato) {
         Optional<Pessoa> findPessoa = pessoaRepository.findById(id);
        if(findPessoa.isPresent()){
            Pessoa updatePessoa = findPessoa.get();
            contato.setPessoa(updatePessoa);
            // Contato newContato = contatoService.adicionarContato(contato);
            List<Contato> contatos = buildContatos(findPessoa.get(), updatePessoa);
            contatos.add(contato);
            updatePessoa.setContatos(contatos);
            return pessoaRepository.save(updatePessoa);
        }
        return null;
    }


}
