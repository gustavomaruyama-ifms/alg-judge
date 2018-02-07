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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Gustavo
 */
@Entity
public class Usuario {

    public static final String PAPEL_ADMINISTRADOR = "Administrador";
    public static final String PAPEL_ALUNO = "Aluno";
    public static final String PAPEL_PROFESSOR = "Professor";

    @Id
    @SequenceGenerator(sequenceName = "seq_usuario", name = "gen_seq_usuario", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gen_seq_usuario", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    @Column(unique = true)
    @NotBlank
    private String email;
    @Length(min = 6)
    private String senha;
    @Column(nullable = false)
    private String papel;

    public Usuario() {

    }

    /**
     * Metodo que retorna uma {@link List} com todos os papeis de um
     * {@link Usuario} em formato {@link String}
     *
     * @return {@link List<Usuario>}
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static List<String> getPapeis() throws IllegalArgumentException, IllegalAccessException {
        List<String> papeis = new ArrayList<String>();
        for (Field f : Usuario.class.getFields()) {
            if (f.getName().contains("PAPEL")) {
                papeis.add(f.get(null).toString());
            }
        }
        return papeis;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
