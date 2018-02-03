/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
    
    public static final String PAPEL_ALUNO =  "ALUNO";
    public static final String PAPEL_PROFESSOR = "PROFESSOR";
    public static final String PAPEL_ADMINISTRADOR = "ADMINISTRADOR";
    
    @Id
    @SequenceGenerator(sequenceName = "seq_user", name = "gen_seq_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_user", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    @Column(nullable = true)
    private Boolean ativo;
    private String papel;

    public Usuario() {
        this.ativo = false;
    }
    
    public void setAtivo(Boolean situacao) {
        this.ativo = situacao;
    }
    
    public static List<String> getPapeis() throws IllegalArgumentException, IllegalAccessException {
        List<String> situacoes = new ArrayList<String>();
        for (Field f : Usuario.class.getFields()) {
            if (f.getName().contains("PAPEL")) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    
    public void setPapel(String papel) {
       this.papel = papel;
    };
    
    public String getPapel() {
       return this.papel;
    };

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
