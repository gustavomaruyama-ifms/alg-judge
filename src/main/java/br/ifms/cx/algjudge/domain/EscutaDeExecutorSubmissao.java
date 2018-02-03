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
    private SubmissaoDAO dao;
    private CasoDeTeste casoDeTeste;
    private Submissao submissao;
    
    public EscutaDeExecutorSubmissao(CasoDeTeste casoDeTeste, SubmissaoDAO dao, TerminalCasoDeTeste terminal, Submissao submissao) {
        this.casoDeTeste = casoDeTeste;
        this.terminal = terminal;
        this.dao = dao;
        this.submissao = submissao;
    }
    
    @Override
    public void aoEncerrarExecucao(ErroEmTempoDeExecucao eetde) {
        saida = terminal.getSaida();
        if (!saida.equals(casoDeTeste.getSaida())) {
            submissao.setSituacao(SituacaoSubmissaoEnum.RESPOSTA_ERRADA);
            dao.saveOrUpdate(submissao);
        }
    }
}
