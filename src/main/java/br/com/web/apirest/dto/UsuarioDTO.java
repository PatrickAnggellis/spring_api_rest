package br.com.web.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UsuarioDTO {
    private String email;
    private String username;
	private String password;
	private Boolean isEstudante;
	private Boolean isCoordenador;

	public Boolean getIsEstudante() {
		return this.isEstudante;
	}

	public Boolean getIsCoordenador() {
		return this.isCoordenador;
	}

    public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
