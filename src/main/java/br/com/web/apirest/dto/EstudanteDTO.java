package br.com.web.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstudanteDTO {

    private Long idEstudante;

	@NonNull
    private String name;

    public EstudanteDTO(Long idEstudante, String name) {
	}

	public String getName() {
		return name;
	}

	public Long getIdEstudante() {
		return idEstudante;
	}


}
