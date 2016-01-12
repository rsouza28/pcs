package br.com.jogodaforca.ranking.controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.jogodaforca.ranking.dao.RankingDao;
import br.com.jogodaforca.vo.RankingVo;

public class RankingControle {

	public void calcularPontosEsalvar(RankingVo rankingVo) {
		calcularPontos(rankingVo);
		new RankingDao().salvar(rankingVo);
	}

	public void calcularPontos(RankingVo rankingVo) {
		int acertos = rankingVo.getRodada().getAcertos();
		int erros = rankingVo.getRodada().getErros();
		int tempoDecrescente = rankingVo.getRodada().getTempo();
		int tempoCrescente = 300 - tempoDecrescente;

		rankingVo.getRodada().setPontuacao(10 * acertos - 5 * erros + tempoCrescente);

		rankingVo.getRodada().setTempo(tempoCrescente);

	}

	private List<RankingVo> ordenarLista() {

		// recupera lista do txt
		List<RankingVo> rankingLista = new RankingDao().lerArquivo();

		// ordena lista
		Collections.sort(rankingLista);

		return rankingLista;
	}

	public List<RankingVo> gerarRankingTotal() {
		return ordenarLista();
	}

	public List<RankingVo> gerarRankingTop10() {
		List<RankingVo> rankingLista = ordenarLista();
		List<RankingVo> rankingListaTop10 = new ArrayList<>();

		try {

			for (int i = 0; i < 10; i++) {
				rankingListaTop10.add(rankingLista.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não existe mais de 10 jogadores para ter top 10!");
		}
		return rankingListaTop10;
	}

	// Teste
	// public static void main(String[] args) {
	// System.out.println("Lista desordenada: " + new
	// RankingDao().lerArquivo());
	// System.out.println("Lista ordenada: " + new
	// RankingControle().gerarRankingTotal());
	// }

}
