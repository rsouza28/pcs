package br.com.jogodaforca.ranking.dao;

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

import javax.swing.JOptionPane;

import br.com.jogodaforca.dao.FileDao;
import br.com.jogodaforca.dao.IGenericDao;
import br.com.jogodaforca.vo.RankingVo;

public class RankingDao extends FileDao implements IGenericDao<RankingVo> {

	public RankingDao() {
		super("RankingTxt", "Ranking.txt");
	}

	@Override
	public RankingVo salvar(RankingVo e) {
		RankingVo rankingVo = (RankingVo) e;

		criarDiretorio();
		criarArquivo(Boolean.FALSE);

		try {
			OutputStream out = new FileOutputStream(getUrl(), true);
			OutputStreamWriter streamWriter = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(streamWriter);

			bw.write(rankingVo.toString());
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			System.err.println("Erro! " + ex.getMessage());
			ex.printStackTrace();
			rankingVo = null;
		}

		return rankingVo;
	}

	@Override
	public List<RankingVo> lerArquivo() {
		List<RankingVo> rankingVoLista = new ArrayList<>();

		try {

			InputStream in = new FileInputStream(getUrl());
			InputStreamReader streamReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(streamReader);

			String rankingString;

			rankingString = br.readLine();
			do {

				String[] rankingStringVet = new String[3];
				rankingStringVet = rankingString.split(":");

				RankingVo rankingVo = new RankingVo();

				rankingVo.getRodada().getAluno().setNome(rankingStringVet[0]);
				rankingVo.getRodada().getAluno().setIdade(Integer.parseInt(rankingStringVet[1]));
				rankingVo.getRodada().setPontuacao(Integer.parseInt(rankingStringVet[2]));
				rankingVo.getRodada().setTempo(Integer.parseInt(rankingStringVet[3]));

				rankingVoLista.add(rankingVo);

				rankingString = br.readLine();

			} while (rankingString != null);

			br.close();

		} catch (Exception e) {
			System.err.println("Erro! " + e.getMessage());
			JOptionPane.showMessageDialog(null,
					"ERRO! Nao foi possivel carregar lista de ranking, talvez nao exista jogo feito!");
			e.printStackTrace();
			rankingVoLista = null;
		}

		return rankingVoLista;
	}

}
