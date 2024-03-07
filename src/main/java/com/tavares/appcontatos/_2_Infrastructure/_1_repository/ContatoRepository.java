package com.tavares.appcontatos._2_Infrastructure._1_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavares.appcontatos._1_dominio.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
