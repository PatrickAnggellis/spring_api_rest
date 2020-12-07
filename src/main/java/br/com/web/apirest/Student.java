package br.com.web.apirest;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/* Classe geradora de ID*/

public class Student {

    private final String id;
    private String name;

    public Student(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Student(String name){
        this(UUID.randomUUID().toString(), name);
    }

	public Object getId() {
		return id;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
   
}
