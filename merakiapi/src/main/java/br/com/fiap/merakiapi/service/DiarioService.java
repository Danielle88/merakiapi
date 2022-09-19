package br.com.fiap.merakiapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.merakiapi.model.Diario;
import br.com.fiap.merakiapi.repository.DiarioRepository;

import java.util.Optional;

@Service
public class DiarioService {

    @Autowired
    private DiarioRepository repository;

    public Page<Diario> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public void create(Diario diario) {
        repository.save(diario);
    }

    public Optional<Diario> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    
}
