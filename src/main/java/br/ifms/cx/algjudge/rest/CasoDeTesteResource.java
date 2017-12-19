
package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.CasoDeTesteDAO;
import br.ifms.cx.algjudge.domain.CasoDeTeste;
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

@Path("/casodeteste")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
@Component
public class CasoDeTesteResource {
    
    @Autowired
    private CasoDeTesteDAO casoDao;
    
    public CasoDeTesteResource () {
        
    }
    
    @POST
    @Transactional
    public Response inserirCasoDeTeste(CasoDeTeste caso) {
        
        try {
            casoDao.inserirCasoDeTeste(caso);
            return Response.Ok("Caso De Teste inserido com sucesso");
        } catch (Exception ex) {
            System.out.println("Falhou");
            return Response.Error(ex.getMessage());
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
            return Response.Ok("Casos De Teste Inseridos com Sucesso");
        } catch (Exception ex) {
            System.out.println("Falhou");
            return Response.Error(ex.getMessage());
        }
        
    }
    
    @GET
    @Path("listarcasos/{id}")
    @Transactional
    public List<CasoDeTeste> listarCasos (@PathParam("id") Long id) {
       return casoDao.listarCasoDeTeste(id);
    }
    
}
