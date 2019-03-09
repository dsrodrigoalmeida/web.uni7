package br.edu.uni7.questoes1e3.business;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.jose4j.lang.JoseException;

@Provider
@JWTRequired
@Priority(Priorities.AUTHENTICATION)
public class JWTResponseFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Principal funcionario = requestContext.getSecurityContext().getUserPrincipal();
		if (funcionario != null) {
			try {
				String jwt = JWTUtil.BEARER + JWTUtil.criar(funcionario.getName());
				responseContext.getHeaders().put(HttpHeaders.AUTHORIZATION, Arrays.asList(jwt));
			} catch (JoseException e) {
				Logger.getGlobal().log(Level.SEVERE, "Erro ao gerar o token", e);
			}
		}
	}
}
