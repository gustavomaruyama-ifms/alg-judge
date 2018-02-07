/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.rest;

import br.com.vinyanalista.portugol.interpretador.Interpretador;
import br.com.vinyanalista.portugol.interpretador.execucao.ErroEmTempoDeExecucao;
import br.com.vinyanalista.portugol.interpretador.execucao.EscutaDeExecutor;
import br.ifms.cx.algjudge.dao.ProblemaDAO;
import br.ifms.cx.algjudge.dao.SubmissaoDAO;
import br.ifms.cx.algjudge.domain.CasoDeTeste;
import br.ifms.cx.algjudge.domain.EscutaDeExecutorSubmissao;
import br.ifms.cx.algjudge.domain.Response;
import br.ifms.cx.algjudge.domain.Submissao;
import br.ifms.cx.algjudge.domain.Usuario;
import javax.annotation.security.RolesAllowed;
import br.ifms.cx.algjudge.domain.TerminalCasoDeTeste;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gustavo
 */
@Path("/submissao")
@RolesAllowed({Usuario.PAPEL_ALUNO,Usuario.PAPEL_PROFESSOR})
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class SubmissaoResource {

    @Autowired
    private ProblemaDAO problemaDAO;
    @Autowired
    private SubmissaoDAO submissaoDAO;

    public SubmissaoResource() {

    }

    @POST
    @Transactional
    public Response submeter(Submissao submissao) {

        try {
            submissaoDAO.salvarSubmissao(submissao);
            List<CasoDeTeste> casosDeTeste = problemaDAO.buscarCasoDeTestePorIdProblema(submissao.getProblema().getId());
            Interpretador interpretador;
            TerminalCasoDeTeste terminalCasoDeTeste;
            String saida;

            for (CasoDeTeste caso : casosDeTeste) {
                terminalCasoDeTeste = new TerminalCasoDeTeste(caso);
                interpretador = new Interpretador(terminalCasoDeTeste);
                interpretador.adicionarEscutaDeExecutor(new EscutaDeExecutorSubmissao(caso, submissaoDAO, terminalCasoDeTeste, submissao));
                interpretador.analisar(submissao.getCodigoFonte());
                interpretador.executar();
            }
            
            return Response.Ok("Submissao incluida com sucesso");
        } catch (IOException ex) {
            return Response.Error(ex.getMessage());
        } catch (Exception ex) {
            submissao.setSituacao(Submissao.SITUACAO_ERRO_DE_SINTAX);
            submissaoDAO.update(submissao);
            return Response.Error(ex.getMessage());
        }
    }
}
