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
public class SenhaInvalidaException extends Exception{
     @Override
    public String getMessage() {
        return "Senha inválida."; //To change body of generated methods, choose Tools | Templates.
    }
}
