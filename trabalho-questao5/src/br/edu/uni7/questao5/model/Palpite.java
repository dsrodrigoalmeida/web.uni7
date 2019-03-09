package br.edu.uni7.questao5.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;


public class Palpite extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3064306490724801147L;

	private List <String> palavraSorteada;
	private List <String> palavraTemp;
    private int numeroTentativas;
    @Size(min=1,max=15)
	private String  letraDigitada;
	private List <String>  listaLetrasDigitadas;
	private String status;
	private String palavraTempString;
	
	public Palpite() {
		this.setPalavraSorteada(new ArrayList<>());
		this.setPalavraTemp(new ArrayList<>());
		this.numeroTentativas=6;
		this.letraDigitada="";
		this.setListaLetrasDigitadas(new ArrayList<>());
		this.status = "";
		this.palavraTempString="";
	}
	
	
	public List<String> getPalavraSorteada() {
		return palavraSorteada;
	}


	public void setPalavraSorteada(List<String> palavraSorteada) {
		this.palavraSorteada = palavraSorteada;
	}


	public List<String> getPalavraTemp() {
		return palavraTemp;
	}


	public void setPalavraTemp(List<String> palavraTemp) {
		this.palavraTemp = palavraTemp;
	}


	public  int getNumeroTentativas() {
		return numeroTentativas;
	}

	public  void setNumeroTentativas(int numeroTentativas) {
		this.numeroTentativas = numeroTentativas;
	}

	public String getLetraDigitada() {
		return letraDigitada;
	}

	public void setLetraDigitada(String letraDigitada) {
		this.letraDigitada = letraDigitada;
	}

	public List <String> getListaLetrasDigitadas() {
		return listaLetrasDigitadas;
	}

	public void setListaLetrasDigitadas(List <String> listaLetrasDigitadas) {
		this.listaLetrasDigitadas = listaLetrasDigitadas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getPalavraTempString() {
		return palavraTempString;
	}


	public void setPalavraTempString(String palavraTempString) {
		this.palavraTempString = palavraTempString;
	}

	
}