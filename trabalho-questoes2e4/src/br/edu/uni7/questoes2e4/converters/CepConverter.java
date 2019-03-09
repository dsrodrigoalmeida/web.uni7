package br.edu.uni7.questoes2e4.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.uni7.questoes2e4.model.Cep;

@FacesConverter("converters.CepConverter")
public class CepConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && !value.equals("")) {
			String cepString = value.replaceAll("\\-", "");
			try {
				// Testa se somente existem numeros.
				Long.valueOf(cepString);
				Cep cep = new Cep();
				cep.setRegiao(cepString.substring(0,5));
				cep.setSufixo(cepString.substring(5));
				
				return cep;
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"O valor informado não é um número de CEP!");
				throw new ConverterException(message);
			}
		}
		return value;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cep = (value == null ? null : value.toString());
		if (cep != null && !cep.equals("")) {
			cep = cep.substring(0, 5) + "-"  + cep.substring(5);
		}
		return cep;
	}
}