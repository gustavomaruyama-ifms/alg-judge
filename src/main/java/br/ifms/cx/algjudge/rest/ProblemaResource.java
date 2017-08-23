package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.ProblemaDAO;
import br.ifms.cx.algjudge.domain.Problema;
import br.ifms.cx.algjudge.domain.Response;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Path("/problema")
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
            return Response.Ok("Problema incluio com sucesso");
        } catch (Exception ex) {
            return Response.Error(ex.getMessage());
        }
    }

    @GET
    @Path("/list/{qtde}")
    public List<Problema> listarProblemas(@PathParam("qtde") Integer qtde) {
        return db.listarProblemas(qtde);
    }
}