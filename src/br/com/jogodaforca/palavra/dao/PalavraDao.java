package br.com.jogodaforca.palavra.dao;

import br.com.jogodaforca.dao.FileDao;
import br.com.jogodaforca.dao.IGenericDao;
import br.com.jogodaforca.vo.PalavraVo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PalavraDao extends FileDao implements IGenericDao<PalavraVo> {

	public PalavraDao() {
		super("PalavraTxt", "Palavra.txt");
	}

	@Override
	public PalavraVo salvar(PalavraVo e) {
		PalavraVo palavra = (PalavraVo) e;

		criarDiretorio();
		criarArquivo(Boolean.FALSE);

		try {
			OutputStream out = new FileOutputStream(getUrl(), true);
			OutputStreamWriter streamWriter = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(streamWriter);

			bw.write(palavra.toString());
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			System.err.println("Erro! " + ex.getMessage());
			ex.printStackTrace();
			palavra = null;
		}

		return palavra;
	}

	public PalavraVo deletarPalavra(PalavraVo palavraVo) {
		List<PalavraVo> palavraLista = lerArquivo();
		List<PalavraVo> listaExclusao = new ArrayList<PalavraVo>();
		try {

			for (PalavraVo palavra : palavraLista) {
				if (palavra.getPalavra().equalsIgnoreCase(palavraVo.getPalavra())) {
					listaExclusao.add(palavra);
				}
			}

			for (PalavraVo palavra : listaExclusao) {
				palavraLista.remove(palavra);
			}

			if (listaExclusao.isEmpty()) {
				palavraVo = null;
			}

			List<PalavraVo> lista = enviarListaArquivo(palavraLista);

			if (lista == null)
				palavraVo = null;

		} catch (Exception e) {
			e.printStackTrace();
			palavraVo = null;
		}
		return palavraVo;

	}

	@Override
	public List<PalavraVo> lerArquivo() {
		List<PalavraVo> palavraLista = new ArrayList<PalavraVo>();

		try {

			InputStream in = new FileInputStream(getUrl());
			InputStreamReader streamReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(streamReader);

			String palavraString;

			// cada obj de palavra linha por linha em string
			palavraString = br.readLine();
			do {

				/*
				 * transformando cada linha (obj de palavra) em um vetor onde
				 * cada linha(obj) se tornara uma index no vetor transformando
				 * em um vetor de string para cada obj sera um vetor
				 */
				String[] palavraStringVet = new String[4];
				palavraStringVet = palavraString.split(":");

				/*
				 * pegando cada index do obj e populando o obj palavra agora do
				 * tipo PalavraVo e adicionando a lista
				 */
				PalavraVo palavra = new PalavraVo();

				palavra.setPalavra(palavraStringVet[0]);
				palavra.setDica(palavraStringVet[1]);

				palavraLista.add(palavra);

				// checa se ainda tem linhas no arquivo
				palavraString = br.readLine();

			} while (palavraString != null);
			/*
			 * fecha o fluxo. caso nao fechar ele acumular o valor que estava no
			 * arquivo mesmo este sendo apagado (tendo antes feito uma leitura)
			 */
			br.close();

		} catch (Exception e) {
			System.err.println("Erro! " + e.getMessage());
			e.printStackTrace();
			palavraLista = null;
		}

		return palavraLista;

	}

	public List<PalavraVo> enviarListaArquivo(List<PalavraVo> palavraLista) {

		criarArquivo(Boolean.TRUE);

		try {

			OutputStream out = new FileOutputStream(getUrl(), true);
			OutputStreamWriter streamWriter = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(streamWriter);

			for (PalavraVo funcionario : palavraLista) {

				bw.write(funcionario.toString());
				bw.flush();

			}
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
			palavraLista = null;
		}

		return palavraLista;

	}

}
