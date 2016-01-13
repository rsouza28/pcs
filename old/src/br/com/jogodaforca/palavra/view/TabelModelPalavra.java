package br.com.jogodaforca.palavra.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.jogodaforca.vo.PalavraVo;

public class TabelModelPalavra extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4385666516042480101L;

	private static final int PALAVRA = 0;
	private static final int DICA = 1;

	private String[] tabelaColunas = new String[] { "Palavra", "Dica" };

	private List<PalavraVo> palavraLinhas;

	public TabelModelPalavra(List<PalavraVo> palavraLinhas) {
		this.palavraLinhas = palavraLinhas;

	}

	@Override
	public int getRowCount() {
		return palavraLinhas.size();
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

		case PALAVRA:
			return String.class;

		case DICA:
			return Integer.class;

		default:
			throw new IndexOutOfBoundsException("Erro! Número de colunas excedido");
		}

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		PalavraVo palavraVo = palavraLinhas.get(rowIndex);

		switch (columnIndex) {

		case PALAVRA:
			return palavraVo.getPalavra();

		case DICA:
			return palavraVo.getDica();

		default:
			throw new IndexOutOfBoundsException("Erro! Numero de colunas excedido, na hora de popular a tabela!");
		}

	}
}
