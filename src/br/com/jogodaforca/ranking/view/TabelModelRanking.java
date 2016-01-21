package br.com.jogodaforca.ranking.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.jogodaforca.vo.RankingVo;

public class TabelModelRanking extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2190148510613148649L;

	private static final int POSICAO = 0;
	private static final int NOME = 1;
	private static final int IDADE = 2;
	private static final int PONTUACAO = 3;
	private static final int TEMPO = 4;

	private String[] tabelaColunas = new String[] { "Posição", "Nome", "Idade", "Pontuação", "Tempo" };

	private List<RankingVo> rankingLinhas;

	public TabelModelRanking(List<RankingVo> rankingLinhas) {
		this.rankingLinhas = rankingLinhas;

	}

	@Override
	public int getRowCount() {
		return rankingLinhas.size();
	}

	@Override
	public int getColumnCount() {
		return tabelaColunas.length;
	}

	@Override
	public String getColumnName(int column) {
		return tabelaColunas[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {

		case POSICAO:
			return Integer.class;

		case NOME:
			return String.class;

		case IDADE:
			return Integer.class;

		case PONTUACAO:
			return Integer.class;

		case TEMPO:
			return Integer.class;

		default:
			throw new IndexOutOfBoundsException("Erro! Número de colunas excedido");
		}

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		RankingVo rankingVo = rankingLinhas.get(rowIndex);

		switch (columnIndex) {

		case POSICAO:
			return rankingVo.getRodada().getAluno().getCodigo();

		case NOME:
			return rankingVo.getRodada().getAluno().getNome();

		case IDADE:
			return rankingVo.getRodada().getAluno().getIdade();

		case PONTUACAO:
			return rankingVo.getRodada().getPontuacao();

		case TEMPO:
			return rankingVo.getRodada().getTempo();

		default:
			throw new IndexOutOfBoundsException("Erro! Numero de colunas excedido, na hora de popular a tabela!");
		}

	}

}
