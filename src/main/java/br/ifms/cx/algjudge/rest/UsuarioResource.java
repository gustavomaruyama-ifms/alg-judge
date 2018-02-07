package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.UsuarioDAO;
import br.ifms.cx.algjudge.domain.Response;
import br.ifms.cx.algjudge.domain.Usuario;
import br.ifms.cx.algjudge.exception.SenhaInvalidaException;
import br.ifms.cx.algjudge.exception.UsuarioInexistenteException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.HeaderParam;
import org.glassfish.jersey.internal.util.Base64;
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
    private UsuarioDAO db;

    public UsuarioResource() {

    }

    @POST
    @Path("/signup")
    @Transactional
    public Response signup(Usuario usuario) {
        try {
            if (db.buscarUsuarioPorEmail(usuario.getEmail()) != null) {
                return Response.Error("Usuário já existe");
            }

            if (null == usuario.getSenha() || usuario.getSenha().length() < 6) {
                return Response.Error("Senha inválida");
            }
            usuario.setPapel(Usuario.PAPEL_ALUNO);
            db.save(usuario);
            return Response.Ok("Usuario incluido com sucesso");
        } catch (Exception ex) {
            return Response.Error(ex.getMessage());
        }
    }

    @GET
    @Path("/login")
    public Response login(@HeaderParam("Authorization") String authorization) {
        String encodedEmailSenha = authorization.replaceFirst("Basic ", "");
        String emailESenha = new String(Base64.decode(encodedEmailSenha.getBytes()));

        //Split username and password tokens
        String splited[] = emailESenha.split(":");
        try {
            
            if (splited.length < 2) {
                throw new UsuarioInexistenteException();
            }

            String email = splited[0];
            String senha = splited[1];

            // Busca usuario pelo email no banco de dados
            Usuario usuario = db.buscarUsuarioPorEmail(email);

            if (usuario == null) {
                throw new UsuarioInexistenteException();
            }

            if (!senha.equals(usuario.getSenha())) {
                throw new SenhaInvalidaException();
            }

            Algorithm algorithm = Algorithm.HMAC256("qawsedrftgyhjuhygtfrvfbgnhvf4651554sa64c1we51651ewc1we51");
            String token = JWT.create()
                    .withClaim("email", usuario.getEmail())
                    .withClaim("papel", usuario.getPapel())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);
            return Response.Ok(token);
        } catch (UsuarioInexistenteException ex) {
            return Response.Error("Usuário inválido");
        } catch (SenhaInvalidaException ex) {
            return Response.Error("Senha inválida");
        } catch (IllegalArgumentException | UnsupportedEncodingException ex) {
            return Response.Error("");
        }
    }
    
    @GET
    @Path("/{id}")
    public Usuario getUsuario  (@PathParam("id") Integer id) {
        return db.get(id);
    }

    @GET
    @Path("/list/{papel}/{pag}/{qtd}")
    public List <Usuario> getUsuarios  
        (
            @PathParam("papel") String papel , 
            @PathParam("pag") Integer pag, 
            @PathParam("qtd") Integer id
        ) 
    {
        return db.listar(papel);  
    }
    
    @POST
    @Path("/admin")
    @Transactional
    public Response inserirAdmin (Usuario u) {
        try {
        db.inserirAdmin(u);
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
        db.inserirProfessor(u);
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
        db.inserirAluno(u);
          return Response.Ok("Usuatio Incerido com sucesso");
        } catch (Exception e) {
          System.out.println("Falhou");
          return Response.Error(e.getMessage()); 
        }
    }
}
