package br.com.web.apirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String senha;

    @Column
    private Boolean isEstudante;

    @Column
    private Boolean isCoordenador;
    
    public Usuario(String email, String username, String senha, Boolean boolean1, Boolean boolean2){
        
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

	public Object getUsername() {
		return username;
	}

	public String getSenha() {
		return senha;
	}
}
