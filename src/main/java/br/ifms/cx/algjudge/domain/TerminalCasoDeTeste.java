/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import br.com.vinyanalista.portugol.interpretador.Terminal;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class TerminalCasoDeTeste extends Terminal {
    
    CasoDeTeste casosDeTeste;
    String entradas[];
    StringBuilder saida;
    int i;
    public TerminalCasoDeTeste(CasoDeTeste casosDeTeste) {
        this.casosDeTeste = casosDeTeste;
        this.entradas = casosDeTeste.getEntrada().split("\n");
        this.saida = new StringBuilder();
        this.i = 0;
    }
    
    public String getSaida () {
        return saida.toString();
    }
    
    @Override
    public void erro(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void escrever(String string) {
        saida.append(string+"\n");
    }

    @Override
    public void informacao(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String ler() {
        return  entradas[i++];
    }

    @Override
    public void limpar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void mudarCor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
