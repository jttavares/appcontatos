package com.tavares.appcontatos._2_Infrastructure._3_exceptions;

public class ContatoNotFoundException extends Exception {
    public ContatoNotFoundException(Long id) {
        super(ContatoNotFoundException.defaultMessage(id));
        
    }
    private static String defaultMessage(Long id){
        return String.format("Contato com o id %s não encontrado na base de dados.", id.toString());
    }
}
