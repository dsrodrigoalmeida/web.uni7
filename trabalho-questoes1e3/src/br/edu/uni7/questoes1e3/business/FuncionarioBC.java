package br.edu.uni7.questoes1e3.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.edu.uni7.questoes1e3.dao.Repositorio;
import br.edu.uni7.questoes1e3.model.Funcionario;

@ApplicationScoped
public class FuncionarioBC {

	@Inject
	private Repositorio repositorio;
	
	@PostConstruct
	public void inicializar() {
		Funcionario funcionario = new Funcionario();
		funcionario.setUsername("rodrigo");
		funcionario.setSenha("123");
		repositorio.inserir(funcionario);
	}
	
	public List<Funcionario> selecionar() {
		return repositorio.selecionar(Funcionario.class);
	}

	public Funcionario selecionar(Long id) throws FuncionarioNaoEncontradoException {
		Funcionario funcionario = repositorio.selecionar(Funcionario.class, id);
		if (funcionario == null) {
			throw new FuncionarioNaoEncontradoException();
		}
		return funcionario;
	}

	public Funcionario autenticarFuncionario(String username, String senha) throws FuncionarioInvalidoException {
		
	for (Funcionario funcionario : selecionar()) {
		if (funcionario.getUsername().equals(username) && funcionario.getSenha().equals(senha)) {
			return funcionario;
		}
	}
	throw new FuncionarioInvalidoException();
}
	
	
}
