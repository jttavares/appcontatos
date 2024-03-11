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
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
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
        System.out.println(">>>>>>>>>>>> PessoaService.obterPessoaPorId() >>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        return pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> listarPessoas() {
        System.out.println(">>>>>>>>>>>> PessoaService.listarPessoas() >>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
       return pessoaRepository.findAll();
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) {
        System.out.println(">>>>>>>>>>>> PessoaService.atualizar() >>>>>>>>>>>");
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
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            return pessoaRepository.save(pessoa);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        return pessoa;
    }

    private List<Contato> buildContatos(Pessoa pessoa, Pessoa updatePessoa) {
        System.out.println(">>>>>>>>>>>> PessoaService.buildContatos() >>>>>>>>>>>");
        List<Contato> contatos = new ArrayList<>();
        for (Contato contatoIn : pessoa.getContatos()) {
            Contato contato = new Contato(contatoIn.getTipoContato(), contatoIn.getContato());
            contato.setId(contatoIn.getId());
            contato.setPessoa(updatePessoa);
            contatos.add(contato);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        return contatos;
    }

    @Override
    public void delete(Long id) {
        System.out.println(">>>>>>>>>>>> PessoaService.delete() >>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        pessoaRepository.deleteById(id);
    }

    @Override
    public List<PessoaDto> buildMalaDireta(Long id) {
        System.out.println(">>>>>>>>>>>> PessoaService.obterPessoaPorId() >>>>>>>>>>>");
        List<PessoaDto> listPessoaDTOs = pessoaRepository.buildMalaDireta(id);
		if(listPessoaDTOs.size() > 0)
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            return listPessoaDTOs;
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		return null;
    }

    @Override
    public Pessoa addContato(Long id, Contato contato) {
        System.out.println(">>>>>>>>>>>> PessoaService.addContato() >>>>>>>>>>>");
        Optional<Pessoa> findPessoa = pessoaRepository.findById(id);
        if(findPessoa.isPresent()){
            Pessoa updatePessoa = findPessoa.get();
            contato.setPessoa(updatePessoa);
            List<Contato> contatos = buildContatos(findPessoa.get(), updatePessoa);
            contatos.add(contato);
            updatePessoa.setContatos(contatos);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            return pessoaRepository.save(updatePessoa);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        return null;
    }


}
