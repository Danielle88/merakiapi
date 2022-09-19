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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.merakiapi.model.Usuario;
import br.com.fiap.merakiapi.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    @Cacheable("usuario")
    public List<Usuario> index(){
        return service.listAll();
    }

    @PostMapping
    @CacheEvict(value="usuario", allEntries = true)
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        service.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("{id}")
    @Cacheable("usuario")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return ResponseEntity.of(service.getById(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value="usuario", allEntries = true)
    public ResponseEntity<Usuario> deletar(@PathVariable Long id){

        Optional<Usuario> optional = service.getById(id);
        
        if(optional.isEmpty()){ 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    @CacheEvict(value="usuario", allEntries = true) 
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario newUsuario){

        Optional<Usuario> optional = service.getById(id);
        
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = optional.get();
        BeanUtils.copyProperties(newUsuario, usuario);
        usuario.setId(id);

        service.create(usuario);
        return ResponseEntity.ok(usuario);

    }
    
}
