package com.tavares.appcontatos._2_Infrastructure._1_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tavares.appcontatos._1_dominio.Pessoa;
import com.tavares.appcontatos._2_Infrastructure._2_dto.PessoaDto;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(name = "build_pessoa_dto" , nativeQuery=true)
    List<PessoaDto> buildMalaDireta(Long id);
}
