/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public static final String SITUACAO_EM_EXECUCAO = "Em Execução";
    public static final String SITUACAO_ACEITO = "Aceito";
    public static final String SITUACAO_RESPOSTA_ERRADA = "Resposta Errada";
    public static final String SITUACAO_ERRO_DE_SINTAX = "Erro de Sintax";

    @Id
    @SequenceGenerator(sequenceName = "seq_submissao", name = "gen_seq_submissao", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_submissao", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50000)
    private String codigoFonte;
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;
    private Boolean ativo = true;
    private Long tempoExecucao;
    @Enumerated(EnumType.STRING)
    private SituacaoSubmissaoEnum situacao;
    @ManyToOne( fetch = FetchType.LAZY)
    private Problema problema;

    public Submissao() {
        this.ativo = true;
    }
    
    
    public SituacaoSubmissaoEnum getSituacao() {
        return situacao;
    }
    
    public void setSituacao(SituacaoSubmissaoEnum situacao){
      this.situacao = situacao;
    };

    /**
     * Metodo que retorna uma {@link List} com todos as situações de uma
     * {@link Submissao} em formato {@link String}
     *
     * @return {@link List<Submissao>}
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<String> getSituacoes() throws IllegalArgumentException, IllegalAccessException {
        List<String> situacoes = new ArrayList<String>();
        for (Field f : Usuario.class.getFields()) {
            if (f.getName().contains("SITUACAO")) {
                situacoes.add(f.get(null).toString());
            }
        }
        return situacoes;
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
    
    
}
