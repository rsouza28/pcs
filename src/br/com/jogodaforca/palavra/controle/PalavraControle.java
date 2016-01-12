package br.com.jogodaforca.palavra.controle;

import br.com.jogodaforca.palavra.dao.PalavraDao;
import br.com.jogodaforca.vo.PalavraVo;
import java.util.Collections;
import java.util.List;

public class PalavraControle {

	public PalavraVo salvarPalavra(PalavraVo palavra) {
		palavra = new PalavraDao().salvar(palavra);
		return palavra;
	}

	public PalavraVo deletarPalavra(PalavraVo palavraVo) {

		palavraVo = new PalavraDao().deletarPalavra(palavraVo);

		return palavraVo;
	}

	public PalavraVo sortearPalavra() {

		PalavraVo palavra;
		List<PalavraVo> palavraLista = buscarListaPalavra();

		try {
			Collections.shuffle(palavraLista);
			palavra = palavraLista.get(0);

		} catch (Exception e) {
			System.err.println("Erro!" + e.getMessage());
			e.printStackTrace();
			palavra = null;
		}

		return palavra;
	}

	public List<PalavraVo> buscarListaPalavra() {
		return new PalavraDao().lerArquivo();
	}

	public boolean verificarSeJaExistePalavra(PalavraVo palavraVo) {
		boolean verifica = false;

		try {
			List<PalavraVo> palavraLista = buscarListaPalavra();

			for (PalavraVo palavra : palavraLista) {
				if (palavra.getPalavra().equalsIgnoreCase(palavraVo.getPalavra())) {
					verifica = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
		}

		return verifica;
	}
}
