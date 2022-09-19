package br.com.fiap.merakiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.merakiapi.model.Diario;

public interface DiarioRepository extends JpaRepository<Diario, Long>{
    
}
