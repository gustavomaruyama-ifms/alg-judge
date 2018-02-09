/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.exception;

/**
 *
 * @author Gustavo
 */
public class EmailJaCadastradoException extends Exception{

    @Override
    public String getMessage() {
        return "E-mail já cadastrado."; //To change body of generated methods, choose Tools | Templates.
    }
    
}
