package br.com.web.apirest.mapper;

import br.com.web.apirest.dto.EstudanteDTO;
import br.com.web.apirest.model.Estudante;

public class EstudanteMapper {

    public static Estudante toModel(EstudanteDTO estudanteDTO){
        return new Estudante(estudanteDTO.getIdEstudante(), estudanteDTO.getName());
    }
 
    public static EstudanteDTO toDTO(Estudante estudante){
        return new EstudanteDTO(estudante.getIdEstudante(), estudante.getName());       
    }

}
