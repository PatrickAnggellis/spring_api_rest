  
package br.com.web.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstudanteAlreadyRegisteredException extends Exception{

    private static final long serialVersionUID = -3338071026156769549L;

    public EstudanteAlreadyRegisteredException(String estudanteName) {
        super(String.format("Estudante com nome %s está registrado no sistema.", estudanteName));
    }
}