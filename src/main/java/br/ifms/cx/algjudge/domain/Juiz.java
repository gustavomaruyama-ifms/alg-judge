/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.domain;

import br.com.vinyanalista.portugol.base.lexer.LexerException;
import br.com.vinyanalista.portugol.base.parser.ParserException;
import br.com.vinyanalista.portugol.interpretador.Interpretador;
import br.com.vinyanalista.portugol.interpretador.analise.ErroSemantico;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class Juiz {

    public void jugar(Submissao submissao, List<CasoDeTeste> casosDeTeste) {
        try {
            Interpretador interpretador;
            TerminalCasoDeTeste terminalCasoDeTeste;

            for (CasoDeTeste caso : casosDeTeste) {
                terminalCasoDeTeste = new TerminalCasoDeTeste(caso);
                interpretador = new Interpretador(terminalCasoDeTeste);
                interpretador.adicionarEscutaDeExecutor(new EscutaDeExecutorSubmissao(caso, terminalCasoDeTeste));
                interpretador.analisar(submissao.getCodigoFonte());
                interpretador.executar();
            }

            // Loop infinito que aguarda os casos de testes serem executados
            while (casosDeTesteCompletados(casosDeTeste).equals(Boolean.FALSE));

            for (CasoDeTeste casoDeTeste : casosDeTeste) {
                if (casoDeTeste.getDeferido().equals(Boolean.FALSE)) {
                    submissao.setSituacao(Submissao.SITUACAO_RESPOSTA_ERRADA);
                    return;
                }
            }

            submissao.setSituacao(Submissao.SITUACAO_ACEITO);
        } catch (ErroSemantico | LexerException | ParserException ex) {
            submissao.setSituacao(Submissao.SITUACAO_ERRO_DE_SINTAX);
        } catch (IOException ex) {
            submissao.setSituacao(Submissao.SITUACAO_ERRO_DESCONHECIDO);
        }
    }

    private Boolean casosDeTesteCompletados(List<CasoDeTeste> casosDeTeste) {
        for (CasoDeTeste caso : casosDeTeste) {
            if (!caso.getExecutado()) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
