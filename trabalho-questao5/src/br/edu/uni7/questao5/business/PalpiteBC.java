package br.edu.uni7.questao5.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.edu.uni7.questao5.dao.Repositorio;
import br.edu.uni7.questao5.model.Palpite;

@ApplicationScoped
public class PalpiteBC {

	int contadorAcertos = 0;
	boolean jogoAcabou = false;
	String palavraSorteadaString;
	
	@Inject
	private Repositorio repositorio;
	@PostConstruct
	public void inicializar() {

		Palpite palpite = new Palpite();
		sortearPalavra();
		popularListas(palpite);
		atualizarPalavraTempString(palpite);
		palpite.setLetraDigitada("");
		palpite.setNumeroTentativas(6);
		repositorio.inserir(palpite);

	}

	public List<Palpite> selecionar() {
		return repositorio.selecionar(Palpite.class);
	}

	public Palpite selecionar(Long id)   {
		Palpite palpite = repositorio.selecionar(Palpite.class, id);
		
		return palpite;
	}

	public void atualizar(Palpite palpite) throws ValidacaoException {
		validar(palpite);
		repositorio.atualizar(palpite); 
		palpite.setLetraDigitada(palpite.getLetraDigitada().toLowerCase());
		if (jogoAcabou == false) {
			if (palpite.getLetraDigitada().length() > 1) {
				allInPalavra(palpite);
			} else {
				boolean letraRepetida = verificarLetraRepetida(palpite);
				if (letraRepetida == false) {
					verificarLetraExiste(palpite);
				} else {
					palpite.setStatus("Voce ja digitou essa letra. Por favor digite outra");
				}
			}

		} else {
			palpite.setStatus("O jogo ja acabou. Feche o navegador e abra novamente para poder jogar. Obrigado");
		}
	}
	
	 private void validar(Palpite palpite) throws ValidacaoException {
	 Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	 Set<ConstraintViolation<Palpite>> violations =
	 validator.validate(palpite);
	 if (!violations.isEmpty()) {
	 ValidacaoException validacaoException =
	 new ValidacaoException();
	 for (ConstraintViolation<Palpite> violation : violations) {
	 String entidade = violation
	 .getRootBeanClass().getSimpleName();
	 String propriedade = violation
	 .getPropertyPath().toString();
	 String mensagem = violation.getMessage();
	 validacaoException.adicionar(
	 entidade, propriedade, mensagem);
	 }
	 throw validacaoException;
	 }
	 }

	
	public void sortearPalavra() {
		List<String> listaPalavraSorteada = new ArrayList<>();
		
		listaPalavraSorteada.add(0,"zelda");
		listaPalavraSorteada.add(1,"link");
		listaPalavraSorteada.add(2,"revali");
		listaPalavraSorteada.add(3,"mipha");
		listaPalavraSorteada.add(4,"urbosa");
		listaPalavraSorteada.add(5,"daruk");
		listaPalavraSorteada.add(6,"sidon");
		listaPalavraSorteada.add(7,"teba");
		listaPalavraSorteada.add(8,"riju");
		listaPalavraSorteada.add(9,"yunobo");
		
		int numero= (int)(Math.random() * 10);
		palavraSorteadaString = listaPalavraSorteada.get(numero);

	}
	
	public void popularListas(Palpite palpite) {
		for (int i=0; i < palavraSorteadaString.length(); i++) {
			
			Character palavraSorteadaChar = palavraSorteadaString.charAt(i);
			palpite.getPalavraSorteada().add(i, palavraSorteadaChar.toString());
			palpite.getPalavraTemp().add(i, "_");
			
		}
		
	}
	
	public void atualizarPalavraTempString (Palpite palpite) {
		palpite.setPalavraTempString("");
		for (int i=0; i< palpite.getPalavraTemp().size(); i++) {
			palpite.setPalavraTempString(palpite.getPalavraTempString() + palpite.getPalavraTemp().get(i));
		}
	}

	public boolean verificarLetraRepetida(Palpite palpite) {

		if (palpite.getListaLetrasDigitadas().size() == 0) {
			palpite.getListaLetrasDigitadas().add(palpite.getLetraDigitada());
		} else {
			for (int i = 0; i < palpite.getListaLetrasDigitadas().size(); i++) {
				if (palpite.getListaLetrasDigitadas().get(i).equals(palpite.getLetraDigitada())) {
					palpite.setLetraDigitada("");
					palpite.setStatus("Você digitou uma letra repetida. Tente novamente");
					return true;
				}
			}
			palpite.getListaLetrasDigitadas().add(palpite.getListaLetrasDigitadas().size(), palpite.getLetraDigitada());
		}

		return false;
	}

	public void verificarLetraExiste(Palpite palpite) {
		boolean acertouPalpite = false;
		for (int i = 0; i < palpite.getPalavraSorteada().size(); i++) {
			if (palpite.getLetraDigitada().equals(palpite.getPalavraSorteada().get(i))) {
				palpite.getPalavraTemp().set(i, palpite.getLetraDigitada());
				acertouPalpite = true;
				contadorAcertos++;

			}
		}
		if (acertouPalpite == true) {
			palpite.setStatus("Voce Acertou!");
		} else {
			palpite.setStatus("Voce Errou!");
		}
		atualizarPalavraTempString(palpite);
		palpite.setNumeroTentativas(palpite.getNumeroTentativas() - 1);
		palpite.setLetraDigitada("");
		verificarJogoAcabou(palpite);
	}

	public void allInPalavra(Palpite palpite) {
		  
		for (int i = 0; i < palpite.getPalavraSorteada().size(); i++) {
			Character letraDigitadaString = palpite.getLetraDigitada().charAt(i);
			if (letraDigitadaString.toString().equals(palpite.getPalavraSorteada().get(i))) {
				palpite.getPalavraTemp().set(i, letraDigitadaString.toString());
				contadorAcertos++;
				
			}
		}
		atualizarPalavraTempString(palpite);
		palpite.setNumeroTentativas(0);
		palpite.setLetraDigitada("");
		verificarJogoAcabou(palpite);
	}

	public void verificarJogoAcabou(Palpite palpite) {
		boolean acertouPalpite = true;
		if (palpite.getNumeroTentativas() == 0 || contadorAcertos == palpite.getPalavraSorteada().size()) {
			for (int i = 0; i < palpite.getPalavraSorteada().size(); i++) {
				if (!(palpite.getPalavraTemp().get(i).equals(palpite.getPalavraSorteada().get(i)))) {
					acertouPalpite = false;
					break;
				}
			}

			if (acertouPalpite == true) {
				palpite.setStatus("Parabens! Voce Venceu!");
			} else {
				palpite.setStatus("Voce Perdeu!");
			}
			jogoAcabou = true;
		}
	}
}
