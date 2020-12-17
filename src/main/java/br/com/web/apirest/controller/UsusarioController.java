package br.com.web.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.apirest.dto.UsuarioDTO;
import br.com.web.apirest.model.Usuario;
import br.com.web.apirest.repository.UsuarioRepository;

@RestController
@CrossOrigin
public class UsusarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioDTO user){
        try {
            return ResponseEntity.ok(usuarioRepository.save(new Usuario(user.getEmail(), user.getUsername(), user.getPassword(), user.getIsCoordenador(), user.getIsEstudante())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
