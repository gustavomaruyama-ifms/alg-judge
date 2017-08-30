/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.domain.Response;
import br.ifms.cx.algjudge.domain.Submissao;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gustavo
 */
@Path("/submissao")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class SubmissaoResource {

    public SubmissaoResource() {

    }

    @POST
    public Response submeter(Submissao submissao) {
        try {
            //InterpretadorObject i = new InterpretadorObject(submissao);
            //i.execute();
            //dao.persistirSubmissao(submissao);            
            return Response.Ok("Submissao incluida com sucesso");
        } catch (Exception ex) {
            return Response.Error(ex.getMessage());
        }
    }
}
