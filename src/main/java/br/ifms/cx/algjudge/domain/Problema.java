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
 * Classe de modelo de dados da entidade Problema
 * @author Gustavo
 */
@Entity
public class Problema {
    @Id
    @SequenceGenerator(sequenceName = "seq_problema", name = "gen_seq_problema", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_problema", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String descricao;
    private String descricaoEntrada;
    private String descricaoSaida;

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
    
}