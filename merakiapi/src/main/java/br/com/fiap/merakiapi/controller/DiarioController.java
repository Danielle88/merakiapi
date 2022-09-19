package br.com.fiap.merakiapi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.fiap.merakiapi.model.Diario;
import br.com.fiap.merakiapi.service.DiarioService;

@RestController
@RequestMapping("/api/diario")
public class DiarioController {
    
    @Autowired
    private DiarioService service;
	
    @GetMapping
    @Cacheable("diario")
    public Page<Diario> index(@PageableDefault Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    @CacheEvict(value="diario", allEntries = true)
    public ResponseEntity<Diario> create(@RequestBody @Valid Diario diario){
        service.create(diario);
        return ResponseEntity.status(HttpStatus.CREATED).body(diario);
    }

    @GetMapping("{id}")
    @Cacheable("diario")
    public ResponseEntity<Diario> getById(@PathVariable Long id){
        return ResponseEntity.of(service.getById(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value="diario", allEntries = true)
    public ResponseEntity<Diario> deletar(@PathVariable Long id){

        Optional<Diario> optional = service.getById(id);
        
        if(optional.isEmpty()){ 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    @CacheEvict(value="diario", allEntries = true) 
    public ResponseEntity<Diario> update(@PathVariable Long id, @RequestBody @Valid Diario newDiario){

        Optional<Diario> optional = service.getById(id);
        
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Diario diario = optional.get();
        BeanUtils.copyProperties(newDiario, diario);
        diario.setId(id);

        service.create(diario);
        return ResponseEntity.ok(diario);

    }

    
}
