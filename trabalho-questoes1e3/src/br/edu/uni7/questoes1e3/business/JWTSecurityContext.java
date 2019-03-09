package br.edu.uni7.questoes1e3.business;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class JWTSecurityContext implements SecurityContext {

	private SecurityContext contextoOriginal;
	private Principal principal;

	public JWTSecurityContext(SecurityContext contextoOriginal, String funcionario) {

		this.contextoOriginal = contextoOriginal;
		this.principal = new Principal() {

			@Override
			public String getName() {
				return funcionario;
			}
		};
	}

	@Override
	public String getAuthenticationScheme() {
		return this.contextoOriginal.getAuthenticationScheme();
	}

	@Override
	public boolean isSecure() {
		return this.contextoOriginal.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}
}
