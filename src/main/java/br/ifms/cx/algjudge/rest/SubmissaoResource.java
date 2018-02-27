/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.ProblemaDAO;
import br.ifms.cx.algjudge.dao.SubmissaoDAO;
import br.ifms.cx.algjudge.dao.UsuarioDAO;
import br.ifms.cx.algjudge.domain.CasoDeTeste;
import br.ifms.cx.algjudge.domain.Juiz;
import br.ifms.cx.algjudge.domain.ApplicationResponse;
import br.ifms.cx.algjudge.domain.Submissao;
import br.ifms.cx.algjudge.domain.Usuario;
import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gustavo
 */
@Path("/submissao")
@RolesAllowed({Usuario.PAPEL_ALUNO, Usuario.PAPEL_PROFESSOR})
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class SubmissaoResource {

    @Autowired
    private ProblemaDAO problemaDAO;
    @Autowired
    private SubmissaoDAO submissaoDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;

    public SubmissaoResource() {

    }

    @POST
    @Transactional
    public Response submeter(Submissao submissao, @HeaderParam("email") String email) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);
        
        submissao.setUsuario(usuario);
        submissao.setDataEnvio(new Date().getTime());
        submissao.setSituacao(Submissao.SITUACAO_EM_EXECUCAO);
        submissaoDAO.salvarSubmissao(submissao);
        List<CasoDeTeste> casosDeTeste = problemaDAO.buscarCasoDeTestePorIdProblema(submissao.getProblema().getId());
  
        Juiz juiz = new Juiz();
        juiz.jugar(submissao, casosDeTeste);
        submissaoDAO.saveOrUpdate(submissao);

        return ApplicationResponse.ok(submissao.getSituacao());
    }
    
    @GET
    @Path("/list/{page}")
    public Response getSubmissoes(@HeaderParam("idUsuario") String id, @PathParam("page") Integer page){
        List<Submissao> submissoes = submissaoDAO.listarSubmissoesPorIdUsuario(new Long(id), page);
        return ApplicationResponse.ok(submissoes);
    }
    
    @GET
    @Path("/list")
    public Response getSubmissoesList(@HeaderParam("idUsuario") String id, @PathParam("page") Integer page){
        List<Submissao> submissoes = submissaoDAO.listarSubmissoesPorIdUsuario(new Long(id));
        return ApplicationResponse.ok(submissoes);
    }
}
