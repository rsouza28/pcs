package br.com.jogodaforca.rodada.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.jogodaforca.admin.controle.AdminControle;
import br.com.jogodaforca.palavra.controle.PalavraControle;
import br.com.jogodaforca.ranking.controle.RankingControle;
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
	StringBuilder letraCerta = new StringBuilder(100);
	StringBuilder letraErrada = new StringBuilder(100);
	StringBuilder tracos = new StringBuilder(300);
	int acertos = 0;
	int erros = 0;
	StringBuilder todasAsletrasSB = new StringBuilder(100);
	StringBuilder conteudoPane;
	JButton botaoComecar;
	AlunoVo alunoVo = new AlunoVo();
	JLabel titulo;
	boolean verificaTempo = false;
	Thread t;
	int i = 0;
	PalavraVo palavraSorteada;

	public RodadaView(AlunoVo alunoVo) {

		super("Jogo Da Forca");
		setLocation(300, 200);
		setSize(630, 260);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());
		this.alunoVo = alunoVo;
		criarHead();
		criarBotoes();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	private void criarHead() {

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout());
		titulo = new JLabel("Clique em sortear palavra para começar");
		titulo.setFont(new Font("Verdana", Font.PLAIN, 30));
		panelTitulo.add(titulo);

		add(panelTitulo, BorderLayout.NORTH);

	}

	private void criarBotoes() {
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout());

		botaoComecar = new JButton("Sortear");
		botaoComecar.addActionListener(listener);
		panelBotoes.add(botaoComecar);

		add(panelBotoes, BorderLayout.CENTER);

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

	private StringBuilder boneco(Integer numeroErros) {

		StringBuilder bonecoBuilder = new StringBuilder();

		switch (numeroErros) {
		case 1:
			bonecoBuilder.append(" ('-')");
			break;
		case 2:
			bonecoBuilder.append(" ('-')");
			bonecoBuilder.append("\n");
			bonecoBuilder.append("/");
			break;
		case 3:
			bonecoBuilder.append(" ('-')");
			bonecoBuilder.append("\n");
			bonecoBuilder.append(" /   \\");
			break;
		case 4:
			bonecoBuilder.append(" ('-')");
			bonecoBuilder.append("\n");
			bonecoBuilder.append(" / | \\");
			break;
		case 5:
			bonecoBuilder.append(" ('-')");
			bonecoBuilder.append("\n");
			bonecoBuilder.append(" / | \\");
			bonecoBuilder.append("\n");
			bonecoBuilder.append("  /");
			break;
		case 6:
			bonecoBuilder.append("Parabéns você montou o boneco. rs");
			bonecoBuilder.append("\n");
			bonecoBuilder.append(" ('-')");
			bonecoBuilder.append("\n");
			bonecoBuilder.append(" / | \\");
			bonecoBuilder.append("\n");
			bonecoBuilder.append("  / \\");
			bonecoBuilder.append("\n");
			break;
		default:
			break;

		}
		return bonecoBuilder;
	}

	public void calcularPontosEsalvar() {
		RodadaVo rodadaVo = new RodadaVo();
		rodadaVo.setAcertos(acertos);
		rodadaVo.setErros(erros);
		rodadaVo.setTempo(i);
		rodadaVo.setAluno(alunoVo);

		RankingVo rankingVo = new RankingVo();
		rankingVo.setRodada(rodadaVo);

		new RankingControle().calcularPontosEsalvar(rankingVo);

	}

	private ActionListener listener = new ActionListener() {
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == botaoComecar) {

				palavraSorteada = new PalavraControle().sortearPalavra();

				if (palavraSorteada != null) {

					botaoComecar.setVisible(false);

					Runnable r = () -> {
						try {
							for (i = 300; i >= 0; i--) {
								titulo.setText("Tempo: " + i + "s");
								Thread.sleep(1000); // 1 segundo
							}
							verificaTempo = true;
							calcularPontosEsalvar();
							JOptionPane.showMessageDialog(null, "Que pena, o tempo acabou!");
							System.exit(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					};
					t = new Thread(r);
					t.start();

					rodada.setPalavra(palavraSorteada);

					qtdTracos(palavraSorteada);
					String tracosString = tracos.toString();

					conteudoPane = new StringBuilder();
					StringBuilder conteudoPaneCopia = new StringBuilder();
					char letra = ' ';
					String letraStr = " ";

					JOptionPane optionPane = new JOptionPane();
					optionPane.setSize(1000, 900);

					ValidaEntrada validaEntrada = new ValidaEntrada();

					while (tracosString.contains("_") && erros <= 5) {
						tracosString = tracos.toString();
						boolean verificaLetraRepetida = true;
						boolean validaCampoLetra = false;
						boolean validaEntradaLetra = false;

						while (verificaLetraRepetida == true && validaCampoLetra == false && validaEntradaLetra == false
								&& verificaTempo == false) {
							conteudoPane.append("Jogador: " + alunoVo.getNome());
							conteudoPane.append("\n");
							conteudoPane.append(aplicarEspaco(tracosString));
							conteudoPane.append("\n");
							conteudoPane.append("Dica: " + palavraSorteada.getDica());
							conteudoPane.append("\n");
							conteudoPane.append("Letras erradas: ");
							conteudoPane.append(letraErrada.toString().replaceAll("(.)", "$1, "));
							conteudoPane.append("\n");
							conteudoPane.append("Digite uma letra");
							conteudoPane.append("\n");
							conteudoPane.append(boneco(erros));

							letraStr = optionPane.showInputDialog(conteudoPane.toString());

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
							conteudoPane = new StringBuilder();
						}
					}

					conteudoPaneCopia.append("Jogador: " + alunoVo.getNome());
					conteudoPaneCopia.append("\n");
					conteudoPaneCopia.append(aplicarEspaco(tracosString));
					conteudoPaneCopia.append("\n");
					conteudoPaneCopia.append("Dica: " + palavraSorteada.getDica());
					conteudoPaneCopia.append("\n");
					conteudoPaneCopia.append("Letras erradas: ");
					conteudoPaneCopia.append(letraErrada.toString().replaceAll("(.)", "$1, "));
					conteudoPaneCopia.append("\n");
					conteudoPaneCopia.append(boneco(erros));

					if (!tracosString.contains("_")) {
						t.interrupt();
						calcularPontosEsalvar();
						conteudoPaneCopia.append("\n");
						conteudoPaneCopia.append("Parabéns! Você acertou a palavra!");
						JOptionPane.showMessageDialog(null, conteudoPaneCopia.toString());
					} else {
						t.interrupt();
						calcularPontosEsalvar();
						conteudoPaneCopia.append("Que pena! Você não acertou a palavra!");
						JOptionPane.showMessageDialog(null, conteudoPaneCopia.toString());
					}
					conteudoPaneCopia = new StringBuilder();
					conteudoPane = new StringBuilder();
				} else {
					JOptionPane.showMessageDialog(null, "Não existe palavra cadastrada!");
				}

			}
		}
	};

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
