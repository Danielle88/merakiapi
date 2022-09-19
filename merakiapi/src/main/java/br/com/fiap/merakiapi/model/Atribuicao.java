package br.com.fiap.merakiapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_mrk_atribuicao")
public class Atribuicao implements GrantedAuthority{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
    

}
