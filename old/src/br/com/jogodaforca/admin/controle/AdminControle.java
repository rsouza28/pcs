package br.com.jogodaforca.admin.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.jogodaforca.admin.dao.AdminDao;
import br.com.jogodaforca.vo.AdminVo;

public class AdminControle {

	public AdminVo salvarAdmin(AdminVo admin) {
		admin = new AdminDao().salvar(admin);
		return admin;
	}

	public String isAdminCadastrado(AdminVo admin) {

		String verifica = "false";

		List<AdminVo> adminLista = new ArrayList<AdminVo>();

		adminLista = buscarListaAdmin();

		if (adminLista == null) {
			verifica = "listaVazia";
		}

		for (AdminVo adminAux : adminLista) {
			if (admin.getLogin().equals(adminAux.getLogin())) {
				verifica = "true";
			}
		}

		return verifica;
	}

	private List<AdminVo> buscarListaAdmin() {
		return new AdminDao().lerArquivo();
	}
}
