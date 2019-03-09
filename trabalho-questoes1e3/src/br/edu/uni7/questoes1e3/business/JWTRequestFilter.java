package br.edu.uni7.questoes1e3.business;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwt.consumer.InvalidJwtException;

@Provider
@JWTRequired
@Priority(Priorities.AUTHENTICATION)
public class JWTRequestFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			if (token == null || !token.startsWith(JWTUtil.BEARER)) {
				throw new InvalidJwtException(null, null, null);
			}
			String funcionario = JWTUtil.validar(token.substring(JWTUtil.BEARER.length()));
			SecurityContext securityContext = requestContext.getSecurityContext();
			requestContext.setSecurityContext(new JWTSecurityContext(securityContext, funcionario));
		} catch (InvalidJwtException e) {
			requestContext
					.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Token de acesso inválido").build());
		}
	}
}
