/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Classe de modelo de dados da entidade Problema
 *
 * @author Gustavo
 */
@Entity
public class Problema implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_problema", name = "gen_seq_problema", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_problema", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    @Column(length = 50000)
    private String descricao;
    @Column(length = 5000)
    private String descricaoEntrada;
    @Column(length = 5000)
    private String descricaoSaida;
    @Column(nullable = false)
    private Boolean ativo;

    public Problema() {
        this.ativo = Boolean.TRUE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoEntrada() {
        return descricaoEntrada;
    }

    public void setDescricaoEntrada(String descricaoEntrada) {
        this.descricaoEntrada = descricaoEntrada;
    }

    public String getDescricaoSaida() {
        return descricaoSaida;
    }

    public void setDescricaoSaida(String descricaoSaida) {
        this.descricaoSaida = descricaoSaida;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
