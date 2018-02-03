/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.teste;

import br.com.vinyanalista.portugol.interpretador.Exemplo;
import br.com.vinyanalista.portugol.interpretador.Interpretador;
import br.com.vinyanalista.portugol.interpretador.Terminal;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        List <String> entradas = new LinkedList<>();
        
        entradas.add("23");
        entradas.add("50"); 
        Copilador c = new Copilador(Exemplo.ESTRUTURA_CONDICIONAL_SIMPLES.getProgramaFonte(), entradas);
        //System.out.println(Copilador.getSaida());
        System.out.println("Saída "+Copilador.cop);
        String saida[] = Copilador.getSaidas();
        System.out.println("Saída"+saida[2]);
        
    }
    
}
