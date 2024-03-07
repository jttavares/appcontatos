package com.tavares.appcontatos._4_presentation._1_controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._3_services.implementations.PessoaService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
    
    private PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    //o   POST /api/pessoas (cria uma nova Pessoa)
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa newPessoa = pessoaService.criarPessoa(pessoa);
        if(newPessoa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newPessoa);
    }
    


//o   GET /api/pessoas/{id} (retorna os dados de uma Pessoa por ID)
@GetMapping("/{id}")
public ResponseEntity<Optional<Pessoa>> obterPessoaPorId(@PathVariable Long id) {
    Optional<Pessoa> pessoaObtida = pessoaService.obterPessoaPorId(id);
    if(pessoaObtida == null){
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(pessoaObtida);
}



//o   GET /api/pessoas/maladireta/{id} (retorna os dados de uma Pessoa por ID para mala direta)

    
}
