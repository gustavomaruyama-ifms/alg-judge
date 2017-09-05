/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

/**
 *
 * @author Gustavo
 */
public class Main {
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        Usuario u = new Usuario();
        u.setPapel(Usuario.PAPEL_PROFESSOR);
        u.setNome("Gustavo");
        for(String s: Usuario.getPapeis()){
            System.out.println(s);
        }
    }
}
