package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.ProblemaDAO;
import br.ifms.cx.algjudge.domain.Problema;
import br.ifms.cx.algjudge.domain.ApplicationResponse;
import br.ifms.cx.algjudge.domain.Usuario;
import javax.annotation.security.RolesAllowed;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Path("/problema")
@RolesAllowed({Usuario.PAPEL_ADMINISTRADOR, Usuario.PAPEL_PROFESSOR})
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class ProblemaResource {

    @Autowired
    private ProblemaDAO db;

    public ProblemaResource() {

    }

    @POST
    @Transactional
    public Response inserirProblema(Problema problema) {
        try {
            db.persistirProblema(problema);
            return ApplicationResponse.ok("Problema incluido com sucesso");
        } catch (Exception ex) {
            return ApplicationResponse.erroInterno(ex.getMessage());
        }
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed({Usuario.PAPEL_ALUNO, Usuario.PAPEL_ADMINISTRADOR, Usuario.PAPEL_PROFESSOR})
    public Response getProblema(@PathParam("id") Long id) {
        Problema p = db.buscarProblemaPorId(id);
        if(p == null){
            return null;
        }
        p.setExemplos(db.buscarExemplosDeCasosDeTestePorIdProblema(id));
        return ApplicationResponse.ok(p);
    }

    @GET
    @Path("/list/{page}")
    @RolesAllowed({Usuario.PAPEL_ALUNO, Usuario.PAPEL_ADMINISTRADOR, Usuario.PAPEL_PROFESSOR})
    public Response listarProblemas(@PathParam("page") Integer page) {
        return ApplicationResponse.ok(db.listarProblemas(page));
    }
    
    @GET
    @Path("/list")
    @RolesAllowed({Usuario.PAPEL_ALUNO, Usuario.PAPEL_ADMINISTRADOR, Usuario.PAPEL_PROFESSOR})
    public Response listarTodosProblemas(@PathParam("page") Integer page) {
        return ApplicationResponse.ok(db.listarTodosProblemas());
    }
}
