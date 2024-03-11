package com.tavares.appcontatos._4_presentation._1_controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tavares.appcontatos._1_dominio.Contato;
import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._2_dto.PessoaDto;
import com.tavares.appcontatos._3_services.implementations.ContatoService;
import com.tavares.appcontatos._3_services.implementations.PessoaService;



@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
    
    private PessoaService pessoaService;
    private ContatoService contatoService;

    public PessoaResource(PessoaService pessoaService, ContatoService contatoService) {
        this.pessoaService = pessoaService;
        this.contatoService = contatoService;
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
        if(pessoaObtida == null || pessoaObtida.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoaObtida);
    }


    //o   GET /api/pessoas/maladireta/{id} (retorna os dados de uma Pessoa por ID para mala direta)
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<List<PessoaDto>> obterMalaDireta(@PathVariable Long id) {
        List<PessoaDto> malaDireta = pessoaService.buildMalaDireta(id);
            if(malaDireta == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(malaDireta);
    }

    // GET /api/pessoas (lista todas as Pessoas)
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas(){
        List<Pessoa> pessoas  = pessoaService.listarPessoas();
        if(pessoas == null){
            return ResponseEntity.notFound().build();
        }
        if(pessoas.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoas);
    }

    // PUT /api/pessoas/{id} (atualiza uma Pessoa existente)
    @PutMapping
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
        Pessoa upPessoa = pessoaService.atualizar(pessoa);
        if(upPessoa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(upPessoa);
    }
        
    // DELETE /api/pessoas/{id} (remove uma Pessoa por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //POST /api/pessoas/{id}/contatos (adiciona um novo Contato a uma Pessoa)
    @PostMapping("/{id}/contatos")
    public ResponseEntity<Pessoa> addContato(@PathVariable Long id, @RequestBody Contato contato) {
        Pessoa findPessoa = pessoaService.addContato(id, contato);
        if(findPessoa == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findPessoa);
    }

    //GET /api/pessoas/{idPessoa}/contatos (lista todos os Contatos de uma Pessoa)
    
    @GetMapping("/{idPessoa}/contatos")
    public ResponseEntity<List<Contato>> listarTodosContatos(@PathVariable Long idPessoa) {
        Optional<Pessoa> findPessoa =  pessoaService.obterPessoaPorId(idPessoa);
        if(findPessoa == null){
            return ResponseEntity.notFound().build();            
        }
        // List<Contato> contatos = contatoService.listarContatos(findPessoa.get());
        List<Contato> contatos = findPessoa.get().getContatos();
        if(contatos == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contatos);
    }
    

}
