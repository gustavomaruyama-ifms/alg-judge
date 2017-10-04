/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.teste;

import br.com.vinyanalista.portugol.interpretador.Interpretador;
import br.com.vinyanalista.portugol.interpretador.Terminal;
import br.com.vinyanalista.portugol.interpretador.TerminalDeTexto;
import br.ifms.cx.algjudge.domain.Submissao;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Copilador {
    static String cop =  "";

    private Interpretador interpretador;
    private static Copilador copilador;
    private static String erro;
    private static String informacao;
    private static String saida;
    private static String saidas[] = new String[12];
    private static String codigoFonte;
    private static Integer sequenciaEntrada = 0;
    private static Integer sequencia = 0;
    private static List<String> entradas;

    public Copilador(String codFonte, List ent) {
        this.codigoFonte = codFonte;
        this.entradas = new ArrayList<>();
        this.entradas = ent;
        this.interpretador(codFonte,ent);
    }
    
    public void interpretador (final String codigoFonte, final List<String> entrada) {  
        
        interpretador = new Interpretador(new Terminal() {
            
            @Override
            public void erro(String string) {
             
            }

            @Override
            protected void escrever(String string) {
               System.out.println("escrever "+string);
               cop = string;
               Copilador.setSaida(string);
               saidas[sequencia++] = string;
            }

            @Override
            public void informacao(String string) {
                System.out.println(string);
                Copilador.setSaida(string);
            }

            @Override
            protected String ler() {
              return entrada.get(sequenciaEntrada++);
            }

            @Override
            public void limpar() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected void mudarCor(Color color) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        try {
            interpretador.analisar(codigoFonte);
            interpretador.executar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setSaida(String aSaida) {
        saida = aSaida;
    }
    
    public static String getSaida() {
        return saida;
    }
    
    public static void setSaidas(String[] aSaidas) {
        saidas = aSaidas;
    }
    
    public static String[] getSaidas() {
        return saidas;
    }
    
    public static void setCodigoFonte(String aCodigoFonte) {
        codigoFonte = aCodigoFonte;
    }

    public static void setEntradas(List<String> aEntradas) {
        entradas = aEntradas;
    }

}
