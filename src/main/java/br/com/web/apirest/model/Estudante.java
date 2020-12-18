package br.com.web.apirest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name ="estudante")
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEstudante;

    @Column(unique = true)
    private int matricula;

    
    @Column(name = "name")
    private String name;

    public Estudante(Long long1, String name){
        this.name = name;       
    }

    @ManyToMany
    @JoinTable(name = "AlunosProjeto",
                uniqueConstraints = @UniqueConstraint(columnNames = {"idEstudante","idProjeto"}))
    private List<Projeto> projetos;


    public Long getIdEstudante(){
        return idEstudante;
    }

    public String getName(){
        return name;
    }

    public int getMatricula() {
        return matricula;
    }

    public String ToString(){
        return this.name;
    }

}
