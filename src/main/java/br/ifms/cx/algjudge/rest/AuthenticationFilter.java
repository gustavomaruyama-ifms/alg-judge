/*
 * Codigo Baseado de: http://howtodoinjava.com/jersey/jersey-rest-security/ 
 */
package br.ifms.cx.algjudge.rest;

import br.ifms.cx.algjudge.dao.UsuarioDAO;
import br.ifms.cx.algjudge.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.DenyAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This filter verify the access permissions for a user based on username and
 * passowrd provided in request
 *
 */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Autowired
    private UsuarioDAO usuarioDAO;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_BASIC_SCHEME = "Basic";
    private static final String AUTHENTICATION_JWT_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method metodo = resourceInfo.getResourceMethod();
        Class classe = metodo.getDeclaringClass();

        // Negar acesso caso o metodo estiver anotado com @DenyAll (negar todos)
        if (metodo.isAnnotationPresent(DenyAll.class)) {
            negarAcesso(requestContext);
            return;
        }

        // Negar acesso caso a classe estiver anotado com @DenyAll (negar todos)
        if (classe.isAnnotationPresent(DenyAll.class)) {
            negarAcesso(requestContext);
            return;
        }

        //Get request headers
        MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            negarAcesso(requestContext);
            return;
        }

        if (authorization.get(0).contains(AUTHENTICATION_BASIC_SCHEME)) {
            autenticacaoBasica(requestContext, classe, metodo);
        } else if (authorization.get(0).contains(AUTHENTICATION_JWT_SCHEME)) {
            autenticacaoJWT(requestContext, classe, metodo);
        } else {
            negarAcesso(requestContext);
            return;
        }
    }

    private void negarAcesso(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Você não tem permissão para acessar este serviço.").build());
    }

    private void autenticacaoBasica(ContainerRequestContext requestContext, Class classe, Method metodo) {
        //Get request headers
        MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            negarAcesso(requestContext);
            return;
        }

        String encodedEmailSenha = authorization.get(0).replaceFirst(AUTHENTICATION_BASIC_SCHEME + " ", "");

        //Decode username and password
        String emailESenha = new String(Base64.decode(encodedEmailSenha.getBytes()));

        //Split username and password tokens
        final StringTokenizer tokenizer = new StringTokenizer(emailESenha, ":");
        final String email = tokenizer.nextToken();
        final String senha = tokenizer.nextToken();

        // Busca usuario pelo email no banco de dados
        Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);

        // Se o usuario veio null
        if (usuario == null) {
            negarAcesso(requestContext);
            return;
        }

        if (!senha.equals(usuario.getSenha())) {
            negarAcesso(requestContext);
            return;
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256("qawsedrftgyhjuhygtfrvfbgnhvf4651554sa64c1we51651ewc1we51");
            String token = JWT.create()
                    .withIssuer(usuario.getEmail()+" "+usuario.getPapel())
                    .sign(algorithm);

            JWTVerifier verifier = JWT.require(algorithm)
                    
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            requestContext.abortWith(Response.status(Response.Status.OK)
                .entity(token).build());
           
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void autenticacaoJWT(ContainerRequestContext requestContext, Class classe, Method metodo) {
        //Get request headers
        MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if (authorization == null || authorization.isEmpty()) {
            negarAcesso(requestContext);
            return;
        }

        String token = authorization.get(0).replaceFirst(AUTHENTICATION_JWT_SCHEME + " ", "");
        try {
            Algorithm algorithm = Algorithm.HMAC256("qawsedrftgyhjuhygtfrvfbgnhvf4651554sa64c1we51651ewc1we51");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
           
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isUserAllowed(final String email, final String password, final Set<String> rolesSet) {
        // Busca usuario pelo email no banco de dados
        Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);

        // Se o usuario veio null
        if (usuario == null) {
            return false;
        }

        if (!usuario.getSenha().equals(password)) {
            return false;
        }

        if (!rolesSet.contains(usuario.getPapel())) {
            return false;
        }
        return true;
    }
}
