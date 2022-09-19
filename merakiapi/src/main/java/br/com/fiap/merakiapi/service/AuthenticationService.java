package br.com.fiap.merakiapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.merakiapi.model.Usuario;
import br.com.fiap.merakiapi.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);

        if (usuario.isPresent()) return usuario.get();

        throw new UsernameNotFoundException("Usuário " + username + " não encontrado");

    }
    
}
