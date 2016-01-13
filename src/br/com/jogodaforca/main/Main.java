package br.com.jogodaforca.main;

import br.com.jogodaforca.view.MenuPrincipal;

public class Main {
	/*
	 * inicia o programa chamando a janela principal inicializo atraves de uma
	 * thread, que é o modo mais apropriado para se iniciar uma aplicacao com
	 * awt e swing.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MenuPrincipal();

			}
		});

	}

}
