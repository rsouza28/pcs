package br.com.jogodaforca.view;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.jogodaforca.admin.controle.AdminControle;
import br.com.jogodaforca.admin.view.AdminView;
import br.com.jogodaforca.aluno.view.AlunoView;
import br.com.jogodaforca.ranking.view.ListaRankingTop10;
import br.com.jogodaforca.ranking.view.ListaRankingTotal;
import br.com.jogodaforca.util.ValidaEntrada;
import br.com.jogodaforca.vo.AdminVo;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4405642999868273952L;

	private JMenu menuAdmin;
	private JMenuItem menuItemAdminLogin;
	private JMenuItem menuItemAdminCadastro;
	private JMenuItem menuItemJogar;
	private JMenu menuRanking;
	private JMenuItem menuItemRankingTotal;
	private JMenuItem menuItemRankingTop10;
	private JMenuItem menuItemSobre;
	private CardLayout cardLayout = new CardLayout();
	private JPanel panelConteudo = new JPanel();

	public MenuPrincipal() {

		// --------------------- TITULO JANELA -----------------------------

		/*
		 * valores das janela (JFrame)
		 */

		// titulo
		super("Jogo Da Forca");

		setLocation(300, 200);
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

		/*
		 * conteúdo da janela
		 */

		JPanel alunoView = new AlunoView();

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());
		JLabel titulo = new JLabel("Bem vindo ao jogo da forca!");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 45));
		panelTitulo.add(titulo);

		panelConteudo.setLayout(cardLayout);
		panelConteudo.add(panelTitulo, "1");
		panelConteudo.add(alunoView, "2");
		cardLayout.show(panelConteudo, "1");

		add(panelConteudo);

	}

	// ------------------------- MENU -----------------------------
	private void criarMenu() {

		/*
		 * criando menu e submenu
		 */

		JMenu menuJogo = new JMenu("Menu Jogo");

		menuAdmin = new JMenu("Admin");
		menuAdmin.addActionListener(listener);

		menuItemAdminLogin = new JMenuItem("Login");
		menuAdmin.add(menuItemAdminLogin);
		menuItemAdminLogin.addActionListener(listener);

		menuItemAdminCadastro = new JMenuItem("Cadastrar Admin");
		menuAdmin.add(menuItemAdminCadastro);
		menuItemAdminCadastro.addActionListener(listener);

		menuItemJogar = new JMenuItem("Jogar");
		menuJogo.add(menuItemJogar);
		menuItemJogar.addActionListener(listener);

		menuRanking = new JMenu("Ranking");
		menuJogo.add(menuRanking);
		menuRanking.addActionListener(listener);

		menuItemRankingTotal = new JMenuItem("Ranking Total");
		menuRanking.add(menuItemRankingTotal);
		menuItemRankingTotal.addActionListener(listener);

		menuItemRankingTop10 = new JMenuItem("Ranking Top 10");
		menuRanking.add(menuItemRankingTop10);
		menuItemRankingTop10.addActionListener(listener);

		JMenu menuAjuda = new JMenu("Ajuda");

		menuItemSobre = new JMenuItem("Sobre...");
		menuAjuda.add(menuItemSobre);

		JMenuBar menubar = new JMenuBar();
		menubar.add(menuJogo);
		menubar.add(menuAdmin);
		menubar.add(menuAjuda);

		setJMenuBar(menubar);
	}

	/*
	 * classe interna que espera os eventos e os trata
	 */

	private ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == menuItemAdminLogin) {

				/*
				 * caso clique em login
				 */

				AdminVo adminVo = new AdminVo();
				boolean verificaEntrada = false;
				boolean verificaCampo = false;
				String login = " ";
				ValidaEntrada validaEntrada = new ValidaEntrada();

				login = JOptionPane.showInputDialog("Digite seu login");

				/*
				 * validacoes, verificando valores de entrada
				 */

				verificaCampo = validaEntrada.validarCampoLetraLogin(login);
				verificaEntrada = new ValidaEntrada().validarEntradaNumerosInteiros(login);

				if (verificaCampo != false && verificaEntrada != false) {

					adminVo.setLogin(Long.parseLong(login));

					/*
					 * verifica admin cadastrado
					 */

					String verifica = new AdminControle().isAdminCadastrado(adminVo);

					if (verifica.equals("true")) {

						/*
						 * abre a janela do admin, se estiver tudo certo
						 */
						new AdminView();

					} else if (verifica.equals("false")) {
						JOptionPane.showMessageDialog(null, "Admin não cadastrado, favor cadastrar!");
					}
				}

			} else if (evento.getSource() == menuItemAdminCadastro) {

				/*
				 * caso clique em cadastrar admin
				 */

				AdminVo adminVo = new AdminVo();
				boolean verificaEntrada = false;
				boolean verificaCampo = false;
				String login = " ";
				ValidaEntrada validaEntrada = new ValidaEntrada();

				login = JOptionPane.showInputDialog("Cadastre um login");
				verificaCampo = validaEntrada.validarCampoLetraLogin(login);
				verificaEntrada = validaEntrada.validarEntradaNumerosInteiros(login);

				/*
				 * valida as entradas
				 */

				if (verificaCampo != false && verificaEntrada != false) {

					adminVo.setLogin(Long.parseLong(login));
					adminVo = new AdminControle().salvarAdmin(adminVo);
					if (adminVo != null) {
						JOptionPane.showMessageDialog(null, "Admin cadastrado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar admin!");
					}
				}

			} else if (evento.getSource() == menuItemJogar) {
				cardLayout.show(panelConteudo, "2");

				/*
				 * caso clicar em jogar, utilizo o recurso cardLayout para
				 * instanciar a classe AlunoView que é um JPane para abrir nessa
				 * mesma JFrame (Janela)
				 */

			} else if (evento.getSource() == menuItemRankingTotal) {
				new ListaRankingTotal();
				/*
				 * chama janela ranking
				 */

			} else if (evento.getSource() == menuItemRankingTop10) {
				new ListaRankingTop10();
			}

		}

	};

}
