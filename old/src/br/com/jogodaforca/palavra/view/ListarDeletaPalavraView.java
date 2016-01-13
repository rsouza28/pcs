package br.com.jogodaforca.palavra.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.jogodaforca.palavra.controle.PalavraControle;
import br.com.jogodaforca.vo.PalavraVo;

public class ListarDeletaPalavraView extends JFrame {

	private static final long serialVersionUID = -6757995801339760318L;

	private JTextField palavraField;
	private JButton botaoDeletar;
	private JScrollPane barraRolagem;

	private JTable tabelPalavra;
	private TabelModelPalavra palavraTabelModel;

	private List<PalavraVo> palavraLista;

	public ListarDeletaPalavraView() {

		super("Lista de palavras cadastrada");
		setLocation(300, 200);
		setSize(830, 460);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());
		criarHead();
		criarTabela();
		criarBotoes();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void criarHead() {

		// ------------------------- HEAD -----------------------------
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Informe a palavra que gostaria de deletar e clique em deletar");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 20));

		panelTitulo.add(titulo);

		add(panelTitulo, BorderLayout.NORTH);

	}

	// ------------------------ TABELA -----------------------------
	private void criarTabela() {

		barraRolagem = new JScrollPane(getTabelPalavra());
		add(barraRolagem, BorderLayout.CENTER);
	}

	private JTable getTabelPalavra() {
		if (tabelPalavra == null) {
			tabelPalavra = new JTable();
			tabelPalavra.setModel(getPalavraTabelModel());
		}

		return tabelPalavra;
	}

	private TabelModelPalavra getPalavraTabelModel() {
		if (palavraTabelModel == null) {
			palavraTabelModel = new TabelModelPalavra(getPalavraLista());
		}
		return palavraTabelModel;
	}

	private List<PalavraVo> getPalavraLista() {
		palavraLista = new PalavraControle().buscarListaPalavra();

		if (palavraLista == null) {
			JOptionPane.showMessageDialog(null,
					"ERRO! Nao foi possivel carregar lista de palavras, talvez nao exista palavra cadastrada!");
		}

		return palavraLista;
	}

	// ------------------------- BOTOES -----------------------------
	private void criarBotoes() {
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout());

		JLabel palavraLabel = new JLabel("Palavra: ");
		palavraField = new JTextField(20);

		botaoDeletar = new JButton("Deletar");
		botaoDeletar.addActionListener(listener);

		panelBotoes.add(palavraLabel);
		panelBotoes.add(palavraField);
		panelBotoes.add(botaoDeletar);

		add(panelBotoes, BorderLayout.SOUTH);

	}

	private ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == botaoDeletar) {
				PalavraVo palavraVo = new PalavraVo();
				palavraVo.setPalavra(palavraField.getText().toUpperCase().trim());

				palavraVo = new PalavraControle().deletarPalavra(palavraVo);

				if (palavraVo != null) {
					JOptionPane.showMessageDialog(null, "Palavra deletada com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Palavra não encontrada!");
				}

				palavraField.setText(" ");
				dispose();
				new ListarDeletaPalavraView();
			}
		}
	};

	// Teste
	// public static void main(String[] args) {
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// new ListarDeletaPalavraView();
	// }
	// });
	//
	// }

}
