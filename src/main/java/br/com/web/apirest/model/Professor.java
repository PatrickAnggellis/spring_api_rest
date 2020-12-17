package br.com.web.apirest.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "professor")
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProfessor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula", unique = true)
    private Long matricula;

    @Column(name = "formacao")
    private String formacao;

    @Column(name = "coordenador")
    private Boolean isCoordenador;

    @OneToMany(mappedBy = "nome")
    private Set<Projeto> projeto;

    public Professor(){

    }

    public Professor(String nome, Long matricula, String formacao){
        this.nome = nome;
        this.matricula = matricula;
        this.formacao = formacao;
    }

}
