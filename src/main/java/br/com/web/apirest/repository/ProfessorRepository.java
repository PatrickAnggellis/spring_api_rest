package br.com.web.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.apirest.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
