package br.edu.uni7.questoes1e3.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.edu.uni7.questoes1e3.business.JWTRequired;
import br.edu.uni7.questoes1e3.business.ValidacaoException;
import br.edu.uni7.questoes1e3.business.VeiculoBC;
import br.edu.uni7.questoes1e3.business.VeiculoNaoEncontradoException;
import br.edu.uni7.questoes1e3.model.Veiculo;

@JWTRequired
@Path("veiculos")
public class VeiculosRS {

	@Inject
	private VeiculoBC veiculoBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Veiculo> selecionar() {
		return veiculoBC.selecionar();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Veiculo selecionar(@PathParam("id") Long id) {
		try {
			return veiculoBC.selecionar(id);
		} catch (VeiculoNaoEncontradoException e) {
			throw new NotFoundException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Veiculo body) throws ValidacaoException {
		Long id = veiculoBC.inserir(body);
		String url = "/api/veiculos/" + id;
		return Response.status(Status.CREATED).header("Location", url).entity(id).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, Veiculo veiculo) throws ValidacaoException,VeiculoNaoEncontradoException {
		
			veiculo.setId(id);
			veiculoBC.atualizar(veiculo);
			return Response.ok(id).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Long id) throws ValidacaoException,VeiculoNaoEncontradoException {
		
			Veiculo veiculo = veiculoBC.excluir(id);
			return Response.ok(veiculo).build();
		
	}

}
