package br.com.jogodaforca.main;

import br.com.jogodaforca.view.MenuPrincipal;

public class Main {
/*
 * inicia o programa chamando a janela principal
 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MenuPrincipal();

			}
		});

	}

}
