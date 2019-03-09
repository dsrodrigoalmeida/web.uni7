package br.edu.uni7.questoes1e3.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jose4j.lang.JoseException;

import br.edu.uni7.questoes1e3.business.FuncionarioBC;
import br.edu.uni7.questoes1e3.business.FuncionarioInvalidoException;
import br.edu.uni7.questoes1e3.business.JWTUtil;
import br.edu.uni7.questoes1e3.model.Funcionario;

@Path("login")
public class LoginRS {
@Inject
private FuncionarioBC funcionarioBC;
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public Response login(Funcionario funcionario) {
try {
funcionarioBC.autenticarFuncionario(funcionario.getUsername(), funcionario.getSenha());
return Response.status(Response.Status.OK).entity("Usuário autorizado")
.header(HttpHeaders.AUTHORIZATION, JWTUtil. BEARER + JWTUtil.criar(funcionario.getUsername()))
.build();
} catch (FuncionarioInvalidoException e) {
return Response.status(Response.Status.FORBIDDEN)
.entity("Usuário ou senha inválidos").build();
} catch (JoseException e) {
return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
.entity("Erro ao gerar o token de acesso").build();
}
}
}
 
