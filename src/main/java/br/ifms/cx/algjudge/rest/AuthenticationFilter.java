/*
 * Codigo Baseado de: http://howtodoinjava.com/jersey/jersey-rest-security/ 
 */
package br.ifms.cx.algjudge.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * This filter verify the access permissions for a user based on username and
 * passowrd provided in request
 *
 */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
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
        String path = requestContext.getUriInfo().getPath();
        if (path.equals("usuario/login") || path.equals("usuario/signup")) {
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

        if (authorization.get(0).contains(AUTHENTICATION_JWT_SCHEME)) {
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
            String papel = jwt.getClaim("papel").asString();
            Set<String> rolesSet;
            RolesAllowed rolesAnnotation;
            
            if (metodo.isAnnotationPresent(RolesAllowed.class)) {
                rolesAnnotation = metodo.getAnnotation(RolesAllowed.class);
                rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
                verificarPapel(papel, rolesSet, requestContext);
            } else if (classe.isAnnotationPresent(RolesAllowed.class)) {
                rolesAnnotation = (RolesAllowed) classe.getAnnotation(RolesAllowed.class);
                rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
                verificarPapel(papel, rolesSet, requestContext);
            }

        } catch (IllegalArgumentException | UnsupportedEncodingException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JWTVerificationException ex) {
            negarAcesso(requestContext);
        }
    }

    private void verificarPapel(String papel, Set<String> rolesSet, ContainerRequestContext requestContext) {
        if (!rolesSet.contains(papel)) {
            negarAcesso(requestContext);
        }
    }
}
