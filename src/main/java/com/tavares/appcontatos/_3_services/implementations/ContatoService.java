package com.tavares.appcontatos._3_services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._1_repository.ContatoRepository;
import com.tavares.appcontatos._3_services.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {

    private ContatoRepository contatoRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Override
    public Contato adicionarContato(Contato contato) {
        System.out.println("<<<<<<<<<<  ContatoService.adicionarContato()   <<<<<<<<<<<<<<<<<<<");
        Contato contatoOut = contatoRepository.save(contato);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return contatoOut;
    }

    @Override
    public Optional<Contato> obterContatoPorId(Long contatoId) {
        return contatoRepository.findById(contatoId);
    }

    @Override
    public List<Contato> listarContatos(Pessoa pessoa) {
        return contatoRepository.findAll()
                                .stream()
                                .filter(contact-> contact.getPessoa().getId() == pessoa.getId())
                                .toList();


    }

    @Override
    public Contato atualizarContato(Contato contato) {
        Optional<Contato> findContato = contatoRepository.findById(contato.getId());
		
		//se ele existir, vou atualizar:
		if(findContato.isPresent()) {
			Contato updateContato = findContato.get(); //setId
			updateContato.setTipoContato(contato.getTipoContato());
			updateContato.setContato(contato.getContato());
			return contatoRepository.save(updateContato);
		}
		return contato;	
    }

    @Override
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }

}
