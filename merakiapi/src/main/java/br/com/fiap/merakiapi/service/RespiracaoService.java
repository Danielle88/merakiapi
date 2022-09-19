package br.com.fiap.merakiapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.merakiapi.model.Respiracao;
import br.com.fiap.merakiapi.repository.RespiracaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RespiracaoService {

    @Autowired
    private RespiracaoRepository repository;

    public List<Respiracao> listAll(){
        return repository.findAll();
    }

    public void create(Respiracao respiracao) {
        repository.save(respiracao);
    }

    public Optional<Respiracao> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    
}

