package br.edu.uni7.questoes2e4.model;

public class Cep {
		private String regiao;
		private String sufixo;

		
		public String getRegiao() {
			return regiao;
		}
		public void setRegiao(String regiao) {
			this.regiao = regiao;
		}
		public String getSufixo() {
			return sufixo;
		}
		public void setSufixo(String sufixo) {
			this.sufixo = sufixo;
		}
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return this.regiao+this.sufixo;
		}
		
		
}
