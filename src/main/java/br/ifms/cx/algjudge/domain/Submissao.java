/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
    private String codigoFonte;
    private Date dataEnvio;
    private Long tempoExecucao;
    @Enumerated(EnumType.STRING)
    private SituacaoSubmissaoEnum situacao;

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
}
