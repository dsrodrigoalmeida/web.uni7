package br.edu.uni7.questoes2e4.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.uni7.questoes2e4.util.ValidacaoHelper;

@FacesValidator("validators.CepValidator")
public class CepValidator implements Validator {

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			String valor = value.toString();
			if (!ValidacaoHelper.validaCep(valor)) {
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("Erro de Validação");
				message.setDetail("CEP Inválido");
				throw new ValidatorException(message);
			}
		}
	}
}