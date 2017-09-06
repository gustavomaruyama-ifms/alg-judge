/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



/**
 *
 * @author Rodrigo
 */
@Entity 
public class Usuario {
    @Id
    @SequenceGenerator(sequenceName = "seq_user", name = "gen_seq_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_user", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    @Column(nullable = false)
    private Boolean delete;
    @Enumerated (EnumType.STRING)
    private PapelDoUsuarioEnum papel;

    public Usuario() {
        this.delete = false;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PapelDoUsuarioEnum getPapel() {
        return papel;
    }

    public void setPapel(PapelDoUsuarioEnum papel) {
        this.papel = papel;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
    
    
}
