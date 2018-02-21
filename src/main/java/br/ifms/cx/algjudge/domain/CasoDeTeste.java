/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

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
    private Boolean ativo;
    private String saida;
    @ManyToOne(fetch = FetchType.LAZY)
    private Problema problema;
    @Transient
    private Boolean executado;
    @Transient
    private String tempoDeExecucao;
    @Transient
    private Boolean deferido;

    public CasoDeTeste() {
        this.ativo = Boolean.TRUE;
        this.executado = Boolean.FALSE;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
  
    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    public Boolean getExecutado() {
        return executado;
    }

    public void setExecutado(Boolean executado) {
        this.executado = executado;
    }

    public String getTempoDeExecucao() {
        return tempoDeExecucao;
    }

    public void setTempoDeExecucao(String tempoDeExecucao) {
        this.tempoDeExecucao = tempoDeExecucao;
    }

    public Boolean getDeferido() {
        return deferido;
    }

    public void setDeferido(Boolean deferido) {
        this.deferido = deferido;
    }

}
