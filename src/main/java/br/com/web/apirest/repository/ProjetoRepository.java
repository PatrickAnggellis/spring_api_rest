package br.com.web.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.apirest.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
    
}
