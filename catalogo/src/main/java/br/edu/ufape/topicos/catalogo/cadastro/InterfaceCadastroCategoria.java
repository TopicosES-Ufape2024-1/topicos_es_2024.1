package br.edu.ufape.topicos.catalogo.cadastro;

import java.util.List;

import br.edu.ufape.topicos.catalogo.basica.Categoria;

public interface InterfaceCadastroCategoria {

	Categoria salvarCategoria(Categoria entity);

	List<Categoria> listarCategorias();

	void apagarCategoria(Long id);

	void apagarCategoria(Categoria entity);

	Categoria encontrarCategoria(Long id);

}