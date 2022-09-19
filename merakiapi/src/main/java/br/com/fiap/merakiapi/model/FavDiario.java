package br.com.fiap.merakiapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_mrk_FavDiario")
public class FavDiario {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public FavDiario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
