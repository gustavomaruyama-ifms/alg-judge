/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

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
    @SequenceGenerator(sequenceName = "seq_submissao", name = "gen_seq_submissao", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_submissao", strategy = GenerationType.SEQUENCE)
    private Long id;
    private Boolean exemplo;
    private String entrada;
    private String saida;

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
    
    
}
