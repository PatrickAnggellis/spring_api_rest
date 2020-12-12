package br.com.web.apirest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.web.apirest.model.Usuario;
import br.com.web.apirest.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = UsuarioRepository.findByUsername(username);

        if(user.getUsername().equals(username)){
            return new User(username, user.getPassord(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Não existe usuário com o nome" + username);
        }
    }
}
