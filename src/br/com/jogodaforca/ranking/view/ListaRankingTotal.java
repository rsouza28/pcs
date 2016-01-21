package br.com.jogodaforca.ranking.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.jogodaforca.ranking.controle.RankingControle;
import br.com.jogodaforca.vo.RankingVo;

public class ListaRankingTotal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6978976060192441149L;

	private JTable tabelRanking;
	private JScrollPane barraRolagem;
	private TabelModelRanking rankingTabelModel;
	private JPanel panelTitulo;
	private JLabel tituloLabel;
	private List<RankingVo> rankingLista;

	public ListaRankingTotal() {

		// --------------------- TITULO JANELA -----------------------------
		super("Ranking Total");
		setLayout(new BorderLayout());
		setLocation(250, 150);
		setSize(800, 400);
		setResizable(true);
		setVisible(true);

		criarHead();
		criarTabela();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	// ------------------------ HEAD -----------------------------
	private void criarHead() {

		panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());
		tituloLabel = new JLabel("Ranking total");
		tituloLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		panelTitulo.add(tituloLabel);
		add(panelTitulo, BorderLayout.NORTH);
	}

	// ------------------------ TABELA -----------------------------
	private void criarTabela() {
		barraRolagem = new JScrollPane(getTabelRanking());
		add(barraRolagem, BorderLayout.CENTER);
	}

	private JTable getTabelRanking() {
		if (tabelRanking == null) {
			tabelRanking = new JTable();
			tabelRanking.setModel(getRankingTabelModel());
			tabelRanking.setEnabled(false);
			tabelRanking.getTableHeader().setReorderingAllowed(false);
			tabelRanking.getTableHeader().setResizingAllowed(false);

		}

		return tabelRanking;
	}

	private TabelModelRanking getRankingTabelModel() {
		if (rankingTabelModel == null) {
			rankingTabelModel = new TabelModelRanking(getRankingLista());
		}
		return rankingTabelModel;
	}

	private List<RankingVo> getRankingLista() {
		rankingLista = new RankingControle().gerarRankingTotal();

		if (rankingLista == null) {
			JOptionPane.showMessageDialog(null,
					"ERRO! Nao foi possivel carregar lista de ranking, talvez nao exista jogo feito!");
		}

		return rankingLista;
	}

	// Teste
	// public static void main(String[] args) {
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// new ListaRankingTotal();
	// }
	// });
	//
	// }

}
