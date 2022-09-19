package br.com.fiap.merakiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.merakiapi.model.Respiracao;

public interface RespiracaoRepository extends JpaRepository<Respiracao, Long> {
    
}
