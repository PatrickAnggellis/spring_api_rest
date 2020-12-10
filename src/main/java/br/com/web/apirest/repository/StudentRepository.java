package br.com.web.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.web.apirest.model.Student;
 
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Método para encontrar uma pessoa por seu nome
   Optional<List<Student>> findByName(String name);
    
}
