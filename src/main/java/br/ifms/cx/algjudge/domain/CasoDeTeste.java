/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Rodrigo
 */
@Entity
public class CasoDeTeste {
    @Id
    @SequenceGenerator(sequenceName = "seq_caso_de_teste", name = "gen_seq_caso_de_teste",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_caso_de_teste", strategy = GenerationType.SEQUENCE)
    private Long id;
    private Boolean exemplo;
    private String entrada;
    @Column(nullable = false)
    private Boolean delete;
    private String saida;

    public CasoDeTeste() {
        this.delete = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getExemplo() {
        return exemplo;
    }

    public void setExemplo(Boolean exemplo) {
        this.exemplo = exemplo;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }  
}
