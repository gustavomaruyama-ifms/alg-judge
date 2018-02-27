/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.domain.ApplicationResponse;
import br.ifms.cx.algjudge.domain.DashboardDTO;
import br.ifms.cx.algjudge.domain.Usuario;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gustavo
 */
@Path("/dashboard")
@RolesAllowed({Usuario.PAPEL_ALUNO, Usuario.PAPEL_PROFESSOR, Usuario.PAPEL_ADMINISTRADOR})
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class DashboardResource {

    
    
    public DashboardResource() {

    }

    @GET
    public Response getDashboard(@HeaderParam("idUsuario") String id) {
        DashboardDTO dashboard = new DashboardDTO();
        return ApplicationResponse.ok(dashboard);
    }
}
