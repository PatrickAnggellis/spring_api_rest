package br.com.web.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.web.apirest.model.Estudante;
 
@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

    // Método para encontrar uma pessoa por seu nome
   Optional<Estudante> findByName(String name);

}
