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
public class ProfessorDTO {

    private String nome;
    private Long matricula;
    private String formacao;
    private String atuacao;

    // public String getNome() {
    //     return this.nome;
    // }

    // public Long getMatricula() {
    //     return this.matricula;
    // }

    // public String getFormacao() {
    //     return this.formacao;
    // }

    // public String getAtuacao() {
    //     return this.atuacao;
    // }
 
}
