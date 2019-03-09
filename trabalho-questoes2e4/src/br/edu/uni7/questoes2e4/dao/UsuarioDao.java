package br.edu.uni7.questoes2e4.dao;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.edu.uni7.questoes2e4.model.Cep;
import br.edu.uni7.questoes2e4.model.Usuario;

/**
 * DAO da entidade Usuario.
 * 
 * @author Fabio Barros
 */
@ApplicationScoped
@ManagedBean(name = "usuarioDao")
public class UsuarioDao extends SimpleDaoGenerico<Usuario> {

	Cep cep = new Cep();
	
	public UsuarioDao() {
		Calendar dataNascimento = Calendar.getInstance();

		Usuario usuario = new Usuario();
		usuario.setNomeReduzido("Pedro de Alcantara");
		usuario.setNomeCompleto("Pedro de Alcantara Francisco Antanio "
				+ "Joao Carlos Xavier de Paula Miguel Rafael Joaquim Jose "
				+ "Gonzaga Pascoal Cipriano Serafim de Braganca e Bourbon");
		usuario.setEmail("pedro.alcantara@gmail.com");
		usuario.setSenha("teste");
		usuario.setCpf("11111111111");
		dataNascimento.set(1798, 9, 12);
		usuario.setDataNascimento(dataNascimento.getTime());
		usuario.setSenha("teste");
		cep = new Cep();
		cep.setRegiao("12345");
		cep.setSufixo("678");
		usuario.setCep(cep);
		super.salvarEntidade(usuario);

		usuario = new Usuario();
		usuario.setNomeReduzido("Santos Dumont");
		usuario.setNomeCompleto("Alberto Santos Dumont");
		usuario.setEmail("santos.dumont@gmail.com");
		usuario.setSenha("teste");
		usuario.setCpf("22222222222");
		dataNascimento.set(1873, 6, 20);
		usuario.setDataNascimento(dataNascimento.getTime());
		cep = new Cep();
		cep.setRegiao("23456");
		cep.setSufixo("789");
		usuario.setCep(cep);
		super.salvarEntidade(usuario);

		usuario = new Usuario();
		usuario.setNomeReduzido("Isabel de Braganca");
		usuario.setNomeCompleto("Isabel Cristina Leopoldina Augusta "
				+ "Micaela Gabriela Rafaela Gonzaga de Braganca e Bourbon");
		usuario.setEmail("maria@gmail.com");
		usuario.setSenha("teste");
		usuario.setCpf("33333333333");
		dataNascimento.set(1846, 6, 29);
		usuario.setDataNascimento(dataNascimento.getTime());
		cep = new Cep();
		cep.setRegiao("34567");
		cep.setSufixo("890");
		usuario.setCep(cep);
		super.salvarEntidade(usuario);
	}

	/**
	 * Insere o usuario na base de dados.
	 * 
	 * @param usuario
	 *            Usuario a ser persistida.
	 */
	public void salvarUsuario(Usuario usuario) {
		super.salvarEntidade(usuario);
	}

	/**
	 * Exclui o usuario na base de dados.
	 * 
	 * @param usuario
	 *            usuario a ser excluido
	 */
	public void excluirUsuario(Usuario usuario) {
		super.excluirEntidade(usuario);
	}

	/**
	 * Seleciona todos os registros aramzenados de um usuario {@link Usuario}.
	 * 
	 * @return lista de {@link Usuario}
	 */
	@Override
	public Usuario selecionar(Usuario usuario) {
		return super.selecionar(usuario);
	}

	/**
	 * Seleciona os usuarios que possuem atributos que correspondam aos
	 * atributos setados no usuario exemplo.
	 * 
	 * @param usuario
	 *            Usuario contendo os parametros da consulta.
	 * @return a usuario que corresponde ao parametro da consulta.
	 */
	@Override
	public List<Usuario> selecionarTodos() {
		return super.selecionarTodos();
	}

	/**
	 * Seleciona o usuario que possue o CPF que corresponda ao parametro.
	 * 
	 * @param cpf
	 *            CPF do usuario desejado
	 * @return {@link Usuario} correspondente ou null caso nao seja encontrado.
	 */
	public Usuario selecionar(String cpf) {
		List<Usuario> usuarios = selecionarTodos();
		for (Usuario usuarioLista : usuarios) {
			if (usuarioLista.getCpf().equals(cpf)) {
				return usuarioLista;
			}
		}

		return null;
	}
}
