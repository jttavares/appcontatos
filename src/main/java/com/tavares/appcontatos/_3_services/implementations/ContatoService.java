package com.tavares.appcontatos._3_services.implementations;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._1_repository.ContatoRepository;
import com.tavares.appcontatos._2_Infrastructure._3_exceptions.ContatoNotFoundException;
import com.tavares.appcontatos._2_Infrastructure._3_exceptions.PessoaNotFoundException;
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
    public Optional<Contato> obterContatoPorId(Long contatoId) throws ContatoNotFoundException  {
        System.out.println("<<<<<<<<<<  ContatoService.obterContatoPorId()   <<<<<<<<<<<<<<<<<<<");
        Optional<Contato> findContato = contatoRepository.findById(contatoId);
        if(findContato.isPresent()){
            return findContato;
        }
        else{
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            throw new ContatoNotFoundException(contatoId);
        }
    }

    @Override
    public List<Contato> listarContatos(Pessoa pessoa) {
        System.out.println("<<<<<<<<<<  ContatoService.listarContatos()   <<<<<<<<<<<<<<<<<<<");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return contatoRepository.findAll()
                                .stream()
                                .filter(contact-> contact.getPessoa().getId() == pessoa.getId())
                                .toList();


    }

    @Override
    public Contato atualizarContato(Contato contato) throws ContatoNotFoundException {
        System.out.println("<<<<<<<<<<  ContatoService.atualizarContato()   <<<<<<<<<<<<<<<<<<<");
        Long contatoId = contato.getId();
        Optional<Contato> findContato = contatoRepository.findById(contatoId);
		
		//se ele existir, vou atualizar:
		if(findContato.isPresent()) {
            Contato updateContato = findContato.get(); //setId
			updateContato.setTipoContato(contato.getTipoContato());
			updateContato.setContato(contato.getContato());
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			return contatoRepository.save(updateContato);
		}
        else{
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            throw new ContatoNotFoundException(contatoId);
        }
    }

    @Override
    public void delete(Long id) {
        System.out.println("<<<<<<<<<<  ContatoService.delete()   <<<<<<<<<<<<<<<<<<<");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        contatoRepository.deleteById(id);
    }

}
