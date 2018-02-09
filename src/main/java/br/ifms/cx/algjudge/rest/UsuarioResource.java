package br.ifms.cx.algjudge.rest;

import javax.ws.rs.core.Response;
import br.ifms.cx.algjudge.dao.UsuarioDAO;
import br.ifms.cx.algjudge.domain.Usuario;
import br.ifms.cx.algjudge.exception.EmailJaCadastradoException;
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
                throw new EmailJaCadastradoException();
            }

            if (null == usuario.getSenha() || usuario.getSenha().length() < 6) {
                throw new SenhaInvalidaException();
            }
            usuario.setPapel(Usuario.PAPEL_ALUNO);
            db.save(usuario);
            return Response.status(200).entity("Usuário incluido com sucesso").build();
        } catch (Exception ex) {
            return Response.status(404).entity(ex.getMessage()).build();
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

            usuario.setSenha(null);
            usuario.setToken(token);
            return Response.status(200).entity(usuario).build();
        } catch (UsuarioInexistenteException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        } catch (SenhaInvalidaException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        } catch (IllegalArgumentException | UnsupportedEncodingException ex) {
            return Response.status(404).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Usuario getUsuario(@PathParam("id") Integer id) {
        return db.get(id);
    }

    @GET
    @Path("/list/{papel}/{pag}/{qtd}")
    public List<Usuario> getUsuarios(
            @PathParam("papel") String papel,
            @PathParam("pag") Integer pag,
            @PathParam("qtd") Integer id
    ) {
        return db.listar(papel);
    }
}
