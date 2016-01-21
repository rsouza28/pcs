package br.com.jogodaforca.palavra.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.jogodaforca.palavra.controle.PalavraControle;
import br.com.jogodaforca.util.ValidaEntrada;
import br.com.jogodaforca.vo.PalavraVo;

public class CadastraPalavraView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3353277167767892648L;

	private JTextField palavraField;
	private JTextField dicaField;
	private JButton botaoCadastrar;

	public CadastraPalavraView() {

		criarHead();
		criarCampos();
		criarBotoes();
	}

	private void criarHead() {

		// ------------------------- HEAD -----------------------------
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Cadastro de palavras");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 50));

		panelTitulo.add(titulo);

		add(panelTitulo, BorderLayout.NORTH);

	}

	// -------------------------CAMPOS -----------------------------
	private void criarCampos() {

		JPanel panelCadastro = new JPanel();
		panelCadastro.setLayout(new FlowLayout());

		JLabel palavraLabel = new JLabel("Palavra:");
		palavraField = new JTextField(15);
		panelCadastro.add(palavraLabel);
		panelCadastro.add(palavraField);

		JLabel dicaLabel = new JLabel("Dica:");
		dicaField = new JTextField(15);
		panelCadastro.add(dicaLabel);
		panelCadastro.add(dicaField);

		add(panelCadastro, BorderLayout.CENTER);
	}

	// ------------------------- BOTOES -----------------------------
	private void criarBotoes() {
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout());

		botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(listener);
		panelBotoes.add(botaoCadastrar);

		add(panelBotoes, BorderLayout.SOUTH);

	}

	private ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == botaoCadastrar) {
				PalavraVo palavraVo = new PalavraVo();

				String palavra = palavraField.getText().toUpperCase().trim();
				String dica = dicaField.getText().toUpperCase().trim();

				boolean verificaCampo = new ValidaEntrada().validarCamposPalavra(palavra, dica);
				boolean verificaEntrada = new ValidaEntrada().validarEntradaPalavra(palavra, dica);

				if (verificaCampo != false && verificaEntrada != false) {

					palavraVo.setPalavra(palavra.replace(" ", ""));
					palavraVo.setDica(dica);

					boolean verifica = new PalavraControle().verificarSeJaExistePalavra(palavraVo);

					if (verifica != true) {

						palavraVo = new PalavraControle().salvarPalavra(palavraVo);

						if (palavraVo != null) {
							JOptionPane.showMessageDialog(null, "Palavra cadastrada com sucesso!");
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao cadastrar palavra!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Palavra já está cadastrada!");
					}

					palavraField.setText(" ");
					dicaField.setText(" ");
				}
			}
		}
	};

}
