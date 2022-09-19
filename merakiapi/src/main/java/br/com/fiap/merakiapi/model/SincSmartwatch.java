package br.com.fiap.merakiapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_mrk_sinc_smartwatch")
public class SincSmartwatch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ds_estresse",length = 100,nullable=false)
    private int estresse;

    @Column(name="ds_horas_sono",length = 6,nullable=false)
    private int horasSono;

    @Column(name="ds_batimento",length = 3,nullable=false)
    private int batimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstresse() {
        return estresse;
    }

    public void setEstresse(int estresse) {
        this.estresse = estresse;
    }

    public int getHorasSono() {
        return horasSono;
    }

    public void setHorasSono(int horasSono) {
        this.horasSono = horasSono;
    }

    public int getBatimento() {
        return batimento;
    }

    public void setBatimento(int batimento) {
        this.batimento = batimento;
    }

    
    
}
