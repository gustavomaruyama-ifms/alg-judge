/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import br.com.vinyanalista.portugol.interpretador.Terminal;
import java.awt.Color;

/**
 *
 * @author Rodrigo
 */
public class TerminalCasoDeTeste extends Terminal {

    private CasoDeTeste casosDeTeste;
    private String entradas[];
    private StringBuilder saida;
    private int i;

    public TerminalCasoDeTeste(CasoDeTeste casosDeTeste) {
        this.casosDeTeste = casosDeTeste;
        this.entradas = casosDeTeste.getEntrada().split("\n");
        this.saida = new StringBuilder();
        this.i = 0;
    }

    public String getSaida() {
        return saida.toString();
    }

    @Override
    public void erro(String string) {
        System.out.println("Erro " + string);
    }

    @Override
    protected void escrever(String string) {
        saida.append(string);
    }

    @Override
    public void informacao(String string) {
        System.out.println(string);
    }

    @Override
    protected String ler() {
        return entradas[i++];
    }

    @Override
    public void limpar() {
        System.out.println("Limpar");

    }

    @Override
    protected void mudarCor(Color color) {
        System.out.println("Mudar Cor");
    }
}
