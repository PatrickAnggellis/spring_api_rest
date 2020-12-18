package br.com.web.apirest.builder;

import br.com.web.apirest.dto.EstudanteDTO;
import lombok.Builder;

@Builder
public class EstudanteDTOBuilder {
    
    @Builder.Default
    private Long idEstudante = 1L;

    @Builder.Default
    private String name = "Patrick";

    public EstudanteDTO toEstudanteDTO() {
        return new EstudanteDTO(idEstudante, name);
    }

}
