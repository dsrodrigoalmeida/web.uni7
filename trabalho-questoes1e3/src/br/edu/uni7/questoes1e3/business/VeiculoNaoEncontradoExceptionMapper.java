package br.edu.uni7.questoes1e3.business;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VeiculoNaoEncontradoExceptionMapper implements ExceptionMapper<VeiculoNaoEncontradoException> {
	@Override
	public Response toResponse(VeiculoNaoEncontradoException exception) {
		return Response.status(Status.NOT_FOUND).build();
	}
}
