package br.com.jogodaforca.aluno.dao;

import br.com.jogodaforca.dao.FileDao;
import br.com.jogodaforca.dao.IGenericDao;
import br.com.jogodaforca.vo.AlunoVo;
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

public class AlunoDao extends FileDao implements IGenericDao<AlunoVo> {

	public AlunoDao() {
		super("AlunoTxt", "Aluno.txt");
	}

	@Override
	public AlunoVo salvar(AlunoVo e) {
		AlunoVo aluno = (AlunoVo) e;

		criarDiretorio();
		criarArquivo(Boolean.FALSE);

		try {
			OutputStream out = new FileOutputStream(getUrl(), true);
			OutputStreamWriter streamWriter = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(streamWriter);

			bw.write(aluno.toString());
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			System.err.println("Erro! " + ex.getMessage());
			ex.printStackTrace();
			aluno = null;
		}

		return aluno;
	}

	@Override
	public List<AlunoVo> lerArquivo() {
		List<AlunoVo> alunoLista = new ArrayList<>();

		try {

			InputStream in = new FileInputStream(getUrl());
			InputStreamReader streamReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(streamReader);

			String alunoString;

			alunoString = br.readLine();
			do {

				String[] alunoStringVet = new String[3];
				alunoStringVet = alunoString.split(":");

				AlunoVo aluno = new AlunoVo();

				aluno.setNome(alunoStringVet[0]);
				aluno.setSerie(Integer.parseInt(alunoStringVet[1]));
				aluno.setIdade(Integer.parseInt(alunoStringVet[2]));

				alunoLista.add(aluno);

				alunoString = br.readLine();

			} while (alunoString != null);

			br.close();

		} catch (Exception e) {
			System.err.println("Erro! " + e.getMessage());
			e.printStackTrace();
			alunoLista = null;
		}

		return alunoLista;
	}

}
