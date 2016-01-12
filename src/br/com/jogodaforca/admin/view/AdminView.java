package br.com.jogodaforca.admin.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.com.jogodaforca.palavra.view.CadastraPalavraView;
import br.com.jogodaforca.palavra.view.ListarDeletaPalavraView;

public class AdminView extends JFrame {

	private static final long serialVersionUID = -4405642999868273952L;

	private JMenuItem menuItemAdminCadastrarPalavra;
	private JMenuItem menuItemAdminDeletarPalavra;
	private CardLayout cardLayout = new CardLayout();
	JPanel panelConteudo;

	public AdminView() {

		// --------------------- TITULO JANELA -----------------------------
		super("Área do admin");
		setLocation(500, 100);
		setSize(830, 460);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());
		criarHead();
		criarMenu();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	// ------------------------- HEAD -----------------------------
	private void criarHead() {

		JPanel cadastraPalavraView = new CadastraPalavraView();
		JPanel telaPrimaria = new JPanel();

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());

		panelConteudo = new JPanel();

		telaPrimaria.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Área do admin");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 45));
		panelTitulo.add(titulo);

		telaPrimaria.add(panelTitulo);

		panelConteudo.setLayout(cardLayout);
		panelConteudo.add(telaPrimaria, "1");
		panelConteudo.add(cadastraPalavraView, "2");
		cardLayout.show(panelConteudo, "1");
		add(panelConteudo, BorderLayout.CENTER);

	}

	// ------------------------- MENU -----------------------------
	private void criarMenu() {

		JMenu menuAdmin = new JMenu("Menu Admin");

		menuAdmin.addActionListener(listener);

		menuItemAdminCadastrarPalavra = new JMenuItem("Cadastrar Palavra");
		menuAdmin.add(menuItemAdminCadastrarPalavra);
		menuItemAdminCadastrarPalavra.addActionListener(listener);

		menuItemAdminDeletarPalavra = new JMenuItem("Deletar Palavra");
		menuAdmin.add(menuItemAdminDeletarPalavra);
		menuItemAdminDeletarPalavra.addActionListener(listener);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		menubar.add(menuAdmin);

	}

	private ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == menuItemAdminCadastrarPalavra) {
				cardLayout.show(panelConteudo, "2");
			} else if (evento.getSource() == menuItemAdminDeletarPalavra) {
				new ListarDeletaPalavraView();
			}

		}
	};

}