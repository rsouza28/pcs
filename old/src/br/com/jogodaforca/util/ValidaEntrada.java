package br.com.jogodaforca.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ValidaEntrada {

	private String primeiroField;
	private String segundoField;
	private String terceiroField;

	public ValidaEntrada() {

	}

	public ValidaEntrada(String primeiroField, String segundoField, String terceiroField) {
		this.primeiroField = primeiroField;
		this.segundoField = segundoField;
		this.terceiroField = terceiroField;
	}

	public boolean validarEntradaNumerosInteiros(String numeroEmString) {
		Pattern p = Pattern.compile("^\\d+$");

		Matcher m = p.matcher(numeroEmString);

		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null, "Entre somento com valores numéricos e inteiros");
			return false;
		}
		return true;
	}

	// usado para nomeAluno, palavra e dica
	public boolean isNome(String nomeField) {

		Pattern p = Pattern.compile("[a-zA-Z]{4,40}");

		Matcher m = p.matcher(nomeField);

		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Valor inválido! \nEntre somente com letras, não use acento. Min de 4, máx de 40 carcteres.");
			return false;
		}
		return true;
	}

	// valida se a letra corresponde a uma letra e não número
	public boolean validarEntradaPalavra(String palavra, String dica) {

		Pattern p = Pattern.compile("[a-zA-Z]{4,40}");

		Matcher mPalavra = p.matcher(palavra);
		Matcher mDica = p.matcher(dica);

		if (mPalavra.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Valor inválido! \nEntre somente com letras, não use acento. Min de 4, máx de 40 carcteres!");
			return false;
		}

		if (mDica.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Valor inválido! \nEntre somente com letras, não use acento. Min de 4, máx de 40 carcteres!");
			return false;
		}

		return true;
	}

	// valida cadastro de palavra
	public boolean validarEntradaLetra(String letra) {

		Pattern p = Pattern.compile("[a-zA-Z]{1,1}");

		Matcher m = p.matcher(letra);

		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null, "Letra inválida, favor insira uma letra válida!");
			return false;
		}

		return true;
	}

	// valida se o valor inserido para letra é nulo ou vazio
	public boolean validarCampoLetraLogin(String letraLogin) {
		if (letraLogin == null || letraLogin.equals("")) {
			JOptionPane.showMessageDialog(null, "Campo vazio ou nulo, favor entre com uma informação válida!");
			return false;
		} else {
			return true;
		}
	}

	public Boolean validarCamposAluno(ValidaEntrada validaEntrada) {

		Boolean valida = Boolean.TRUE;

		if (primeiroField == null || primeiroField.equals("")) {
			JOptionPane.showMessageDialog(null, "Primeiro campo vazio. Favor preencher todos os campos");

			valida = Boolean.FALSE;
		}

		if (segundoField == null || segundoField.equals("")) {
			JOptionPane.showMessageDialog(null, "Segundo campo vazio. Favor preencher todos os campos");

			valida = Boolean.FALSE;
		}

		if (terceiroField == null || terceiroField.equals("")) {
			JOptionPane.showMessageDialog(null, "Terceiro campo vazio. Favor preencher todos os campos");

			valida = Boolean.FALSE;
		}
		return valida;

	}

	public Boolean validarCamposPalavra(String palavra, String dica) {

		Boolean valida = Boolean.TRUE;

		if (palavra == null || palavra.equals("")) {
			JOptionPane.showMessageDialog(null, "Campo palavra vazio. Favor preencher todos os campos");

			valida = Boolean.FALSE;
		}

		if (dica == null || dica.equals("")) {
			JOptionPane.showMessageDialog(null, "Campo dica vazio. Favor preencher todos os campos");
			valida = Boolean.FALSE;
		}

		return valida;

	}

	public boolean verificaEntradaAluno(ValidaEntrada validaEntrada) {

		if (isNome(primeiroField) == false) {
			return false;
		}

		if (validarEntradaNumerosInteiros(segundoField) == false) {
			return false;
		}

		if (validarEntradaNumerosInteiros(terceiroField) == false) {
			return false;
		}

		return true;

	}

}
