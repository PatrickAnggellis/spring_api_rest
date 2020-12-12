package br.com.web.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.apirest.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByUsername(String username);
    
}
