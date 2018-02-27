package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.CasoDeTesteDAO;
import br.ifms.cx.algjudge.domain.CasoDeTeste;
import br.ifms.cx.algjudge.domain.ApplicationResponse;
import br.ifms.cx.algjudge.domain.Usuario;
import java.util.List;
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

@Path("/casodeteste")
@RolesAllowed({Usuario.PAPEL_ADMINISTRADOR, Usuario.PAPEL_PROFESSOR})
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class CasoDeTesteResource {
    
    @Autowired
    private CasoDeTesteDAO casoDao;
    
    public CasoDeTesteResource() {
        
    }
    
    @POST
    @Transactional
    public Response inserirCasoDeTeste(CasoDeTeste caso) {
        try {
            casoDao.inserirCasoDeTeste(caso);
            return ApplicationResponse.ok("Caso De Teste inserido com sucesso id=" + caso.getId());
        } catch (Exception ex) {
            return ApplicationResponse.erroInterno(ex.getMessage());
        }
    }
    
    @POST
    @Path("inserircasos")
    @Transactional
    public Response inserirListaCasoDeTeste(List<CasoDeTeste> casos) {
        try {
            for (CasoDeTeste caso : casos) {
                casoDao.inserirCasoDeTeste(caso);
            }
            return ApplicationResponse.ok("Casos De Teste Inseridos com Sucesso");
        } catch (Exception ex) {
            return ApplicationResponse.erroInterno(ex.getMessage());
        }
        
    }
    
    @PUT
    @Transactional
    public Response updateCasoDeTeste(CasoDeTeste caso) {
        try {
            casoDao.updateCasoDeTeste(caso);
            return ApplicationResponse.ok("Casos De Teste Atualizado com Sucesso");
        } catch (Exception ex) {
            return ApplicationResponse.erroInterno(ex.getMessage());
        }
    }
    
    @PUT
    @Path("deletecasos")
    @Transactional
    public Response deleteCasoDeTeste(CasoDeTeste caso) {
        try {
            casoDao.deleteCasoDeTeste(caso);
            return ApplicationResponse.ok("Casos De Teste Deletado com Sucesso");
        } catch (Exception ex) {
            return ApplicationResponse.erroInterno(ex.getMessage());
        }
    }
    
    @GET
    @Path("listarcasos/{id}")
    @Transactional
    public Response listarCasos(@PathParam("id") Long id) {
        List<CasoDeTeste> casosDeTestes = casoDao.listarCasoDeTeste(id);
        return ApplicationResponse.ok(casosDeTestes);
    }
}
