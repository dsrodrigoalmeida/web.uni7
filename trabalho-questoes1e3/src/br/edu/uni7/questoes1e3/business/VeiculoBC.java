package br.edu.uni7.questoes1e3.business;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.edu.uni7.questoes1e3.dao.Repositorio;
import br.edu.uni7.questoes1e3.model.Veiculo;

@ApplicationScoped
public class VeiculoBC {

	@Inject
	private Repositorio repositorio;

	@PostConstruct
	public void inicializar() {

		Calendar data = Calendar.getInstance();

		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("IOP-1310");
		veiculo.setNomeProprietario("Pedro de Alcantara");
		data.set(1798, 9, 12);
		veiculo.setDataEmplacamento(data.getTime());
		veiculo.setValorIPVA(1000.20);
		repositorio.inserir(veiculo);

		veiculo = new Veiculo();
		veiculo.setPlaca("ERT-5837");
		veiculo.setNomeProprietario("Santos Dumont");
		data.set(1873, 6, 20);
		veiculo.setDataEmplacamento(data.getTime());
		veiculo.setValorIPVA(1400.27);
		repositorio.inserir(veiculo);

		veiculo = new Veiculo();
		veiculo.setPlaca("GKD-5846");
		veiculo.setNomeProprietario("Isabel de Braganca");
		data.set(1846, 6, 29);
		veiculo.setDataEmplacamento(data.getTime());
		veiculo.setValorIPVA(1634.95);
		repositorio.inserir(veiculo);

		veiculo = new Veiculo();
		veiculo.setPlaca("DEV-0001");
		veiculo.setNomeProprietario("Master");
		data.set(2019, 3, 5);
		veiculo.setDataEmplacamento(data.getTime());
		veiculo.setValorIPVA(0);

		repositorio.inserir(veiculo);

	}

	public List<Veiculo> selecionar() {
		return repositorio.selecionar(Veiculo.class);
	}

	public Veiculo selecionar(Long id) throws VeiculoNaoEncontradoException {
		Veiculo veiculo = repositorio.selecionar(Veiculo.class, id);
		if (veiculo == null) {
			throw new VeiculoNaoEncontradoException();
		}
		return veiculo;
	}

	public Long inserir(Veiculo veiculo) throws ValidacaoException {
		validar(veiculo);
		formatarPlaca(veiculo);
		return repositorio.inserir(veiculo);
	}

	public void atualizar(Veiculo veiculo) throws VeiculoNaoEncontradoException, ValidacaoException {
		if (!repositorio.atualizar(veiculo)) {
			validar(veiculo);
			throw new VeiculoNaoEncontradoException();
		}
		formatarPlaca(veiculo);
	}

	public Veiculo excluir(Long id) throws VeiculoNaoEncontradoException, ValidacaoException {
		Veiculo veiculo = repositorio.excluir(Veiculo.class, id);
		validar(veiculo);
		if (veiculo == null) {
			throw new VeiculoNaoEncontradoException();
		}
		return veiculo;
	}

	private void validar(Veiculo veiculo) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Veiculo>> violations = validator.validate(veiculo);
		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();
			for (ConstraintViolation<Veiculo> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}
			throw validacaoException;
		}
	}

	

	private void formatarPlaca(Veiculo veiculo) {
		veiculo.setPlaca(veiculo.getPlaca().substring(0, 3) + "-" + veiculo.getPlaca().substring(3));
	}
}
