package com.tavares.appcontatos._2_Infrastructure._2_dto;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

@NamedNativeQuery(
    name = "build_pessoa_dto",
    query = "SELECT p.id , p.nome ,format('%s – CEP: %s – %s/%s',p.logradouro, p.cep, p.municipio, p.uf) AS  mala_direta  FROM pessoa p where p.id = ?1 ORDER BY p.nome ASC",
    resultSetMapping = "pessoa_dto"
)

    
@SqlResultSetMapping(
    name = "pessoa_dto",
    classes = @ConstructorResult(
        targetClass = PessoaDto.class,
        columns = {
            @ColumnResult(name = "id", type = Long.class),
            @ColumnResult(name = "nome", type = String.class),
            @ColumnResult(name = "mala_direta", type = String.class)
        }
    )
)

@Entity
public record PessoaDto(@Id Long id, String nome, String malaDireta) {
    
}


