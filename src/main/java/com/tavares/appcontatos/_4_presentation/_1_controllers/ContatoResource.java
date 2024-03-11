package com.tavares.appcontatos._4_presentation._1_controllers;


import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._3_services.implementations.ContatoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {

    private ContatoService contatoService;

    public ContatoResource(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    // GET /api/contatos/{id} (retorna os dados de um Contato por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contato>> obterContatosPorId(@PathVariable Long id) {
        Optional<Contato> contatoObtido = contatoService.obterContatoPorId(id);
        if(contatoObtido == null || contatoObtido.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contatoObtido);
    }


    // PUT /api/contatos/{id} (atualiza um Contato existente)
    @PutMapping
    public ResponseEntity<Contato> update(@RequestBody Contato contato){
        Contato upContato = contatoService.atualizarContato(contato);
        if(upContato == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(upContato);
    }

    // DELETE /api/contatos/{id} (remove um Contato por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
