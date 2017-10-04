/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gustavo
 */
@Entity
public class Submissao {

    @Id
    @SequenceGenerator(sequenceName = "seq_submissao", name = "gen_seq_submissao", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_submissao", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50000)
    private String codigoFonte;
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;
    private Boolean delete = false;
    private Long tempoExecucao;
    @Enumerated(EnumType.STRING)
    private SituacaoSubmissaoEnum situacao;
    @ManyToOne( fetch = FetchType.LAZY)
    private Problema problema;

    public Submissao() {
        this.delete = false;
    }
    
    
    public SituacaoSubmissaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoSubmissaoEnum situacao) {
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoFonte() {
        return codigoFonte;
    }

    public void setCodigoFonte(String codigoFonte) {
        this.codigoFonte = codigoFonte;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Long getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(Long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }
    
    
}
