package br.edu.ufage.topicos.catalogo.cadastro;

public class RegistroDuplicadoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public RegistroDuplicadoException(String msg) {
		super(msg);
	}
}
