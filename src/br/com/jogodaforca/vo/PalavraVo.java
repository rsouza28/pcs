package br.com.jogodaforca.vo;

public class PalavraVo {

	private String palavra;
	private String dica;

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	@Override
	public String toString() {
		return palavra + ":" + dica + "\r\n";
	}

}
