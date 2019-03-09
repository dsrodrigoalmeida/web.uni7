package br.edu.uni7.questao5.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.edu.uni7.questao5.business.PalpiteBC;
import br.edu.uni7.questao5.business.ValidacaoException;
import br.edu.uni7.questao5.model.Palpite;


@Path("palpites")
public class PalpitesRS {

	@Inject
	private PalpiteBC palpiteBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Palpite> selecionar() {
		return palpiteBC.selecionar();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Palpite selecionar(@PathParam("id") Long id) {
		
			return palpiteBC.selecionar(id);
		
	}

	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, Palpite palpite) throws ValidacaoException {
		
			palpite.setId(id);
			palpiteBC.atualizar(palpite);
			return Response.ok(id).build();
	}
}