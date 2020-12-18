package br.com.web.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstudanteNotFoundException extends Exception {
    
    private static final long serialVersionUID = 5486937127569898244L;

    public EstudanteNotFoundException(String coffeeName) {
        super(String.format("Estudante com nome %s não foi encontrado no sistema.", coffeeName));
    }

    public EstudanteNotFoundException(Long id) {
        super(String.format("Estudante com o id %s não foi encontrado no sistema.", id));
    }
}