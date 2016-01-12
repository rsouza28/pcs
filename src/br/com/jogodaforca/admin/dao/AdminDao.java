package br.com.jogodaforca.admin.dao;

import br.com.jogodaforca.dao.FileDao;
import br.com.jogodaforca.dao.IGenericDao;
import br.com.jogodaforca.vo.AdminVo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AdminDao extends FileDao implements IGenericDao<AdminVo> {

	public AdminDao() {
		super("AdminTxt", "Admin.txt");
	}

	@Override
	public AdminVo salvar(AdminVo e) {

		AdminVo admin = (AdminVo) e;
		criarDiretorio();
		criarArquivo(Boolean.FALSE);

		try {
			OutputStream out = new FileOutputStream(getUrl(), true);
			OutputStreamWriter streamWriter = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(streamWriter);

			bw.write(admin.toString());
			bw.flush();
			bw.close();

		} catch (Exception ex) {
			System.err.println("Erro! " + ex.getMessage());
			ex.printStackTrace();
			admin = null;
		}

		return admin;
	}

	@Override
	public List<AdminVo> lerArquivo() {

		List<AdminVo> adminLista = new ArrayList<AdminVo>();

		try {

			InputStream in = new FileInputStream(getUrl());
			InputStreamReader streamReader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(streamReader);

			// cada obj de admin linha por linha em string
			String adminString = br.readLine();
			do {

				/*
				 * pegando cada index do obj e populando o obj admin agora do
				 * tipo AdminVo e adicionando a lista
				 */
				AdminVo admin = new AdminVo();
				admin.setLogin(Long.parseLong(adminString));
				adminLista.add(admin);

				// checa se ainda tem linhas no arquivo
				adminString = br.readLine();

			} while (adminString != null);
			/*
			 * fecha o fluxo. caso nao fechar ele acumular o valor que estava no
			 * arquivo mesmo este sendo apagado (tendo antes feito uma leitura)
			 */
			br.close();

		} catch (FileNotFoundException file) {
			JOptionPane.showMessageDialog(null, "Não existe admin cadastrado, cadastre pelo menos um!");
			adminLista = null;
		} catch (Exception e) {
			System.err.println("Erro! " + e.getMessage());
			e.printStackTrace();
			adminLista = null;
		}

		return adminLista;
	}

}
