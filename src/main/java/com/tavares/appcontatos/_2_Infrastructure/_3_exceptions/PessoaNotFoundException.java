package com.tavares.appcontatos._2_Infrastructure._3_exceptions;

public class PessoaNotFoundException extends Exception {
    public PessoaNotFoundException(Long id) {
        super(PessoaNotFoundException.defaultMessage(id));
    }
    private static String defaultMessage(Long id){
        return String.format("Pessoa com o id %s não encontrado na base de dados.", id.toString());
    }
}
