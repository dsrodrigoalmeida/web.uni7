package br.edu.uni7.questoes1e3.business;

/**
 * Classe de excecao disparada pela camada de negocio.
 * @author Fabio Barros
 */
public class VeiculoInvalidoException extends Exception {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1071896537277884578L;

	public VeiculoInvalidoException() {
		super("Veiculo invalido!");
	}
}
