package br.com.fiap.merakiapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.merakiapi.model.Respiracao;
import br.com.fiap.merakiapi.service.RespiracaoService;

@RestController
@RequestMapping("/api/respiracao")
public class RespiracaoController {

    @Autowired
    private RespiracaoService service;

    @GetMapping
    @Cacheable("respiracao")
    public List<Respiracao> index(){
        return service.listAll();
    }

    @PostMapping
    @CacheEvict(value="respiracao", allEntries = true)
    public ResponseEntity<Respiracao> create(@RequestBody @Valid Respiracao respiracao){
        service.create(respiracao);
        return ResponseEntity.status(HttpStatus.CREATED).body(respiracao);
    }

    @GetMapping("{id}")
    @Cacheable("respiracao")
    public ResponseEntity<Respiracao> getById(@PathVariable Long id){
        return ResponseEntity.of(service.getById(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value="respiracao", allEntries = true)
    public ResponseEntity<Respiracao> deletar(@PathVariable Long id){

        Optional<Respiracao> optional = service.getById(id);
        
        if(optional.isEmpty()){ 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    @CacheEvict(value="respiracao", allEntries = true) 
    public ResponseEntity<Respiracao> update(@PathVariable Long id, @RequestBody @Valid Respiracao newRespiracao){

        Optional<Respiracao> optional = service.getById(id);
        
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Respiracao respiracao = optional.get();
        BeanUtils.copyProperties(newRespiracao, respiracao);
        respiracao.setId(id);

        service.create(respiracao);
        return ResponseEntity.ok(respiracao);

    }


    
}
