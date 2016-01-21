package br.com.jogodaforca.rodada.view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridLayout;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.jogodaforca.admin.controle.AdminControle;
import br.com.jogodaforca.palavra.controle.PalavraControle;
import br.com.jogodaforca.ranking.controle.RankingControle;
import br.com.jogodaforca.ranking.view.ListaRankingTotal;
import br.com.jogodaforca.util.ValidaEntrada;
import br.com.jogodaforca.vo.AdminVo;
import br.com.jogodaforca.vo.AlunoVo;
import br.com.jogodaforca.vo.PalavraVo;
import br.com.jogodaforca.vo.RankingVo;
import br.com.jogodaforca.vo.RodadaVo;

public class RodadaView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4405642999868273952L;

	AdminControle adminCtrl = new AdminControle();

	AdminVo admin = new AdminVo();
	RodadaVo rodada = new RodadaVo();
	AlunoVo alunoVo = new AlunoVo();
	PalavraVo palavraSorteada = new PalavraVo();

	StringBuilder letraCerta = new StringBuilder(100);
	StringBuilder letraErrada = new StringBuilder(100);
	StringBuilder tracos = new StringBuilder(300);
	StringBuilder todasAsletrasSB = new StringBuilder(100);

	JLabel imagemBonecoLabel = new JLabel();
	JLabel nomeAlunoTempoLabel = new JLabel();
	JLabel palavraLabel = new JLabel();
	JLabel errosLabel = new JLabel();
	JLabel dicaLabel = new JLabel();

	ImageIcon icon = new ImageIcon();

	Thread t;

	boolean verificaTempo = false;
	int acertos = 0;
	int erros = 0;
	int i = 0;

	public RodadaView(AlunoVo alunoVo) {

		super("Jogo Da Forca");

		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(350, 250);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.alunoVo = alunoVo;

		this.criarConteudo1();
		this.criarConteudo2();
		this.jogar();

	}

	private void criarConteudo1() {

		JPanel conteudoJPanel1 = new JPanel();
		conteudoJPanel1.setLayout(new FlowLayout());
		this.add(conteudoJPanel1, BorderLayout.WEST);

		// imagemBonecoLabel = new JLabel(new
		// ImageIcon("src/br/com/jogodaforca/imagens/forca.png"));

		// imagemBonecoLabel.setIcon(icon);
		conteudoJPanel1.add(imagemBonecoLabel);

	}

	private void criarConteudo2() {

		// panel direito principal
		JPanel conteudoJPanel2 = new JPanel();
		conteudoJPanel2.setLayout(new GridLayout(4, 1));
		this.add(conteudoJPanel2);

		// label do nome
		nomeAlunoTempoLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		// nomeAlunoTempoLabel.setText("NomeTempo");
		conteudoJPanel2.add(nomeAlunoTempoLabel);

		// label da palavra
		palavraLabel.setFont(new Font("Verdana", Font.BOLD, 40));
		// palavraLabel.setText("Palavra");
		conteudoJPanel2.add(palavraLabel);

		// label dos erros
		errosLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		// errosLabel.setText("Erros");
		errosLabel.setForeground(Color.RED);
		conteudoJPanel2.add(errosLabel);

		// label da dica
		dicaLabel.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 25));
		// dicaLabel.setText("Dica");
		errosLabel.setForeground(Color.RED);
		conteudoJPanel2.add(dicaLabel);

	}

	private StringBuilder qtdTracos(PalavraVo palavra) {

		for (int i = 0; i < palavra.getPalavra().length(); i++) {
			tracos.append("_");
		}

		return tracos;
	}

	private String aplicarEspaco(String tracos) {

		tracos = tracos.replaceAll("(.)", "$1 ");

		return tracos;
	}

	private Boolean verificaLetraRepetida(char letra) {
		Boolean verifica = false;

		for (int i = 0; i < todasAsletrasSB.length(); i++) {
			if (letra == todasAsletrasSB.charAt(i)) {
				verifica = true;
			}
		}

		return verifica;
	}

	private Boolean verificarLetra(char letra) {
		Boolean verifica = false;
		String palavra = rodada.getPalavra().getPalavra();

		for (int i = 0; i < palavra.length(); i++) {

			if (palavra.charAt(i) == letra) {
				verifica = true;
				acertos = acertos + 1;
				rodada.setAcertos(acertos);
				trocaTracoLetra(i, letra);
			}
		}

		if (verifica != false) {
			letraCerta.append(letra);
		} else {
			letraErrada.append(letra);
			erros = erros + 1;
			rodada.setErros(erros);
		}

		todasAsletrasSB.append(letraCerta).append(letraErrada);

		rodada.setLetras(todasAsletrasSB.toString());
		return verifica;
	}

	private void trocaTracoLetra(int posicaoLetra, char letra) {
		String tracosString = tracos.toString();

		char[] arrayChar = tracosString.toCharArray();

		arrayChar[posicaoLetra] = letra;

		tracos = new StringBuilder();
		for (int i = 0; i < arrayChar.length; i++) {
			tracos.append(arrayChar[i]);
		}

	}

	private void boneco(Integer numeroErros) {

		switch (numeroErros) {

		case 0:
			icon = new ImageIcon("src/br/com/jogodaforca/main/forca.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		case 1:
			icon = new ImageIcon("src/br/com/jogodaforca/main/cabeca.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		case 2:
			icon = new ImageIcon("src/br/com/jogodaforca/main/corpo.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		case 3:
			icon = new ImageIcon("src/br/com/jogodaforca/main/bracoE.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		case 4:
			icon = new ImageIcon("src/br/com/jogodaforca/main/bracoD.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		case 5:
			icon = new ImageIcon("src/br/com/jogodaforca/main/pernaE.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		case 6:
			icon = new ImageIcon("src/br/com/jogodaforca/main/completo.png");
			imagemBonecoLabel.setIcon(icon);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Erro!");
			break;

		}
	}

	private void calcularPontosEsalvar() {
		RodadaVo rodadaVo = new RodadaVo();
		rodadaVo.setAcertos(acertos);
		rodadaVo.setErros(erros);
		rodadaVo.setTempo(i);
		rodadaVo.setAluno(alunoVo);

		RankingVo rankingVo = new RankingVo();
		rankingVo.setRodada(rodadaVo);

		new RankingControle().calcularPontosEsalvar(rankingVo);

	}

	@SuppressWarnings("static-access")
	private void jogar() {

		palavraSorteada = new PalavraControle().sortearPalavra();

		if (palavraSorteada != null) {

			Runnable r = () -> {
				try {
					for (i = 300; i >= 0; i--) {
						nomeAlunoTempoLabel.setText(alunoVo.getNome() + " --- Tempo: " + i + "s");
						Thread.sleep(1000); // 1 segundo
					}
					verificaTempo = true;
					calcularPontosEsalvar();
					JOptionPane.showMessageDialog(null, "Que pena, o tempo acabou!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			t = new Thread(r);
			t.start();

			rodada.setPalavra(palavraSorteada);

			qtdTracos(palavraSorteada);
			String tracosString = tracos.toString();

			char letra = ' ';
			String letraStr = " ";

			JOptionPane optionPane = new JOptionPane();
			optionPane.setSize(1000, 900);

			ValidaEntrada validaEntrada = new ValidaEntrada();

			while (tracosString.contains("_") && erros <= 5 && letraStr != null) {
				tracosString = tracos.toString();
				boolean verificaLetraRepetida = true;
				boolean validaCampoLetra = false;
				boolean validaEntradaLetra = false;

				while (verificaLetraRepetida == true && validaCampoLetra == false && validaEntradaLetra == false
						&& verificaTempo == false && letraStr != null) {

					palavraLabel.setText("Palavra: " + aplicarEspaco(tracosString));
					errosLabel.setText("Erros: " + letraErrada.toString().replaceAll("(.)", "$1, "));
					dicaLabel.setText("*Dica: " + palavraSorteada.getDica());

					boneco(erros);

					// ("Dica: "+ palavraSorteada.getDica());
					// ("Letras erradas: ");
					// (letraErrada.toString().replaceAll("(.)",
					// "$1, "));
					// conteudoPane.append(boneco(erros));

					letraStr = optionPane.showInputDialog("Digite uma letra");

					if (letraStr != null) {

						validaCampoLetra = validaEntrada.validarCampoLetraLogin(letraStr);
						if (validaCampoLetra == true) {
							validaEntradaLetra = validaEntrada.validarEntradaLetra(letraStr);
						}
						if (validaCampoLetra == true && validaEntradaLetra == true) {

							letra = letraStr.trim().toUpperCase().charAt(0);

							verificaLetraRepetida = verificaLetraRepetida(letra);
							if (verificaLetraRepetida == true) {
								JOptionPane.showMessageDialog(null, "Letra repetida!");
							} else {
								verificarLetra(letra);
							}
						}
						tracosString = tracos.toString();
					}

				}
			}
			palavraLabel.setText("Palavra: " + aplicarEspaco(tracosString));
			errosLabel.setText("Erros: " + letraErrada.toString().replaceAll("(.)", "$1, "));
			dicaLabel.setText("*Dica: " + palavraSorteada.getDica());
			boneco(erros);

			t.interrupt();

			if (!tracosString.contains("_")) {
				calcularPontosEsalvar();
				JOptionPane.showMessageDialog(null, "Parabéns! Você acertou a palavra!");
			} else {
				calcularPontosEsalvar();
				JOptionPane.showMessageDialog(null, "Que pena! Você não acertou a palavra!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não existe palavra cadastrada!");
		}

		setVisible(false);
		new ListaRankingTotal();

	}

	// Teste
	// public static void main(String[] args) {
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// AlunoVo alunoVo = new AlunoVo();
	// alunoVo.setNome("Tiago");
	// alunoVo.setIdade(23);
	// alunoVo.setSerie(7);
	// new RodadaView(alunoVo);
	//
	// }
	// });
	//
	// }

}
