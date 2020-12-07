package br.com.web.apirest;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/*Classe geradora de ID*/
public class Turma {

    private String id;
    private String turma;

    public Turma(String id, String turma){
        this.id = id;
        this.turma = turma;
    }
    
    public Turma(String turma){
        this(UUID.randomUUID().toString(), turma);
    }

	public Object getId() {
		return id;
    }
    
    public String getTurma(){
        return turma;
    }

    public void setTurma(String turma){
        this.name = turma;
    }
}
