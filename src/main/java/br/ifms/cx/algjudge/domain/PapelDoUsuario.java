/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

/**
 *
 * @author Rodrigo
 */
public enum PapelDoUsuario {
    
    ALUNO ("ALUNO"),
    PROFESSOR("PROFESSOR"),
    ADMINISTRADOR("ADMINISTRADOR");
    
    private String papel;
    
    private PapelDoUsuario (String papel) {
        this.papel = papel;
    }
}
