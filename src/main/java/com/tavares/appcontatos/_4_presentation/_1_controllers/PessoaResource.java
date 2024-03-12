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
import com.tavares.appcontatos._2_Infrastructure._3_exceptions.PessoaNotFoundException;
import com.tavares.appcontatos._3_services.implementations.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name="Pessoas")
@RequestMapping("/api/pessoas")
public class PessoaResource {
    
    private PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    //o   POST /api/pessoas (cria uma nova Pessoa)
    @Operation(summary = "Criar um novo registro de Pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa newPessoa = pessoaService.criarPessoa(pessoa);
        if(newPessoa == null){            
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPessoa);
    }
    


    //o   GET /api/pessoas/{id} (retorna os dados de uma Pessoa por ID)
    @Operation(summary = "Busca registros de uma Pessoa cm base em seu id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> obterPessoaPorId(@PathVariable Long id) throws PessoaNotFoundException  {
        Optional<Pessoa> pessoaObtida = pessoaService.obterPessoaPorId(id);
        if(pessoaObtida == null || pessoaObtida.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pessoaObtida);
    }


    //o   GET /api/pessoas/maladireta/{id} (retorna os dados de uma Pessoa por ID para mala direta)
    @Operation(summary = "Busca dados de correspondência de uma Pessoa pra envio de mala direta")
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<List<PessoaDto>> obterMalaDireta(@PathVariable Long id) throws PessoaNotFoundException  {
        List<PessoaDto> malaDireta = pessoaService.buildMalaDireta(id);
            if(malaDireta == null)
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(malaDireta);
    }

    // GET /api/pessoas (lista todas as Pessoas)
    @Operation(summary = "Busca registros de todas as Pessoas cadastradas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas(){
        List<Pessoa> pessoas  = pessoaService.listarPessoas();
        if(pessoas == null){
            return ResponseEntity.badRequest().build();
        }
        if(pessoas.size() == 0){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pessoas);
    }

    // PUT /api/pessoas/{id} (atualiza uma Pessoa existente)
    @Operation(summary = "Atualizar os dados de uma Pessoa")
    @PutMapping
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) throws PessoaNotFoundException {
        Pessoa upPessoa = pessoaService.atualizar(pessoa);
        if(upPessoa == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(upPessoa);
    }
        
    // DELETE /api/pessoas/{id} (remove uma Pessoa por ID)
    @Operation(summary = "Remover uma Pessoa do cadastro")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //POST /api/pessoas/{id}/contatos ()
    @Operation(summary = "Adicionar um novo Contato a uma Pessoa")
    @PostMapping("/{id}/contatos")
    public ResponseEntity<Pessoa> addContato(@PathVariable Long id, @RequestBody Contato contato) throws PessoaNotFoundException  {
        Pessoa findPessoa = pessoaService.addContato(id, contato);
        if(findPessoa == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(findPessoa);
    }

    //GET /api/pessoas/{idPessoa}/contatos ()
    @Operation(summary = "Listar todos os Contatos de uma Pessoa")
    @GetMapping("/{idPessoa}/contatos")
    public ResponseEntity<List<Contato>> listarTodosContatos(@PathVariable Long idPessoa) throws PessoaNotFoundException  {
        Optional<Pessoa> findPessoa =  pessoaService.obterPessoaPorId(idPessoa);
        if(findPessoa == null){
            return ResponseEntity.badRequest().build();            
        }
        // List<Contato> contatos = contatoService.listarContatos(findPessoa.get());
        List<Contato> contatos = findPessoa.get().getContatos();
        if(contatos == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(contatos);
    }
    

}
