package br.com.web.apirest.config;

public enum FuncaoEstudantes {
    ESTAGIO ("estagio"), 
    JUNIOR("junior"),
    PLENO("pleno"), 
    SENIOR("senior"),
    MASTER("master"),
    ARQUITETO("arquiteto");  
    
    private String descricao;

    FuncaoEstudantes(String descricao){
        this.descricao = descricao;
    }
}
