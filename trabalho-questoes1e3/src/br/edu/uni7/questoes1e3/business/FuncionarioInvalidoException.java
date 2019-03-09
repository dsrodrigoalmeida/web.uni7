package br.edu.uni7.questoes1e3.business;

public class FuncionarioInvalidoException extends Exception {
		private static final long serialVersionUID = -1071896537277884578L;

		public FuncionarioInvalidoException() {
			super("Login ou senha invalidos!");
		}
	}


