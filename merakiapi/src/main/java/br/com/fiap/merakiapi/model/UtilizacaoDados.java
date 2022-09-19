package br.com.fiap.merakiapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_mrk_utilizacao_dados")
public class UtilizacaoDados {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="ds_aceite")
    private Boolean aceite;

    
    public UtilizacaoDados() {
    }

    public UtilizacaoDados(Boolean aceite) {
        this.aceite = aceite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAceite() {
        return aceite;
    }

    public void setAceite(Boolean aceite) {
        this.aceite = aceite;
    }

    
    
}
