package br.com.fiap.merakiapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.merakiapi.model.Usuario;
import br.com.fiap.merakiapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listAll(){
        return repository.findAll();
    }

    public void create(Usuario usuario) {
        repository.save(usuario);
    }

    public Optional<Usuario> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
}
