/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import br.com.vinyanalista.portugol.interpretador.execucao.ErroEmTempoDeExecucao;
import br.com.vinyanalista.portugol.interpretador.execucao.EscutaDeExecutor;
import br.ifms.cx.algjudge.dao.SubmissaoDAO;

/**
 *
 * @author Gustavo
 */
public class EscutaDeExecutorSubmissao implements EscutaDeExecutor {
    private TerminalCasoDeTeste terminal;
    private String saida;
    private CasoDeTeste casoDeTeste;
    
    public EscutaDeExecutorSubmissao(CasoDeTeste casoDeTeste, TerminalCasoDeTeste terminal) {
        this.casoDeTeste = casoDeTeste;
        this.terminal = terminal;
    }
    
    @Override
    public void aoEncerrarExecucao(ErroEmTempoDeExecucao eetde) {
        saida = terminal.getSaida();
        casoDeTeste.setExecutado(Boolean.TRUE);
        
        if (saida.equals(casoDeTeste.getSaida())) {
            casoDeTeste.setDeferido(Boolean.TRUE);
        }else{
            casoDeTeste.setDeferido(Boolean.FALSE);
        }
    }
}
