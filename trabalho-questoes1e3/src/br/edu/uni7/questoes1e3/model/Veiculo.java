package br.edu.uni7.questoes1e3.model;

import java.io.Serializable;
import java.util.Date;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import br.edu.uni7.questoes1e3.model.adapter.DateAdapter;

public class Veiculo extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3064306490724801147L;

	@NotNull
	@Size(min=7,max=7)
	private String placa;
	
	@NotNull
	@Size(min=5, max=100)
	private String nomeProprietario;
	
	@NotNull
	@Past
	@JsonbTypeAdapter(DateAdapter.class)
	private Date dataEmplacamento;
	
	@NotNull
	double valorIPVA;

	public Veiculo() {
		super();
	}

	public Veiculo(Long id) {
		this();
		setId(id);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public Date getDataEmplacamento() {
		return dataEmplacamento;
	}

	public void setDataEmplacamento(Date dataEmplacamento) {
		this.dataEmplacamento = dataEmplacamento;
	}

	public double getValorIPVA() {
		return valorIPVA;
	}

	public void setValorIPVA(double valorIPVA) {
		this.valorIPVA = valorIPVA;
	}


	

	
}