package br.com.fiap.merakiapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_mrk_utilizacao_dados")
public class DadosVoz {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="ds_estado_usuario",length = 30,nullable=false)
    private String estado;

    @Column(name="ds_voz_usuario",length = 50,nullable=false)
    private String voz;

    public DadosVoz() {
    }

    public DadosVoz(String estado, String voz) {
        this.estado = estado;
        this.voz = voz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVoz() {
        return voz;
    }

    public void setVoz(String voz) {
        this.voz = voz;
    }

    
    
}
