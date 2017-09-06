/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.UsuarioDAO;
import br.ifms.cx.algjudge.domain.Response;
import br.ifms.cx.algjudge.domain.Usuario;
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

/**
 *
 * @author Rodrigo
 */
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class UsuarioResource {
    
    @Autowired
    private UsuarioDAO udao;
    
    public UsuarioResource () {
    }
    
    @GET
    @Path("/{id}")
    public Usuario getUsuario  (@PathParam("id") Integer id) {
        return udao.get(id);
    }
    
    @GET
    @Path("/list/{papel}/{pag}/{qtd}")
    public List <Usuario> getUsuario  
        (
            @PathParam("papel") String papel , 
            @PathParam("pag") Integer pag, 
            @PathParam("qtd") Integer id
        ) 
    {
        return udao.listar(papel);  
    }
    
    @POST
    @Path("/admin")
    @Transactional
    public Response inserirAdmin (Usuario u) {
        try {
        udao.inserirAdmin(u);
          return Response.Ok("Usuatio Incerido com sucesso");
        } catch (Exception e) {
          System.out.println("Falhou");
            return Response.Error(e.getMessage()); 
        }
    }
   
    @POST
    @Path("/professor")
    @Transactional
    public Response inserirProfessor (Usuario u) {
        try {
        udao.inserirProfessor(u);
          return Response.Ok("Usuatio Incerido com sucesso");
        } catch (Exception e) {
          System.out.println("Falhou");
            return Response.Error(e.getMessage()); 
        }
    }
    
    @POST
    @Path("/aluno")
    @Transactional
    public Response inserirAluno (Usuario u) {
        try {
        udao.inserirAluno(u);
          return Response.Ok("Usuatio Incerido com sucesso");
        } catch (Exception e) {
          System.out.println("Falhou");
            return Response.Error(e.getMessage()); 
        }
    }
}
