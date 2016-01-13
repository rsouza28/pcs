package br.com.jogodaforca.vo;

public class RankingVo implements Comparable<RankingVo> {

	private RodadaVo rodada;

	public RankingVo() {
		rodada = new RodadaVo();
	}

	public RodadaVo getRodada() {
		return rodada;
	}

	public void setRodada(RodadaVo rodada) {
		this.rodada = rodada;
	}

	@Override
	public String toString() {
		return rodada.getAluno().getNome() + ":" + rodada.getAluno().getIdade() + ":" + rodada.getPontuacao() + ":"
				+ rodada.getTempo() + "\r\n";
	}

	@Override
	public int compareTo(RankingVo o) {

		if (this.rodada.getPontuacao() > o.rodada.getPontuacao()) {
			return -1;
		} else if (this.rodada.getPontuacao() < o.rodada.getPontuacao()) {
			return 1;
		} else {
			return 0;
		}
	}

}
