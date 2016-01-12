package br.com.jogodaforca.dao;

import java.io.File;

public class FileDao {

	private String caminho;
	private String arquivo;
	private String url;

	public FileDao(String caminho, String arquivo) {
		this.caminho = caminho;
		this.arquivo = arquivo;
		setUrl();

	}

	public void criarDiretorio() {

		try {

			// diretório apontado para própria pasta do projeto
			File dir = new File(caminho);
			if (!dir.exists()) {

				dir.mkdir();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void criarArquivo(boolean criaOuNCria) {

		File txt = new File(caminho, arquivo);

		if (!txt.exists()) {
			try {
				txt.createNewFile();

			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		} else if (criaOuNCria == true) {

			try {
				txt.delete();
				txt.createNewFile();

			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl() {
		StringBuilder caminhoBuilder = new StringBuilder(caminho);
		StringBuilder arquivoBuilder = new StringBuilder(arquivo);
		StringBuilder caminhoArquivo = caminhoBuilder.append("/").append(arquivoBuilder);
		this.url = caminhoArquivo.toString();
	}

	/*
	 * TESTE
	 * 
	 * antes da classe ser abstrata
	 * 
	 * public static void main(String[] args) { new Dao().criarArquivo(); }
	 */
}
