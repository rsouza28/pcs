package br.com.jogodaforca.vo;

public class RodadaVo {

	private AlunoVo aluno;
	private PalavraVo palavra;
	private Integer tempo;
	private Integer acertos;
	private Integer erros;
	private Integer pontuacao;
	private String letras;

	public RodadaVo() {
		aluno = new AlunoVo();
		palavra = new PalavraVo();
	}

	public AlunoVo getAluno() {
		return aluno;
	}

	public void setAluno(AlunoVo aluno) {
		this.aluno = aluno;
	}

	public PalavraVo getPalavra() {
		return palavra;
	}

	public void setPalavra(PalavraVo palavra) {
		this.palavra = palavra;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Integer getAcertos() {
		return acertos;
	}

	public void setAcertos(Integer acertos) {
		this.acertos = acertos;
	}

	public Integer getErros() {
		return erros;
	}

	public void setErros(Integer erros) {
		this.erros = erros;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getLetras() {
		return letras;
	}

	public void setLetras(String letras) {
		this.letras = letras;
	}

	@Override
	public String toString() {
		return aluno.getNome() + ":" + palavra.getPalavra() + ":" + tempo + ":" + acertos + ":" + erros + ":"
				+ pontuacao + ":" + letras + "\r\n";
	}

}
